package com.zzg.actions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	/**
	 * 模仿用户登录
	 * @return
	 * @throws IOException 
	 */
	public String login() throws IOException {
		System.out.println(userName+","+password);
		//获取请求头
		HttpServletRequest  request=ServletActionContext.getRequest();
		System.out.println("请求头：--->S-ZZG-TOKEN:"+request.getHeader("S-ZZG-TOKEN"));
		//返回给客户端信息
		HttpServletResponse response= ServletActionContext.getResponse();
		PrintWriter writer=response.getWriter();
		writer.write("login success!");
		writer.flush();
		return null;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
