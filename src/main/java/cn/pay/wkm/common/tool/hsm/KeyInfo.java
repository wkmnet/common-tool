/**
 * Project Name:tool
 * File Name:KeyInfo.java
 * Package Name:cn.pay.wkm.common.tool.hsm
 * Date:2015年1月16日下午3:37:13
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.hsm;
/**
 * ClassName:KeyInfo <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年1月16日 下午3:37:13 <br/>
 * @author   wkmnet@foxmail.com
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class KeyInfo {
	
	/**
	 * 密钥明文
	 */
	private String keyValue = null;
	
	/**
	 * 密钥校验值
	 */
	private String checkValue = null;
	
	/**
	 * 密钥密文
	 */
	private String keyCiphertext = null;

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getCheckValue() {
		return checkValue;
	}

	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}

	public String getKeyCiphertext() {
		return keyCiphertext;
	}

	public void setKeyCiphertext(String keyCiphertext) {
		this.keyCiphertext = keyCiphertext;
	}

	@Override
	public String toString() {
		return "KeyInfo [keyValue=" + keyValue + ", checkValue=" + checkValue
				+ ", keyCiphertext=" + keyCiphertext + "]";
	}
}

