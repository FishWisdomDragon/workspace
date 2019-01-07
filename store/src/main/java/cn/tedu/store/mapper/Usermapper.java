package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;
/**
 * 
 * 处理用户数据
 *
 */
public interface Usermapper {
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @return 受影响行数
	 */
	Integer addnew(User user);
	/**
	 * 根据用户名查询用户数据
	 * @param username 用户名
	 * @return 匹配用户数据，如果没有匹配的数据，则返回null
	 */
	User findByUsername(String username);
	/**
	 * 根据用户名查询用户数据
	 * @param id 
	 * @return 匹配用户数据，如果没有匹配的数据，则返回null
	 */
	User findById(Integer id);
	/**
	 * 
	 * @param id
	 * @param password 新密码
	 * @param modified_user 修改人名称
	 * @param modified_time 修改时间
	 * @return 返回受影响行数
	 */
	Integer updataPassword(@Param("id")Integer id,@Param("password") String password ,@Param("modified_user")String modified_user,@Param("modified_time")Date modified_time);
	/**
	 * 修改用户数据(不含用户名，密码，头像)
	 * @param user 用户
	 * @return
	 */
	Integer updataInfo(User user);
	/**
	 * 修改头像地址
	 * @param id
	 * @param avatar 头像地址
	 * @param modified_user 修改人名称
	 * @param modified_time 修改时间
	 * @return 受影响行数
	 */
	Integer updataAvatar(@Param("id")Integer id ,@Param("avatar")String avatar,@Param("modified_user")String modified_user,@Param("modified_time")Date modified_time );
}
