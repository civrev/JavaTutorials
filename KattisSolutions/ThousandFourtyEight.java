//2048

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThousandFourtyEight {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        ArrayList<int[]> grid = new ArrayList<int[]>();
        int[][] temp = new int[4][4];
        for(int y=0; y<4; y++){
            for(int x=0; x<4; x++){
                temp[y][x] = scan.nextInt();
            }
        }
        int d = scan.nextInt(); // direction 0=L, 1=U, 2=R, 3=D
        boolean reverse = false;
        if(d%2==1){ //odd #
            for(int y=0; y<4; y++){
                int[] l = new int[4];
                for(int x=0; x<4; x++){
                    l[x] = temp[x][y];
                }
                grid.add(l);
            }
            if(d==3){
                reverse=true;
            }
        } else {
            for(int y=0; y<4; y++){
                int[] l = new int[4];
                for(int x=0; x<4; x++){
                    l[x] = temp[y][x];
                }
                grid.add(l);
            }
            if(d==2){
                reverse=true;
            }
        }

        for(int y=0; y<4; y++){
            int[] r = arrange(grid.get(y), reverse);
            for(int x=0; x<4; x++){
                if(d==1 || d==3){
                    temp[x][y] = r[x];
                }else {
                    temp[y][x] = r[x];
                }
            }
        }
        for(int y=0; y<4; y++){
            for(int x=0; x<4; x++){
                System.out.print(temp[y][x] + " ");
            }
            System.out.println();
        }

    }

    public static int[] arrange(int[] oldAL, boolean reverse){
        int old = 0;
        int count = 0;
        ArrayList<Integer>newAL = new ArrayList<Integer>();
        for(int i=0; i<4; i++){
            int x = oldAL[i];
            if(reverse){
                x=oldAL[3-i];
            }
            if(x!=0 && x==old){
                x=x+old;
                newAL.set((newAL.size()-1), x);
                count=count+1;
                old=0;
                x=0;
            } else if (x!=0){
                newAL.add(x);
            }
            if(x!=0){
            old=x;
            }
        }
        for(int k = count; k<4; k++){
            int t=0;
            newAL.add(t);
        }
        if(reverse){
            ArrayList<Integer>temp = new ArrayList<Integer>();
            for(int i=0; i<4; i++){
                temp.add(0);
            }
            for(int i=0;i<4;i++){
                temp.set(Math.abs(i-3), newAL.get(i));
            }
            newAL = temp;
        }
        for(int i=0; i<4; i++){
            oldAL[i]=newAL.get(i);
        }
        return oldAL;
    }

}