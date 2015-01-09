/**
 * Project Name:tool
 * File Name:FolderScanner.java
 * Package Name:cn.pay.wkm.common.tool.dir.listener
 * Date:2015年1月9日下午1:50:49
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.dir.listener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyException;

import org.apache.commons.lang.StringUtils;
import org.jpos.core.Configuration;
import org.jpos.q2.QBeanSupport;
import org.jpos.util.Log;
import org.jpos.util.Logger;
import org.jpos.util.NameRegistrar;

/**
 * ClassName:FolderScanner <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年1月9日 下午1:50:49 <br/>
 * @author   wkmnet@foxmail.com
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FolderScanner extends QBeanSupport {

	//jpos日志管理
	private Log log = new Log((Logger)NameRegistrar.getIfExists("logger.Q2"), FolderScanner.class.getName());
	
	//获取配置文件
	private Configuration config = getConfiguration();
	
	//路径分隔符
	private	String pathSeparator = System.getProperty("path.separator");
	
	//用户主目录
	private	String userHome = System.getProperty("user.dir");
	
	//watch id
	private List<Integer> watchIds = new ArrayList<Integer>();
	
	/**
	 * 初始化目录扫描器
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see org.jpos.q2.QBeanSupport#initService()
	 */
	protected void initService() throws Exception {
		// TODO Auto-generated method stub
		String paths = config.get("path", userHome);
		log.info("获取监听目录:" + paths);
		if(StringUtils.isBlank(paths.trim())){
			log.error("监听目录为空,启动目录扫描器失败!");
			return ;
		}
		String[] path = paths.split(pathSeparator);
		try {
			
			for(String temp:path){
				File f = new File(temp);
				if(!f.exists() || f.isFile()){
					log.info("文件不存在或者不是目录:" + temp);
					continue;
				}
				log.info("监听目录:" + temp);
				ScannerConfig config = new ScannerConfig();
				config.setSourcePath(temp);
				config.setAddress(cfg.get("address", "127.0.0.1"));
				config.setTargetPath(cfg.get("remotePath", System.getProperty("user.dir")));
				config.setUserName(cfg.get("userName", "appuser"));
				config.setRemote(cfg.getBoolean("isRemote", true));
				
				//加载处理器
				FolderListener listener = null;
				String listenerClass = cfg.get("listener");
				if(StringUtils.isBlank(listenerClass)){
					if(cfg.getBoolean("isRemote", true)){
						listener = new ScpFileListener();
					} else {
						listener = new LocalCopyListener();
					}
				} else {
					listener = (FolderListener)Class.forName(listenerClass).newInstance();
				}
				
				watchIds.add(JNotify.addWatch(config.getSourcePath(), JNotify.FILE_ANY, true, listener));
			}
			log.info("初始化目录扫描器成功!");
		} catch (JNotifyException e){
			log.info("初始化目录扫描器出错:" + e.getMessage(),e);
		} catch (ClassNotFoundException e){
			log.info("初始化目录扫描器出错:" + e.getMessage(),e);
		} catch (InstantiationException e){
			log.info("初始化目录扫描器出错:" + e.getMessage(),e);
		} catch (IllegalAccessException e){
			log.info("初始化目录扫描器出错:" + e.getMessage(),e);
		}
	}

	/**
	 * 移除所有监听项
	 * TODO 简单描述该方法的实现功能（可选）.
	 * @see org.jpos.q2.QBeanSupport#stopService()
	 */
	protected void stopService() throws Exception {
		// TODO Auto-generated method stub
		for(Integer watchId:watchIds){
			boolean res = JNotify.removeWatch(watchId);
			log.info("移出目录的监听【" + watchId + "】:" + res);
		}
	}
	
}

