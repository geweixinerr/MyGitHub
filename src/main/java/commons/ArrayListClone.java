package commons;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author gewx 理解ArrayList GC优化
 * **/
public class ArrayListClone {

	public static void remove(int index) {
		String [] elementData = new String[] {"a","b","c","d","e"};
		int size = elementData.length;
		int numMoved = elementData.length - 2 - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        
        System.out.println("elementData: " + ArrayUtils.toString(elementData));
        elementData[--size] = null; // clear to let GC do its work [将无效数组下标引用手工置为NULL]
        System.out.println("elementData: " + ArrayUtils.toString(elementData));
	}
	
	public static void main(String[] args) {
		remove(2);
	}
	
}
