package magic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

/**
 * 文件魔数辅助类
 * 
 * @author gewx
 **/
public final class FileUtils {

	/**
	 * 获取文件头
	 * 
	 * @author gewex
	 * @param filePath 文件路径
	 * @throws IOException
	 * @return 16 进制的文件头信息
	 */
	private static String getFileHeader(String filePath) throws IOException {
		byte[] b = new byte[28];
		try (InputStream inputStream = new FileInputStream(filePath)) {
			inputStream.read(b, 0, 28);
		}
		return bytes2hex(b);
	}

	/**
	 * 将字节数组转换成16进制字符串
	 * 
	 * @author gewx
	 * @param byteArray 字节数组
	 * @return 字符串
	 **/
	private static String bytes2hex(byte[] byteArray) {
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : byteArray) {
			int v = b & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 根据文件路径获取文件类型
	 * 
	 * @author gewx
	 * @param filePath 文件路径 *
	 * @throws IOException
	 * @return 文件类型
	 */
	public static String getFileType(String filePath) throws IOException {
		String fileHead = getFileHeader(filePath);
		if (StringUtils.isBlank(fileHead)) {
			return StringUtils.EMPTY;
		}

		return Stream.of(FileType.values()).filter(val -> fileHead.toUpperCase().startsWith(val.getValue()))
				.map(val -> val.getKey()).collect(Collectors.joining());
	}
}
