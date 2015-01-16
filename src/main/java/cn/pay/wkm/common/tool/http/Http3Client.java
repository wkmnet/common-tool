/**
 * Project Name:tool
 * File Name:Http3Client.java
 * Package Name:cn.pay.wkm.common.tool.http
 * Date:2015年1月16日下午1:40:22
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.http;

import java.io.IOException;
import java.util.Arrays;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * ClassName:Http3Client <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年1月16日 下午1:40:22 <br/>
 * @author   wkmnet@foxmail.com
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Http3Client implements Runnable{

private String url = null;
	
	private String name = null;
	

	public void setUrl(String url) {
		this.url = url;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void request(){
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.addParameters(new NameValuePair[]{
				new NameValuePair("name",name)});
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		try {
			int status = client.executeMethod(method);
			System.out.println("返回交易状态：" + status);
			String body = method.getResponseBodyAsString();
			if(status == 200){
				System.out.println("接收到的返回数据：" + body);
				JSONObject jsonObject = JSONObject.fromObject(body);
				if(jsonObject.getBoolean("flag")){
					System.out.println("成功：" + jsonObject.toString());
				} else {
					System.out.println("服务器返回错误信息：" + jsonObject.toString());
				}
			} else {
				System.out.println("错误的返回数据：" + body);
			}
			method.releaseConnection();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("HttpException请求：" + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("url请求IOException：" + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("url请求Exception：" + e.getMessage());
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		while(true){
			request();
			rest();
		}
	}
	
	private void rest(){
		try {
			Thread.sleep(1000*6);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("线程中断:" + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(args));
		if(args.length != 2){
			System.out.println("参数非法!");
			return;
		}
		Http3Client b = new Http3Client();
//		b.setName("武坤萌");
		b.setName(args[0]);
//		b.setUrl("http://127.0.0.1:8080/woshua/attendance/insert.action");
		b.setUrl(args[1]);
		Thread t = new Thread(b);
		t.start();
	}
}

