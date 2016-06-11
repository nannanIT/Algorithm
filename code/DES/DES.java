package DES;

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

public class DES {
	
	public static final String DES = "DES";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String encrypt = encrypt("1", "1234abcd");
		System.out.println(encrypt);
		String decrypt = decrypt(encrypt, "1234abcd");
		System.out.println(decrypt);
	}
	
	public static byte[] encrypt(byte[] src, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFac = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFac.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		return cipher.doFinal(src);
	}
	
	public final static String encrypt(String password, String key) {
	    try {
	        return byte2String((encrypt(password.getBytes(), key.getBytes())));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
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
    *
    * @param src ����Դ
    * @param key ��Կ�����ȱ�����8�ı���
    * @return
    * @throws Exception
    */
   public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
       // DES�㷨Ҫ����һ�������ε������Դ
       SecureRandom sr = new SecureRandom();
       // ��ԭʼ�ܳ����ݴ���һ��DESKeySpec����
       DESKeySpec dks = new DESKeySpec(key);
       // ����һ���ܳ׹�����Ȼ��������DESKeySpec����ת����һ��SecretKey����
       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
       SecretKey securekey = keyFactory.generateSecret(dks);
       // Cipher����ʵ����ɽ��ܲ���
       Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
       // ���ܳ׳�ʼ��Cipher����
       cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

       // ��ʽִ�н��ܲ���
       return cipher.doFinal(src);
   }

   public final static String decrypt(String data, String key) {
       try {
    	   byte[] bs = (data.getBytes());
    	   System.out.println("length is " + bs.length);
           return new String(decrypt((data.getBytes()), key.getBytes()));
       } catch (Exception e) {
           e.printStackTrace();
       }
       return null;
   }

   public static byte[] String2byte(byte[] b) {
       if ((b.length % 2) != 0)
           throw new IllegalArgumentException("���Ȳ���ż��");
       byte[] b2 = new byte[b.length / 2];
       for (int n = 0; n < b.length; n += 2) {
           String item = new String(b, n, 2);
           b2[n / 2] = (byte) Integer.parseInt(item, 16);
       }
       return b2;
   }

}
