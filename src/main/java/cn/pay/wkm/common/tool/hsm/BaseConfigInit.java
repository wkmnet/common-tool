/**
 * Project Name:tool
 * File Name:BaseConfigInit.java
 * Package Name:cn.pay.wkm.common.tool.hsm
 * Date:2015年1月12日下午5:24:35
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.hsm;

import java.io.File;
import java.net.SocketException;

import org.apache.log4j.Logger;
import org.jpos.core.ConfigurationException;
import org.jpos.core.SimpleConfiguration;
import org.jpos.ext.channel.HEXChannel;
import org.jpos.ext.security.MyJCEHandler;
import org.jpos.ext.security.SoftSecurityModule;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISOUtil;
import org.jpos.iso.packager.GenericPackager;
import org.jpos.security.SimpleKeyFile;
import org.jpos.security.jceadapter.JCEHandlerException;
import org.jpos.util.DailyLogListener;

/**
 * ClassName:BaseConfigInit <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年1月12日 下午5:24:35 <br/>
 * @author   wkmnet@foxmail.com
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public abstract class BaseConfigInit {
	
	private static Logger logger = Logger.getLogger(BaseConfigInit.class);
	
	private org.jpos.util.Logger log = new org.jpos.util.Logger();
	
	protected ISOPackager packager = null;
	
	protected HEXChannel channel = null;
	
	protected SoftSecurityModule ssm = null;
	
	protected MyJCEHandler handler = null;
	
	protected SimpleKeyFile skf = null;
	
	private String serverHost = "192.168.1.2";
	
	private int serverPort = 5555;
	
	private String lmk = "D:/.lmk";
	
	private String key = "D:/.key";
	
	public BaseConfigInit(){
		initConfig();
	}
	
	/**
	 * 初始化加密机配置
	 * initConfig:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @since JDK 1.6
	 */
	private void initConfig(){
		try {
			logger.info("初始化:iso8583.xml");
			File f = new File("config/posptu/posp-v1.xml");
			packager = new GenericPackager(f.getAbsolutePath());
		} catch (ISOException e){
			logger.error("初始化[iso8583.xml]错误:" + e.getMessage(),e);
			return;
		}
		
		try {
			logger.info("初始化HEXChannel...");
			channel = new HEXChannel(serverHost,serverPort,packager);
//			channel.setHeader(ISOUtil.str2bcd("6000060001603000000000",false));
			channel.setHeader(ISOUtil.hex2byte("6000060001603000000000"));
			channel.setOverrideHeader(true);
			DailyLogListener dailyLog = new DailyLogListener();
			SimpleConfiguration cfg = new SimpleConfiguration();
//			dailyLog.setPrefix("log/test");
			cfg.put("suffix", "pos");
			dailyLog.setConfiguration(cfg);
			log.addListener(dailyLog);
			
			channel.setLogger(log, "channel");
			channel.setTimeout(60*5*1000);
			logger.info("初始化Channel完成.");
			
			logger.info("config provider...");
			SimpleConfiguration config = new SimpleConfiguration();
			config.put("provider", "com.sun.crypto.provider.SunJCE");
			config.put("host", "192.168.1.3");//加密机ip
			config.put("port", "8000");//加密机端口
			config.put("lmk", lmk);
			config.put("key-file", key);
			logger.info("config provider over!");
			
			ssm = new SoftSecurityModule();
			ssm.setLogger(log, "ssm");
			ssm.setConfiguration(config);
			handler = new MyJCEHandler("com.sun.crypto.provider.SunJCE");
			
			skf = new SimpleKeyFile();
			skf.setLogger(log, "skf");
			skf.setConfiguration(config);
			
			
		} catch (SocketException e){
			logger.error("连接服务器异常:" + e.getMessage(),e);
		} catch (ConfigurationException e){
			logger.error("配置信息异常:" + e.getMessage(),e);
		} catch (JCEHandlerException e){
			logger.error("创建JCEHandler异常:" + e.getMessage(),e);
		}
	}

}

