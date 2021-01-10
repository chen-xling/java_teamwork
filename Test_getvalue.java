package test;

import java.io.FileNotFoundException;

public class Test_getvalue {

	public static void main(String[] args) throws FileNotFoundException {
		GetValue t = new GetValue( );

		String a = t.getValue( Global.objectPath + "\\" + "cbd62c4b142d48c3d1f9adf8f64fc3a294f91cc2");
		System.out.println("新生成的文件内容为11111：\n" +  a );
	}

}
