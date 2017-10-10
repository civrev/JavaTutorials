//speed limit

import java.util.Scanner;

public class speed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		while(cases!=-1){
			int total=0;
			int prev = 0;
			for(int i =0;i<cases;i++){
				int mph = scan.nextInt();
				int cur= scan.nextInt();
				total = total + (cur-prev)*mph;
				prev=cur;
			}
			System.out.println(total+" miles");
			cases = scan.nextInt();
		}

	}

}


/*


3
20 2
30 6
10 7
2
60 1
30 5
4
15 1
25 2
30 3
10 5
-1



*/