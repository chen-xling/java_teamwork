package test;

import java.io.IOException;

public class Test_Tree   {
	
	public static void main(String[] args) throws IOException, Exception {
		String filePath = "D:\\Users\\chenxling\\Pictures\\pyͼ";
		//String filePath = "C:\\Users\\chenxling\\Desktop\\workspace\\Java�γ���ҵ";
	    String objectPath = Global.objectPath;
		Tree  temp = new Tree( filePath, objectPath );  
				
		System.out.println("ԭʼ·����Ϊ�� " + temp.getFilePath() );
		System.out.println("�µ�·����Ϊ�� " + temp.getObjectPath() );
		System.out.println("���ļ���keyΪ�� " + temp.getKey() );
		System.out.println("�����ɵ��ļ�����Ϊ��\n" +  temp.getValue() );
		
		
	}
	
	
	
}
