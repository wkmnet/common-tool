/**
 * Project Name:tool
 * File Name:ScannerConfig.java
 * Package Name:cn.pay.wkm.common.tool.dir.listener
 * Date:2015年1月9日下午2:09:09
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.dir.listener;
/**
 * ClassName:ScannerConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年1月9日 下午2:09:09 <br/>
 * @author   wkmnet@foxmail.com
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ScannerConfig {

	//源目录 即监听目录
	private  String sourcePath = null;
	
	//目标目录  即文件被复制的目的地
	private  String targetPath = null;
	
	//目标地址 ip
	private String address = null;
	
	//目标地址用户名
	private String userName = null;
	
	//远程文件拷贝
	private boolean  remote =  true;

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isRemote() {
		return remote;
	}

	public void setRemote(boolean remote) {
		this.remote = remote;
	}

	@Override
	public String toString() {
		return "ScannerConfig [sourcePath=" + sourcePath + ", targetPath="
				+ targetPath + ", address=" + address + ", userName="
				+ userName + ", remote=" + remote + "]";
	}
}

