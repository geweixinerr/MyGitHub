package com;

public class Lambada {

	public Lambada() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Runnable runObject = () -> {
			System.out.println("Hello,Lambada!");	
		};
		
		Thread t = new Thread(runObject);
		t.start();
		
	}
}
