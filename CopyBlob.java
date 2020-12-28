package test;

import java.io.*;

public class CopyBlob {
	
	private String filePath;
	private String goalPath;
	private String newName;
	
	public CopyBlob(String filePath, String goalPath, String newName ) throws IOException {
		this.filePath = filePath;
		this.goalPath = goalPath;
		this.newName = newName;
		gen_file();
		
	}
	//重载
	public CopyBlob(File file, String goalPath, String newName ) throws IOException {
		this.filePath = file.getPath();
		this.goalPath = goalPath;
		this.newName = newName;
		gen_file();		
	}
	

	public void gen_file() throws IOException {
		File goalFile =new File( goalPath + "\\" + newName);
		goalFile.createNewFile();
		FileInputStream fileis = new FileInputStream(filePath);
		FileOutputStream fileos = new FileOutputStream(goalFile);
		 
		byte[] buffer = new byte[1024];  //定义一个字节缓冲区
		int numRead = 0;
		 
		do {
			 numRead = fileis.read(buffer);
			 if( numRead > 0 ) {
				fileos.write(buffer, 0, numRead); 
				 
			 }
		} while ( numRead != -1);
		 
		fileis.close();
		fileos.close(); 
		
	}
	
}
