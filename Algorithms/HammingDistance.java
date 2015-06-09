import acmx.export.java.util.Scanner;

public class HammingDistance {

	public static void main(String[] args) {
		System.out.println("Enter the 2 strings ");

		Scanner sc = new Scanner(System.in);

		String str1 = sc.nextLine();
		String secStr = sc.nextLine();

		 int num = findHammingDist(str1, secStr);
		//String num = str1.substring(0, str1.length() - 1);
		System.out.println("dist is" + num);

	}

	private static int findHammingDist(String str1, String str2) {
		if (str1.equals(str2)) {
			return 0;
		} else {
			int num=0;
			
			if(str1.charAt(str1.length()-1)!=str2.charAt(str2.length()-1))
			{	num=1;
			}
			return findHammingDist(str1.substring(0, str1.length() - 1),
					str2.substring(0, str2.length() - 1))+num;
		}
	}

}
