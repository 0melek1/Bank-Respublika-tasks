package march7;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.getName;

public class Account {

   private String accountNumber;
   private double balance;
   private Customer owner;
   private List<Transaction> history;


   public String getAccountNumber(){
       return accountNumber;
   }

   public double getBalance(){
       return balance;
   }

   public Customer getOwner(){
       return owner;
   }

   List<Transaction> getHistory(){
       return history;
   }


   void setAccountNumber(){
       this.accountNumber = accountNumber;
   }


   void setBalance( double balance){
       this.balance = balance;
   }

   void setOwner( Customer owner) {
       this.owner = owner;
   }

   void  setHistory( List<Transaction> history){

       this.history = history;
   }

    public Account() {
    }

    Account(String accountNumber, Customer owner){
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.owner = owner;
        this.history    = new ArrayList<>();
    }

    public void deposit(double amount,String description){

        if(amount<=0){
            System.out.println("Daxil edilen mebleg 0-dan boyuk olmalidir");
                 return;
        }

        balance += amount;
        history.add(new Transaction("Deposit: ",amount,description));
        System.out.println("Balansiniz - " + amount + " qeder artirildi ");

    }


    public void withdraw(double amount,String description) {
        if (amount <= 0) {
            System.out.println("Daxil etdiyiniz mebleg duzgun deyil");
            return;
        }
        if (amount > balance) {
            System.out.println("Bu emeliyyat ucun balansinizda kifayet qeder pul mebleg yoxdu");
            return;
        }

        balance -= amount;
        history.add(new Transaction("Withdraw: ", amount, description));
        System.out.println("Balansinizdan - " + amount + " qeder vesait cixarildi");
    }


    public void showInfo(){
         System.out.println("Hesab No: " + accountNumber
                           + "Balansi: " + balance
                           + "Sahibi: " + owner.getName());
    }


    public void showHistory(){
        System.out.println("Hesab sahibi: " + owner.getName() + "-nin tarixcesi");
        if(history.isEmpty()){
            System.out.println("Emeliyyat tarixcesi yoxdur");
            return;
        }

        for(Transaction t: history){
            t.showInfo();
        }
    }





}
