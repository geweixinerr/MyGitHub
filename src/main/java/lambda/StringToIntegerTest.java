package lambda;

public class StringToIntegerTest {

	public StringToIntegerTest() {

	}

	public static void Test(StringToInteger val) {
		Integer length = val.toInteger("Java");
		System.out.println("转换后的字符长度: " + length);
	}

	public static int toInt(String val) {
		return 10;
	}

	public static void main(String[] args) {
		Test(StringToIntegerTest::toInt);
	}
}
