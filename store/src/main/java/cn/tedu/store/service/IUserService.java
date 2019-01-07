package cn.tedu.store.service;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.exception.DupticateKeyException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.PasswordNotMatchException;
import cn.tedu.store.service.exception.UpdateFailureException;
import cn.tedu.store.service.exception.UpdatePasswordFailureException;
import cn.tedu.store.service.exception.UserNotFoundException;

/**
 * 处理用户业务
 * @author Administrator
 *
 */
public interface IUserService {
	/**
	 * 用户注册
	 * @param User用户的注册信息
	 * @return 成功注册的用户数据
	 * @throws DupticateKeyException
	 * @throws InsertException
	 */
	User reg(User user) throws DupticateKeyException,InsertException;
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 用户密码
	 * @return 用户数据
	 * @throws UserNotFoundException 用户名不存在
	 * @throws PasswordNotMatchException 密码错误
	 */
	User login(String username,String password) throws UserNotFoundException,PasswordNotMatchException;
	/**
	 * 修改密码
	 * @param id
	 * @param oldPassword 老密码
	 * @param newPassword 新密码
	 * @throws UserNotFoundException 用户名不存在
	 * @throws PasswordNotMatchException 密码不正确
	 * @throws UpdatePasswordFailureException 密码更新时出现未知错误
	 */
	void updatePassword(Integer id,String oldPassword ,String newPassword)throws UserNotFoundException,PasswordNotMatchException,UpdatePasswordFailureException;
	/**
	 * 修改用户资料
	 * @param user 用户数据
	 * @throws UserNotFoundException 用户没有找到
	 * @throws UpdateFailureException 修改出现错误
	 */
	void changeInfo(User user) throws UserNotFoundException,UpdateFailureException;
	/**
	 * 用户资料查询
	 * @param id
	 * @return 用户资料
	 */
	User getById(Integer id);
	/**
	 * 头像上传
	 * @param id
	 * @param avatar
	 * @return
	 */
	void changeAvatar(Integer id, String avatar)throws UpdateFailureException,UserNotFoundException;
	
	
}
