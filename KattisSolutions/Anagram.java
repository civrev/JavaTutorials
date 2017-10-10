//Anagram Counting

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Anagram {

    private static final char[] String = null;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner s = new Scanner(System.in);
        while(s.hasNext()){
            String word = s.next();
            char[] cA = word.toCharArray();
            BigInteger length = new BigInteger(Integer.toString(cA.length));
            HashSet<String> hs = new HashSet<String>();
            ArrayList<Integer> al = new ArrayList<Integer>();
            for(int i=0; i<cA.length; i++){
                hs.add(cA[i]+"");
            }
            for(int i=0; i<hs.size(); i++){
                al.add(0);
            }
            ArrayList<String> hsal = new ArrayList<String>();
            hsal.addAll(hs);
            for(int i=0; i<cA.length; i++){
                String temp = cA[i]+"";
                int index = hsal.indexOf(temp);
                int num = al.get(index);
                al.set(index, (num+1));
            }
            BigInteger size = new BigInteger(Integer.toString(hs.size()));
            BigInteger repeats = length.subtract(size);
            //factorial
            if(repeats.toString().equals("0")){
                System.out.println(factorial(length));
            } else{
                //repeat of reach letter length!/a!*b!*c!*d!
                BigInteger ans = new BigInteger("1");
                for(int i=0; i<al.size(); i++){
                    BigInteger temp = new BigInteger(Integer.toString(al.get(i)));
                    ans = ans.multiply(factorial(temp));
                }
                System.out.println(factorial(length).divide(ans));
            }
        }
    }

    public static BigInteger factorial(BigInteger b){
        if(b.toString().equals("1")){
            return b;
        } else {
            return b.multiply(factorial(b.subtract(new BigInteger("1"))));
        }
    }

}