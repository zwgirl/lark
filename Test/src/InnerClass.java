
public class InnerClass {

	String name1;
	public class MemberClass{
		String addr;
		public MemberClass() {
			this.addr = name;
		}
		
		public String getAddr(){
			return addr;
		}
	}
	
	public static String sName;
	public static class StaticMemberClass{
		static final String age = "100";
		String addr;
		public StaticMemberClass() {
			this.addr = sName;
		}
		
		public String getAddr() {
			return addr;
		}
	}
	
	interface LL{
		
	}
	
	static void  test(){
		class LocalClass{
			String name = InnerClass.sName;
			int age =10;
		}
		
//		interface SS{
//			
//		}
		
		
		
		LocalClass lClass = new LocalClass();
		String name = lClass.name;
	}
}
