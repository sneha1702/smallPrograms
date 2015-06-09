import java.util.Scanner;


public class NumToString {
	private static String lookup[]= {"0","1","2","3","4","5","6","7","8","9"};
	public static void main(String[] arg){
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int base = sc.nextInt();
		
		
		String str =convertBase(num,base);
		System.out.println("string rep "+ str);
	}
	
	private static String convertBase(int num, int base){
		//base case
		if(num<base){
			return lookup[num];
		}
		else{
		// recursive case
			
			return convertBase(num/base,base)+lookup[num%base];
			
		}

	}
	

}
