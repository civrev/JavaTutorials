//Spanovac

import java.util.Scanner;

public class Spanovac {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        int hours = scan.nextInt();
        int minutes = scan.nextInt();
        int outMinutes;
        int outHours;

        if (hours == 0){
            hours = 24;
        }

        if (minutes < 45){
            minutes = minutes + 60;
            hours = hours - 1;
        }

        outMinutes = minutes - 45;
        System.out.println(hours + " " + outMinutes);
    }

}

