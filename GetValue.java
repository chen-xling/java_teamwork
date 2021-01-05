package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


//本类，用于读取一个文件中的全部内容，返回字符串形式的value
public class GetValue {
	
	 public  String getValue ( String path )  throws FileNotFoundException {
		 File file = new File ( path );
		 try {
			 Scanner fileInput = new Scanner (file); // 扫描读入的是新路径的文件的内容
             StringBuilder string = new StringBuilder();

             if(fileInput.hasNextLine())      //多写一步，是为了不让文件末尾也有换行符，我选择把换行符放在新增内容的开头
                 string.append( fileInput.nextLine());
             while (fileInput.hasNextLine()) {
                 string.append('\n').append( fileInput.nextLine());
             }
             fileInput.close();
             return string.toString();
             
		 }
		 catch (Exception c) {
			 System.out.println("路径对应的文件不存在");
			 return null;
		 }	 
	 }
	 
	 /*不知道为什么，这个方法用着用着后来又报bug了，于是我就彻底放弃了这种读取文件的方法
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
					 stringbuffer.append( new String(buffer) ,0, numRead );  //注意事项一！字符数组没有toString方法
					 //stringbuffer.append( new String(buffer)  );     //注意事项二！如果写成这样，读取的末尾会有很多空格
					 /*注意事项三，append( new String(buffer) ,0, numRead )这种方法中，如果文件内容中带有中文，读取到的文件末尾仍然会有7到13个空格。
						我还没有想明白这个。但是改用Scanner(File)中的input.hasNextLine方法就没有这些奇怪的问题
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
	 */
	 
	 
}
