package test;
import java.io.*;

/* 小组任务一要实现：
封装成一个类
	1. 给定value，向存储中添加对应的key-value
	2. 给定key，查找得到对应的value值

定义一个Blob类:
数据域：
	-原始文件的路径： filePath
	-该文件的哈希值：key
	-新的文件路径：gitPath
	-文件的内容：value

方法：
	+1 getFilePath
	+2 getGitPath
	+3 getKey
	+4 给定value，向存储中添加对应的key-value的函数，也就是创建同样内容但文件名为key的新文件：addFile(String filePath)
		返回值为key;其中调用了Gen_hash类中的hash函数
	+5 给定key，调用GetValue类，查找对应的value,返回的是文件夹key里的字符串形式的全部内容：getValue(String key)	
*/

/*再单独封装一个GetValue类：
 * 	给定路径，进行读文件操作，查找对应的value：getValue(String key)	
 * 
 */

public class Blob {
	
	private String filePath;    // filePath一定是一个文件路径，而不是一个文件夹路径
	private  String objectPath;
	private  String key;
	

	//构造函数，输入原始路径名，和想要放进文件的新路径名。
	//由构造函数，便可完成把原始路径对应的Blob, “拷贝”进新路径里
	public Blob( String filePath, String objectPath) throws IOException{
		this.filePath = filePath;
		this.objectPath  = objectPath;
		key = addFile( );   //在这个函数里，会往新路径里写入文件内容
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
	 
	//4 给定value的原文件名，向存储中添加对应的key-value的函数：addFile(String filePath)，返回的是原文件的key
	 public  String addFile() throws IOException{
		 Gen_hash t = new Gen_hash();
		 this.key = t.hash (filePath);
		 File newFile = new File( objectPath + "\\"+ key);   //括号中的是新的文件路径名
		 
		 if( !newFile.exists() ) {          //如果已有同名文件则不会再生成。
			 newFile.createNewFile();   
				
			 FileInputStream fileis = new FileInputStream(filePath);
			 FileOutputStream fileos = new FileOutputStream(newFile);
			 
			 byte[] buffer = new byte[1024];  //定义一个字节缓冲区
			 int numRead = 0;
			 
			 do {
				 numRead = fileis.read(buffer);
				 if( numRead > 0 ) {
					fileos.write(buffer, 0, numRead); 
					 
				 }
			 } while ( numRead != -1);
			 
			fileis.close();
			fileos.close(); 
		 }
		 	 
		 return key;
	 }
	 
	//	+5 给定key，调用GetValue类，查找对应的value。返回的是文件夹key里的字符串形式的全部内容：getValue( )	
	 public  String getValue ( ) throws FileNotFoundException  {
		 GetValue t = new GetValue();
		 return t.getValue( objectPath +"//" + key );
		
	 }
	 
	

}
