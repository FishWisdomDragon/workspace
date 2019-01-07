package cn.tedu.store.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.tedu.store.ResponseResult.ResponseResult;
import cn.tedu.store.controller.exception.FileEmptyEXception;
import cn.tedu.store.controller.exception.FileSizeEXception;
import cn.tedu.store.controller.exception.FileTypeEXception;
import cn.tedu.store.controller.exception.FileuploadEXception;
import cn.tedu.store.controller.exception.RequestException;
import cn.tedu.store.service.exception.DupticateKeyException;
import cn.tedu.store.service.exception.InsertException;
import cn.tedu.store.service.exception.PasswordNotMatchException;
import cn.tedu.store.service.exception.ServiceException;
import cn.tedu.store.service.exception.UpdateFailureException;
import cn.tedu.store.service.exception.UpdatePasswordFailureException;
import cn.tedu.store.service.exception.UserNotFoundException;
/**
 * 当前项目中所有控制器类的基类
 * @author Administrator
 *
 */
public abstract class BaseController {
	public static final Integer SUCCESS=200;
	@ExceptionHandler({ServiceException.class,RequestException.class})
	@ResponseBody
	public ResponseResult<Void> handleServiceException(Exception e){
		if(e instanceof DupticateKeyException ){
			return new ResponseResult<>(101,e);
		}else if(e instanceof InsertException){
			return new ResponseResult<>(102,e);
		}else if(e instanceof UserNotFoundException ){
			return new ResponseResult<>(103,e);
		}else if(e instanceof PasswordNotMatchException){
			return new ResponseResult<>(104,e);
		}else if(e instanceof UpdatePasswordFailureException){
			return new ResponseResult<>(105,e);
		}else if(e instanceof UpdateFailureException){
			return new ResponseResult<>(106,e);
		}else if(e instanceof FileEmptyEXception ){
			return new ResponseResult<>(201,e);
		}else if(e instanceof FileSizeEXception){
			return new ResponseResult<>(202,e);
		}else if(e instanceof FileTypeEXception){
			return new ResponseResult<>(203,e);
		}else if(e instanceof FileuploadEXception){
			return new ResponseResult<>(204,e);
		}
		return null;
	}
	
}
