package security;

import org.apache.commons.codec.binary.Base64;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * JAVA加密与解密,Demo示例程序  
 * added by gewx 2017.12.30 
 * **/
public final class DESUtils {

	private static byte [] secret = null; //DES秘钥, 8位校验位、
	
	//private static byte [] secret = "我爱中国".getBytes(); //此种方式也可以,8位校验位.
	
	private static final String ALGORITHM = "DES";  //加密算法
	
	private DESUtils(){
		
	}
	
	//初始化DES对称秘钥 
	static {
		try {
			KeyGenerator desGenerator = KeyGenerator.getInstance(ALGORITHM);
			SecretKey secretKey =  desGenerator.generateKey(); //获得秘钥对象(对称加密秘钥)
			secret = secretKey.getEncoded(); //获得秘钥编码
		} catch (NoSuchAlgorithmException e) {
			throw new SecurityException("DES秘钥初始化异常,message"+e.getMessage());
		}
	}
	
	/**
	 *@author gewx DES加密 
	 *@param encryptStr - 待加密字符
	 *@return 加密后字符 
	 * **/
	public static String encrypt(final String encryptStr){
		try {
			//DESKeySpec API 解读:使用 key 中的前 8 个字节作为 DES 密钥的密钥内容,8位校验位
			DESKeySpec desSpec = new DESKeySpec(secret);//还原DES秘钥对象.本处亦可直接引用init静态块当中的secretKey对象
			SecretKeyFactory secretFactory = SecretKeyFactory.getInstance(ALGORITHM);// 对称秘钥工厂类
			SecretKey  secretKey = secretFactory.generateSecret(desSpec);
		    SecureRandom random = new SecureRandom();
			Cipher cipher = Cipher.getInstance(ALGORITHM);//初始化加密/解密核心组件
			cipher.init(Cipher.ENCRYPT_MODE,secretKey,random);
			//return Base64.encodeBase64String((cipher.doFinal(encryptStr.getBytes())));//普通BASE64
			return Base64.encodeBase64URLSafeString(cipher.doFinal(encryptStr.getBytes()));//URL编码的BASE64
		} catch (Exception e) {
			throw new SecurityException("DES加密异常,message:"+e.getMessage());
		}
	}
	
	/**
	 *@author gewx DES解密 
	 *@param decryptStr - 待解密字符
	 *@return 解密后字符 
	 * **/
	public static String decrypt(final String decryptStr){
		try{
			DESKeySpec desSpec = new DESKeySpec(secret); 
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			SecretKey secretKey = secretKeyFactory.generateSecret(desSpec);
			SecureRandom random = new SecureRandom();
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secretKey,random);
			return new String(cipher.doFinal(Base64.decodeBase64(decryptStr.getBytes())));
		}catch (Exception e) {
			throw new SecurityException("DES解密异常,message:"+e.getMessage());
		}
	} 
	

	public static void main(String[] args) {
		final String demoStr = "我爱中华民族,我家在镇江!A-Z";
		String encryptStr = DESUtils.encrypt(demoStr);
		System.out.println("加密字符:"+encryptStr);
		String decryptStr = DESUtils.decrypt(encryptStr);
		System.out.println("解密字符:"+decryptStr);
	}

}
