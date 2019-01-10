package com.zzg.actions;

 
import java.io.BufferedReader;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
 
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
 
 

public class UserAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	//上传文件,mPhoto跟客户端保持一致，这样就可以直接接收，
	//跟上面的userName和password一样，只不过不是私有的，改成私有的就得加getter、setter
	public File mPhoto;
	//这个写法也是固定的，FileName，不能改变大小写
	public String mPhotoFileName;
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
	/**
	 * 从客户端接收jsonString
	 * @return
	 * @throws IOException
	 */
	public String postString() throws IOException {
		StringBuilder result=new StringBuilder();
		HttpServletRequest request=ServletActionContext.getRequest();
		InputStream inputStream=request.getInputStream();
		BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
		String buf;
		while((buf=reader.readLine())!=null) {
			result.append(buf);
		}
		//返回给客户端信息
		HttpServletResponse response= ServletActionContext.getResponse();
		//设置编码格式，不然客户端是gbk解析就乱码了
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer=response.getWriter();
		writer.write("服务端接收数据："+result);
		writer.flush();
		System.out.println("从客户端接收的数据为：\n"+result);
		return null;
	}
	
	
	/**
	 * 接收客户端发送过来的文件
	 * @throws IOException
	 */
	public void postFile() throws IOException {
	 HttpServletRequest request=ServletActionContext.getRequest();
	 InputStream inputStream=request.getInputStream();
	 //File
//	 String fileName="D:/okhttpServer/zzg.jpg";
//	 
//	 File file = new  File(fileName);
//	 if (!file.exists()) {
//	   //  File dirs=new File(fileName.substring(0,fileName.lastIndexOf("/")));
//		 File dirs=file.getParentFile();
//		if (!dirs.isDirectory()||!dirs.exists()) {
//			dirs.mkdirs();
//		}
//		file.createNewFile();
//	}
	 String dir=ServletActionContext.getServletContext().getRealPath("files");
	 File file=new File(dir,"zzzz.jpg");
	 FileOutputStream fos = new FileOutputStream(file);
	 int len=0;
	 byte[] buf=new byte[1024*2];
	 while((len=inputStream.read(buf))!=-1) {
		 fos.write(buf, 0, len);
	 }
	 inputStream.close();
	 fos.flush();
	 fos.close();
	}
	/**
	 * 上传文件和其他信息
	 * @return
	 * @throws IOException
	 */
	public String uploadInfo() throws IOException{
		if (mPhoto==null) {
			System.out.println(mPhotoFileName+"is null!");
		}
		String dir="D:/okhttpServer/"+mPhotoFileName;
		File file=new File(dir);
		 if (!file.exists()) {
			   //  File dirs=new File(fileName.substring(0,fileName.lastIndexOf("/")));
				 File dirs=file.getParentFile();
				if (!dirs.isDirectory()||!dirs.exists()) {
					dirs.mkdirs();
				}
				file.createNewFile();
			}
		 
		FileUtils.copyFile(mPhoto, file);
		System.out.println("fileName"+mPhotoFileName+"||userName:"+userName
				+"||password:"+password);
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
