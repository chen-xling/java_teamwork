package test;
import java.io.*;

//这是一个测试文件

/* 小组任务一要实现：
封装成一个类
	1. 给定value，向存储中添加对应的key-value
	2. 给定key，查找得到对应的value值
*/



public class test_teamwork1 {

	public static void main(String[] args)throws IOException, Exception  {
		String filePath = "D:\\Atest_teamtask1\\introduce.txt";
        String newPath = "D:\\Ayingyong5_test\\";
		TeamTask1 teamtask1 = new TeamTask1( filePath, newPath );  //调用已经写好的TeamTask1类 
		
		String test_key = teamtask1.key;
		String content = teamtask1.getValue(test_key);
		
		System.out.println("原始路径名为： " + teamtask1.filePath );
		System.out.println("新的路径名为： " + teamtask1.newPath );
		System.out.println("该文件的key为： " + test_key);
		System.out.println("给定key值，查找到文件的内容为： " +  content);
		
		
		
		
		

	}

}
