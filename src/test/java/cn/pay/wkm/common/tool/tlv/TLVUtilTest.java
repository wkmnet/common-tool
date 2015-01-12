/**
 * Project Name:tool
 * File Name:TLVUtilTest.java
 * Package Name:cn.pay.wkm.common.tool.tlv
 * Date:2015年1月12日下午2:51:11
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.tlv;

import java.util.List;

import org.jpos.iso.ISOUtil;
import org.jpos.tlv.TLVMsg;
import org.junit.Test;

/**
 * ClassName:TLVUtilTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年1月12日 下午2:51:11 <br/>
 * @author   wkmnet@foxmail.com
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TLVUtilTest {
	
	/**
	 * 解析TLV报文
	 * formatTlv:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 *
	 * @author wkmnet@foxmail.com
	 * @since JDK 1.6
	 */
	@Test
	public void formatTlv(){
		String tlv = "9F26089D5425021C5DC4CA9F2701409F100807000103602400019F3704DC7B05399F36020122950540800400009A031411259C01009F02060000000018005F2A02015682025C009F1A0201569F03060000000000009F33036040C89F34030203009F3501229F1E0830333338373639378408A0000003330101019F090200209F4104000000004F08A0000003330101019B02E800500A424354432044454249545F340104";
		List<TLVMsg> msgs = TLVUtil.formatTlv(tlv).getTags();
		for(TLVMsg msg:msgs){
			System.out.println(ISOUtil.byte2hex(ISOUtil.int2byte(msg.getTag())));
			System.out.println(ISOUtil.byte2hex(msg.getValue()));
		}
	}

}

