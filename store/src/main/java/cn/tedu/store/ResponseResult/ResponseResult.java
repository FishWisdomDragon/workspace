package cn.tedu.store.ResponseResult;

import java.io.Serializable;
/**
 * 服务器端向客户的响应结果的类型
 * @author Administrator
 *
 * @param <Y> 服务器端向客户的响应的数据结果类型
 */
public class ResponseResult<Y> implements Serializable{
	private Integer state;
	private String message;
	private Y data;
	
	public ResponseResult() {
		super();
	}
	
	public ResponseResult(Integer state) {
		super();
		setState(state);
	}

	public ResponseResult(Integer state, String message) {
		this(state);
		setMessage(message);
	}
	
	public ResponseResult(Integer state, Exception e) {
		this(state,e.getMessage());
	}
		
	public ResponseResult(Integer state, Y data) {
		this(state);
		setData(data);
	}

	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Y getData() {
		return data;
	}
	public void setData(Y data) {
		this.data = data;
	}
	
}
