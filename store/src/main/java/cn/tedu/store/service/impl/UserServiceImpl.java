package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.Usermapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.exception.DupticateKeyException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.PasswordNotMatchException;
import cn.tedu.store.service.exception.UpdateFailureException;
import cn.tedu.store.service.exception.UpdatePasswordFailureException;
import cn.tedu.store.service.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private Usermapper usermapper;

	/**
	 * 注册
	 */
	@Override
	public User reg(User user) throws DupticateKeyException, InsertException {
		// 根据用户数据查询数据
		User data = findByUsername(user.getUsername());
		if (data == null) {
			// 补充非用户提交数据
			Date now = new Date();
			user.setCreated_user("admin");
			user.setCreated_time(now);
			user.setModified_user("lili");
			user.setModified_time(now);
			user.setIs_delete(0);
			// 根据结果为null,处理密码加密
			String uuid = UUID.randomUUID().toString();
			String srcpassword = user.getPassword();
			String md5password = getMD5Password(srcpassword, uuid);
			user.setPassword(md5password);
			user.setSalt(uuid);
			// 添加用户
			addnew(user);
			// 返回注册对象
			return user;
		} else {
			// 结果不为null，用户已存在抛出异常
			throw new DupticateKeyException("注册失败，用户名" + user.getUsername() + "已被占用");
		}

	}

	/**
	 * 用户登入
	 */
	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		User user = usermapper.findByUsername(username);
		if (user == null) {
			throw new UserNotFoundException("用户名不存在");
		}
		if (user.getPassword().equals(getMD5Password(password, user.getSalt()))) {
			if (user.getIs_delete() == 0) {
				user.setSalt(null);
				user.setPassword(null);
				return user;
			}
			throw new UserNotFoundException("用户名已被查封");
		} else {
			throw new PasswordNotMatchException("密码错误");
		}
	}

	/**
	 * 密码修改
	 */
	@Override
	public void updatePassword(Integer id, String oldPassword, String newPassword)
			throws UserNotFoundException, PasswordNotMatchException, UpdatePasswordFailureException {
		User data = findById(id);
		if (data == null || data.getIs_delete() != 0) {
			throw new UserNotFoundException("用户名已被查封");
		}
		oldPassword = getMD5Password(oldPassword, data.getSalt());
		if (data.getPassword().equals(oldPassword)) {
			newPassword = getMD5Password(newPassword, data.getSalt());
			Date date = new Date();
			date.setTime(date.getTime() + 8 * 60 * 60 * 1000);
			updataPassword(id, newPassword, data.getUsername(), date);
		} else {
			throw new PasswordNotMatchException("原密码错误");
		}
	}
	/**
	 * 用户资料修改
	 */
	@Override
	public void changeInfo(User user) throws UserNotFoundException,UpdateFailureException{
		User data = findById(user.getId());
		if (data == null || data.getIs_delete() != 0) {
			throw new UserNotFoundException("修改资料失败，用户不存在");
		}
		user.setModified_time(new Date());
		user.setUsername(user.getUsername());
		updataInfo(user);
	}
	/**
	 * 用户资料查询
	 */
	@Override
	public User getById(Integer id) {
		User data=findById(id);
		data.setPassword(null);
		data.setSalt(null);
		data.setIs_delete(null);
		return data;
	}
	/**
	 * 修改用户头像地址
	 * @param id
	 * @param avatar 头像地址
	 * @throws UpdateFailureException 修改异常
	 * @throws UserNotFoundException 用户异常
	 */
	@Override
	public void changeAvatar(Integer id, String avatar) throws UpdateFailureException,UserNotFoundException{
		User data = findById(id);
		if(data==null||data.getIs_delete()!=0){
			throw new UserNotFoundException("用户名已被查封");
		}
		Date date = new Date();
		date.setTime(date.getTime() + 8 * 60 * 60 * 1000);
		updataAvatar(id, avatar,data.getUsername(), date);
	}
	
	/**
	 * 添加用户  
	 * @param user 用户信息
	 * @throws InsertException
	 * @author 余智龙
	 */
	private void addnew(User user) throws InsertException {
		Integer row = usermapper.addnew(user);
		if (row != 1) {
			throw new InsertException("添加用户数据时出现未知错误！");
		}
	}

	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 用户信息
	 */
	private User findByUsername(String username) {
		return usermapper.findByUsername(username);
	}

	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return 用户信息
	 */
	private User findById(Integer id) {
		return usermapper.findById(id);
	}

	/**
	 * 修改密码
	 * 
	 * @param id
	 * @param password
	 *            新密码
	 * @param modified_user
	 *            修改人名称
	 * @param modified_time
	 *            修改时间
	 */
	private void updataPassword(@Param("id") Integer id, @Param("password") String password,
			@Param("modified_user") String modified_user, @Param("modified_time") Date modified_time)
			throws UpdatePasswordFailureException {
		Integer row = usermapper.updataPassword(id, password, modified_user, modified_time);
		if (row != 1) {
			throw new UpdatePasswordFailureException("密码修改失败，出现未知错误");
		}
	}

	/**
	 * 密码加密
	 * 
	 * @param srcpassword 原密码
	 * @param salt 盐值
	 * @return 加密后密码
	 */
	private String getMD5Password(String srcpassword, String salt) {
		String md5password = salt + srcpassword + salt;
		for (int i = 0; i < 10; i++) {
			md5password = DigestUtils.md5DigestAsHex(md5password.getBytes()) + i;
		}
		return DigestUtils.md5DigestAsHex(md5password.getBytes());
	}
	/**
	 * 修改用户资料
	 * @param user 用户资料
	 */
	private void updataInfo(User user) {
		Integer row=usermapper.updataInfo(user);
		if (row != 1) {
			throw new UpdateFailureException("修改失败，出现未知错误");
		}
	}
	/**
	 * 修改头像
	 * @param id
	 * @param avatar 头像地址
	 * @param modified_user 修改人姓名
	 * @param modified_time 修改时间
	 * @throws UpdateFailureException 修改失败异常
	 */
	private void updataAvatar(Integer id ,String avatar,String modified_user,Date modified_time ) throws UpdateFailureException {
		Integer row=usermapper.updataAvatar( id , avatar, modified_user,modified_time );
		if(row!=1){
			throw new  UpdateFailureException("头像修改，出现未知错误");
		}
	}

	


}
