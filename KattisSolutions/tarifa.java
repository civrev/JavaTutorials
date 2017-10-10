//Tarifa

import java.util.Scanner;

public class tarifa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int cap = scan.nextInt();
		int n = scan.nextInt();
		int extra = 0;
		for(int i=0; i<n; i++){
			int x = scan.nextInt();
			extra = extra + cap - x;
		}
		System.out.println(extra+cap);
	}

}
/*
10
3
4
6
2

10
3
10
2
12




*/