package test;

import java.io.IOException;

/*
小组任务2：
	给定一个文件夹目录，将其转化成若干tree和blob
	深度优先遍历此目录
	遇到子文件就转化成blob并保存
	遇到子文件夹就递归调用其内部的子文件/文件夹最后构造tree并保存

	使用任务1提供的接口 --- hash表
	单元测试
 * 
 * 
 */
public class test_teamtask2 {

	public static void main(String[] args) throws IOException {
		String path1 = "C:\\Users\\chenxling\\Desktop\\workspace\\Java课程作业";
		String path2 = "D:\\Ayingyong5_test";
		
		TeamTask2  temp = new TeamTask2( path1, path2 );
		//由构造函数，就已经完成了全部文件的写入操作
		
		System.out.println("原始路径名为： " + temp.getFilePath() );
		System.out.println("新的路径名为： " + temp.getNewPath() );
		System.out.println("该文件的key为： " + temp.getKey() );

		

	}

}

