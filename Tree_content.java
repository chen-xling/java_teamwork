package test;

/*根据任务二的需要，定义一个内容类，Tree_content：
 * 	数据域：
 * 		-类型：blob or tree
 * 		-哈希码
 * 		-文件原名（含后缀名）
 * 	方法：
 * 		+构造函数：传入子文件的路径，即可完成数据域的填充
 * 			即存储原文件夹里的【子文件】的名字、【子文件】的哈希码、【子文件】的类型（blob or tree)
 * 		+String output():
 * 			打印数据域的所有内容
 * 
 */

import java.io.File;
public class Tree_content {
	private String name;
	private String key;
	private String type;
	
	Tree_content(String f ) {    //传入的是P1的子文件路径， file2[i].getPath()
		File file = new File(f);
		name = file.getName();
		Gen_hash t = new Gen_hash();
		key = t.hash(f);
		if(file.isFile()) 
			type = "Blob";
		else if (file.isDirectory())
			type = "Tree";
	}
	
	public String output() {
		StringBuilder result = new StringBuilder ();
		result.append(this.type).append("\t");
		result.append(this.key).append("\t");
		result.append(this.name).append("\n");	
		return result.toString();
	}

}
