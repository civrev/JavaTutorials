//All About That Base

import java.util.Scanner;

public class allbase {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        int cases = scan.nextInt();
        for(int i=0; i<cases;i++){
            boolean inval=true;
            String one=scan.next();
            String oper = scan.next();
            String two=scan.next();
            scan.next();
            String three=scan.next();
            String total = one+two+three;
            String outer="";
            boolean allOne=true;
            for(int p=0;p<total.length();p++){
                if(total.charAt(p)!='1'){
                    allOne=false;
                }
            }
            if(allOne){
                int len=0;
                if(oper.equals("+")){
                    len = one.length()+two.length();
                }else if(oper.equals("-")){
                    len = one.length()-two.length();
                } else if(oper.equals("*")){
                    len = one.length()*two.length();
                }else{
                    len = one.length()/two.length();
                    if(one.length()%two.length()!=0){
                        len = 0;
                    }
                }
                if(len==three.length()){
                    outer=outer+"1";
                }
            }


            for(int n=2; n<37;n++){
                boolean base = false;
                try{
                    int oneB = Integer.parseInt(one, n);
                    int twoB = Integer.parseInt(two, n);
                    int threeB =Integer.parseInt(three, n);
                    int result=0;
                    if(oper.equals("+")){
                        result = oneB+twoB;
                    }else if(oper.equals("-")){
                        result = oneB-twoB;
                    } else if(oper.equals("*")){
                        result = oneB*twoB;
                    }else{
                        result = oneB/twoB;
                        if(oneB%twoB!=0){
                            result = 0;
                        }
                    }
                    String resultB = Integer.toString(result, n);
                    if(resultB.equals(three)){
                        //System.out.println("YES: "+n);
                        base=true;
                    } else{
                        //System.out.println("?: "+n + " one:"+oneB+" two:"+twoB+" three:"+threeB);
                    }

                }catch(Exception e){
                    //System.out.println("NO: "+n);
                }
                if(base){
                    inval=false;
                    String bases = "123456789abcdefghijklmnopqrstuvwxyz0";
                    for(int j=0; j<37;j++){
                        if(j==n){
                            outer=outer+bases.charAt(j-1);
                        }
                    }
                }
            }
            if(outer.equals("")){
                System.out.println("invalid");
            }else{
                System.out.println(outer);
            }

        }
    }

}