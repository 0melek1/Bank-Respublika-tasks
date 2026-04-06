package march7;

import java.util.Scanner;

public class BankApp {


    public static void main(String[] args) {

             Scanner sc = new Scanner(System.in);
            BankService bankService = new BankService();

            System.out.print("Ad daxil edin: ");
            String name = sc.nextLine();

            System.out.print("Email daxil edin: ");
            String email = sc.nextLine();

            Customer customer = bankService.addCustomer(name,email);
            Account account = bankService.openAccount(customer);


            if(account == null){

                    System.out.println("Hesab yoxdur");
                  return;

            }


            boolean running = true;

            while(running == true)  {
                System.out.println("Emeliyyat secin: ");
                System.out.println("1 - Medaxil");
                System.out.println("2 - Mexaric");
                System.out.println("3 - Balansa bax");
                System.out.println("4 - Tarixceye bax");
                System.out.println("0 - Cixis");
                System.out.print("Seciminiz: ");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice){
                    case 1:
                        System.out.println("Meblwg daxil edin:");
                        double depositAmount = sc.nextDouble();
                        sc.nextLine();
                        account.deposit(depositAmount,"Medaxil - deposit");
                        break;


                    case 2:
                            System.out.println("Mebleg daxil edin: ");
                            double withdrawAmount = sc.nextDouble();
                            sc.nextLine();
                            account.withdraw(withdrawAmount,"Mexaric - withdraw");

                    case 3:

                        account.showInfo();
                        break;

                        case 4:
                        account.showHistory();
                        break;

                        case 0:
                            running = false;
                            System.out.println("Sessiyaniz bitdi.");
                            break;
                }


            }

               sc.close();

        }


    }





