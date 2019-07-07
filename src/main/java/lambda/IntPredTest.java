package lambda;

import java.util.function.Predicate;

public class IntPredTest {

	public IntPredTest() {

	}

	public static boolean check(Predicate<Integer> predicate) {
		return predicate.test(10);
	}
	
	public static boolean check(IntPred predicate) {
		return predicate.test(10);
	}
	
	public static void main(String[] args) {
		//重载正确作法
		boolean b = IntPredTest.check((IntPred)(x -> x.intValue() == 10));
		
		//boolean b = IntPredTest.check(x -> x.intValue() == 10); //Error
		System.out.println("value: " + b);
	}
}
