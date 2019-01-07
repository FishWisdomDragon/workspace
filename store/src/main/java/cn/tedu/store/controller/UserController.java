package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import cn.tedu.store.ResponseResult.ResponseResult;
import cn.tedu.store.controller.exception.FileEmptyEXception;
import cn.tedu.store.controller.exception.FileSizeEXception;
import cn.tedu.store.controller.exception.FileTypeEXception;
import cn.tedu.store.controller.exception.FileuploadEXception;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	private static final String UPLOAD_DIR_NAME="upload";
	private static final Long FILE_MAX_SIZE=2L*1024*1024;
	private static final List<String> FILE_CONTENT_TYPES=new ArrayList<String>();
	/**
	 * 初始化允许上传的文件类型的集合
	 */
	static {
		FILE_CONTENT_TYPES.add("image/png");
		FILE_CONTENT_TYPES.add("image/jpeg");
	}
	@Autowired
	private IUserService userService;
	/**
	 * 注册
	 * @param user 用户数据
	 * @return 注册结果
	 */
	@RequestMapping("/reg.do")
	public ResponseResult<Void> reg(User user){	
		userService.reg(user);
		return new ResponseResult<>(SUCCESS);
	}
	/**
	 * 登入
	 * @param username 用户名
	 * @param password 密码
	 * @param session 状态管理
	 * @return 登入结果
	 */
	@RequestMapping("/login.do")
	public ResponseResult<User> handleLogin(@RequestParam("username") String username,@RequestParam("password")String password,HttpSession session){	
		User user=userService.login(username, password);
		System.out.println(user);
		session.setAttribute("uid",user.getId());
		session.setAttribute("username", user.getUsername());
		return new ResponseResult<User>(SUCCESS,user);
	}
	/**
	 *  修改密码
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @param session 用户状态
	 * @return 修改结果
	 */
	@RequestMapping("/updatePassword.do")
	public ResponseResult<Void> updatePassword(String oldPassword,String newPassword,HttpSession session){
		System.out.println(oldPassword);
		System.out.println(newPassword);
		Integer id= (Integer) session.getAttribute("uid");
		System.out.println(id);
		userService.updatePassword(id, oldPassword, newPassword);
		return new ResponseResult<>(SUCCESS);
	}
	@RequestMapping("/info.do")
	public ResponseResult<User> info(HttpSession session){
		Integer id=(Integer) session.getAttribute("uid");
		User data=userService.getById(id);
		return new ResponseResult<User>(SUCCESS, data);
	}
	@RequestMapping("/change_info.do")
	public ResponseResult<Void> changeInfo(User user,HttpSession session){
		Integer id=(Integer) session.getAttribute("uid");
		user.setId(id);
		userService.changeInfo(user);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@PostMapping("/upload.do")
	public ResponseResult<String> handleupload(MultipartFile file, HttpSession session){
		if(file.isEmpty()){
			throw new FileEmptyEXception("上传失败！没有选择文件或选择文件为空");
		}
		if(file.getSize()>FILE_MAX_SIZE){
			throw new FileSizeEXception("上传失败！文件大小超标");
		}
		if(!FILE_CONTENT_TYPES.contains(file.getContentType())){
			throw new FileTypeEXception("上传失败！文件类型不匹配");
		}
		String parentpath=session.getServletContext().getRealPath(UPLOAD_DIR_NAME);
		File parent=new File(parentpath);
		if(!parent.exists()){
			parent.mkdirs();
		}
		String originalFileName=file.getOriginalFilename();
		String suffix=originalFileName.substring(originalFileName.indexOf("."));
		String newFileName=UUID.randomUUID().toString()+suffix;
		File dest=new File(parent,newFileName );
		try {
			file.transferTo(dest);
			System.err.println("上传完成");
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new FileuploadEXception("上传失败！系统繁忙");
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileuploadEXception("上传失败！系统繁忙");
		}
		Integer id=(Integer) session.getAttribute("uid");
		String avatar="/"+UPLOAD_DIR_NAME+"/"+newFileName;
		userService.changeAvatar(id, avatar);
		ResponseResult<String> rr=new ResponseResult<>();
		rr.setState(SUCCESS);
		rr.setData(avatar);
		return rr;
	}
}
