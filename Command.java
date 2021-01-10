package test;

import java.io.IOException;


/*命令行交互类：
 通过命令行向Command.java文件的main函数传入字符串数组，实现以下功能：

1.git initial "仓库路径"  		//还未实现
 * 	初始化，传入仓库路径，作为工作区目录；默认为main分支
2.git commit
 * 	在当前分支下，提交当前仓库的内容
3.git history
 * 	在当前分支下，查看提交过的commit历史
4.git rollback "某次commit的哈希值"
 * 	在当前分支下，实现回滚到某一次历史状态
5.git branch "新的分支名"
 * 	新建分支
6.git checkout "已有的分支名"
 * 	切换到另一条分支；工作区文件夹得内容也会随着改变，相当于回滚



 */

public class Command {

	//从命令行中读取git命令
	public static void main(String[] args) throws IOException {
			if( args[0].equals("git") ) {
				String command = args[1];
				
				switch(command){
					case "initial":
						String path = args[2];   //还未完成初始化命令
						break;
					case "commit":
						new Commit( Global.filePath, Global.gitPath );
						break;
					case "history":
						Commit t = new Commit();
						System.out.println( t.get_all_commit() );
						break;		
					case "rollback":
						String oldCommit = args[2];
						new RollBack(oldCommit);
						break;
					case "checkout":
						String BranchName = args[2];
						Branch b = new Branch();
						b.change_branch(BranchName);
						break;
					case "branch":
						String new_branch_name = args[2];
						new Branch(new_branch_name);
						break;
					default:
						System.out.println("Sorry, this is not a git command. Try again.");
								
				}
				
				
			}
			else {
				System.out.println("Sorry, this is not a git command. Try again.");
			}
		
		
		

	}
		
		

}
