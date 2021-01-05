package test;
import java.io.*;

/*
 * 本次任务：
 * 实现Commit
	给定一个工作区目录，生成对应的blob和tree(上周已完成)以及commit
	提示：
	需要存储指向当前最新commit的HEAD指针
	每次新生成一个commit前，需要把根目录的tree key与已有的最新commit的tree key进行比较，发现不相同时（即文件发生了变动）才添加这个commit
 * 
 * Commit：
	包含根目录tree对象的key   //第一行
	包含前一次commit的key，即前一次commit的文件名    //第二行
	以上两行构成本次commit的value，本次commit的key就是以上两行内容的哈希
	第一次commit没有前一次的commit.   
 */

/*设计思路
 * 定义一个叫做Commit的类：
 * 数据域：
 * -String filePath           //工作区目录
 * -String gitPath            //存放commit文件的目录
 * -String current_tree_key   //本次要commit的文件夹的哈希值tree key
 * -String last_tree_key       //上一次commit的tree key
 * -String head                 //上一次生成的commit文件的文件名
 * -String current_commit_key        //新生成的commit文件的文件名
 * -String value                     //本次commit文件的value   
 * 
 *方法：
 *+构造函数Commit(path1, path2)：调用构造函数，即可完成主要操作
 *+gen_commit()：被构造函数调用
 *+getValue():   获得本次commit的value
 *+get_all_commit(): 返回历史所有commit的哈希值，供回滚时使用
 * 
 * 
 * //原本我想的是使用静态变量count来计数，当它为0时，没有提交过。
	 * 但是它只能在内存中，无法保存下来，所以我另写了一个HEAD类，用于：
	 * （1）生成或更新含有最新commit哈希值的HEAD文件：update_head()
	 * （2）获得HEAD文件里面的内容，即之前最新的commit的哈希值：get_head()
	 * （3）获得上一次commit的tree_key: get_last_tree_key()
 */

public class Commit {

	protected String filePath ;   //工作区目录
	protected String gitPath;      //存放commit文件的目录
	
	private String head;             //上一次生成的commit文件的文件名，也可命名为 last_commit_key
	private String current_tree_key;   //新生成的commit文件的文件名
	private String last_tree_key;         //上一次commit的tree key
	private String current_commit_key;     //新生成的commit文件的文件名
	private StringBuffer value = new StringBuffer();    //本次commit文件的内容
	
	
	Commit(){};  //可以用默认的filePath和gitPath
	
	Commit(String  path1, String gitPath ) throws IOException{
		this.filePath = path1;
		this.gitPath = gitPath;
		gen_commit();
	}
	
	public void gen_commit() throws IOException {
		Gen_hash t = new Gen_hash();     //调用Gen_hash获得文件哈希值
		Tree tree = new Tree( filePath, Global.objectPath );   //工作区目录的所有新的Tree和Blob都要存在同一个Object文件夹中。
	
		this.current_tree_key = t.hashString( tree.getValue() );   //生成本次要提交的文件夹的哈希值
	
		
		
		HEAD g = new HEAD();            //调用HEAD类，获得之前最新的commit有关信息
		this.head = g.head;
		this.last_tree_key = g.get_last_tree_key();
		
		if( !current_tree_key.equals(last_tree_key) )  {      //判断内容要用equal，而不是判断字符串的地址==
			this.value.append(this.current_tree_key);
			this.value.append("\n");
			this.value.append(this.head);
			current_commit_key = t.hashString( value.toString() );
			File commit = new File (gitPath + "\\" + current_commit_key );
			commit.createNewFile();
			PrintWriter p = new PrintWriter (commit);
			p.write( this.value.toString() );  
			p.close();                            //输出流要及时关闭
			g.update_head( current_commit_key );  //更新HEAD文件；在哪个分支下面，就写入到哪个分支文件夹下的HEAD.txt
			
		}
			
		else {
			System.out.println("本次commit无更新。");
		}
	}
	
	public String getValue() {
		return this.value.toString();
	}
	
	//返回历史所有commit的哈希值，供回滚时使用
	public String get_all_commit() throws FileNotFoundException { 
		
		//得到当前分支Branch的文件夹路径
		GetValue t = new GetValue();
		String current_branch_path = Global.branches + "\\" + t.getValue( Global.save_current_branch_file );
		
		//得到当前分支下的HEAD文件内容
		String all_commit = t.getValue( current_branch_path + "\\" + "HEAD" );
		return all_commit;
	}
	

}
