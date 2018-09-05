package com;

public class Main {
	
	public static void main(String[] args) {
		example();
	}

	public static void example() {
		Foo foo = new Foo(); // alloc
		Bar bar = new Bar(); // alloc
		bar.setFoo(foo);
	}
}

class Foo {
}

class Bar {
	private Foo foo;

	public void setFoo(Foo foo) {
		this.foo = foo;
	}
}