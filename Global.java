package test;

import java.io.FileNotFoundException;

public class Global {
	/*所有blob、 tree、 commit文件和HEAD文件、保存当前分支的文件，
	 *其存放路径都定义在Global.java这个文件类中，且定义为静态变量（全局）。
	 *注意，这些路径如果用代码更改了，都是只停留在内存中的，上次退出时停留在哪个分支是不会记得的
	 *其中，Tree.java和Blob.java是没有用到这些全局变量的。
		*/
		protected static String filePath = "D:\\Users\\chenxling\\Pictures\\py图" ;   //工作区目录
		protected static String objectPath = "D:\\AsimpleGit\\object";      //存放所有blob、 tree文件的目录
		protected static String save_current_branch_file = "D:\\AsimpleGit\\current_branch.txt";  //用于存放当前分支名
		protected static String branches = "D:\\AsimpleGit\\branches";    //该文件夹用于存放所有分支文件夹，包括主分支
		protected static String current_branch ;     // "mainCommitPath";     	  //当前分支名
		protected static String gitPath ;      //= "D:\\AsimpleGit\\branches\\mainCommitPath";  //存放commit文件的目录
	
		Global() throws FileNotFoundException{
			//初始化，从文件中读取当前分支。
			
			//得到当前分支的名字，以及它的文件夹路径
			GetValue t = new GetValue();
			current_branch = t.getValue( Global.save_current_branch_file );
			gitPath = Global.branches + "\\" + current_branch;
			
			
		}	

}
