package test;
//旧版任务二，理解错题意啦--

//本页做的是以下工作：将原始路径的文件或文件夹，复制到新路径去。新文件夹和文件的名字为相应内容的哈希码
/*
 * 定义一个TeamTask2类：
 * 数据域：
 * -filePath ：传入的文件夹目录
 * -newFilePath ：新生成的文件夹目录
 * 
 * 方法：
 * +构造方法(filePath, newFilePath)  //在创建TeamTask2类时，调用构造函数即可完成全部copy
 * +boolean gen_file()   //实现主要功能
 * +String getKey()
 * +String getFilePath()
 * +String getNewFilePath()
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TeamTask2_outdated {
	private  String filePath;   
	private  String newFilePath;
	private  String key;   
	//public   boolean flag;
	
	
	TeamTask2_outdated( String filePath, String newFilePath ) throws IOException{
		this.filePath = filePath;
		this.newFilePath = newFilePath;
		Gen_hash t = new Gen_hash();
		this.key = t.hash(filePath);
		gen_file( filePath, newFilePath);
	}
	
	public boolean gen_file( String filePath, String newFilePath  ) {
		//分成三种情况，1.filePath是文件blob，2.是文件夹，里面全是blob, 3. 文件夹里还有文件夹
		try {
			File file1 = new File( filePath );  //file1是原始目录
		
			if(file1.isFile()) {  //直接是文件的话
				new TeamTask1( filePath, newFilePath );
				return true;
			}
			else if( file1.isDirectory() ) {
				File[] fileList = file1.listFiles();
				Gen_hash t2 = new Gen_hash();
				key = t2.hash(filePath);
				
				File file2 = new File ( newFilePath + "\\" + key );
				file2.mkdir();
				
				for ( int i=0; i<fileList.length; i++) {
					
					if( fileList[i].isFile() ) {
						new TeamTask1( fileList[i].getPath(), file2.getPath() );
					}
		
					if ( fileList[i].isDirectory() ) {
						gen_file( fileList[i].getPath(), file2.getPath() );
					}					
				}
				
				return true;
						
			}			
		
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
		return false;

	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public String getNewPath() {
		return newFilePath;
	}
	
	public String getKey() {
		return key;
	}
	

}
