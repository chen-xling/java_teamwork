package test;

import java.io.File;


public class DeleteFolder {

		DeleteFolder( String folderPath ){
			delete_file (  folderPath );		
		}
		
		//重载
		DeleteFolder( File folder ){
			delete_file ( folder.getPath() );	
		}
		
		
		//第一版本的删文件夹函数：传入的路径只能是文件夹路径，不能是文件
		public void delete_file( String folderPath ) {
			File folder = new File( folderPath );
			File[] fileList = folder.listFiles();
			if( fileList != null ) {
				for(File f: fileList) {
					if( f.isFile() )
						f.delete();
					else if (f.isDirectory()){
						delete_file_completely( f.getPath() ); 
					}
				}
			}
			
			
		}
		
		//以下是第二版本的删除文件函数
		//用于删除整个文件夹或文件。如果传入的是文件夹，会把最大的这个文件夹也删掉
		public void delete_file_completely ( String folderPath ) { 
			File folder = new File( folderPath);
			if(folder.isFile()) {
				folder.delete();
			}
			else if(folder.isDirectory()) {
				File[] fileList = folder.listFiles();
				if( fileList != null ) {
					for(File f: fileList) {
						if(f.isFile()) 
							f.delete();
						else if(f.isDirectory())
							delete_file_completely( f.getPath());
					}
				}
				folder.delete();
			}
		}
	
	
}
