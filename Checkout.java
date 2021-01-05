package test;
/*还可以进一步实现的功能
 * 	1.删除当前分支
 * 	
 * 

 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*Checkout.java类要实现切换分支的功能，步骤如下：
 *	1.找到目标分支的最新commit文件
 *	2.回滚到该commit文件对应的仓库状态 
 *	3.往current_commit.txt文件内写入新的分支名，覆盖原有的内容。	
 * 
 */

public class Checkout {
	
	public Checkout(String a_branch){
		
	}
		
	
	
	
	
	
	/*切换分支时，要完成下类操作：
	 * 1.往current_commit.txt文件内写入新的分支名，覆盖原有的内容。
	 * 2.用回滚的办法，把仓库切换到另一个分支的最新状态
	 * 
	 * 
	 */
	public void change_branch( String branch_name ) throws FileNotFoundException{  
		File save = new File( Global.save_current_branch_file);
		PrintWriter out = new PrintWriter( save);
		out.write( branch_name );
		out.close();	
	
	}
	
	//返回当前的所有分支名字
	public String listBranch () { 
		File f = new File( Global.branches );
		File[] branchList = f.listFiles();
		StringBuffer allBranch = new StringBuffer();
		
		for( File b: branchList ) {
			allBranch.append(b.getName()).append("\n");
		}
		
		allBranch.deleteCharAt(allBranch.length()-1);  //删除最后一个换行符
		
		return allBranch.toString();
		
	}
	
}
