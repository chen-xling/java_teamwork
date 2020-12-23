package test;

import java.io.IOException;

public class Test_commit {

	public static void main(String[] args) throws IOException {

		String filePath = "D:\\Users\\chenxling\\Pictures\\pyͼ";
	    String gitPath = "D:\\Ayingyong5_test";
	    new Commit( filePath, gitPath );
	    Gen_hash t = new Gen_hash();
	  
	}

}
