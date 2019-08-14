package util;

import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import static util.StringUtil.getString;

/**
 * @author gewx MD5摘要校验类
 * **/
public final class MD5CheckSumUtils {

	private static final String KEY = "geweixin"; //加密key前缀
	
	/**
	 * @author gewx checkSum
	 * @param signature 客户端传递校验签名 , params 客户端请求参数, args 限定参数数组
	 * **/
	public static boolean checkSum(String signature, Map<String,?> params, String[] args) {
		StringBuilder sb = new StringBuilder(256);
		sb.append(KEY);
		for (String val : args) {
			String value = getString(params.get(val));
			sb.append(value);
		}
		String _signature = DigestUtils.md5Hex(sb.toString());
		return signature.equals(_signature);
	}
	
	/**
	 * @author gewx 生成MD5签名摘要
	 * **/
	public static String getSignature(String val) {
		StringBuilder sb = new StringBuilder();
		sb.append(KEY);
		sb.append(val);
		return DigestUtils.md5Hex(sb.toString());
	}
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		sb.append(KEY);
		
		String signature = DigestUtils.md5Hex(sb.toString());
		System.out.println(signature);
	}
}
