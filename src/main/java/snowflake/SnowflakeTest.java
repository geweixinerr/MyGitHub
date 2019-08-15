package snowflake;

/**
 * 开源URL地址: https://gitee.com/yu120/sequence 
 * **/
public final class SnowflakeTest {

	private static final Sequence SNOWFLAKE = new Sequence(0);
	
	public static void main(String[] args) {
		System.out.println("数值: " + SNOWFLAKE.nextId());
		System.out.println("数值: " + SNOWFLAKE.nextId());
	}

}
