package march19.repository;

import march19.exceptions.checked.AccountNotFoundException;
import march19.exceptions.checked.DuplicateAccountException;
import march19.model.Account;

import java.util.*;

public class AccountRepository {

    private final Map<String, Account> accountMap = new HashMap<>();
    private final Set<String> hashSetAccountNumbers = new HashSet<>();
    private final Set<String> treeSetAccountNumbers = new TreeSet<>();

    public void save(Account account) throws DuplicateAccountException {
        String accountNumber = account.getAccountNumber();

        if (hashSetAccountNumbers.contains(accountNumber)) {
            throw new DuplicateAccountException("Bu hesab nömrəsi artıq mövcuddur: " + accountNumber);
        }

        accountMap.put(accountNumber, account);
        hashSetAccountNumbers.add(accountNumber);
        treeSetAccountNumbers.add(accountNumber);
    }

    public Account findByAccountNumber(String accountNumber) throws AccountNotFoundException {
        Account account = accountMap.get(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException("Bu hesab tapılmadı: " + accountNumber);
        }

        return account;
    }

    public Set<String> getHashSetAccountNumbers() {
        return hashSetAccountNumbers;
    }

    public Set<String> getTreeSetAccountNumbers() {
        return treeSetAccountNumbers;
    }


}
