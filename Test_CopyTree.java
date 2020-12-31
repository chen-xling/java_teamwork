package test;

import java.io.IOException;

public class Test_CopyTree {

	public static void main(String[] args) throws IOException {

		String filePath = "D:\\AsimpleGit\\branches\\mainCommitPath";
		String goalPath = "D:\\AsimpleGit\\test";
		new CopyTree ( filePath, goalPath );
		System.out.println("复制文件夹成功！");
	

	}

}
