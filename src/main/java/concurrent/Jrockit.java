package concurrent;

public class Jrockit {

	private volatile int a = 1;
	
	private volatile int b = 1;
	
	
	public synchronized void add() {
		a++;
		b++;
	}
	
	public  synchronized void print() {
		if (a != b) {
			System.out.println("a : " + a + ", b: " + b);	
		}
	}
	
	public static void main(String[] args) {
		Jrockit t = new Jrockit(); 
		new Thread(() -> {
			while (true) {
				t.add();	
			}
		}).start();
		
		new Thread(() -> {
			while (true) {
				t.print();	
			}
		}).start();
	}
}
