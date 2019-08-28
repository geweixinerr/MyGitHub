package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gewx HTML正则检索替换数据 示例<img>标签
 * **/
public final class HtmlPattern {
	
	private static final Pattern P = Pattern.compile("<img(.*?)src=\"(.*?)\"(.*?)>");

	/**
	 * @author gewx html解析
	 * @param htmlData html文件
	 * @return 解析替换后的文件
	 * **/
	public static String parseHtml(String htmlData, String replaceVal) {
		Matcher matcher = P.matcher(htmlData);
		while (matcher.find()) {
			String url = matcher.group(2);
			htmlData = htmlData.replace(url, replaceVal);
		}
		return htmlData;
	}
	
	public static void main(String[] args) {
		String htmlData = "中人民万岁<div>很好啊。。。<img src=\"http://mmbiz.qpic.cn/mmbiz_jpg/6xnlBPppQLAfJz0ibKwecE1MMrLZq040UD8JCqVB0ciaqyIhIZdcSIwXnvSZibmGXCEwyr54Sg3U9kTHB77T7ib4Wg/0\"><br><br>放个假发个包包vv<br><br><img src=\"http://mmbiz.qpic.cn/mmbiz_jpg/6xnlBPppQLAfJz0ibKwecE1MMrLZq040UD8JCqVB0ciaqyIhIZdcSIwXnvSZibmGXCEwyr54Sg3U9kTHB77T7ib4Wg/0\"><br></div>";
		
		String val = parseHtml(htmlData,"测试");
		
		System.out.println("解析后的文本: " + val);
	}
}
