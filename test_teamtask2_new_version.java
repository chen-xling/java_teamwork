package test;

import java.io.IOException;

public class test_teamtask2_new_version   {
	
	public static void main(String[] args) throws IOException, Exception {
		String filePath = "D:\\Users\\chenxling\\Pictures\\py图";
	    String newPath = "D:\\Ayingyong5_test";
		Tree  temp = new Tree( filePath, newPath );  
		
		System.out.println("原始路径名为： " + temp.getFilePath() );
		System.out.println("新的路径名为： " + temp.getNewFilePath() );
		System.out.println("该文件的key为： " + temp.getKey() );
		System.out.println("新生成的文件内容为：\n" +  temp.getValue() );
		
		
	}
	
	
	
}
