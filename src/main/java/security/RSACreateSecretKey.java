package security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import exception.SecurityException;

/**
 * @author gewx RSA算法秘钥创建类
 * **/
public final class RSACreateSecretKey {

	private static final String ALGORITHM = "RSA";  //加密算法
	
	private static final String PUBLICKEY = "publicKey";
	
	private static final String PRIVATEKEY = "privateKey";

	private static final Map<String,String> SECRETMAP = new HashMap<String,String>();

	//init secretKey
	static {
		try {
			KeyPairGenerator rsaGenerator = KeyPairGenerator.getInstance(ALGORITHM);
			rsaGenerator.initialize(1024);
			KeyPair keyPair = rsaGenerator.genKeyPair();
			PublicKey rsaPublicKey = keyPair.getPublic();
			PrivateKey rsaPrivateKey = keyPair.getPrivate();
			String publicKeyStr = Base64.encodeBase64String(rsaPublicKey.getEncoded());
			String privateKeyStr = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
			SECRETMAP.put(PUBLICKEY, publicKeyStr);
			SECRETMAP.put(PRIVATEKEY, privateKeyStr);			
		} catch (NoSuchAlgorithmException e) {
			throw new SecurityException("RSA秘钥初始化失败!message:" + e.getMessage());
		}
	}
	
	/**
	 * @author gewx 获取秘钥
	 * @return byte[] 秘钥数组
	 * **/
	public static byte[] getSecretKey(String key) {
		if (StringUtils.isBlank(key)) {
			throw new java.lang.IllegalArgumentException("获取秘钥枚举值错误!");
		}
		return Base64.decodeBase64(SECRETMAP.get(key));
	}
	
	/**
	 * @author 创建公钥/私钥
	 * @throws NoSuchAlgorithmException 
	 * **/
	public static Map<String,String> createSecret() throws NoSuchAlgorithmException {
		KeyPairGenerator rsaGenerator = KeyPairGenerator.getInstance(ALGORITHM);
		rsaGenerator.initialize(1024);
		KeyPair keyPair = rsaGenerator.genKeyPair();
		PublicKey rsaPublicKey = keyPair.getPublic();
		PrivateKey rsaPrivateKey = keyPair.getPrivate();
		String publicKeyStr = Base64.encodeBase64String(rsaPublicKey.getEncoded());
		String privateKeyStr = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
		Map<String,String> secretMap = new HashMap<String,String>();
		secretMap.put(PUBLICKEY, publicKeyStr);
		secretMap.put(PRIVATEKEY, privateKeyStr);
		return secretMap;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		createSecret();
	}
	
}
