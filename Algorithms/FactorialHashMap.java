import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class FactorialHashMap {
	private static Map<Integer, Double> factMap = new HashMap<Integer, Double>();

	public static void main(String[] args) {
		System.out.println("Enter no ");
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		double result = factorial(a);
		System.out.println("factorial is " + result);
	}

	private static double factorial(int n) {
		if (n == 0)
			return 1;
		if (n == 1)
			return 1;
		else {
			double series1;
			if (factMap.containsKey(n - 1)) {
				series1 = factMap.get(n - 1);
			} else {
				series1 = factorial(n - 1);
				factMap.put(n - 1, series1);
			}

			return series1 * n;
		}
	}

}
