package util.jasypt;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.util.text.AES256TextEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

/**
 * @author gewx jasypt PBE基于口令加密 官方示例地址:
 *         http://www.jasypt.org/encrypting-texts.html
 **/
public final class JasyptUtils {

	private static final String myEncryptionPassword = "123456"; // salt 随机盐

	/**
	 * @author gewx 最易使用:基本文本加密器使用类, BasicTextEncryptor thread-safe
	 **/
	public static void basic(String encryptText) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(myEncryptionPassword);
		String myEncryptedText = textEncryptor.encrypt(encryptText);
		System.out.println("加密后文本: " + myEncryptedText);
		String plainText = textEncryptor.decrypt(myEncryptedText);
		System.out.println("解密后文本: " + plainText);
	}

	/**
	 * @author gewx 更安全:强文本加密器使用类具有更安全(但较慢!)算法 (您可能需要下载并安装 Java 加密扩展 (JCE)
	 *         无限强度权限策略文件才能使用它), StrongTextEncryptor thread-safe
	 **/
	public static void basicStrong(String encryptText) {
		StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
		textEncryptor.setPassword(myEncryptionPassword);
		String myEncryptedText = textEncryptor.encrypt(encryptText);
		System.out.println("加密后文本: " + myEncryptedText);
		String plainText = textEncryptor.decrypt(myEncryptedText);
		System.out.println("解密后文本: " + plainText);
	}

	/**
	 * @author gewx 更安全:AES256TextEncryptor
	 *         使用具有更安全算法的类:PBEWithHMACSHA512AndAES_256(至少需要 Java 8 才能使用它),
	 *         AES256TextEncryptor thread-safe
	 * 
	 **/
	public static void basicAES256(String encryptText) {
		AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
		textEncryptor.setPassword(myEncryptionPassword);
		String myEncryptedText = textEncryptor.encrypt(encryptText);
		System.out.println("加密后文本: " + myEncryptedText);
		String plainText = textEncryptor.decrypt(myEncryptedText);
		System.out.println("解密后文本: " + plainText);
	}

	/**
	 * @author gewx 自定义安全策略配置, StandardPBEStringEncryptor thread-safe
	 **/
	public static void basicStandard(String encryptText) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(myEncryptionPassword); // we HAVE TO set a password
		encryptor.setAlgorithm("PBEWithHMACSHA512AndAES_256"); // optionally set the algorithm
		encryptor.setIvGenerator(new RandomIvGenerator()); // for PBE-AES-based algorithms, the IV generator is
															// MANDATORY

		String myEncryptedText = encryptor.encrypt(encryptText);
		System.out.println("加密后文本: " + myEncryptedText);
		String plainText = encryptor.decrypt(myEncryptedText); // myText.equals(plainText)
		System.out.println("解密后文本: " + plainText);
	}

	/**
	 * @author gewx 多核环境下使用池化提升性能, PooledPBEStringEncryptor thread-safe
	 **/
	public static void basicPoolPBE(String encryptText) {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		encryptor.setPoolSize(4); // This would be a good value for a 4-core system
		encryptor.setPassword(myEncryptionPassword);
		encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
		String encryptedText = encryptor.encrypt(encryptText);
		System.out.println("加密后文本: " + encryptedText);
		String plainText = encryptor.decrypt(encryptedText);
		System.out.println("解密后文本: " + plainText);
	}

	/**
	 * @author gewx 加密元数据配置
	 **/
	public static SimpleStringPBEConfig cryptor(String password) {
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword(password);
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		return config;
	}

	public static void main(String[] args) {
		basicStandard("你好,Jasypt!");
	}
}
