package DES;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES加密介绍 DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
 * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
 * 24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现 。
 * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
 */
public class DESA {
	public DESA() {
	}

	// 测试
	public static void main(String args[]) {
		// 待加密内容
		String str = "1234567890hah3a就骄傲骄傲就骄傲骄傲";
		// 密码，长度要是8的倍数
		String password = "1234abcd123";

		byte[] result = DESA.encrypt(str.getBytes(), password);
		for(int i = 0; i<result.length;i++){
			System.out.print(result[i]+ "=");
		}
		System.out.println("\n" +result.length + "--" + password.getBytes().length);
		String str2 = bytesToHexString(result);
		System.out.println(str2);
		byte[] sss = hexStringToByte(str2);
		for(int i = 0; i<sss.length;i++){
			System.out.print(sss[i]+ "=");
		}
		System.out.println("\n加密后：" + byte2String(result));
		// 直接将如上内容解密
		try {
			byte[] decryResult = DESA.decrypt(hexStringToByte(str2), password);
			System.out.println("解密后：" + new String(decryResult));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public static final String bytesToHexString(byte[] bArray) {   
	    StringBuffer sb = new StringBuffer(bArray.length);   
	    String sTemp;   
	    for (int i = 0; i < bArray.length; i++) {   
	     sTemp = Integer.toHexString(0xFF & bArray[i]);   
	     if (sTemp.length() < 2)   
	      sb.append(0);   
	     sb.append(sTemp.toUpperCase());   
	    }   
	    return sb.toString();   
	}  
	
	public static byte[] String2byte(String b) {
	       if ((b.length() % 2) != 0)
	           throw new IllegalArgumentException("���Ȳ���ż��");
	       byte[] b2 = new byte[b.length() / 2];
	       for (int n = 0; n < b.length(); n += 2) {
	           String item = new String(b.getBytes(), n, 2);
	           b2[n / 2] = (byte) Integer.parseInt(item, 16);
	       }
	       return b2;
	   }
	
	public static byte[] hexStringToByte(String hex) {   
	    int len = (hex.length() / 2);   
	    byte[] result = new byte[len];   
	    char[] achar = hex.toCharArray();   
	    for (int i = 0; i < len; i++) {   
	     int pos = i * 2;   
	     result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));   
	    }   
	    return result;   
	}  
	
	private static byte toByte(char c) {   
	    byte b = (byte) "0123456789ABCDEF".indexOf(c);   
	    return b;   
	}  
	
	private static String byte2String(byte[] b) {
	    String hs="";
	    String stmp="";
	    for(int n=0;n<b.length;n++){
	        stmp=(java.lang.Integer.toHexString(b[n]&0XFF));
	        if(stmp.length() == 1)
	            hs+=hs+"0"+stmp;
	        else
	            hs=hs+stmp;
	    }
	    return hs.toUpperCase();
	}

	/**
	 * 加密
	 * 
	 * @param datasource
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 */
	public static byte[] encrypt(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			// 用密匙初始化Cipher对象
			System.out.println("key is " + bytesToHexString(securekey.getEncoded()) + "--" + securekey.getEncoded().length);
			for(int i = 0 ; i < securekey.getEncoded().length; i++) {
				System.out.println(String.valueOf(securekey.getEncoded()[i]));
			}
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, String password) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}
}