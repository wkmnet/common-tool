/**
 * Project Name:tool
 * File Name:LocalCopyListener.java
 * Package Name:cn.pay.wkm.common.tool.dir.listener
 * Date:2015年1月9日下午3:19:59
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.dir.listener;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jpos.util.Log;
import org.jpos.util.Logger;
import org.jpos.util.NameRegistrar;

/**
 * ClassName:LocalCopyListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年1月9日 下午3:19:59 <br/>
 * 
 * @author wkmnet@foxmail.com
 * @version
 * @since JDK 1.6
 * @see
 */
public class LocalCopyListener implements FolderListener {

	//jpos日志
	private Log log = new Log((Logger) NameRegistrar.getIfExists("logger.Q2"),
			LocalCopyListener.class.getName());

	//配置文件
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
				log.info("创建目录：" + f.getAbsolutePath());
				f.mkdirs();
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

	}

	public void fileModified(int wd, String rootPath, String name) {

		// TODO Auto-generated method stub

	}

	public void fileRenamed(int wd, String rootPath, String oldName,
			String newName) {
		// TODO Auto-generated method stub
		if (!StringUtils.isBlank(oldName)) {
			File f = new File(new File(rootPath), oldName);
			log.info("从本地移走的文件【不管】：" + f.getAbsolutePath());
			return;
		}

		File f = new File(new File(rootPath), newName);
		try {
			if (f.exists()) {
				log.info("移动文件：" + f.getAbsolutePath());
			}
			if (f.isDirectory()) {
				File targetDir = new File(
						new File(pathAddress.getTargetPath()), newName);
				log.info("创建目录：" + targetDir.getAbsolutePath());
				targetDir.mkdirs();
				return;
			}

			FileUtils.copyFile(f, new File(
					new File(pathAddress.getTargetPath()), newName));

			log.info("复制到：" + pathAddress.getAddress() + ":"
					+ pathAddress.getTargetPath());

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
