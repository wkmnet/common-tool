/**
 * Project Name:tool
 * File Name:RSACoderTest.java
 * Package Name:cn.pay.wkm.common.tool.rsa
 * Date:2015年1月9日上午10:59:18
 * Copyright (c) 2015, wkmnet@foxmail.com All Rights Reserved.
 **/

package cn.pay.wkm.common.tool.rsa;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Map;

import javax.crypto.Cipher;

import org.jpos.iso.ISOUtil;

/**
 * ClassName:RSACoderTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年1月9日 上午10:59:18 <br/>
 * 
 * @author wkmnet@foxmail.com
 * @version
 * @since JDK 1.6
 * @see
 */
public class RSACoderTest {
	private String publicKey;
	private String privateKey;

	public void setUp() throws Exception {
		Map<String, Object> keyMap = RSACoder.initKey();

		publicKey = RSACoder.getPublicKey(keyMap);
		privateKey = RSACoder.getPrivateKey(keyMap);
		System.out.println("公钥: \n\r" + publicKey);
		System.out.println("私钥： \n\r" + privateKey);
	}

	public void test() throws Exception {
		System.err.println("公钥加密——私钥解密");
		String inputStr = "abc";
		byte[] data = inputStr.getBytes();

		byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);

		System.out.println("加密后：\r\n" + ISOUtil.byte2hex(encodedData));

		byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,
				privateKey);

		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);

	}

	public void test001() throws Exception {
		try {
			String privateKeyStr = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCcAm8XdlwHK6udq3P5K4WYK7+qfKOZla+St1CY5/yOWGO8omtbDnufEphrtEn3jQb9iVeXRRDVB2NcrBexBlvn4TRafq+SOtt87CP6FRTo9j/6RKlMwIH1iqpnrTlL9xdAlSJkbx13wTtSYdNTab90TuWRd2riMAyvr3AlrZNKYWeIvn/iNlSGd6NaAZ1hUbcOQUpc2k4Ub+YB2AlBHRxby+6D6Bfcjo2pNZC3giJ+d5aQuJimDi5EmE2cN4F4njuyslByf687rhjQLVWK52Kmbitu3HAhsXXFPyB0gazcA6+xPjvYtGaOtrMx0ByHdfiqAP4fUPsFupxF2p5D/MhdAgMBAAECggEANnVPDDSfyU0AOiiochk0KDtVS5D4lts+j4t4EfghHBtUrXtQ2FdE5fFazXYAv+41u3ke30oXpxVOJQJZvszGdjoRyWkhpnzvMztHD7weMXgMOzOOmaE1fF3sQAai0patQjkM6Vw0QCJur8JAP4TRdSs5Tb8Oy2gUdZtbHXvqx3KPX91+RGHbp8VOsYV8eBPUaCn/mY3vWFz04JnNTm4tpKYlFbAXJzLv/JzAhF9LWPTNA/sLiAs2RwOazi32uovc/pHHXbBPEetU+m0Kl5CU7FLxtE0RdzdhVwLz5Lvx3TanPVZQAm3LoWSzXUlbrfGMKsd7I8QfsWxlggMAHgIqrQKBgQDMXbJFpgsC0l+JqjcEW6ExaIYWBCKwp7ZQzjtvHFc8m13P0JipkRnKfCtAt9h6ZXDNLdY+PZim+m/t1q6ge/mDDkfYcOU4Qr/Pp+O463vliaewcZflYSJAdAaQ55TNtRnb2i9aWiuSbaV1ots3mMXMkbcbE/tfpIJWXzGpXlXslwKBgQDDbQ+Jz3ziee1wE9pU1WOSgU1oedLDgHvMcRg+msTdt4wle832CnCHyHFObj9GFyrR1l4AADO/mLy25st2SCj2QEbROJtzwhrp62xkGAbZXbmlFZvw0Madxi2vUrzWGOiXbIJiDRQUbShUJthqVulxXse2FxTMhXbQ+B9QlDStKwKBgQCPqVYGrACVjTln1uYyBA++YdHQbDvOnzcEetVA+FVC350E14hZuwy8UKk/J33gHKs9MPmoaziCOQ6xzPaZDk2KGWHb/xDDifi4izcD6VYBN9FPsaqrrqqbUHTFLGZobOSuriQXiceTecyUc1VTMevqMIzdViChTU3qVaTdgNk6JQKBgDROS3tiOpXPdpqp5aURHD6JNzPAb4X+LKRynL+c4xZI20zpDx5MewQMxS7t2SekacSSMMSlSTR95/y4VzrIY5ZNa+bx1MGt8VAsv+0mdSbWrCqgVwWki5SNB4tGhN50+kQVOeyOfZJeCQ5E+2Wy73Z1nNSC5wE6s6uIzbDeih7TAoGAJdQ3yv01h3R+3Xp8eI+mIaVb8qJFaRucoCoTxHeqwcMV+q8LIYqwkEoWeDmzqsFw5Cd2gYKpWs8KSNHm5EE7rXFN5yKFqFwhlONi601h/274P2o0EDYzkOWjK+nDwh7DdYvDlGAn7bzCQJQnp2rBb/1uLgNVRqGe8osIukjorRI=";
			String publicKeyStr = "VsZzjxNRG2soVB5/0lX056F+NafGJsO4/zF1zQH1m3N/iJwuMKFRu4w7wyiJ3fNegTEXDYjfcQTN8+dnLHwBc2jw/08CvDct5Tc87ANgJix2iHFsCOL2B8dVJW2/ifmKhr+v5KYgk7Af0opXeDGO9sr/zeodcjlkJoWch8NcS8AjNFZ2aHjORfSkupXO858mM94On91miGwl3vzU7hbCnjdO3QGG1Mz8BncsaLyTBR/dTMrH1huoKjP3lew+upkGha9rZwBRhO64PzmDd23ub0Ti5xBTUx4mgke6wWqTXsZ96I0Pzc8/KXBS+j6ZWSgPMGb7uJp8yAkPNemaJO+Kgg==";
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] base64 = RSACoder.decryptBASE64(privateKeyStr);
			PrivateKey myKey = keyFactory
					.generatePrivate(new PKCS8EncodedKeySpec(base64));
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, myKey);
			byte[] pin = cipher.doFinal(RSACoder.decryptBASE64(publicKeyStr));
			System.out.println("pin===" + ISOUtil.dumpString(pin));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test003() throws Exception {
		try {
			String str = "123456";
			String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAumMi+4vy8kjEf2EtgSZ0oHQY7g6gkZs2XpEXrGNWOZvJX66wcAaF1zyd5VC6t2SUrtLJbjWkh/XBB72cI/EJZDtLgzOTQMJLzJ6RKDkL5+x+lXTYWWGLeh0yzSDa2WqEKKyAVVxibVPoGEdrUnoOhVeA7u9gPOzcglC8TB5rNf217BamA981g6y5W5n/Mo0enY7r8PESaNMOBQG3Jwm86uCItzxSqZXoCbw+HKSid79yrwaGrGafHCSWuwVxfivw81z/mTa94oRJJhGKkA7bfq821sKbyQFcPXp5auHLoOG1dAJb73Gdna6CwTTdnHU7F1JiZuk+C2x0P1UfpkCicQIDAQAB";
			byte[] encodedData = RSACoder.encryptByPublicKey(str.getBytes(),
					publicKeyStr);
			System.out.println("pin===" + ISOUtil.byte2hex(encodedData));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test002() throws Exception {
		String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnAJvF3ZcByurnatz+SuFmCu/qnyjmZWvkrdQmOf8jlhjvKJrWw57nxKYa7RJ940G/YlXl0UQ1QdjXKwXsQZb5+E0Wn6vkjrbfOwj+hUU6PY/+kSpTMCB9YqqZ605S/cXQJUiZG8dd8E7UmHTU2m/dE7lkXdq4jAMr69wJa2TSmFniL5/4jZUhnejWgGdYVG3DkFKXNpOFG/mAdgJQR0cW8vug+gX3I6NqTWQt4IifneWkLiYpg4uRJhNnDeBeJ47srJQcn+vO64Y0C1Viudipm4rbtxwIbF1xT8gdIGs3AOvsT472LRmjrazMdAch3X4qgD+H1D7BbqcRdqeQ/zIXQIDAQAB";
		byte[] temp = RSACoder.decryptBASE64(publicKey);
		System.out.println(ISOUtil.byte2hex(temp));
	}

	public void testSign() throws Exception {
		System.err.println("私钥加密——公钥解密");
		String inputStr = "sign";
		byte[] data = inputStr.getBytes();

		byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);

		byte[] decodedData = RSACoder
				.decryptByPublicKey(encodedData, publicKey);

		String outputStr = new String(decodedData);
		System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);

		System.err.println("私钥签名——公钥验证签名");
		// 产生签名
		String sign = RSACoder.sign(encodedData, privateKey);
		System.err.println("签名:\r" + sign);

		// 验证签名
		boolean status = RSACoder.verify(encodedData, publicKey, sign);
		System.err.println("状态:\r" + status);

	}

	public void testPublicBase64() throws Exception {
		String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp9tQc5yJNQAJ5yWt5zQWMAewPPwwkqAiLuMr2XZgB1tBGH9F/vxcGA9VauxRXaPE2EX3fILZnTkuXt6j/fj+nX2aP+mslXmHn7fH4cKTeQNp0N/45mhtCyUZK/fhNewISvX5pURm7tXSegpBf4/ODGnCY8fCyrDxDN3r0TUnQ5stOc/1mX/H4bR/9OBVqOpxfWBngxRd36drVOeq0LvHgNitC8RhL3RSUmqGd2c0DNUq2mC1Uk6mreNN0hoeMaj3c/mfkSgmhCjkGGL/n/x4UOke4nv/EFDtbzSqgguuLGGG12RiMhCZheoHAdL+3Pm+zITps3VdwaQVdKOs5IIrOwIDAQAB";
		System.out.println(ISOUtil.byte2hex(RSACoder.decryptBASE64(publicKey)));
	}
}
