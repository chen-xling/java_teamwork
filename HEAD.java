package test;

import java.io.*;
import java.util.Scanner;

//要建立一个存储最新HEAD的文件，否则commit记录只停留在内存中，下次无法找到
public class HEAD {

	private static String gitPath;
	private File head_file;
	public String head;
	
	HEAD() throws FileNotFoundException{
		gitPath = Commit.gitPath;
		this.head_file = new File(gitPath + "\\" + "HEAD");
		this.head = get_head();
	}
	
	public void update_head ( String commit ) throws IOException {
		if( !head_file.exists() ) {
			head_file.createNewFile();
			PrintWriter output = new PrintWriter(head_file);
			output.print( commit );        
			output.close();
			System.out.println("HEAD文件已建立。");
		}
		else {
			PrintWriter output = new PrintWriter(head_file);
			output.print( commit );        //会覆盖之前的内容。HEAD文件永远只保存最新的一次commit
			output.close();
			System.out.println("HEAD文件已更新。");
		}
	}
	
	public String get_head() throws FileNotFoundException {
		if( !head_file.exists() ) {
			return null;
		}
		else {
			/*
			 * GetValue gg = new GetValue();  //会多一个换行符，不行
				String head = gg.getValue(gitPath + "\\" + "HEAD");
			 */
			Scanner input = new Scanner(head_file);
			head = input.nextLine();
			input.close();
			return head;
		}
	}
	
	public String get_last_tree_key() throws FileNotFoundException {
		if( !this.head_file.exists() )
			return null;
		else {
			File last_commit = new File(gitPath + "\\"+ this.head);	
			Scanner input = new Scanner( last_commit );
			String last_tree_key = input.nextLine();   //第一行就是存放着tree_key  
			input.close();
			return last_tree_key;
		}
	}
	
}
