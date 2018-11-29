package util.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 * @author gewx Gzip压缩 (网络传输前压缩字节流)
 * 更多解压缩方式: https://blog.csdn.net/mazaiting/article/details/79707927
 **/

public final class GzipUtils {

	public static final String GZIP_ENCODE_UTF_8 = "UTF-8";

	public static final String GZIP_ENCODE_ISO_8859_1 = "ISO-8859-1";

	/**
	 * @author gewx Gzip压缩
	 * @param str
	 *            压缩的字符, encoding 字符编码
	 **/
	public static byte[] compress(String str, String encoding) {
		if (str == null || str.length() == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes(encoding));
			gzip.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	public static byte[] compress(String str) throws IOException {
		return compress(str, GZIP_ENCODE_UTF_8);
	}

	/**
	 * @author gewx 解压Gzip字符流
	 * @param bytes
	 *            Gzip字符流
	 **/
	public static byte[] uncompress(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		try {
			GZIPInputStream ungzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = ungzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	/**
	 * @author gewx 解压Gzip字符流并转化为字符串
	 * @param bytes
	 *            Gzip字符流 ,encoding 字符编码
	 **/
	public static String uncompressToString(byte[] bytes, String encoding) {
		if (bytes == null || bytes.length == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		try {
			GZIPInputStream ungzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = ungzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			return out.toString(encoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String uncompressToString(byte[] bytes) {
		return uncompressToString(bytes, GZIP_ENCODE_UTF_8);
	}

	
	public static void main(String[] args) throws IOException {
		String s = "中国人民万岁,非常好的的多功能下.中国人民万岁,非常好的的多功能下.中国人民万岁,非常好的的多功能下.";
		System.out.println("字符串长度: " + s.length());
		System.out.println("字符比特数组长度: " + s.getBytes().length);
		System.out.println("压缩后比特数组: " + compress(s).length);
		System.out.println("解压后比特数组长度: " + uncompress(compress(s)).length);
		System.out.println("解压后字符串长度: " + uncompressToString(compress(s)).length());
		System.out.println("解压后字符串: " + uncompressToString(compress(s)));
	}
	
}