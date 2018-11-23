package util;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import exception.ConvertException;

/**
 * 字符串常用操作类
 */
public final class StringUtil { 

	/**
	 * @author gewx
	 * @param Object 需要转换的对象
	 * @return string
	 * **/
	public static String getStr(Object obj) {
		if (obj == null) {
			return null;
		} else {
			return StringUtils.trimToEmpty(obj.toString());
		}
	}

	/**
	 * @author gewx
	 * @param Object 需要转换的对象
	 * @return map
	 * **/
	public static Map<String, String> ConvertPojoToMap(Object c) {
		Map<String, String> dataMap = new HashMap<String, String>();
		try {
			Field[] field = c.getClass().getDeclaredFields();
			for (Field f : field) {
				f.setAccessible(true);// 打通权限,指示反射的对象在使用时应该取消 Java 语言访问检查
				dataMap.put(f.getName(),StringUtils.trimToEmpty(getStr(f.get(c))));
			}
		} catch (Exception ex) {
			throw new ConvertException("转换异常:" + ex.getMessage());
		}
		return dataMap;
	}

	/**
	 * @author gewx
	 * @param 需要转换的对象 c,目标pojo
	 * @void
	 * **/
	@SuppressWarnings("rawtypes")
	public static void ConvertMapToPojo(Object c, Map<String, Object> params) {
		try {
			String[] keyArray = params.keySet().toArray(new String[] {});
			Class[] parameterTypes = new Class[1];// 调用参数数量
			parameterTypes[0] = String.class;
			for (String key : keyArray) {
				Method m = c.getClass().getMethod(
						"set" + firstStrUpperCase(key), parameterTypes);
				m.invoke(c, StringUtil.getStr(params.get(key)));
			}
		} catch (Exception ex) {
			throw new ConvertException("转换异常:" + ex.getMessage());
		}
	}

	/**
	 * @author gewx  验证map设定非空字段是否为空
	 * @param params
	 *            :目标map,args:校验数组
	 * @return boolean
	 * **/
	public static boolean isEmptyMap(Map<String, String> params, String[] args) {
		if (params == null || args == null || params.isEmpty()
				|| args.length == 0) {
			return true;
		}

		boolean bool = false;
		for (String arg : args) {
			if (StringUtils.isBlank(getStr(params.get(arg)))) {
				bool = true;
				break;
			}
		}
		return bool;
	}

	/**
	 * @author gewx 2015.12.3 验证map所有key非NULL,非空字符
	 * @param params,目标map
	 * @return boolean
	 * **/
	public static boolean isEmptyMap(Map<String, String> params) {
		if (params == null || params.isEmpty()) {
			return true;
		}

		boolean bool = false;
		Iterator<String> it = params.keySet().iterator();
		while (it.hasNext()) {
			if (StringUtils.isBlank(getStr(params.get(it.next())))) {
				bool = true;
				break;
			}
		}
		return bool;
	}

	/**
	 * @author gewx  Map对象key值必须至少一个是非空项
	 * @param params,目标map
	 * @return boolean
	 * **/
	public static boolean isOneMoreMap(Map<String, String> params) {
		if (params == null || params.isEmpty()) {
			return false;
		}

		boolean bool = false;
		Iterator<String> it = params.keySet().iterator();
		while (it.hasNext()) {
			if (StringUtils.isNotBlank(getStr(params.get(it.next())))) {
				bool = true;
				break;
			}
		}
		return bool;
	}

	/**
	 * @author gewx 校验Map对象中args[key数组]必须存在至少一个是非空项
	 * @param params
	 *            :目标map
	 * @return boolean
	 * **/
	public static boolean isOneMoreMap(Map<String, String> params, String[] args) {
		if (params == null || args == null || params.isEmpty()
				|| args.length == 0) {
			return false;
		}

		boolean bool = false;
		for (String arg : args) {
			if (StringUtils.isNotBlank(getStr(params.get(arg)))) {
				bool = true;
				break;
			}
		}
		return bool;
	}

	/**
	 * @author gewx 清理Map,只保留入参数组当中的key-value
	 * @param params,目标map ,入参数组 args 
	 * @return void
	 * **/
	public static void cleanMap(Map<String, String> params, String[] args,
			Boolean bool) {
		if (params != null && !params.isEmpty()) {
			List<String> array = Arrays.asList(args);
			String[] keyArray = params.keySet().toArray(new String[] {});
			for (String arg : keyArray) {
				if (bool) {
					if (array.contains(arg)) {
						params.remove(arg);
					}
				} else {
					if (!array.contains(arg)) {
						params.remove(arg);
					}
				}
			}
		}
	}

