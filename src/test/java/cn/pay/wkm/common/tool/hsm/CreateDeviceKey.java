/**
 * Project Name:tool
 * File Name:CreateDeviceKey.java
 * Package Name:cn.pay.wkm.common.tool.hsm
 * Date:2015年1月16日下午5:19:13
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.hsm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * ClassName:CreateDeviceKey <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年1月16日 下午5:19:13 <br/>
 * @author   wkmnet@foxmail.com
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class CreateDeviceKey {
	
	//标准日志输出
	private static Logger logger = Logger.getLogger(BaseConfigInit.class);
	
	private BaseKeyTool tool = null;
	
	@Before
	public void before(){
		tool = new BaseKeyTool();
	}
	
	/**
	 * 移动支付密钥生成---联迪M35机型
	 * mobilepay:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @since JDK 1.6
	 */
	@Test
	public void mobilepay(){
		StringBuilder builer = new StringBuilder();
		StringBuilder builerNext = new StringBuilder();
		StringBuilder builerSn_1 = new StringBuilder();
		StringBuilder builerSn_2 = new StringBuilder();
		
		StringBuilder sql = new StringBuilder();
		
		for(int i = 8139;i < 10139;i++){
			String tNo = "001" + String.format("%05d", i);
			builer.append(String.format("%-32s",tNo));
			builer.append(",");
			builer.append("16");
			KeyInfo keyInfo = new KeyInfo();
			tool.create3DESTMKByZMK("E43DF8B42C55131076AF8873AA62AD76", keyInfo);
			builer.append(",");
			builer.append(String.format("%-48s",keyInfo.getKeyCiphertext().substring(0, 32)));
			builer.append(",");
			builer.append(String.format("%-8s",keyInfo.getCheckValue()));
			builer.append(",");
			builer.append("00");
			builer.append(System.getProperty("line.separator"));
			builerSn_1.append(tNo + "=" + keyInfo.toString());
			builerSn_1.append(System.getProperty("line.separator"));
			
			
			builerNext.append(String.format("%-32s",tNo));
			builerNext.append(",");
			builerNext.append("16");
			tool.encrypt3DESTMKByZMK("84C254C7909005AB67E9CB81D21BAC88", keyInfo);
			builerNext.append(",");
			builerNext.append(String.format("%-48s",keyInfo.getKeyCiphertext().substring(0, 32)));
			builerNext.append(",");
			builerNext.append(String.format("%-8s",keyInfo.getCheckValue()));
			builerNext.append(",");
			builerNext.append("00");
			builerNext.append(System.getProperty("line.separator"));
			builerSn_2.append(tNo + "=" + keyInfo.toString());
			builerSn_2.append(System.getProperty("line.separator"));
			
			sql.append("insert into mcr_ksn (id,date_created,is_activated,is_used,ksn_no) values (seq_mcrksn.nextval,systimestamp,0,0,'");
			sql.append("6000");
			sql.append(tNo);
			sql.append("');");
			sql.append(System.getProperty("line.separator"));
			
			sql.append("insert into key_store  (id,check_value,class_name,key_alias,key_length,key_type,key_value)values (seq_keystore.nextval,'");
			sql.append(keyInfo.getCheckValue());
			sql.append("','org.jpos.security.SecureDESKey','ws.");
			sql.append("6000");
			sql.append(tNo);
			sql.append(".tmk',128,'TMK','");
			sql.append(tool.encrypt3DESTMKByLMK(keyInfo.getKeyValue()));
			sql.append("');");
			sql.append(System.getProperty("line.separator"));
		}
		
		String bathNo = "00001_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		File f = new File("E:/keys/mobilepay_key");
		File keyDir = new File(f,bathNo);
		if(!keyDir.exists()){
			keyDir.mkdir();
		}
		toLocation(builer.toString(),keyDir.getAbsolutePath() + "/key_sn_03756459.csv");
		toLocation(builerSn_1.toString(),keyDir.getAbsolutePath() + "/tmk_sn_03756459.txt");
		toLocation(builerNext.toString(), keyDir.getAbsolutePath() + "/key_sn_03756458.csv");
		toLocation(builerSn_2.toString(), keyDir.getAbsolutePath() + "/tmk_sn_03756458.txt");

		//数据库文件
		sql.append("commit;");
		toLocation(sql.toString(), keyDir.getAbsolutePath() + "/tmk_sql.txt");
	}
	
	
	
	
	
	

	/**
	 * 密钥写入文件
	 * toLocation:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param buffer
	 * @param file
	 * @since JDK 1.6
	 */
	private void toLocation(String buffer,File file){
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(buffer);
			writer.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 密钥写入文件
	 * toLocation:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param buffer
	 * @param file
	 * @since JDK 1.6
	 */
	private void toLocation(String buffer,String file){
		toLocation(buffer,new File(file));
	}
}

