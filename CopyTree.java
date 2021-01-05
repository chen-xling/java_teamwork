package test;

import java.io.File;
import java.io.IOException;

//本类是一个工具类，用于复制文件夹的全部内容

public class CopyTree {
	
	    //filePath是原文件夹的路径名
	    //goalPath是新文件夹的路径名      //传入之前，文件夹已存在，最好是空文件夹，但没有强制要求
	CopyTree( String filePath, String goalPath ) throws IOException{
		gen_file(filePath, goalPath);	
	}
	CopyTree(){};
	
	public void gen_file( String filePath, String goalPath) throws IOException {
		File file = new File (filePath);
		File[] fileList = file.listFiles();
		
		for( int i=0; i<fileList.length; i++) {
			if( fileList[i].isFile() )
				new CopyBlob(fileList[i], goalPath );
			else if ( fileList[i].isDirectory() ) {
				File sec_file = new File( goalPath + "\\" + fileList[i].getName() );
				sec_file.mkdir();
				gen_file( fileList[i].getPath(), sec_file.getPath() );
				
				
			}
		}		
	}	
}
