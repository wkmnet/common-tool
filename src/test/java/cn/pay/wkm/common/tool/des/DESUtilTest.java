/**
 * Project Name:tool
 * File Name:DESUtilTest.java
 * Package Name:cn.pay.wkm.common.tool.des
 * Date:2015年1月9日下午6:25:51
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.des;

import org.jpos.iso.ISOUtil;
import org.junit.Test;

/**
 * ClassName:DESUtilTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年1月9日 下午6:25:51 <br/>
 * @author   wkmnet@foxmail.com
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class DESUtilTest {

	/**
	 * 测试单DES加密
	 * desEncrypt:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @since JDK 1.6
	 */
	@Test
	public void desEncrypt(){
		byte[] clearData = ISOUtil.hex2byte("9999999999999999");
		byte[] key = ISOUtil.hex2byte("1111111111111111");
		byte[] clear = ISOUtil.hex2byte("0000000000000000");
		byte[] result = DESUtil.desEncrypt(clearData, key);
		byte[] checkValue = DESUtil.desEncrypt(clear, clearData);
		
		byte[] res = new byte[16];
		System.out.println(ISOUtil.byte2hex(result).toUpperCase());
		if(result.length > 8){
			System.arraycopy(result, 0, res,0, 8);
		}
		for(int i=8;i < res.length;i++){
			res[i] = (byte)0xff;
		}
		System.out.println(ISOUtil.byte2hex(res).toUpperCase());
		System.out.println(ISOUtil.byte2hex(checkValue).substring(0, 8).toUpperCase());
		byte[] clearKey = DESUtil.desDecrypt(result, key);
		System.out.println(ISOUtil.byte2hex(clearKey).toUpperCase());
	}
	
}

