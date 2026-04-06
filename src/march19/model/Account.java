package march19.model;

public class Account {
    private String accountNumber;
    private String ownerName;
    private double balance;
    private AccountStatus status;


    public Account(String accountNumber, String ownerName, double balance, AccountStatus status) {
            this.accountNumber = accountNumber;
            this.ownerName = ownerName;
            this.balance = balance;
            this.status = status;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public double getBalance() {
            return balance;
        }

        public AccountStatus getStatus() {
            return status;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public void setStatus(AccountStatus status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Hesab məlumatı {" +
                    "hesab nömrəsi='" + accountNumber + '\'' +
                    ", sahib='" + ownerName + '\'' +
                    ", balans=" + balance +
                    ", status=" + status +
                    '}';
        }
}
