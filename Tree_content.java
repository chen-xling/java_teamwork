package test;

/*根据任务二的需要，定义一个内容类Tree_content：
 * 	数据域：
 * 		-类型：blob or tree
 * 		-哈希码
 * 		-文件原名（含后缀名）
 * 	方法：
 * 		+构造函数：传入子文件的路径，即可完成数据域的填充
 * 			即存储原文件夹里的【子文件】的名字、【子文件】的哈希码、【子文件】的类型（blob or tree)
 * 		+String output(): 返回数据域的所有内容
 * 		+String getName();
 * 		+String getKey();
 * 		+String getType();
 * 		+void get_tree_content( String line)   //传入的内容形式为Tree文件中的一行 
 * 				//供回滚使用，得到tree的每一行的内容信息
 */

import java.io.File;
import java.io.IOException;

public class Tree_content {
	private String name;
	private String key;
	private String type;
	protected String objectPath = Tree.objectPath;
	
	
	Tree_content(){};
	Tree_content(String f ) throws IOException {    //传入的是P1的子文件路径， file2[i].getPath()
		File file = new File(f);
		name = file.getName();
		Gen_hash t = new Gen_hash(); 
		
		if(file.isFile()) {
			type = "Blob";
			key = t.hash(f);
			new Blob( file.getPath(), objectPath );   //要把所有Blob文件都存起来, 供回滚使用
				                 
		}
			
		
		else if (file.isDirectory()) {
			type = "Tree";
			Tree tree = new Tree( f, objectPath );      //要把所有Tree文件都存起来, 供回滚使用
			String treeValue = tree.getValue();
			key = t.hashString(  treeValue );
		}	
	}
	
	public String output() {
		StringBuilder result = new StringBuilder ();
		result.append(this.type).append("\t");
		result.append(this.key).append("\t");
		result.append(this.name);	          //末尾不要加换行符，要在Tree.java文件里的函数中，加在该行的开头。
		return result.toString();
	}
	
	public String getType() {
		return this.type;	
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getKey() {
		return this.key;
	}
	
	//供回滚使用，得到tree的每一行的内容信息
	public void get_tree_content( String line) {    //传入的内容形式为Tree文件中的一行  
		this.type = line.substring(0,4);
		this.key = line.substring(5,45);
		this.name = line.substring(46);	
	}     
	

}
