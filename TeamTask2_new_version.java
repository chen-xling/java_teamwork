package test;
/*
小组任务2：
	给定一个文件夹目录，将其转化成若干tree和blob
	深度优先遍历此目录
	遇到子文件就转化成blob并保存
	遇到子文件夹就递归调用其内部的子文件/文件夹最后构造tree并保存

	使用任务1提供的接口 --- hash表
	单元测试 
 */

/*设计目标：
 * (P1：原始路径，P2：目标路径)
 * (1)若P1给定的是一个文件blob，则把blob从P1拷贝到P2，内容不变，文件名改成blob的哈希码
 * (2)若P1给定的是一个文件夹tree，则在P2中生成一个txt文件，文件内容为:
 * 		原文件夹里的子文件的名字、子文件的哈希码、子文件的类型（blob or tree)
 * 		P2里的新文件夹名字为以上内容的哈希值
 * 
 */

/*设计思路：
 * 	定义一个叫做任务二的类：
 * 	数据域：
 * 		-总的key
 * 		-原始路径P1
 * 		-新路径P2
 * 			
 * 方法：
 * 		+构造方法( P1, P2 )
 * 		+goal
 * 
 * 再定义一个内容类，Tree_content：
 * 	数据域：
 * 		-类型：blob or tree
 * 		-哈希码
 * 		-文件原名（含后缀名）
 * 	方法：
 * 		+构造函数：传入子文件的路径，即可完成数据域的填充
 * 			即存储原文件夹里的【子文件】的名字、【子文件】的哈希码、【子文件】的类型（blob or tree)
 * 		+String output():
 * 			打印数据域的所有内容
 * 		+getFilePath
		+getNewFilePath
		+getKey
 */



import java.io.*;
import java.util.Scanner;

public class TeamTask2_new_version {
	
	private String gross_key;
	private String filePath;
	private String newFilePath;
	
	TeamTask2_new_version(String P1, String P2){
		this.filePath = P1;
		this.newFilePath = P2;
		Gen_hash t = new Gen_hash();
		gross_key = t.hash(P1);
		gen_tree_file();
		
	};
	
	public void gen_tree_file() {
		//分成两种情况，1.filePath是文件blob，2.filePath是文件夹
		File file1 = new File( filePath );
 
		try {
			if( file1.isFile()) {
				new TeamTask1( filePath, newFilePath );
			}
			else if (file1.isDirectory()) {
				File newFile = new File( newFilePath + "\\"+ gross_key);   
				newFile.createNewFile();
				FileOutputStream fos = new FileOutputStream( newFile );
				File[] file2 = file1.listFiles();
		
				for(int i=0; i<file2.length; i++) {
					Tree_content g = new Tree_content( file2[i].getPath());
					fos.write( g.output().getBytes() );
				}
			fos.close();	
			}	
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
		
	}
	
	public  String getValue ( )  throws FileNotFoundException {
		 File file = new File ( newFilePath + "\\"+ gross_key );
		 try {
			 Scanner fileInput = new Scanner (file); // 扫描读入的是新路径的文件的内容
			 StringBuffer stringbuffer = new StringBuffer();
			 while (fileInput.hasNextLine()) {
				 stringbuffer.append( fileInput.nextLine()).append('\n');
			 }
			 fileInput.close();
			 return stringbuffer.toString();
		 }
		 catch (Exception c) {
			 System.out.println("key对应的文件不存在");
			 return null;
		 }
		 
	 }
	
	public String getFilePath() {
		return filePath;
	}
	
	public String getNewFilePath() {
		return newFilePath;
	}
	
	public String getKey() {
		return gross_key;
	}
	
	

}
