package march19.service;

import march19.exceptions.checked.AccountNotFoundException;
import march19.exceptions.checked.DuplicateAccountException;
import march19.exceptions.checked.InsufficientBalanceException;
import march19.exceptions.checked.InvalidAmountException;
import march19.model.Account;
import march19.model.AccountStatus;
import march19.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountService {


    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public void createAccount(String accountNumber, String ownerName, double balance, AccountStatus status)
            throws DuplicateAccountException, InvalidAmountException {

        logger.debug("Creating account: accountNumber={}, ownerName={}, balance={}, status={}",
                accountNumber, ownerName, balance, status);

        if (accountNumber == null || accountNumber.isBlank()) {
            throw new InvalidAmountException("Hesab nömrəsi boş ola bilməz.");
        }

        if (ownerName == null || ownerName.isBlank()) {
            throw new InvalidAmountException("Sahib adı boş ola bilməz.");
        }

        if (balance < 0) {
            throw new InvalidAmountException("Başlanğıc balans mənfi ola bilməz.");
        }

        if (status == null) {
            throw new InvalidAmountException("Status boş ola bilməz.");
        }

        Account account = new Account(accountNumber.trim(), ownerName.trim(), balance, status);
        repository.save(account);

        logger.info("Account created successfully: {}", accountNumber);
    }

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        logger.debug("Finding account: {}", accountNumber);

        if (accountNumber == null || accountNumber.isBlank()) {
            throw new AccountNotFoundException("Hesab nömrəsi boş ola bilməz.");
        }

        return repository.findByAccountNumber(accountNumber.trim());
    }

    public void deposit(String accountNumber, double amount)
            throws AccountNotFoundException, InvalidAmountException {

        logger.debug("Deposit request: accountNumber={}, amount={}", accountNumber, amount);

        if (amount <= 0) {
            throw new InvalidAmountException("Yatırılan məbləğ 0-dan böyük olmalıdır.");
        }

        Account account = repository.findByAccountNumber(accountNumber);

        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new InvalidAmountException("Bu hesaba əməliyyat etmək olmur. Hesab aktiv deyil.");
        }

        account.setBalance(account.getBalance() + amount);

        logger.info("Deposit successful: {} -> new balance={}", accountNumber, account.getBalance());
    }

    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException, InvalidAmountException, InsufficientBalanceException {

        logger.debug("Withdraw request: accountNumber={}, amount={}", accountNumber, amount);

        if (amount <= 0) {
            throw new InvalidAmountException("Çıxarılan məbləğ 0-dan böyük olmalıdır.");
        }

        Account account = repository.findByAccountNumber(accountNumber);

        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new InvalidAmountException("Bu hesaba əməliyyat etmək olmur. Hesab aktiv deyil.");
        }

        if (account.getBalance() < amount) {
            logger.error("Exception occurred: insufficient balance for account {}", accountNumber);
            throw new InsufficientBalanceException("Balans kifayət etmir. Hazırkı balans: " + account.getBalance());
        }

        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);

        if (newBalance < 100) {
            logger.warn("Low balance warning for account {} : {}", accountNumber, newBalance);
        }

        logger.info("Withdraw successful: {} -> new balance={}", accountNumber, newBalance);
    }
}
