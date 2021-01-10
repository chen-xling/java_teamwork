package test;
//implements wisdom, everything_beautiful 
public class Friend {
	public String name;
	public final int age = 18;
	public final int bug = 0;
	Friend ( String name ){
		this.name = name;
	}
	public void best_wishes () {
		System.out.println( "魔镜魔镜，快告诉我谁是世界上最美丽的女人？" );
		System.out.println( "My lady, honestly she is " + name );
		System.out.println( "魔镜魔镜，快告诉我她现在在哪里？" );
		System.out.println( "My lady, she is at School of Software & Microelectronics, PKU.");
		java.util.Date today = new java.util.Date();
		System.out.println( "Right this beautiful night, tell me her age: " + age);
		for( int i=1; i<=6; i++) {	
			System.out.println(  i*10 +" years later, tell me her age: " + age);
		}
		System.out.println( today.toString() + ", " + name + "生日快乐！找着调了要请喝奶茶哦~");
		
	}

	

}
