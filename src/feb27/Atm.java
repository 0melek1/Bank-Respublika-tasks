package feb27;

import java.util.Scanner;

public class Atm {


    static String userid = "1234";
    static String pin = "1234";
    static double balance = 1234.1234;


    static String melek = "melek";


    static void deposit(double amount) {

        balance += amount;

        System.out.println("Balansiniz" + amount + "qeder artirildi");

    }

    static void withdraw(double amount) {

        if (amount > balance) {
            System.out.println("Bu emeliyyat ucun balansinizda kifayet qeder vesait yoxdur");
        } else {
            balance = balance - amount;
            System.out.println("Balansinizdan" + amount + "qeder vesait cixarildi");
        }

    }

     static void checkBalance() {
             System.out.println("balansiniz: " + balance + "-dir");
        }


       public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("user idinizi daxil edin : ");
        String inputId = sc.nextLine();
        System.out.println("Pininizi daxil edin: ");
        String inputPin = sc.nextLine();


        if (!inputId.equals(Atm.userid) || !inputPin.equals(Atm.pin)) {
            System.out.println("Pininiz ve ya Id niz sehvdir,zehmet olmasa bir daha daxil edin");
            return;

        }

        System.out.println("Giris ugurludur");


        System.out.println("Cari balansiniz:" + Atm.balance + "-dir ");
        Atm.deposit(1000);
        Atm.withdraw(1000);
        Atm.checkBalance();


        sc.close();

    }

}




