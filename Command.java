package test;

import java.io.IOException;


/*�����н����ࣺ
 ͨ����������Command.java�ļ���main���������ַ������飬ʵ�����¹��ܣ�

1.git initial "�ֿ�·��"  		//��δʵ��
 * 	��ʼ��������ֿ�·������Ϊ������Ŀ¼��Ĭ��Ϊmain��֧
2.git commit
 * 	�ڵ�ǰ��֧�£��ύ��ǰ�ֿ������
3.git history
 * 	�ڵ�ǰ��֧�£��鿴�ύ����commit��ʷ
4.git rollback "ĳ��commit�Ĺ�ϣֵ"
 * 	�ڵ�ǰ��֧�£�ʵ�ֻع���ĳһ����ʷ״̬
5.git branch "�µķ�֧��"
 * 	�½���֧
6.git checkout "���еķ�֧��"
 * 	�л�����һ����֧���������ļ��е�����Ҳ�����Ÿı䣬�൱�ڻع�



 */

public class Command {

	public static void main(String[] args) throws IOException {
		while(true) {
			if( args[0].equals("git") ) {
				String command = args[1];
				switch(command){
					case "initial":
						
					case "commit":
						new Commit( Global.filePath, Global.gitPath );
						break;
					case "history":
						Commit t = new Commit();
						System.out.println( t.get_all_commit() );
						break;
						
					case "rollback":
						
					case "checkout":
					
					case "branch":
						
					
				}
				
				
			}
			else {
				System.out.println("Sorry, this is not a git command. Try again.");
			}
			
		}
		
		
		

	}
		
		

}