/**
 * Project Name:tool
 * File Name:TLVUtil.java
 * Package Name:cn.pay.wkm.common.tool.tlv
 * Date:2015年1月12日下午1:47:53
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.tlv;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;
import org.jpos.tlv.TLVList;

/**
 * ClassName:TLVUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年1月12日 下午1:47:53 <br/>
 * @author   wkmnet@foxmail.com
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TLVUtil {

	/**
	 * 解析TLV报文格式
	 * formatTlv:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param tlv
	 * @return
	 * @since JDK 1.6
	 */
	public static TLVList formatTlv(String tlv){
		return formatTlv(ISOUtil.hex2byte(tlv));
	}
	
	/**
	 * 解析TLV报文格式
	 * formatTlv:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @param tlv
	 * @return
	 * @since JDK 1.6
	 */
	public static TLVList formatTlv(byte[] tlv){
		TLVList list = new TLVList();
		try {
			list.unpack(tlv);
		} catch (ISOException e) {
			e.printStackTrace();
		}
		return list;
	}
}

