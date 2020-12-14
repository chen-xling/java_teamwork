package test;
import java.util.*;
import java.io.*;
import java.security.MessageDigest;

/* 小组任务一要实现：
封装成一个类
	1. 给定value，向存储中添加对应的key-value
	2. 给定key，查找得到对应的value值

定义一个TeamTask1类，

数据域：
	-原始文件的路径： filePath
	-该文件的哈希值：key
	-新的文件路径：newPath
	-文件的内容：value

方法：
	+1 getFilePath
	+2 getNewPath
	+3 getKey
	+4 给定value，向存储中添加对应的key-value的函数，也就是创建同样内容但文件名为key的新文件：addFile(String filePath)
		返回值为key;其中调用了Gen_hash类中的hash函数
	+5 给定key，查找对应的value的函数,返回的是文件夹key里的字符串形式的全部内容：getValue(String key)	
*/

public class TeamTask1 {
	
	private String filePath;   
	private  String newPath;
	private  String key;
	
	//构造函数，输入原始路径名，和想要放进文件的新路径名
	TeamTask1( String filePath, String newPath) throws IOException{
		this.filePath = filePath;
		this.newPath  = newPath;
		key = addFile( );   //在这个函数里，会往新路径里写入文件内容
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public String getNewPath() {
		return newPath;
	}
	
	public String getKey() {
		return key;
	}
	 
	//4 给定value的原文件名，向存储中添加对应的key-value的函数：addFile(String filePath)，返回的是原文件的key
	 public  String addFile() throws IOException{
		 Gen_hash t = new Gen_hash();
		 this.key = t.hash (filePath);
		 File newFile = new File( newPath + "\\"+ key);   //括号中的是新的文件路径名
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
		 
		 
		 return key;
	 }
	 
	 //5 给定key，查找对应的value的函数,返回的是文件夹key里的字符串形式的全部内容：getValue(String key)
	 public  String getValue ( )  throws FileNotFoundException {
		 File file = new File (newPath + "\\"+ key);
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
	 
	

}
