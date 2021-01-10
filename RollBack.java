package test;

import java.io.*;
import java.util.Scanner;

/*ʵ�ֻع�����
 *1.��ʵ�����Ǹ���һ���汾�ţ�Ҳ����ĳ��commit���ļ����� ��ȡ���ļ����ĵ�һ�У�
 *�͵õ����Ǵ�commit���ύ���������ļ��е�tree
 *�ٸ���tree��������ݣ�һһ���ļ����Ƶ�Ŀ��·��(�����Ŀ��·���µ��ļ�����
 *	���ļ�����blob, �͸������Ĺ�ϣ�룬ȥ���object��·�����ҵ�����Ȼ���Ƶ�Ŀ��·��
 *	���ļ�����Tree, ����Ŀ��·�����½�һ�����ļ��У����Ҹ������Ĺ�ϣ�룬ȥ���object��·�����ҵ���ӦTree�ļ����ݹ顣
 *2.�ڻع�֮ǰ���½�һ����֧������֮�������ع����µķ�֧�����ǵ�ǰ��֧���µ�commit��ϣֵ
 *3.����HEAD�ļ����ָ�����Ӧ����ʷ״̬
 *
 */

/*����һ��object·����
 * 	���ڵ�һ��commit, �����е�tree��blob�浽���·���¡�
 * 		����ǲ�ͬ�ļ�������������ȫ��ͬ�����ļ���ֻ�ᱣ��һ��blob��������������
 * 	���ڵڶ���commit��
 * 		���ĳЩ���ļ��Ķ��ˣ�����ڸ�object·����������Ӧ��tree��blob��ͬʱ�ɵ�tree��blob���ᱻɾ����
 * 		���������ȫ�µ��ļ�������object·���������µ�tree��blob
 * 	�ɴˣ����֪���е���ʷ�汾���ļ����ݡ�
 * 
 */


public class RollBack {
	private String treeKey;
	private String treePath;
	private String goalPath = Global.filePath;  //�ع��������ļ�ȫ���������·���£�Ҳ��������չ�������Ȼ��������乤����
	private String current_branch_path;
	
	RollBack( String old_commit ) throws IOException {
		
		//�õ���ǰ��֧Branch���ļ���·��
		Global g = new Global();
		current_branch_path = Global.gitPath;
		
		
	
		File file1 = new File(  current_branch_path + "\\" + old_commit );
		Scanner input1 = new Scanner( file1 );
		treeKey = input1.nextLine();
		input1.close();
		
		//�Ȱѹ������ļ���ɾ�ɾ���ע�������ļ��б�����ɾ����
		File folder = new File ( goalPath );
		new DeleteFolder(folder); 
		
		treePath = Global.objectPath + "\\" + treeKey;
		
		gen_file( treePath, folder.getPath() );
		System.out.println("�ع��ɹ���");
		
	}
	
	//����tree�ļ������ݣ���ԭ����ʷ״̬���ļ���
	public boolean gen_file( String treePath, String goalPath ) throws IOException {
		File file2 = new File ( treePath );
		Scanner input2 = new Scanner( file2 );
		String oneLine = null;
		Tree_content  read_a_line = new Tree_content();
		
		while ( input2.hasNextLine() ) {
			oneLine = input2.nextLine();         //���ζ�ȡtree�ļ���ÿһ�е�����
			read_a_line.get_tree_content(oneLine);
			
			String copyedPath = Global.objectPath + "\\" + read_a_line.getKey();  //���ǽ�����ԭ���ļ���·��
			
			if (read_a_line.getType().equals("Blob")) {       //����ע�ⲻ����==   
				new CopyBlob( copyedPath, goalPath, read_a_line.getName() );
			}
			else if (read_a_line.getType().equals("Tree")) {
				String sec_file_path = goalPath + "\\" + read_a_line.getName();
				File sec_file = new File( sec_file_path  );
				sec_file.mkdir();        //���������ļ���
				
				gen_file( copyedPath ,sec_file_path  );
			}
						
		}	
		input2.close();	
		return true;
	}
}
