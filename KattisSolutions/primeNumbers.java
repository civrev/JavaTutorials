//Goldbach's Conjecture

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class primeNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int count=0;
        int[] primes = new int[3533];
        BigInteger test = BigInteger.ZERO;
        while(test.intValue() < 32000)
        {
            test = test.nextProbablePrime();
            primes[count] = test.intValue();
            count+=1;
        }
        ArrayList<Integer[]> arList = new ArrayList<Integer[]>();
        int cases = scan.nextInt();
        for(int k =0;k<cases;k++){
            int summed = scan.nextInt();
            if(summed>3){
            for(int x=0;x<primes.length;x++){
                for(int y=x;y<primes.length;y++){
                    //System.out.println(primes[x] +"|" + primes[y]);
                    int pos = primes[x]+primes[y];
                    if(pos==summed){
                        Integer[] temp = {primes[x],primes[y]};
                        Integer[] rev = {primes[y],primes[x]};
                        arList.add(temp);
                    }
                }
            }
            int size=arList.size();
                System.out.println(summed+" has "+size+" representation(s)");
                for(int i=0;i<size;i++){
                    Integer[] temp = arList.get(i);
                    System.out.println(temp[0]+"+"+temp[1]);
                }
            }
            arList.clear();
            System.out.println();
        }


    }
}