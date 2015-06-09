import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class FibonacciSequence {
	private static Map<Integer,Integer> fiboMap=new HashMap<Integer,Integer>();
	public static void main(String [] args) {
		System.out.println("Enter no ");
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int result = fibonacci(a);
		System.out.println("No is fibonacci "+result);
	}
	
	private static int fibonacci(int n){
		if(n==0)
			return 0;
		if(n==1)
			return 1;
		else
		{
			int series1=0,series2=0; 
			if(fiboMap.containsKey(n-1)){
				series1 = fiboMap.get(n-1);
			}
			else{
				series1 = fibonacci(n-1);
				fiboMap.put(n-1, series1);
			}
			
			if(fiboMap.containsKey(n-2)){
				series2 = fiboMap.get(n-2);
			}
			else{
				series2 = fibonacci(n-2);
				fiboMap.put(n-2, series2);
			}
			
			return series1+series2;
		}
	}
	
}
