/**
 * Project Name:tool
 * File Name:DESUtil.java
 * Package Name:cn.pay.wkm.common.tool.des
 * Date:2015年1月9日下午6:03:46
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.des;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * ClassName:DESUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年1月9日 下午6:03:46 <br/>
 * @author   wkmnet@foxmail.com
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DESUtil {

	/**
	 * DES加密总结
	 * Creates a new instance of DESUtil.
	 *
	 */
	private DESUtil(){}
	
	/**
	 * 单DES加密
	 * desEncrypt:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * 说明:
	 * 获取Cipher对象的时候一定要写成
	 * Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
     * 不要写成
     * Cipher cipher = Cipher.getInstance("DES");
     * 否则解密的时候会报错：
     * Given final block not properly padded
     *
     * 原因是Cipher cipher = Cipher.getInstance("DES");与Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");等同，填充方式错误，加密的时候会得到16长度的字节数组。
     * 
     * 转载地址:http://lhc1986.iteye.com/blog/1730477
	 *
	 * @author wkmnet@foxmail.com
	 * @param clearData
	 * @param key
	 * @return
	 * @since JDK 1.6
	 */
	public static byte[] desEncrypt(byte[] clearData,byte[] key){
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec	dks = new DESKeySpec(key);
			
			// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			
			SecretKey securekey = keyFactory.generateSecret(dks);
			
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			
			// 用密钥初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			
			return cipher.doFinal(clearData);
		} catch (InvalidKeyException e){
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		} catch (InvalidKeySpecException e){
			e.printStackTrace();
		} catch (NoSuchPaddingException e){
			e.printStackTrace();
		} catch (BadPaddingException e){
			e.printStackTrace();
		} catch (IllegalBlockSizeException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 单DES解密
	 * desDecrypt:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param secretData
	 * @param key
	 * @return
	 * @since JDK 1.6
	 */
	public static byte[] desDecrypt(byte[] secretData, byte[] key) {
		try {
	        // DES算法要求有一个可信任的随机数源  
	        SecureRandom random = new SecureRandom();  
	        // 创建一个DESKeySpec对象  
	        DESKeySpec desKey = new DESKeySpec(key);  
	        // 创建一个密匙工厂  
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
	        // 将DESKeySpec对象转换成SecretKey对象  
	        SecretKey securekey = keyFactory.generateSecret(desKey);  
	        // Cipher对象实际完成解密操作  
	        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");  
	        // 用密匙初始化Cipher对象  
	        cipher.init(Cipher.DECRYPT_MODE, securekey, random);  
	        // 真正开始解密操作  
	        return cipher.doFinal(secretData); 
		} catch (InvalidKeyException e){
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		} catch (InvalidKeySpecException e){
			e.printStackTrace();
		} catch (NoSuchPaddingException e){
			e.printStackTrace();
		} catch (BadPaddingException e){
			e.printStackTrace();
		} catch (IllegalBlockSizeException e){
			e.printStackTrace();
		}
		return null;
	} 
	
}

