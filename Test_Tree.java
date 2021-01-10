package test;

import java.io.IOException;

public class Test_Tree   {
	
	public static void main(String[] args) throws IOException, Exception {
		String filePath = "D:\\Users\\chenxling\\Pictures\\py图";
		//String filePath = "C:\\Users\\chenxling\\Desktop\\workspace\\Java课程作业";
	    String objectPath = Global.objectPath;
		Tree  temp = new Tree( filePath, objectPath );  
				
		System.out.println("原始路径名为： " + temp.getFilePath() );
		System.out.println("新的路径名为： " + temp.getObjectPath() );
		System.out.println("该文件的key为： " + temp.getKey() );
		System.out.println("新生成的文件内容为：\n" +  temp.getValue() );
		
		
	}
	
	
	
}
