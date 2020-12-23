package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GetValue {
	
	 public  String getValue ( String path)  throws FileNotFoundException {
		 File file = new File ( path );
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
			 System.out.println("路径对应的文件不存在");
			 return null;
		 }
		 
	 }
}
