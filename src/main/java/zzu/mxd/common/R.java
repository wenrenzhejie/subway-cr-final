package zzu.mxd.common;

import java.io.Serializable;

public class R<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final int SUCCESS = 0;
	private static final int ERROR = 1;
	private Integer status;
	private T content;
	private String message;
	public R(Integer status , String message) {
		this.status = status;
		this.message = message;
	}
	/**
	 * 调用成功,状态码加提示信息
	 * @param message
	 * @return
	 */
	public static<Y> R<Y> succM(String message) {
		return succMC(null, message);
	}
	public static<Y> R<Y> succ() {
		return succMC(null,null);
	}
	/**
	 * 调用失败,状态码加提示信息
	 * @param message
	 * @return
	 */
	public static<Y> R<Y> errM(String message) {
		return new R<>(ERROR, message);
	}
	/**
	 * 调用成功 , 返回状态码 , 提示信息 , 接口数据
	 * @param content
	 * @param message
	 * @return
	 */
	public static<Y> R<Y> succMC(Y content , String message) {
		return new R<>(SUCCESS, content, message);
	}
	public static<Y> R<Y> succC(Y content) {
		return succMC(content, null);
	}
	
	
	public R(Integer status , T content) {
		this.status = status;
		this.content = content;
	}
	
	public R(Integer status , T content , String message) {
		this.status = status;
		this.content = content;
		this.message = message;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
