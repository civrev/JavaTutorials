//Take Two Stones

import java.util.Scanner;

public class Stones {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        int check = number%2;
        //System.out.println(check);
        if(check!=0){
        	System.out.println("Alice");
        } else {
        	System.out.println("Bob");
        }
	}

}
