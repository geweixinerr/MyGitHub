package util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author gewx Http请求辅助类
 **/
public final class HttpRequestUtils {

	private HttpRequestUtils() {

	}

	/**
	 * @author gewx 2013.3.29 获得客户真实IP(支持代理)
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 **/
	/*
	public static String getRemoteAddrIP(HttpServletRequest request) {
		String header = request.getHeader("X-Forwarded-For");
		if (StringUtils.isBlank(header)) {
			return request.getRemoteAddr();
		} else {
			String[] ipArray = StringUtils.split(header, ",");
			if (ArrayUtils.isNotEmpty(ipArray)) {
				return ipArray[0];
			} else {
				return StringUtils.EMPTY;
			}
		}
	}
    */
}
