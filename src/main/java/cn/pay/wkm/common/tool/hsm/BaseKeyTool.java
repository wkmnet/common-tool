/**
 * Project Name:tool
 * File Name:BaseKeyTool.java
 * Package Name:cn.pay.wkm.common.tool.hsm
 * Date:2015年1月16日下午3:48:06
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.hsm;

import java.security.Key;

import org.apache.log4j.Logger;
import org.jpos.iso.ISOUtil;
import org.jpos.security.SMAdapter;
import org.jpos.security.SMException;
import org.jpos.security.SecureDESKey;
import org.jpos.security.jceadapter.JCEHandlerException;

/**
 * ClassName:BaseKeyTool <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年1月16日 下午3:48:06 <br/>
 * @author   wkmnet@foxmail.com
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class BaseKeyTool extends BaseConfigInit{

	private static Logger logger = Logger.getLogger(BaseKeyTool.class);
	
	/**
	 * 创建3DES TMK
	 * create3DESTMK:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param tmk
	 * @since JDK 1.6
	 */
	public void create3DESTMK(KeyInfo tmk){
		createKey(tmk, SMAdapter.LENGTH_DES3_2KEY, SMAdapter.TYPE_TMK);
	}
	
	/**
	 * 创建3DES的TAK
	 * create3DESTAK:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param tak
	 * @since JDK 1.6
	 */
	public void create3DESTAK(KeyInfo tak){
		createKey(tak, SMAdapter.LENGTH_DES3_2KEY, SMAdapter.TYPE_TAK);
	}
	
	/**
	 * 创建DESTMK
	 * createDESTMK:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param tmk
	 * @since JDK 1.6
	 */
	public void createDESTMK(KeyInfo tmk){
		createKey(SMAdapter.LENGTH_DES, SMAdapter.TYPE_TMK, tmk);
	}
	
	/**
	 * 创建3DES的ZMK
	 * create3DESZMK:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param zmk
	 * @since JDK 1.6
	 */
	public void create3DESZMK(KeyInfo zmk){
		createKey(zmk, SMAdapter.LENGTH_DES3_2KEY, SMAdapter.TYPE_ZMK);
	}
	
	/**
	 * 生成3DESTMK并通过KEK对3DESTMK进行加密
	 * create3DESTMKByZMK:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param clearZmk
	 * @param tmk
	 * @since JDK 1.6
	 */
	public void create3DESTMKByZMK(String clearZmk,KeyInfo tmk){
		create3DESTMK(tmk);
		encryptByKek(clearZmk, tmk, SMAdapter.LENGTH_DES3_2KEY, SMAdapter.TYPE_ZMK, SMAdapter.TYPE_TMK);
	}
	
	public void create3DESTAKByTMK(String clearTmk,KeyInfo tak){
		create3DESTAK(tak);
		encryptByKek(clearTmk, tak, SMAdapter.LENGTH_DES3_2KEY, SMAdapter.TYPE_TMK, SMAdapter.TYPE_TAK);
	}
	
	/**
	 * 通过KEK对3DESTMK进行加密
	 * encrypt3DESTMKByZMK:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param clearZmk
	 * @param tmk
	 * @since JDK 1.6
	 */
	public void encrypt3DESTMKByZMK(String clearZmk,KeyInfo tmk){
		encryptByKek(clearZmk, tmk, SMAdapter.LENGTH_DES3_2KEY, SMAdapter.TYPE_ZMK, SMAdapter.TYPE_TMK);
	}
	
	/**
	 * 通过LMK加密TMK
	 * encrypt3DESTMKByLMK:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param clearKey
	 * @since JDK 1.6
	 */
	public String encrypt3DESTMKByLMK(String clearKey){
		return encryptToLMK(clearKey, SMAdapter.LENGTH_DES3_2KEY, SMAdapter.TYPE_TMK);
	}
	
	/**
	 * 通过LMK加密TAK
	 * encrypt3DESTAKByLMK:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param clearKey
	 * @return
	 * @since JDK 1.6
	 */
	public String encrypt3DESTAKByLMK(String clearKey){
		return encryptToLMK(clearKey, SMAdapter.LENGTH_DES3_2KEY, SMAdapter.TYPE_TAK);
	}
	
	/**
	 * 获取密文密钥并解密成明文
	 * createKey:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param keyInfo
	 * @param keyLength
	 * @param keyType
	 * @since JDK 1.6
	 */
	private void createKey(KeyInfo keyInfo,short keyLength, String keyType){
		try {
			//获取密文KEY
			SecureDESKey key = ssm.generateKey(keyLength, keyType);
			logger.info("key=" + ISOUtil.byte2hex(key.getKeyBytes()).toUpperCase());
			logger.info("key=" + ISOUtil.byte2hex(key.getKeyCheckValue()).toUpperCase());
			keyInfo.setCheckValue(ISOUtil.byte2hex(key.getKeyCheckValue()).toUpperCase());
			keyInfo.setKeyCiphertext(ISOUtil.byte2hex(key.getKeyBytes()).toUpperCase());
			//解密密文KEY
			Key clearZmk = handler.formDESKey(keyLength, new byte[keyLength/8]);
			SecureDESKey zmk = ssm.encryptToLMK(keyLength, keyType, clearZmk);
			byte[] tmk_zmk = ssm.exportKey(key, zmk);
			byte[] clearKey = handler.decryptData(tmk_zmk, clearZmk);
			Key clearTmk = handler.formDESKey(keyLength, clearKey);
			logger.info("key=" + ISOUtil.byte2hex(clearTmk.getEncoded()).toUpperCase());
			keyInfo.setKeyValue(ISOUtil.byte2hex(clearTmk.getEncoded()).toUpperCase().substring(0, keyLength/4));
		} catch (SMException e) {
			logger.error("create key SMException:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 根据明文密钥生成密文密钥
	 * createKey:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param keyLength
	 * @param keyType
	 * @param keyInfo
	 * @since JDK 1.6
	 */
	private void createKey(short keyLength, String keyType,KeyInfo keyInfo){
		try {
			//获取密文KEY
			Key key = handler.generateDESKey(keyLength);
			handler.formDESKey(keyLength, key.getEncoded());
			logger.info("key=" + ISOUtil.byte2hex(key.getEncoded()).toUpperCase().substring(0, keyLength/4));
			keyInfo.setKeyValue(ISOUtil.byte2hex(key.getEncoded()).toUpperCase());
			SecureDESKey lmk_key = ssm.encryptToLMK(keyLength, keyType, key);
			keyInfo.setCheckValue(ISOUtil.byte2hex(lmk_key.getKeyCheckValue()).toUpperCase());
			keyInfo.setKeyCiphertext(ISOUtil.byte2hex(lmk_key.getKeyBytes()).toUpperCase());
			logger.info("key=" + ISOUtil.byte2hex(lmk_key.getKeyBytes()).toUpperCase());
			logger.info("key=" + ISOUtil.byte2hex(lmk_key.getKeyCheckValue()).toUpperCase());
		} catch (SMException e) {
			logger.error("create key SMException:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 通过LMK解密数据库密钥
	 * decryptByLMK:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param keyInfo
	 * @param keyLength
	 * @param keyType
	 * @since JDK 1.6
	 */
	protected void decryptByLMK(KeyInfo keyInfo,short keyLength, String keyType){
		try {
			SecureDESKey key = new SecureDESKey(keyLength, keyType, keyInfo.getKeyCiphertext(), keyInfo.getCheckValue());
			Key clearZmk = handler.formDESKey(keyLength, new byte[keyLength/8]);
			SecureDESKey zmk = ssm.encryptToLMK(keyLength, keyType, clearZmk);
			byte[] tmk_zmk = ssm.exportKey(key, zmk);
			byte[] clearKey = handler.decryptData(tmk_zmk, clearZmk);
			Key clearTmk = handler.formDESKey(keyLength, clearKey);
			logger.info("key=" + ISOUtil.byte2hex(clearTmk.getEncoded()).toUpperCase().substring(0, keyLength/4));
			keyInfo.setKeyValue(ISOUtil.byte2hex(clearTmk.getEncoded()).toUpperCase().substring(0, keyLength/4));
		} catch (SMException e) {
			logger.error("create key SMException:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 通过KEK对密钥进行加密
	 * decryptByKek:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param hexKek
	 * @param keyInfo
	 * @param keyLength
	 * @param keyType
	 * @since JDK 1.6
	 */
	protected void encryptByKek(String hexKek,KeyInfo keyInfo,short keyLength, String kekType, String keyType){
		try {
			Key clearKek = handler.formDESKey(keyLength, ISOUtil.hex2byte(hexKek));
			SecureDESKey encryptKek = ssm.encryptToLMK(keyLength, kekType, clearKek);
			Key clearKey = handler.formDESKey(keyLength, ISOUtil.hex2byte(keyInfo.getKeyValue()));
			SecureDESKey encryptKey = ssm.encryptToLMK(keyLength, keyType, clearKey);
			keyInfo.setKeyCiphertext(ISOUtil.byte2hex(ssm.exportKey(encryptKey, encryptKek)).toUpperCase().substring(0, keyLength/4));
		} catch (SMException e) {
			logger.error("create key SMException:" + e.getMessage(), e);
		}
	}
	
	/**
	 * 通过LMK加密后导出
	 * encryptToLMK:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param clearKey
	 * @param keyLength
	 * @param kekType
	 * @return
	 * @since JDK 1.6
	 */
	protected String encryptToLMK(String clearKey, short keyLength, String kekType){
		try {
			Key key = handler.formDESKey(SMAdapter.LENGTH_DES3_2KEY, ISOUtil.hex2byte(clearKey));
			SecureDESKey tmkLmk = ssm.encryptToLMK(keyLength, kekType,key);
			return ISOUtil.hexString(tmkLmk.getKeyBytes());
		} catch (JCEHandlerException e) {
			logger.error(e.getMessage(), e);
		} catch (SMException e) {
			logger.error(e.getMessage(), e);
		} 
		return null;
	}
	
	public static void main(String[] args) {
		BaseKeyTool tool = new BaseKeyTool();
		KeyInfo info = new KeyInfo();
		info.setKeyCiphertext("0CFA0FBB232B6E018578B5252694F9B3");
		info.setCheckValue("A59032F5");
		tool.create3DESTMK(info);
	}
}

