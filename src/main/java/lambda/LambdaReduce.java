package lambda;

import java.util.ArrayList;
import java.util.List;

public class LambdaReduce {

	public LambdaReduce() {

	}

	public static void main(String[] args) {
		List<Integer> arrayList = new ArrayList<>();
		arrayList.add(10);
		arrayList.add(11);
		arrayList.add(9);
		arrayList.add(6);
		arrayList.add(8);
		
		//http://www.conyli.cc/archives/2479
		int i = arrayList.stream().reduce(0, (x, y) -> x + y, (x, y) -> x + y).intValue();

		System.out.println("sum-value: " + i); //44
	}
}