	public static void printPOJO(Object c) {
		if (c != null) {
			try {
				Field[] field = c.getClass().getDeclaredFields();
				for (int j = 0; j < field.length; j++) {
					field[j].setAccessible(true);
					if (field[j].get(c) != null) {
						System.out.println("属性为->" + field[j].getName());
						System.out.println("值为->" + field[j].get(c));
					}
				}
			} catch (Exception ex) {

			}
		}
	}

	/**
	 * @author gewx 将Map对象转换为XML字符串
	 * @param map
	 * @return java.lang.String
	 * **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String convertXMLStr(Map map) {
		Document doc = new Document();
		Element rootEl = new Element("suning");
		rootEl.setAttribute("sourceType", "mc");
		doc.setRootElement(rootEl);
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> en = it.next();
			Element childEl = new Element(en.getKey());
			childEl.setText(getStr(en.getValue()));
			rootEl.addContent(childEl);
		}

		Format xmlFormat = Format.getPrettyFormat();
		xmlFormat.setEncoding("UTF-8");
		xmlFormat.setIndent(" ");
		XMLOutputter xmlOut = new XMLOutputter(xmlFormat);
		return xmlOut.outputString(doc);
	}

	/**
	 * @author gewx 2013.10.24 将pojo对象反射输出XML
	 * @param Object c
	 * @return String
	 * **/
	public static String convertXMLPOJO(Object c) {
		Document doc = new Document();
		Element rootEl = new Element("root");
		rootEl.setAttribute("sourceType", "systemOut");
		doc.setRootElement(rootEl);
		try {
			Field[] field = c.getClass().getDeclaredFields();
			for (int j = 0; j < field.length; j++) {
				field[j].setAccessible(true);
				Element childEl = new Element(field[j].getName());
				childEl.setText(getStr(field[j].get(c)));
				rootEl.addContent(childEl);
			}
		} catch (Exception e) {

		}
		Format xmlFormat = Format.getPrettyFormat();
		xmlFormat.setEncoding("UTF-8");
		xmlFormat.setIndent(" ");
		XMLOutputter xmlOut = new XMLOutputter(xmlFormat);
		return xmlOut.outputString(doc);
	}

	/** 首字母大写 **/
	public static String firstStrUpperCase(String name) {
		char[] cs = name.toCharArray();
		/**
		 * ascii相关资料说明 a-z：97-122 A-Z：65-90 0-9：48-57
		 * **/
		cs[0] -= 32;// 进行字母的ascii编码前移
		return String.valueOf(cs);
	}

	/** 首字母小写 **/
	public static String firstStrLowerCase(String name) {
		char[] cs = name.toCharArray();
		/**
		 * ascii相关资料说明 a-z：97-122 A-Z：65-90 0-9：48-57
		 * **/
		cs[0] += 32;// 进行字母的ascii编码前移
		return String.valueOf(cs);
	}

	/**
	 * @author gewx 2013.3.29 获得客户真实IP(支持代理)
	 * @param HttpServletRequest request
	 * @return String
	 * **/
	/*
	 * public static String getRemoteAddrIP(HttpServletRequest request) { if
	 * (StringUtils.isBlank(request.getHeader("x-forwarded-for"))) return
	 * request.getRemoteAddr(); else{ String [] ipArray =
	 * StringUtils.split(request.getHeader("x-forwarded-for"), ",");
	 * if(ipArray!=null)return ipArray[0]; else return null; } }
	 */

	/**
	 * @author gewx 2012.12.18 格式化输出XML
	 * @param String
	 * @return void
	 * */
	public static void outputForamtXML(String strXml) {
		try {
			SAXBuilder sax = new SAXBuilder();
			Document doc = sax.build(new StringReader(strXml));
			Format xmlFormat = Format.getPrettyFormat();
			xmlFormat.setEncoding("UTF-8");
			xmlFormat.setIndent(" ");
			XMLOutputter xmlOut = new XMLOutputter(xmlFormat);
			System.out.println(xmlOut.outputString(doc));
		} catch (Exception e) {
			System.out.println("Exception----->" + e.getMessage());
		}
	}

