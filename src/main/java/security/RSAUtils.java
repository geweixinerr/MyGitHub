package security;

import org.apache.commons.codec.binary.Base64;

import exception.SecurityException;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * JAVA加密与解密,Demo示例程序  
 * added by gewx 2017.12.30 
 * **/
public final class RSAUtils {

	private static final String ALGORITHM = "RSA";  //加密算法
	
	private static final String SIGN_ALGORITHM = "MD5WithRSA";  //数字签名算法

	private static final String PUBLICKEY = "publicKey";
	
	private static final String PRIVATEKEY = "privateKey";
	
	private RSAUtils() {

	}
	
	/**
	 *@author gewx RSA加密 
	 *@param encryptStr - 待加密字符[PS:如加密字符过长,可以采取分段字符加密的方式逻辑分割处理]
	 *@return 加密后字符 
	 * **/
    public static String encrypt(final String encryptStr) {
    	try {
	    		//------------------秘钥数组转换秘钥对象[start]--------------------------
	        	PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(RSACreateSecretKey.getSecretKey(PRIVATEKEY));
	        	KeyFactory rsaFactory = KeyFactory.getInstance(ALGORITHM);
	        	PrivateKey privateKey = rsaFactory.generatePrivate(pkcs8);
	        	//------------------秘钥数组转换秘钥对象[end]--------------------------
	        	SecureRandom random = new SecureRandom();
	        	Cipher cipher = Cipher.getInstance(ALGORITHM);
	        	cipher.init(Cipher.ENCRYPT_MODE, privateKey, random);
	    		return Base64.encodeBase64URLSafeString(cipher.doFinal(encryptStr.getBytes()));//URL编码的BASE64
		} catch (Exception e) {
			throw new SecurityException("RSA加密异常,message:"+e.getMessage());
		}
    }

	/**
	 *@author gewx RSA解密 
	 *@param encryptStr - 待解密字符
	 *@return 解密后字符 
	 * **/
    public static String decrypt(final String decryptStr) {
    	try {
	    		X509EncodedKeySpec x509 = new X509EncodedKeySpec(RSACreateSecretKey.getSecretKey(PUBLICKEY));
	    		KeyFactory rsaFactory = KeyFactory.getInstance(ALGORITHM);
	    		PublicKey publicKey = rsaFactory.generatePublic(x509);
	    		
	        	SecureRandom random = new SecureRandom();
	    		Cipher cipher = Cipher.getInstance(ALGORITHM);
	    		cipher.init(Cipher.DECRYPT_MODE, publicKey,random);
				return new String(cipher.doFinal(Base64.decodeBase64(decryptStr.getBytes())));
		} catch (Exception e) {
			throw new SecurityException("RSA解密异常,message:"+e.getMessage());
		}
    }
    
    /**
     * @author gewx 生成数字签名
     * **/
    public static String generateSign(final String signStr) {
    	try {
        	  PKCS8EncodedKeySpec pkcs8 = new PKCS8EncodedKeySpec(RSACreateSecretKey.getSecretKey(PRIVATEKEY));
        	  KeyFactory rsaFactory = KeyFactory.getInstance(ALGORITHM);
        	  PrivateKey privateKey = rsaFactory.generatePrivate(pkcs8);
        	
	          Signature signObject = Signature.getInstance(SIGN_ALGORITHM);
	          signObject.initSign(privateKey);
	          signObject.update(signStr.getBytes());
	          return Base64.encodeBase64URLSafeString(signObject.sign());
    	} catch (Exception e) {
			 throw new SecurityException("RSA生成签名异常,message:"+e.getMessage());
    	}
    }
    
    /**
     * @author gewx 数字签名验签
     * @param signStr 数字签名字符
     * @param verifyStr 验签字符
     * **/
    public static boolean verifySign(final String signStr,final String verifyStr) {
    	try{
	    	 X509EncodedKeySpec x509 = new X509EncodedKeySpec(RSACreateSecretKey.getSecretKey(PUBLICKEY));
	    	 KeyFactory rsaFactory = KeyFactory.getInstance(ALGORITHM);
	    	 PublicKey publicKey = rsaFactory.generatePublic(x509);
	    	 
	         Signature signObject = Signature.getInstance(SIGN_ALGORITHM);
	         signObject.initVerify(publicKey);
	         signObject.update(verifyStr.getBytes());
	         return signObject.verify(Base64.decodeBase64(signStr));        	
    	} catch (Exception e) {
			 throw new SecurityException("RSA验签异常,message:"+e.getMessage());
    	}
    }
    
    public static void main(String[] args) {
		final String demoStr = "我爱中华民族,我家在镇江!A-Z";
		String encryptStr = RSAUtils.encrypt(demoStr);
		System.out.println("加密字符:"+encryptStr);
		String decryptStr = RSAUtils.decrypt(encryptStr);
		System.out.println("解密字符:"+decryptStr);
		
		String signStr = RSAUtils.generateSign(demoStr);
		System.out.println("数字签名字符:"+signStr);
		boolean verifyBool = RSAUtils.verifySign(signStr,demoStr);
		System.out.println("验签结果:"+verifyBool);
	}
    
}
