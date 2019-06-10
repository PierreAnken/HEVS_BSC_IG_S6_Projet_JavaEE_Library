package library.toolbox;

public class Tb{
	
	public static boolean stringExists(String str) {
		if(str == null)
			return false;
		
		if(str.length() == 0)
			return false;
		
		return true;
	}
}
