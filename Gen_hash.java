package test;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;
import java.security.MessageDigest;


public class Gen_hash{
	/*
		����ר�������ļ����ļ��еĹ�ϣ��ĺ���
	} */
	
	//1 ����blob�Ĺ�ϣֵ�ġ��ַ����顱��ʾ
	 public byte[] SHA1Checksum( InputStream is ) throws NoSuchAlgorithmException, IOException  {   //�β����ļ�������
	        
	        byte[] buffer = new byte[1024];    // ���ڼ���hashֵ���ļ�������
	        MessageDigest complete = MessageDigest.getInstance("SHA-1");  // ʹ��SHA1��ϣ/ժҪ�㷨
	        int numRead = 0;
	        do {
	            numRead = is.read(buffer);   // ��is�������ж�ȡbuffer.length��������Ƕ�ȡ�����ޣ��ֽڣ��浽buffer��
	            							//����ʵ�ʶ�ȡ�����ֽ������ظ�numRead��
	            							//����ȡ���ļ�ĩβʱ������-1��
	            if (numRead > 0) {
	                complete.update(buffer, 0, numRead);   
	                	//�ú���update����һ��������ʾ��Ҫ���ӵ�complete�����ݣ�
	                	//�ڶ�����������buffer�Ķ�ȡ��ʼ�㣬��������������buffer��ȡ�೤��
	            } 
	        } while (numRead != -1 );    // numRead == -1�����ļ���ȡ�����
	        is.close();      			// �ر������� ??�����ܹأ����滹Ҫ�õ� 
	        return complete.digest(); 
	        // ����SHA1��ϣֵ,����MessageDigest�����digest()�������ص����ֽ����飬����ȥ��Ҫת����16����
	    }
	 
	 //2 ���ַ���������ʮ�����ƹ�ϣֵ�ĺ���:
	 public String convertToHexString(byte data[]) { 
	    	// ����MessageDigest�����digest()�������ص����ַ����飬
	    	//Ҫ�õ�ʮ�����Ƶ�sha1ֵ����ҪתΪ�ַ���  
	    	StringBuffer strBuffer = new StringBuffer(); 
	    	
	    	for(int i = 0; i< data.length; i++) {
	    		if( (int)data[i]<16 && (int)data[i] >=0 ) {   //�����˲���16��4λ�������⣬��0��ͬʱ���ǵ�11111111Ϊ���������
	    			strBuffer.append("0").append(Integer.toString(0xff & data[i], 16));
	    			continue;
	    		}
	    		strBuffer.append(Integer.toString(0xff & data[i], 16));
	    		}
	    	return strBuffer.toString();
	}
	 
	//3 path�������ļ��У���Ŀ¼tree����Ҳ�������ļ���blob), ����ֵΪĿ¼���ļ��Ĺ�ϣֵ	 
    public String hash( String path  ) { 
    	//���������1��pathֱ�����ļ���2���ļ�����ֻ���ļ� 3���ļ����ﻹ���ļ���
    	 
    	try {
    		File dir = new File(path); // ��Path·���½�һ��File����·����������һ��Ŀ¼����һ���ļ�
		
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
    					String blob = hash( fs[i].getPath() );  //�ݹ飬�õ�blob�Ĺ�ϣֵ
    					m.update(blob.getBytes());   //�ѹ�ϣ��浽��һ����������ȥ��Ч������ǹ�ϣ��Ĺ�ϣ��
    					m.update(fs[i].getName().getBytes());  //���ļ������ȥ
    				}
    				if(fs[i].isDirectory()) { 
    					
    					m.update(fs[i].getName().getBytes());    //���ļ������ּӽ�ȥ
    					String tree = hash(path + File.separator + fs[i].getName());   //�õݹ飬���ļ����ݼӽ�ȥ
    				
    					m.update(tree.getBytes()); 
    				}	
    		
    			}
			}
			return convertToHexString( m.digest() );
		
		} 
    	catch (Exception e) {
			e.printStackTrace();
			return  "error! ·��������";
		}
		
    }
    
    //4 hashString�������ڷ����ַ����Ĺ�ϣֵ
    public String hashString( String s)  {
    	try {
    		MessageDigest m = MessageDigest.getInstance("SHA-1");
        	m.update( s.getBytes() );    //������������һ������������������ֱ��תҲ��
        	return convertToHexString( m.digest() );
    	}
    	catch( NoSuchAlgorithmException ex) {
    		return "NoSuchAlgorithmException error!!";
    	}
    	
    }

}

