
import java.util.Scanner;


public class isFibonacciNum {
	
	
	public static void main(String [] args) {
		System.out.println("Enter no ");
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		boolean result = isFibonacci(a);
		System.out.println("No is fibonacci "+result);
	}
	
	private static boolean isFibonacci(int n){
		 return isPerfectSquare(5*n*n + 4) ||
		           isPerfectSquare(5*n*n - 4);
	}
	
	private static boolean isPerfectSquare(int x)
	{
	    int s = (int) Math.sqrt(x);
	    return (s*s == x);
	}
}
