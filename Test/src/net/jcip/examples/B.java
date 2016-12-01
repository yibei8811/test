package net.jcip.examples;

public class B {
	A a;
	public B(){
		System.out.println("~~~B");
		a = new A(this);
	}
	
	public static void main(String[] args) {
		B b = new B();
	}
}
