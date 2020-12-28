package test;

import java.io.*;
import java.util.Scanner;

/*实现回滚功能
 *其实，就是根据一个版本号（也就是某次commit的文件名） 读取该文件它的第一行，
 *就得到了那次commit所提交的整个大文件夹的tree
 *再根据tree里面的内容，一一把文件复制到目标路径：
 *	子文件若是blob, 就根据它的哈希码，去存放object的路径下找到它，然后复制到目标路径
 *	子文件若是Tree, 就在目标路径下新建一个子文件夹；并且根据它的哈希码，去存放object的路径下找到相应Tree文件，递归。
 *
 */

/*解释一下object路径：
 * 	对于第一次commit, 把所有的tree和blob存到这个路径下。
 * 		如果是不同文件名，但内容完全相同的子文件，只会保存一个blob，而不是两个。
 * 	对于第二次commit：
 * 		如果某些旧文件改动了，则会在该object路径下新增相应的tree和blob，同时旧的tree和blob不会被删除。
 * 		如果新增了全新的文件，就在object路径下新增新的tree或blob
 * 	由此，便可知所有的历史版本的文件内容。
 * 
 */


public class RollBack {
	private String treeKey;
	private String treePath;
	private String goalPath = "D:\\AsimpleGit\\goalPath";  //回滚出来的文件全部放在这个路径下
	private String commitPath = "D:\\AsimpleGit\\commitPath";
	
	RollBack( String commit ) throws IOException {
		File file1 = new File(  commitPath + "\\" + commit );
		Scanner input1 = new Scanner( file1 );
		treeKey = input1.nextLine();
		input1.close();
		treePath = Tree_content.objectPath + "\\" + treeKey;
		gen_file( treePath, this.goalPath );
		System.out.println("回滚成功！");
		
	}
	
	public boolean gen_file( String filePath, String goalPath ) throws IOException {
		File file2 = new File ( filePath );
		Scanner input2 = new Scanner( file2 );
		String oneLine = null;
		Tree_content  read_a_line = new Tree_content();
		
		while ( input2.hasNextLine() ) {
			oneLine = input2.nextLine();         //依次读取tree文件中每一行的内容
			read_a_line.get_tree_content(oneLine);
			
			String copyedPath = Tree_content.objectPath + "\\" + read_a_line.getKey();  //这是将被还原的文件的路径
			
			if (read_a_line.getType().equals("Blob")) {       //！！注意不能是==   
				new CopyBlob( copyedPath, goalPath, read_a_line.getName() );
			}
			else if (read_a_line.getType().equals("Tree")) {
				String sec_file_path = goalPath + "\\" + read_a_line.getName();
				File sec_file = new File( sec_file_path  );
				sec_file.mkdir();        //建立二级文件夹
				
				gen_file( copyedPath ,sec_file_path  );
			}
						
		}	
		input2.close();	
		return true;
	}
}
