package test;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;
import java.security.MessageDigest;


public class Gen_hash{
	/*
		这是专门生成文件或文件夹的哈希码的函数
	} */
	
	//1 返回blob的哈希值的“字符数组”表示
	 public byte[] SHA1Checksum( InputStream is ) throws NoSuchAlgorithmException, IOException  {   //形参是文件输入流
	        
	        byte[] buffer = new byte[1024];    // 用于计算hash值的文件缓冲区
	        MessageDigest complete = MessageDigest.getInstance("SHA-1");  // 使用SHA1哈希/摘要算法
	        int numRead = 0;
	        do {
	            numRead = is.read(buffer);   // 从is输入流中读取buffer.length个（这个是读取的上限）字节，存到buffer中
	            							//并将实际读取到的字节数返回给numRead。
	            							//当读取到文件末尾时，返回-1；
	            if (numRead > 0) {
	                complete.update(buffer, 0, numRead);   
	                	//该函数update（第一个参数表示所要增加到complete的内容，
	                	//第二个参数代表buffer的读取起始点，第三个参数代表buffer读取多长）
	            } 
	        } while (numRead != -1 );    // numRead == -1代表文件读取完毕了
	        is.close();      			// 关闭输入流 ??好像不能关，后面还要用到 
	        return complete.digest(); 
	        // 返回SHA1哈希值,其中MessageDigest对象的digest()方法返回的是字节数组，接下去还要转换成16进制
	    }
	 
	 //2 由字符数组生成十六进制哈希值的函数:
	 public String convertToHexString(byte data[]) { 
	    	// 由于MessageDigest对象的digest()方法返回的是字符数组，
	    	//要得到十六进制的sha1值还需要转为字符串  
	    	StringBuffer strBuffer = new StringBuffer(); 
	    	
	    	for(int i = 0; i< data.length; i++) {
	    		if( (int)data[i]<16 && (int)data[i] >=0 ) {   //修正了不足16（4位）的问题，补0，同时考虑到11111111为负数的情况
	    			strBuffer.append("0").append(Integer.toString(0xff & data[i], 16));
	    			continue;
	    		}
	    		strBuffer.append(Integer.toString(0xff & data[i], 16));
	    		}
	    	return strBuffer.toString();
	}
	 
	//3 path可以是文件夹（即目录tree），也可以是文件（blob), 返回值为目录或文件的哈希值	 
    public String hash( String path  ) { 
    	//三种情况：1，path直接是文件，2，文件夹里只有文件 3，文件夹里还有文件夹
    	 
    	try {
    		File dir = new File(path); // 以Path路径新建一个File对象，路径名可能是一个目录或是一个文件
		
    		if ( dir.isFile() ) {
    			FileInputStream is = new FileInputStream( dir );
    			byte[] data = SHA1Checksum( is ) ;
    			String result = convertToHexString ( data );
    			return result;
				}
		
    		MessageDigest m = MessageDigest.getInstance("SHA-1");
    		if ( dir.isDirectory()) {
    			File[] fs = dir.listFiles(); 
			
    			for(int i = 0; i < fs.length; i++) { 
    				if(fs[i].isFile()) { 		
    					String blob = hash( fs[i].getPath() );  //递归，得到blob的哈希值
    					m.update(blob.getBytes());   //把哈希码存到上一级的内容中去，效果最后是哈希码的哈希码
    					m.update(fs[i].getName().getBytes());  //把文件名存进去
    				}
    				if(fs[i].isDirectory()) { 
    					
    					m.update(fs[i].getName().getBytes());    //把文件夹名字加进去
    					String tree = hash(path + File.separator + fs[i].getName());   //用递归，把文件内容加进去
    				
    					m.update(tree.getBytes()); 
    				}	
    		
    			}
			}
			return convertToHexString( m.digest() );
		
		} 
    	catch (Exception e) {
			e.printStackTrace();
			return  "error! 路径不存在";
		}
		
    }
    
    
    public String hashString( String s)  {
    	try {
    		MessageDigest m = MessageDigest.getInstance("SHA-1");
        	m.update( s.getBytes() );    //或许还可以设置一个缓冲区，不过这样直接转也行
        	return convertToHexString( m.digest() );
    	}
    	catch( NoSuchAlgorithmException ex) {
    		return "NoSuchAlgorithmException error!!";
    	}
    	
    }

}

