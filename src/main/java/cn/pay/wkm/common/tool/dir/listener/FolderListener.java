/**
 * Project Name:tool
 * File Name:FolderListener.java
 * Package Name:cn.pay.wkm.common.tool.dir.listener
 * Date:2015年1月9日下午2:23:06
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.dir.listener;

import net.contentobjects.jnotify.JNotifyListener;

/**
 * ClassName:FolderListener <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年1月9日 下午2:23:06 <br/>
 * @author   wkmnet@foxmail.com
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public interface FolderListener extends JNotifyListener {

	public void setFolderConfig(ScannerConfig config);
}

