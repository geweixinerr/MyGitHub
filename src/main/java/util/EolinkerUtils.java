package util;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * Eolinker API管理工具辅助工具类,主要生成VO对应的JSON
 * 
 * @author gewx
 **/
public final class EolinkerUtils {

	/**
	 * 构建文档JSON
	 * 
	 * @author gewx
	 * @param serObject POJO对象
	 * @throws Exception
	 * @return JSON字符串
	 **/
	public static String buildDocJson(Serializable serObject) throws Exception {
		JSONObject json = new JSONObject();
		Field[] field = serObject.getClass().getDeclaredFields();
		for (int j = 0; j < field.length; j++) {
			field[j].setAccessible(true);
			json.put(field[j].getName(), StringUtils.EMPTY);
		}
		return JSONObject.toJSONString(json);
	}
}
