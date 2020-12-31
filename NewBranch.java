package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


/*实现分支的思路：
 * 原先的分支默认是主分支，所有的commit文件和HEAD文件都放在了"D:\\AsimpleGit\\branches\\mainCommitPath"这个路径下。
 * 提交第一次commit时，会往"D:\\AsimpleGit"路径下的current_commit.txt文件内写入当前的分支名
 * 若要创立新的分支。要做的操作有：
 * 		（1）在"D:\\AsimpleGit\\branches\\"路径下建立新的文件夹，文件夹名字是新的分支名
 * 		（2）把当前分支路径下的所有commit文件和HEAD文件原封不动地复制到新的分支文件下。
 * 			 这相当于是复制整个文件夹的操作，所以我写了一个工具类: CopyTree，用来复制整个文件夹
 * 		（3）往current_commit.txt文件内写入新的分支名，覆盖原有的内容。
 * 		 
 * 
 */


public class NewBranch {
	private String newBranchName;   //新的分支名
	public String save_current_branch_file = "D:\\AsimpleGit\\current_branch.txt";  //该文件用于存放当前分支名
	public String branches = "D:\\AsimpleGit\\branches";    //该文件夹用于存放所有分支文件夹，包括主分支
	
	NewBranch( String newBranchName) throws IOException{
		this.newBranchName = newBranchName;
		change_branch( newBranchName );
		gen_branch();
		
		
	}
	
	public void gen_branch() throws IOException {
		File branch_file = new File ( this.branches + "\\" + newBranchName );
		branch_file.mkdir();            //创建新的分支文件夹，用来放该分支的commit文件
		 
		//得到当前分支名
		GetValue t = new GetValue();
		String branch = this.branches + "\\" + t.getValue( this.save_current_branch_file );

		new CopyTree( branch, branch_file.getPath());
		
	}
	
	//往current_commit.txt文件内写入新的分支名，覆盖原有的内容。
	private void change_branch( String branch_name ) throws FileNotFoundException{  
		//File save = new File( save_current_branch_file);
		PrintWriter out = new PrintWriter(branch_name);
		out.write( branch_name );
		out.close();
			
	}
	

}
