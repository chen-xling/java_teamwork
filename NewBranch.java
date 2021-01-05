package test;

import java.io.File;
import java.io.IOException;

/*实现分支的思路：
 * 默认分支是主分支，所有的commit文件和HEAD文件都放在了Global.gitPath这个路径下。
 * 初始化仓库，即提交第一次commit时，会往Global.save_current_branch_file（即current_commit.txt文件）写入当前的分支名
 * 若要创立新的分支。要做的操作有：
 * 		（1）在Global.branches路径下建立新的文件夹，文件夹名字是新的分支名
 * 		（2）把当前分支路径下的所有commit文件和HEAD文件原封不动地复制到新的分支文件下。
 * 			 这相当于是复制整个文件夹的操作，所以我写了一个工具类: CopyTree，用来复制整个文件夹
 * 		（3）仅仅是创建新分支而已，并没有切换当前分支。
 */

//本类用于创建新分支
public class NewBranch {
	//本类不需要数据域

	public NewBranch(){}
	public NewBranch( String newBranchName ) throws IOException{
		gen_branch( newBranchName );
	}
	
	public void gen_branch(String newBranchName) throws IOException {
		File branch_file = new File ( Global.branches + "\\" + newBranchName );
		branch_file.mkdir();            //创建新的分支文件夹，用来放该分支的commit文件
		 
		//得到当前分支Branch的文件夹路径
		GetValue t = new GetValue();
		String current_branch_path = Global.branches + "\\" + t.getValue( Global.save_current_branch_file );

		//把当前Branch文件夹的全部commit文件复制到新的Branch文件夹下面
		new CopyTree( current_branch_path, branch_file.getPath());    	
	}
	
}
