package lambda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Apple {

	public Apple() {

	}

	private boolean isBool;
	
	public boolean isBool() {
		return isBool;
	}

	public void setBool(boolean isBool) {
		this.isBool = isBool;
	}

	public static boolean isHidden(String v) {
		return true;
	}

	public static String processFile(BufferedReaderProcessor p) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(new File("c:\\1.txt")))) {
			return p.process(br);
		}
	}

	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> arraylist = new ArrayList<>();
		list.stream().forEach(val -> {
			if (p.test(val)) {
				arraylist.add(val);
			}
		});
		return arraylist;
	}
	
	public static void main(String[] args) throws IOException {
		//String val = processFile(br -> br.readLine() + ":" + br.readLine());
		//System.out.println(val);

		/*
		List<String> list = new ArrayList<String>();
		list.add("Java");
		list.add(".Net");
		list.add(null);
		System.out.println("[1]-----------> " + Arrays.toString(list.toArray()));
		Predicate<String> p1 = v -> "Java".equals(v);
		
		List<String> list_2 = filter(list,p1);
		
		System.out.println("[2]-----------> " + Arrays.toString(list_2.toArray()));
		*/
		
		List<Integer> list = Arrays.asList(1,2,3,4);
		int sum = list.stream().reduce(10,(x,y) -> x + y);
		System.out.println(sum);
	}

}
