package feb27;

import java.util.Scanner;

public class Currencyexchange {

    static final double usd = 1.7;
    static final double eur = 1.85;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Mebleg daxil edin: ");

        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("Daxil etdiyiniz mebleg duzgun deyil!");
            return;

        }


        System.out.println("1 - USD");
        System.out.println("2 - EUR");

        int choice = sc.nextInt();

        convert(amount, choice);

        sc.close();


    }

    static void convert(double amount,int choice){


        switch (choice) {
            case 1:
                System.out.println("Nəticə: " + (amount / usd) + " USD");
                break;
            case 2:
                System.out.println("Nəticə: " + (amount / eur) + " EUR");
                break;
            default:
                System.out.println("Yanlış seçim!");
        }



    }

}