	/**
	 * @author gewx java去除字符串中的空格、回车、换行符、制表符，本方法采用的是java的正则表达式
	 * @param String 处理的字符
	 * @return String 处理后的字符
	 * */
	public static String replaceBlank(String replaceValue) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(replaceValue);
		String after = m.replaceAll("");
		after = after.replace("\r\n", " ");
		after = after.replace("\r", " ");
		after = after.replace("\n", " ");
		after = after.replace("'", "&acute;");
		after = after.replace("\"", "&quot;");
		return after;
	}

	/**
	 * @author gewx 修改email,替换为****
	 * @param 需要修改的email
	 * @return 返回修改后的email
	 * **/
	public static String getEmail(String email) {
		if (StringUtils.isBlank(email))
			return StringUtils.trimToEmpty(email);
		String[] emailArray = StringUtils.split(email, "@");
		if (emailArray.length == 1)
			return StringUtils.trimToEmpty(email);// 非正确邮箱
		StringBuilder emailBuffer = new StringBuilder();
		emailBuffer.append(email.substring(emailArray[0].length(),
				email.length()));// 后半部分
		int len = emailArray[0].length() / 3; // 模糊处理1/3中间段
		int mod = emailArray[0].length() % 3; // 取模
		int maxLen = emailArray[0].length(); // @左边最大长度
		char[] emailCharArray = emailArray[0].toCharArray();
		char[] email_ = new char[emailArray[0].toCharArray().length];
		for (int i = 0; i < maxLen; i++) {
			if (i >= len && i < len * 2 + mod)
				email_[i] = '*';
			else
				email_[i] = emailCharArray[i];
		}
		String p = new String(email_);
		emailBuffer.insert(0, p);
		return emailBuffer.toString();
	}

	/**
	 * @author gewx 修改手机号码。替换为****
	 * @param phone手机,start开始下标,end 结束下标
	 * @return 修改后的手机号码
	 * **/
	public static String getPhone(String phone, int start, int end) {
		if (StringUtils.trimToEmpty(phone).length() < 11)
			return StringUtils.trimToEmpty(phone);
		char[] phoneArray = phone.toCharArray();
		char[] phone_ = new char[phone.toCharArray().length];

		for (int i = 0; i < phoneArray.length; i++) {
			if (i >= start && i < end)
				phone_[i] = '*';
			else
				phone_[i] = phoneArray[i];
		}
		String p = new String(phone_);
		return p;
	}

	/**
	 * @author gewx Linux, unix,windows下分割路径处理方法.
	 * @param String
	 * @return 分割的地址串数组
	 * **/
	@SuppressWarnings("unused")
	private static String[] getSplitFile(String file) {
		return file.replaceAll("\\\\", "/").split("/");
	}

	/**
	 * @author Administrator added by gewx 2015.6.8 记录日志
	 * */
	public static String getError(Exception e) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			sw.close();
			pw.close();
			return sw.toString();
		} catch (Exception ex) {
			return "exception is error!";
		}
	}

	/**
	 *
	 * 将通过Base64编码后的String类型的公钥字符串转换为PublicKey对象
	 *
	 * @param strPubKey
	 *            Base64编码后的String类型的公钥字符串
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @since 10.15
	 */
	public static PublicKey getPublicKey(String strPubKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(
				Base64.decodeBase64(strPubKey));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
		return pubKey;
	}

	/**
	 *
	 * 将通过Base64编码后的String类型的私钥字符串转换为PrivateKey对象
	 *
	 * @param strPriKey
	 *            Base64编码后的String类型的私钥字符串
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @since 10.15
	 */
	public static PrivateKey getPrivateKey(String strPriKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		PKCS8EncodedKeySpec priKeySpec = new PKCS8EncodedKeySpec(
				Base64.decodeBase64(strPriKey));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey priKey = keyFactory.generatePrivate(priKeySpec);
		return priKey;
	}

	/**
	 * @author gewx 字符串反转-递归算法
	 * **/
    public static String reverse(String str,int index,char[] array) {
    	char [] c1 = str.toCharArray();
    	
    	if(index == c1.length) {
    		return new String(array);
    	}
    	
    	if(array == null) {
    		array = new char[c1.length];
    	}
    	
    	array[index] = c1[c1.length-(++index)];
    	
    	return reverse(str,index,array);
    }
    
	/**
	 * @author gewx 字符串反转-递归算法
	 * **/
    public static String reverse(char[] c1,int index,char[] array) {
    	if(index == c1.length) {
    		return new String(array);
    	}
    	
    	if(array == null) {
    		array = new char[c1.length];
    	}
    	
    	array[index] = c1[c1.length-(++index)];
    	
    	return reverse(c1,index,array);
    }
    
}
