package test;

import java.io.File;

//此类用于删除整个文件夹或文件
public class DeleteFolder {

		DeleteFolder( String folderPath ){
			delete_file (  folderPath );		
		}
		
		//重载
		DeleteFolder( File folder ){
			delete_file ( folder.getPath() );	
		}
		
		public void delete_file ( String folderPath ) {
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
							delete_file( f.getPath());
					}
				}
				folder.delete();
			}
		}
	
	
}
