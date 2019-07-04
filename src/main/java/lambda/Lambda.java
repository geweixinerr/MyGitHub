package lambda;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * @author gewx lambda表达式工具类
 **/
public class Lambda {

	public Lambda() {
	}

	public static void main(String[] args) {
		/*
		 * List<String> list = new ArrayList<String>(); list.add("1");
		 * list.add("2"); list.add("3"); list.add("4"); list.add("5");
		 * 
		 * Message message = () -> { System.out.println("Hello"); };
		 * message.sendMessage();
		 * 
		 * UnaryOperator<String> t = v -> v + "Java";
		 * 
		 * String s = t.apply("Hello,"); System.out.println(s);
		 */
		testPredicate();
		testConsumer();
		testFunction();
		testSupplier();
		testUnarOperator();
		testBinaryOperator();
	}

	/**
	 * Predicate<T>
	 **/
	public static void testPredicate() {
		System.out.println("predicate------------------>");
		Predicate<String> p = x -> x.length() > 10;
		boolean bool = p.test("name");
		System.out.println("bool: " + bool);
	}

	/**
	 * Consumer<T>
	 **/
	public static void testConsumer() {
		System.out.println("consumer------------------>");
		Consumer<String> c = x -> System.out.println(x);
		c.accept("Java");
	}

	/**
	 * Function<T,R>
	 * **/
	public static void testFunction() {
		System.out.println("Function------------------>1");
		Function<MessageBody,String> f = x -> x.getCertNo();
		MessageBody body = new MessageBody();
		body.setCertNo("3211011");
		body.setCustName("葛伟新");
		String certNo = f.apply(body);
		System.out.println("certNo: " + certNo);
		
		System.out.println("Function------------------>2");
		Function<String,Integer> f2 = (String x) -> x.length();
		Integer count = f2.apply("Java");
		System.out.println("字符长度: " + count);
	}
	
	/**
	 * Supplier<T>
	 * **/
	public static void testSupplier() {
		System.out.println("supplier------------------>");
		Supplier<MessageBody> s = () -> {
			MessageBody message = new MessageBody();
			message.setCertNo("3211");
			message.setCustName("葛伟新");
			return message;
		};
		MessageBody object = s.get();
		System.out.println(object.getCertNo());
		System.out.println(object.getCustName());
	}
	
	/**
	 * UnarOperator<T>
	 * **/
	public static void testUnarOperator() {
		System.out.println("unaryOperator------------------>");
		UnaryOperator<String> u = x -> "Java";
		String val = u.apply("Java");
		System.out.println("val: " + val);
		
		Object val_2 = UnaryOperator.identity().apply("Java Hello");
		System.out.println("val_2: " + val_2);
	}
	
	/**
	 * BinaryOperator<T>
	 * **/
	public static void testBinaryOperator() {
		System.out.println("binaryOperator------------------>");
		BinaryOperator<Integer> b = (x,y) -> x * y;
		Integer result = b.apply(10, 10);
		System.out.println("result: " + result);
	}
}
