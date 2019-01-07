package cn.tedu.store.mapper;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {
	@Autowired
	private Usermapper usermapper;
	@Test
	public void  addnew(){
		Date now =new Date();
		User user =new User();
		user.setUsername("qqai");
		user.setPassword("1234");
		user.setGender(1);
		user.setPhone("13585558975");
		user.setSalt("hello,md5");
		user.setIs_delete(0);
		user.setCreated_user("admin");
		user.setCreated_time(now);
		user.setModified_time(now);
		user.setModified_user("admin");
		System.out.println(usermapper.addnew(user));
	}
	@Test
	public void  findByUsername(){
		User user=usermapper.findByUsername("root");
		System.out.println(user);
	}
	@Test
	public void  findById(){
		User user=usermapper.findById(3);
		System.out.println(user);
	}
	@Test
	public void  updataPassword(){
		Date date=new Date();
		date.setTime(date.getTime()+8*60*60*1000);
		Integer row=usermapper.updataPassword(3, "5555", "zz", date);
		System.out.println(row);
	}
	@Test
	public void  updataInfo(){
		Date date=new Date();
		date.setTime(date.getTime()+8*60*60*1000);
		User u=new User();
		u.setId(6);
		u.setGender(1);
		//u.setPhone("111000");
	//	u.setEmail("a@a.com");
		Integer row=usermapper.updataInfo(u); 
		System.out.println(row);
	}
	@Test
	public void  updataavatar(){
		Date date=new Date();
		date.setTime(date.getTime()+8*60*60*1000);
		Integer row=usermapper.updataAvatar(6, "dhdhr", "yy", date);
		System.out.println(row);
	}
}
