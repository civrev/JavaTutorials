//ACM Contest Scoring

import java.util.ArrayList;
import java.util.Scanner;

public class ACM {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        ArrayList<String> teamy = new ArrayList<String>();
        ArrayList<String> twrong = new ArrayList<String>();
        ArrayList<String> tright = new ArrayList<String>();

        int timeCheck = 0;
        int ansCheck = 0;
        int penalty = 0;
        int time = scan.nextInt();
        while(time>0){
                String team = scan.next();
                String ans = scan.next();
                if(ans.equals("right")){
                    ansCheck++;
                    timeCheck = timeCheck + time;
                    tright.add(team + " " + ans);
                }
                teamy.add(team);
                if(ans.equals("wrong")){
                    twrong.add(team + " " + ans);
                }
                time = scan.nextInt();
        }
        for(int i =0; i<tright.size(); i++){
            String checkR = tright.get(i).charAt(0) + "";
            for(int k = 0; k<twrong.size(); k++){
                String checkW = twrong.get(k).charAt(0) + "";
                if(checkR.equals(checkW)){
                    penalty = penalty +20;
                }
            }



        }
        //for(int i =0; i<listy.size(); i++){
        //  if(ans.equals("wrong") && listy.contains(team)){
        //      timeCheck = timeCheck - 20;
        //  }
        //}

        System.out.println(ansCheck + " " + (timeCheck+penalty));
        //System.out.println(teamy);
        //System.out.println(twrong);
        //System.out.println(tright);
        //System.out.println(penalty);

    }

}