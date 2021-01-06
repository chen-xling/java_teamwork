package test;

import java.io.File;
import java.io.IOException;

public class Test_CopyBlob {

	public static void main(String[] args) throws IOException {
		String filePath = "D:\\Ajava_gitPath\\1cc3e819b6ff0f282e3f3d04ec22e4df82f02950";
		File file = new File(filePath);
		String goalPath = "D:\\AsimpleGit";
		CopyBlob s = new CopyBlob( file, goalPath , "Ä³ÎÄ¼þ");
	}

}
