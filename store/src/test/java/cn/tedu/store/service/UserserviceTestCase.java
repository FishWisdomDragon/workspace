package cn.tedu.store.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.exception.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserserviceTestCase {
	@Autowired
	private IUserService iUserService;
	@Test
	public void  addnew(){
		try {
			User user =new User();
			user.setUsername("yu");
			user.setPassword("1234");
			user.setGender(1);
			user.setPhone("13585558975");			
			System.out.println(iUserService.reg(user));
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void  login(){
		User u=iUserService.login("yu", "123");
		System.out.println(u);
	}
	@Test
	public void updatePassword(){
		iUserService.updatePassword(6, "123", "1234");
	}
	@Test
	public void updateInfo(){
		User user=new User();
		user.setPhone("11111111");
		user.setId(6);
		user.setEmail("BB@b.com");
		iUserService.changeInfo(user);
	}
	@Test
	public void updateAvatar(){
		iUserService.changeAvatar(6, "dhdhaaaaaaaaaaaaaaaaaaaa");
	}
}
