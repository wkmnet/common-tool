/**
 * Project Name:tool
 * File Name:ScpFileListener.java
 * Package Name:cn.pay.wkm.common.tool.dir.listener
 * Date:2015年1月9日下午3:15:58
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.dir.listener;

import java.io.File;

import org.jpos.util.Log;
import org.jpos.util.Logger;
import org.jpos.util.NameRegistrar;

/**
 * ClassName:ScpFileListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年1月9日 下午3:15:58 <br/>
 * @author   wkmnet@foxmail.com
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ScpFileListener implements FolderListener {

	//jpos 日志
	private Log log = new Log((Logger) NameRegistrar.getIfExists("logger.Q2"),
			ScpFileListener.class.getName());
	//配置信息
	private ScannerConfig pathAddress = null;

	//文件分隔符
	private String fileSeparator = System.getProperty("file.separator");


	public void fileCreated(int wd, String rootPath, String name) {
		// TODO Auto-generated method stub
		File f = new File(new File(rootPath), name);
		try {
			if (f.exists()) {
				log.info("创建文件：" + f.getAbsolutePath());
			}
			if (f.isDirectory()) {
				log.info("创建目录：" + f.getAbsolutePath() + ";这个我搞不定了!");
				return ;
			}

			String command = "scp " + f.getAbsolutePath() + " "
					+ pathAddress.getUserName() + "@"
					+ pathAddress.getAddress() + ":"
					+ pathAddress.getTargetPath() + fileSeparator
					+ name.replaceAll(f.getName(), "");
			log.info("复制到：" + pathAddress.getAddress() + ":"
					+ pathAddress.getTargetPath());
			Runtime runtime = Runtime.getRuntime();
			log.info("执行：" + command);
			runtime.exec(command);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("复制文件出错：" + f.getAbsolutePath());
		}

	}

	public void fileDeleted(int wd, String rootPath, String name) {
		// TODO Auto-generated method stub
		File f = new File(new File(rootPath), name);
		log.info("删除文件：" + f.getAbsolutePath());
	}

	public void fileModified(int wd, String rootPath, String name) {

		// TODO Auto-generated method stub

		File f = new File(new File(rootPath), name);
		try {
			if (f.exists()) {
				log.info("修改文件：" + f.getAbsolutePath());
			}
			if (f.isDirectory()) {
				log.info("修改目录：" + f.getAbsolutePath() + ";这个我搞不定了!");
				return ;
			}

			String command = "scp " + f.getAbsolutePath() + " "
					+ pathAddress.getUserName() + "@"
					+ pathAddress.getAddress() + ":"
					+ pathAddress.getTargetPath() + fileSeparator
					+ name.replaceAll(f.getName(), "");
			log.info("复制到：" + pathAddress.getAddress() + ":"
					+ pathAddress.getTargetPath());
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(command);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("复制文件出错：" + f.getAbsolutePath());
		}

	}

	public void fileRenamed(int wd, String rootPath, String oldName,
			String newName) {
		// TODO Auto-generated method stub
		if(oldName != null){
			log.info("目录内的文件重命名无法处理：" + oldName + " 变为：" + newName);
			return ;
		}
		log.info("文件：" + oldName + " 变为：" + newName);
		File f = new File(new File(rootPath), newName);
		try {
			if (f.exists()) {
				log.info("移动文件：" + f.getAbsolutePath());
			}
			if (f.isDirectory()) {
				log.info("修改目录：" + f.getAbsolutePath() + ";这个我搞不定了!");
				return ;
			}

			String command = "scp " + f.getAbsolutePath() + " "
					+ pathAddress.getUserName() + "@"
					+ pathAddress.getAddress() + ":"
					+ pathAddress.getTargetPath() + fileSeparator
					+ newName.replaceAll(f.getName(), "");
			log.info("复制到：" + pathAddress.getAddress() + ":"
					+ pathAddress.getTargetPath());
			Runtime runtime = Runtime.getRuntime();
			runtime.exec(command);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("复制文件出错：" + f.getAbsolutePath());
		}
	}

	public void setFolderConfig(ScannerConfig config) {
		// TODO Auto-generated method stub
		this.pathAddress = config;
	}

}

