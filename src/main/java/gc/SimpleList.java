package gc;

/**
 * @author gewx 数据承载主体
 * **/
public final class SimpleList {

	private StringBuilder [] values = new StringBuilder[10];
	
	public void setValue(int index, StringBuilder val) {
		values[index] = val;
	}
}
