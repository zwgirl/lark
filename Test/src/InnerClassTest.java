import java.util.HashMap;


public class InnerClassTest {
	public static void main(String[] args) {

		
		InnerClass iClass = new InnerClass();
		iClass.
		InnerClass.MemberClass mClass = iClass.new MemberClass();
		mClass.getAddr();
		
		String s = InnerClass.StaticMemberClass.age;
		
		InnerClass.StaticMemberClass sClass = new InnerClass.StaticMemberClass();
		sClass.getAddr();
		
		
		class TTT{
			HashMap hh;
		}
		
		TTT t = new TTT();

	}
}
