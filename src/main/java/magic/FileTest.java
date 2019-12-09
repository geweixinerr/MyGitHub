package magic;

import java.io.IOException;

/**
 * 文件魔数测试
 * 
 * @author gewx
 **/
public class FileTest {

	public static void main(String[] args) throws IOException {
		String filePath = "C:\\desktop\\IMG_4080.JPG";
		String fileType = FileUtils.getFileType(filePath);
		System.out.println(fileType);
	}
}
