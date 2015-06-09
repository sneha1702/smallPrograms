import java.util.Scanner;
public class palindrome {

	public static void main(String[] args){
		System.out.println("Enter String ");
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		boolean status = isPalindrome(str);
		if(status){
			System.out.println("String is palindrome");
		}
		else{
			System.out.println("String is not palindrome");
		}
			
	}
	
	private static boolean isPalindrome(String str){
		if(str.length()==0 || str.length()==1){
			return true;
		}
		else if(str.charAt(0)==str.charAt(str.length()-1)){
				return isPalindrome(str.substring(1, str.length()-1));
			}
		return false;	
		}
			
	
	
}
