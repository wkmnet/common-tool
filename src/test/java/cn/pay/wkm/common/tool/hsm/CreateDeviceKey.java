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

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.junit.Before;

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
	 * 爱创机型密钥生成
	 * itron:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @since JDK 1.6
	 */
	public void itron(){
		String OUTPUT_DIR = "E:/keys/itron_key";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		File dir = new File(OUTPUT_DIR);
		File current = new File(dir,format.format(new Date()));
		if(!current.exists()){
			current.mkdirs();
		}
		KeyInfo key = new KeyInfo();
		tool.create3DESTMK(key);
		toLocation(key.getKeyValue(),new File(current,"tmk.txt").getAbsolutePath());
		itronTAK(current,key.getKeyValue());
	}
	private void itronTAK(File dir,String tpk){
		String clearTpk = tpk;
		String pre = "8000";
		StringBuilder itron = new StringBuilder();
		StringBuilder sql = new StringBuilder();
		StringBuilder clearTAK= new StringBuilder();
		for(int i=5001; i < 15001;i++){
			String ksn = "000" + String.format("%05d", i);
			KeyInfo key = new KeyInfo();
			tool.create3DESTAKByTMK(clearTpk,key);
			//生成爱创所需密钥格式
			//itron.append(pre); 2015年1月12日确定不加8000
			itron.append(ksn);
			itron.append("=");
			itron.append(key.getKeyCiphertext());
			itron.append(" " + key.getCheckValue());
			itron.append(System.getProperty("line.separator"));
			//生成中汇密钥入库格式
			sql.append(itronCreateSQL(pre + ksn,key.getCheckValue(),tool.encrypt3DESTAKByLMK(key.getKeyValue())));
			clearTAK.append(pre + ksn);
			clearTAK.append(",");
			clearTAK.append(key.getKeyValue());
			clearTAK.append(",");
			clearTAK.append(key.getCheckValue());
			clearTAK.append(System.getProperty("line.separator"));
		}
		//写入文件
		toLocation(itron.toString(),new File(dir,"key.txt").getAbsolutePath());
		sql.append("commit;");
		toLocation(sql.toString(),new File(dir,"tak_store.sql").getAbsolutePath());
		toLocation(clearTAK.toString(),new File(dir,"clear_key.sql").getAbsolutePath());
	}
	private String itronCreateSQL(String ksn,String checkValue,String keyValue){
		StringBuilder sql = new StringBuilder();
		sql.append("insert into mcr_ksn (id,date_created,is_activated,is_used,ksn_no) values (seq_mcrksn.nextval,systimestamp,0,0,'");
		sql.append(ksn);
		sql.append("');");
		sql.append(System.getProperty("line.separator"));
		
		sql.append("insert into key_store  (id,check_value,class_name,key_alias,key_length,key_type,key_value)values (seq_keystore.nextval,'");
		sql.append(checkValue);
		sql.append("','org.jpos.security.SecureDESKey','ws.");
		sql.append(ksn);
		sql.append(".tak',128,'TAK','");
		sql.append(keyValue);
		sql.append("');");
		sql.append(System.getProperty("line.separator"));
		return sql.toString();
	}
	
	/**
	 * 宜信密钥生成,只针对宜信机型
	 * creditease:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @since JDK 1.6
	 */
	public void creditease(){
		
		String workDir = "E:/keys/yixin_key";
		
		String currentTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
		
		File work = new File(workDir);
		if(!work.exists() || !work.isDirectory()){
			logger.error("不存在目录:" + workDir);
			return ;
		}
		File currentDir = new File(work,currentTime);
		
		if(!currentDir.exists()){
			logger.info("创建目录:" + currentDir.getAbsolutePath());
			currentDir.mkdir();
		}
		crediteaseCreateKey(currentDir);
	}
	private void crediteaseCreateKey(File dir){
		StringBuilder posKey = new StringBuilder();
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlTest = new StringBuilder();
		
		for(int i = 3001;i <= 4519;i++) {
			String ksn = "1710000100002000" + String.format("%04d", i);
			KeyInfo tmk = new KeyInfo();
			tool.create3DESTMK(tmk);;
			posKey.append(crediteaseCreateKey(ksn,tmk));
			
			sql.append(crediteaseCreateKsnSQL(ksn, tmk));
			sql.append(crediteaseCreateKsnAgency(ksn, "PM13"));
			
			sqlTest.append(crediteaseCreateKsnSQL(ksn, tmk));
			sqlTest.append(crediteaseCreateKsnAgency(ksn, "YIXIN_TEST"));
		}
		
		toLocation(posKey.toString(), new File(dir,"pos_id.txt"));
		sql.append("commit;");
		toLocation(sql.toString(), new File(dir,"yixin_shangxian.txt"));
		sqlTest.append("commit;");
		toLocation(sqlTest.toString(), new File(dir,"yixin_test.txt"));
	}
	private String crediteaseCreateKey(String ksn,KeyInfo tmk){
		StringBuilder key = new StringBuilder();
		key.append(ksn);
		key.append(",");
		key.append(tmk.getKeyValue());
		key.append(tmk.getCheckValue());
		key.append(System.getProperty("line.separator"));
		return key.toString();
	}
	private String crediteaseCreateKsnSQL(String ksn,KeyInfo tmk){
		StringBuilder sql = new StringBuilder();
		sql.append("insert into mcr_ksn (id,date_created,is_activated,is_used,ksn_no) values (seq_mcrksn.nextval,systimestamp,0,0,'");
		sql.append(ksn);
		sql.append("');");
		sql.append(System.getProperty("line.separator"));
		
		sql.append("insert into key_store  (id,check_value,class_name,key_alias,key_length,key_type,key_value)values (seq_keystore.nextval,'");
		sql.append(tmk.getCheckValue());
		sql.append("','org.jpos.security.SecureDESKey','ws.");
		sql.append(ksn);
		sql.append(".tmk',128,'TMK','");
		sql.append(tool.encrypt3DESTMKByLMK(tmk.getKeyValue()));
		sql.append("');");
		sql.append(System.getProperty("line.separator"));
		return sql.toString();
	}
	private String crediteaseCreateKsnAgency(String ksn,String agencyCode){
		StringBuilder sql = new StringBuilder();
		sql.append("insert into agency_ksn (ksn_no,agency_code) values ('");
		sql.append(ksn);
		sql.append("','");
		sql.append(agencyCode);
		sql.append("');");
		sql.append(System.getProperty("line.separator"));
		return sql.toString();
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

