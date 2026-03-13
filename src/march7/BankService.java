package march7;

import java.util.ArrayList;
import java.util.List;

public class BankService {

    List<Customer> customers = new ArrayList<>();
    List<Account> accounts = new ArrayList<>();

    int nextCustomerId = 1;

    int nextAccountId = 1;

    public Customer addCustomer(String name,String email){
        Customer customer = new Customer(name,email,nextCustomerId);
            customers.add(customer);
            nextCustomerId++;
            System.out.println("Yeni musteri elave edildi - " + name);
            return customer;
    }

    public Account openAccount(Customer customer){
        if (customer == null) {
            System.out.println("Musteri tapilmadi");
            return null;
        }

        String accountNumber = "ACC: " + nextAccountId;
        Account account = new Account(accountNumber, customer);
        accounts.add(account);
        nextAccountId++;

        System.out.println("Yeni hesab acildi. Hesab nomresi: " + accountNumber);
        return account;
    }

    public void showAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("Hech bir musteri yoxdur");
            return;
        }

        for (Customer customer : customers) {
            customer.showInfo();
        }
    }


    public void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("Hech bir hesab yoxdur");
            return;
        }

        for (Account account : accounts) {
            account.showInfo();
        }
    }

}





