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
	原始文件的路径：filePath
	该文件的哈希值：key
	新的文件路径：newPath
	文件的内容：value

方法：
	1 返回哈希值的“字符数组”表示：public static byte[] SHA1Checksum(InputStream is) throws Exception
	2 由字符数组生成十六进制哈希值的函数: public static String convertToHexString(byte data[])
	3 由文件路径，得到文件内容的哈希值：hash(String filePath)
	4 给定value，向存储中添加对应的key-value的函数，也就是创建同样内容但文件名为key的新文件：addFile(String filePath)
	5 给定key，查找对应的value的函数,返回的是文件夹key里的字符串形式的全部内容：getValue(String key)	
*/

public class TeamTask1 {
	
	public String filePath;
	public String newPath;
	public String key;
	public String value;
	
	//构造函数，输入原始路径名，和想要放进文件的新路径名
	TeamTask1( String filePath, String newPath) throws IOException{
	
		
		this.filePath = filePath;
		this.newPath = newPath;
		key = addFile( filePath );
		value = getValue(key);
	}
	

	 public static byte[] SHA1Checksum( InputStream is ) throws Exception {   //形参是文件输入流
	        
	        byte[] buffer = new byte[1024];    // 用于计算hash值的文件缓冲区
	        
	        MessageDigest complete = MessageDigest.getInstance("SHA-1");  // 使用SHA1哈希/摘要算法
	        int numRead = 0;
	        do {
	            numRead = is.read(buffer);   // 从is输入流中读取buffer.length个（这个是读取的上限）字节，存到buffer中
	            							//并将实际读取到的字节数返回给numRead。
	            							//当读取到文件末尾时，返回-1；
	            if (numRead > 0) {
	                
	                complete.update(buffer, 0, numRead);   
	                	//该函数update（第一个参数表示所要增加到complete的内容，
	                	//第二个参数代表buffer的读取起始点，第三个参数代表buffer读取多长）
	            } 
	        } while (numRead != -1);    // numRead == -1代表文件读取完毕了
	        //is.close();      			// 关闭输入流 ??好像不能关，后面还要用到 
	        return complete.digest(); 
	        // 返回SHA1哈希值,其中MessageDigest对象的digest()方法返回的是字符数组，接下去还要转换成16进制
	    }
	 
	 public static String convertToHexString(byte data[]) { 
	    	// 由于MessageDigest对象的digest()方法返回的是字符数组，
	    	//要得到十六进制的sha1值还需要转为字符串  
	    	StringBuffer strBuffer = new StringBuffer(); 
	    	for (int i = 0; i < data.length; i++) {
	    		strBuffer.append(Integer.toHexString(0xff & data[i])); // 用十六进制数oxff与某个字节值做按位与运算，
											// 只保留了32位的最后8位，保证负数转换成十六进制不会出错
	    		}
	    	return strBuffer.toString();
	}
	 
	 //3 由文件路径，得到文件内容的哈希值：hash(String filePath)
	 public static String hash ( String path) {
		 try {
			 File file = new File ( path);   //把文件路径转换成文件类的对象
			 FileInputStream is = new FileInputStream (file);
			 byte[] sha1 = SHA1Checksum(is);     //形参是文件输入流
			 String result_hash = convertToHexString( sha1);
			 return result_hash;
		 }
		 
		 catch(Exception e) {
			 return "error! 路径不存在";
		 }
	
	 }
	 
	//4 给定value的原文件名，向存储中添加对应的key-value的函数：addFile(String filePath)，返回的是原文件的key
	 public  String addFile(String filePath) throws IOException{
		 String key = hash (filePath);
		 File newFile = new File( newPath + key);   //括号中的是新的文件路径名
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
	 public  String getValue ( String key )  throws FileNotFoundException {
		 File file = new File (newPath + key);
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
