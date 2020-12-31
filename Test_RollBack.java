package test;

import java.io.IOException;

public class Test_RollBack {

	public static void main( String[] args) throws IOException {
		
/*		String s = "tree\t1234567890\tcxl.java";
		String s1 = s.substring(0,4);
		String s2 = s.substring(5,15);
		String s3 = s.substring(16);	
 * 		System.out.println(s);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		
 */
		String commit = "941fe113ebaf985dd80914da19c41e7e6b59bcca";   //这个是指要回到历史哪个commit
		new RollBack( commit);
		
		
		
	}
	
	
}
