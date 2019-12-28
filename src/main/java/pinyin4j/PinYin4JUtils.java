package pinyin4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * @author gewx 拼音转换工具类
 **/
public final class PinYin4JUtils {

	private static final Pattern PATTERN_CN_ZH = Pattern.compile("[\\u4E00-\\u9FA5]+");

	private static final Pattern PATTERN_NUMBER = Pattern.compile("\\d");

	public static void main(String[] args) {
		String val = "机械";
		System.out.println(Arrays.toString(convertCNzhToPinYin(val)));
	}

	/**
	 * 中文转拼音
	 * 
	 * @author gewx
	 * @param hyVal 中文
	 * @return 拼音数值
	 **/
	public static String[] convertCNzhToPinYin(String hyVal) {
		if (StringUtils.isBlank(hyVal)) {
			return Collections.emptyList().toArray(new String[] {});
		}

		List<String> dataList = new ArrayList<>(16);
		char[] charArray = hyVal.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (PATTERN_CN_ZH.matcher(Character.toString(charArray[i])).matches()) {
				String val = PinyinHelper.toHanyuPinyinStringArray(charArray[i])[0];
				val = PATTERN_NUMBER.matcher(val).replaceAll(StringUtils.EMPTY);
				dataList.add(val);
			}
		}
		return dataList.toArray(new String[] {});
	}
}
