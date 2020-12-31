package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


//本类，用于读取一个文件中的全部内容，返回字符串形式的value
public class GetValue {
	
	 public  String getValue ( String path)  throws FileNotFoundException {
		 File file = new File ( path );
		 try {
			FileInputStream fileis = new FileInputStream(file);
			 byte[] buffer = new byte[1024];  //定义一个字节缓冲区
			 int numRead = 0;
			 StringBuffer stringbuffer = new StringBuffer();
			
			 do {
				 numRead = fileis.read(buffer);
				 if( numRead > 0 ) {
					 stringbuffer.append( new String(buffer) );  //一定要注意，字符数组没有toString方法
				 }
			 } while ( numRead != -1);
			fileis.close(); 
			return stringbuffer.toString();
		 }
		 catch (Exception c) {
			 System.out.println("路径对应的文件不存在");
			 return null;
		 }	 
	 }
	 
	 //重载
	 public  String getValue ( File file)  throws FileNotFoundException {
		 try {
			FileInputStream fileis = new FileInputStream(file);
			 byte[] buffer = new byte[1024];  //定义一个字节缓冲区
			 int numRead = 0;
			 StringBuffer stringbuffer = new StringBuffer();
			
			 do {
				 numRead = fileis.read(buffer);
				 if( numRead > 0 ) {
					 stringbuffer.append( new String(buffer));
				 }
			 } while ( numRead != -1);
			fileis.close(); 
			return stringbuffer.toString();
		 }
		 catch (Exception c) {
			 System.out.println("路径对应的文件不存在");
			 return null;
		 }	 
	 }
	 
	 
}
