package test;

import java.io.IOException;

public class Test_commit {

	public static void main(String[] args) throws IOException {
		
		Global g = new Global();
		
		String filePath = Global.filePath;
		String gitPath = Global.gitPath;
	    Commit tijiao = new Commit();
	    System.out.println("以下是所有的历史commit：");
	   System.out.println( tijiao.get_all_commit() );
	    //GetValue tt = new GetValue();
	    //System.out.println( tt.getValue("D:\\AsimpleGit\\branches\\mainCommitPath"+ "\\" + "HEAD"));
	  
	  
	}

}
