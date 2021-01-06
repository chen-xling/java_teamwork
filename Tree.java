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
 * 	定义一个叫做Tree的类：
 * 	数据域：
 * 		-总的key
 * 		-原始路径P1
 * 		-新路径P2
 * 		-value
 * 			
 * 方法：
 * 		+构造方法( P1, P2 )
 * 		+void gen_tree()  //完成主要功能
 * 		+String getValue()  //返回新生成的文件内容
 * 		+String getKey()
 * 		+String getFilePath()
 * 		+String getNewFilePath()
 * 
 * 
 * 再定义一个内容类，Tree_content：
 * 	数据域：
 * 		-类型：blob or tree
 * 		-哈希码
 * 		-文件原名（含后缀名）
 * 	方法：
 * 		+构造函数：传入子文件的路径，即可完成数据域的填充
 * 			即存储原文件夹里的【子文件】的名字、【子文件】的哈希码、【子文件】的类型（blob or tree)
 * 		+String output()  返回数据域的所有内容
 * 		+getFilePath
		+getNewFilePath
		+getKey
		+getValue
 */

import java.io.*;

public class Tree {
	
	private String key;
	private String filePath;    //一般来说，filePath应该是一个文件夹路径，而不是文件路径
	protected static String objectPath;
	private StringBuffer value;
	
	public Tree() {}
	public Tree(String P1, String P2){
		filePath = P1;
		objectPath = P2;
		gen_tree();
		
	}
	
	public void gen_tree() {
		//分成两种情况，1.filePath是文件，2.filePath是文件夹
		File file1 = new File( filePath );
		value = new StringBuffer();
		try {
			if( file1.isFile()) {
				Blob b = new Blob( filePath, objectPath );
				value.append( b.getValue() );
			}
			else if (file1.isDirectory()) {
				File[] file2 = file1.listFiles();
				
				for(int i=0; i<file2.length; i++) {
					Tree_content g = new Tree_content( file2[i].getPath());
					if( i!=0 ) 
						value.append("\n");
					value.append( g.output() );
					
					
				}
				
				Gen_hash t = new Gen_hash();
				key = t.hashString( value.toString() );
				
				File tree = new File( objectPath + "\\" + key );
				
				if ( !tree.exists() ) {       //如果相同的tree文件已经存在，则不会再生成
					tree.createNewFile();
					PrintWriter output = new PrintWriter( tree );
					output.write( value.toString() );
					output.close();
				}
			}	
		}
		catch (IOException e) {	
			e.printStackTrace();
		}  		
	}
	
	
	public String getFilePath() {
		return filePath;
	}
	
	public String getObjectPath() {
		return objectPath;
	}
	
	public String getKey() {
		return key;
	}
	
	public  String getValue ( ) throws FileNotFoundException {
		 GetValue t = new GetValue();
		 return t.getValue( objectPath +"//" + key );
	 }
}
