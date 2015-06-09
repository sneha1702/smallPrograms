import java.util.Scanner;

public class Power {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		  int x = sc.nextInt(); 
		  int n = sc.nextInt();
		  int y = raise(x,n);
		  System.out.println("result "+y);
	}

	static int raise(int x, int n) {
		 System.out.println("x = "+x+ " n= "+n + " n/2 " + n/2+" n%2 "+n%2);
		
		if (n == 0)
			return 1;
		else {
			
			int temp = raise(x, n / 2);
			int square = temp*temp;
			 System.out.println("temp = "+temp);
			if (n % 2 == 0)
			{
				 System.out.println("when n= "+n+" temp for even nos= "+temp*temp);
				return square;
			}
			else
			{
				System.out.println("when n= "+n+" temp for odd nos= "+x*temp*temp);
				return x*square;
			}
		}
	}

}
