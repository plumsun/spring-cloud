package com.migu.utils;

import java.io.Serializable;


public class Response implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String default_message = "成功";

	private int code;
	private String message;
	private Object body;
	private long timeStamp = System.currentTimeMillis();
	private String respId;

	public String getRespId() {
		return respId;
	}

	public void setRespId(String respId) {
		this.respId = respId;
	}

	public static Response ok() {
		Response r = new Response();
		r.setCode(200);
		r.setMessage(default_message);
		r.setTimeStamp(System.currentTimeMillis());
		return r;
	}

	public static Response ok(String message) {
		Response r = new Response();
		r.setCode(200);
		r.setMessage(message);
		r.setTimeStamp(System.currentTimeMillis());
		return r;
	}

	public static Response ok(Object body) {
		Response r = new Response();
		r.setCode(200);
		r.setMessage(default_message);
		r.setBody(body);
		r.setTimeStamp(System.currentTimeMillis());
		return r;
	}

	public static Response displayOk(Object body) {
		Response r = new Response();
		r.setCode(200);
		r.setMessage("ok");
		r.setBody(body);
		r.setTimeStamp(System.currentTimeMillis());
		return r;
	}

	public static Response error(int code, String message) {
		Response r = new Response();
		r.setCode(code);
		r.setMessage(message);
		r.setTimeStamp(System.currentTimeMillis());
		return r;
	}

	public static Response contentOk(Object body) {
		Response r = new Response();
		r.setCode(200);
		r.setBody(body);
		r.setTimeStamp(System.currentTimeMillis());
		return r;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * 服务请求成功
	 * 
	 * @param code    响应状态码
	 * @param message 响应提示信息
	 * @param body    响应消息体
	 * @return
	 */
	public static Response response(int code, String message, Object body) {
		Response r = new Response();
		r.setCode(code);
		r.setMessage(message);
		r.setBody(body);
		r.setTimeStamp(System.currentTimeMillis());
		return r;
	}

	/**
	 * 服务异常
	 * 
	 * @param code    响应状态码
	 * @param message 响应提示信息
	 * @param body    响应消息体
	 * @return
	 */
	public static Response exception(int code, String message, Object body) {
		Response r = new Response();
		r.setCode(code);
		r.setBody(body);
		r.setMessage(message);
		r.setTimeStamp(System.currentTimeMillis());
		return r;
	}


	@Override
	public String toString() {
		return "Response{" +
				"code=" + code +
				", message='" + message + '\'' +
				", body=" + body +
				", timeStamp=" + timeStamp +
				", respId='" + respId + '\'' +
				'}';
	}
}
