package test;

public class Singleton {

	private static Singleton singleton=null;
	private Singleton(){
		System.out.println(111);
	}
	public static Singleton singleton(){
	if(singleton==null) singleton=new Singleton();
	return singleton;
	}
	public static void main(String[] args) {
		singleton();
		singleton();
		
	}
}
