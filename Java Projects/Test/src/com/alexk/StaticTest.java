package com.alexk;

public class StaticTest {

	public static void main(String[] args) {
		Young john = new Young();
		//john.g();
		
		Old kate = new Young();
		kate.g();
	}
}

class Old {
	public static void f() {
		System.out.println("hello");
	}
	
	public  void g() {
		System.out.println("bye");
	}
}

class Young extends Old {
	public  void g() {
		System.out.println("yeh");
	}
}