import java.util.Scanner;
public class TowerOfHanaoi {
	
	public static void main(String [] args){
		System.out.println("Height of tower is ");
		Scanner sc= new Scanner(System.in);
		int height = sc.nextInt();
		moveTower(height,"A","B","Temp");
	}
	
	private static void moveTower(int height, String fromPole, String toPole,String tempPole){
		if(height>=1)
		{
			moveTower(height-1,fromPole,tempPole,toPole);
			moveDisk(height-1,fromPole,toPole);
			moveTower(height-1,tempPole,toPole,fromPole);
		}
	}
	
	private static void moveDisk(int num,String from,String to){
		System.out.println("Moving "+num+" Disks from "+from+ " to "+to);
	}
	

}
