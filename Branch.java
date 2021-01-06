package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*实现分支的思路：
 * 默认分支是主分支，所有的commit文件和HEAD文件都放在了Global.gitPath这个路径下。
 * 初始化仓库，即提交第一次commit时，会往Global.save_current_branch_file（即current_commit.txt文件）写入当前的分支名
 * 
 * 若要创立新的分支。要做的操作有：
 * 		（1）在Global.branches路径下建立新的文件夹，文件夹名字是新的分支名
 * 		（2）把当前分支路径下的所有commit文件和HEAD文件原封不动地复制到新的分支文件下。
 * 			 这相当于是复制整个文件夹的操作，所以我写了一个工具类: CopyTree，用来复制整个文件夹
 * 		（3）仅仅是创建新分支而已，并没有切换当前分支。
 * 
 *切换分支时，要完成下列操作：
	 	（1）往current_commit.txt文件内写入新的分支名，覆盖原有的内容。
	 	（2）用回滚的办法，把仓库切换到另一个分支的最新状态
	 	
	 	
	 	
可以再加一个删除分支的功能	 	
 */


public class Branch {
	protected String current_branch;        //当前分支的名字
	protected String current_branch_path;	//当前分支的文件夹路径
	protected String latest_commit;         //最新的commit文件名（其哈希值）
	
	
	//重载构造函数
	public Branch() throws FileNotFoundException{
		//得到当前分支的名字，以及它的文件夹路径
		GetValue t = new GetValue();
		current_branch = t.getValue( Global.save_current_branch_file );
		current_branch_path = Global.branches + "\\" + current_branch;
		
	}
	public Branch( String newBranchName ) throws IOException{
		
		//得到当前分支的名字，以及它的文件夹路径
		Global g = new Global();   //要初始化
		current_branch = Global.current_branch;
		current_branch_path = Global.gitPath;
		
		gen_branch( newBranchName );
	}
	
	
	//本函数用于创建新分支
	public void gen_branch(String newBranchName) throws IOException {
		File branch_file = new File ( Global.branches + "\\" + newBranchName );
		branch_file.mkdir();            //创建新的分支文件夹，用来放该分支的commit文件
		 
	
		//把当前Branch文件夹的全部commit文件复制到新的Branch文件夹下面
		new CopyTree( current_branch_path, branch_file.getPath());   
		System.out.println("新建分支成功！");
	}
	
	
	
	//本函数用于切换分支
	public void change_branch( String branch_name ) throws IOException{  
		File save = new File( Global.save_current_branch_file);
		PrintWriter out = new PrintWriter( save );
		out.write( branch_name );
		out.close();
		
		File the_head_file = new File ( Global.branches + "\\" + branch_name +"\\" + "HEAD");
		Scanner input = new Scanner(the_head_file);
		String s = null;
		while( input.hasNextLine() ) {
			s = input.nextLine();
				
		}   //这个while循环是为了读取HEAD文件的最后一行，最后一行是最新的Head值。
		input.close();
		
		latest_commit = s ;  //找到该分支的最新commit
		
		new RollBack(latest_commit);
		System.out.println("已切换到分支：" + branch_name);
	
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
