package pinyin4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 拼音转换工具类
 * 
 * @author gewx
 **/
public final class PinYin4JUtils {

	public static final String SPLIT_SYMBOL = " / ";
	
	private static final Pattern PATTERN_CN_ZH = Pattern.compile("[\\u4E00-\\u9FA5]+");

	private static final Pattern PATTERN_NUMBER = Pattern.compile("\\d");

	/**
	 * 中文转拼音数组
	 * 
	 * @author gewx
	 * @param hyVal 中文
	 * @return 拼音数值数组
	 **/
	public static String[] convertCNzhToPinYinArray(String hyVal) {
		if (StringUtils.isBlank(hyVal)) {
			return Collections.emptyList().toArray(new String[] {});
		}

		List<String> dataList = new ArrayList<>(16);
		char[] charArray = hyVal.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			String tempVal = Character.toString(charArray[i]);
			if (PATTERN_CN_ZH.matcher(tempVal).matches()) {
				String val = PinyinHelper.toHanyuPinyinStringArray(charArray[i])[0];
				val = PATTERN_NUMBER.matcher(val).replaceAll(StringUtils.EMPTY);
				dataList.add(val);
			} else {
				dataList.add(tempVal);
			}
		}
		return dataList.toArray(new String[] {});
	}

	/**
	 * 中文转拼音首字符大写
	 * 
	 * @author gewx
	 * @param hyVal 中文
	 * @return 拼音数值
	 **/
	public static String convertCNzhToPinYinVal(String hyVal) {
		String[] valArray = convertCNzhToPinYinArray(hyVal);
		return Stream.of(valArray).map(val -> {
			return Character.toString(val.charAt(0));
		}).collect(Collectors.joining());
	}

	/**
	 * 包装字符
	 * 
	 * @param val 中文数据, splitVal 分隔符
	 * @return 包装后数据
	 **/
	public static String wrap(String val, String splitVal) {
		String pyVal = convertCNzhToPinYinVal(val);
		if (StringUtils.isNotBlank(pyVal)) {
			StringBuilder sb = new StringBuilder(32);
			sb.append(val + splitVal + pyVal.toUpperCase());
			return sb.toString();
		} else {
			return val;
		}
	}
}
