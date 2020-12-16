package test;
import java.io.IOException;

//旧版任务二，理解错题意啦--

public class test_teamtask2_outdated {

	public static void main(String[] args) throws IOException {
		String path1 = "C:\\Users\\chenxling\\Desktop\\workspace\\Java课程作业";
		String path2 = "D:\\Ayingyong5_test";
		
		TeamTask2_outdated  temp = new TeamTask2_outdated( path1, path2 );
		//由构造函数，就已经完成了全部文件的写入操作
		
		System.out.println("原始路径名为： " + temp.getFilePath() );
		System.out.println("新的路径名为： " + temp.getNewPath() );
		System.out.println("该文件的key为： " + temp.getKey() );

		

	}

}

