package march19.main;

import march19.exceptions.checked.*;
import march19.exceptions.unchecked.BusinessValidationException;
import march19.model.AccountStatus;
import march19.repository.AccountRepository;
import march19.service.AccountService;
import march19.util.FileProcessor;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountRepository repository = new AccountRepository();
        AccountService service = new AccountService(repository);
        FileProcessor fileProcessor = new FileProcessor();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            System.out.println("========== BANK HESABLARI İDARƏETMƏ SİSTEMİ ==========");

            while (running) {
                printMenu();
                System.out.print("Seçiminizi daxil edin: ");

                int option;
                try {
                    option = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Yanlış giriş. Zəhmət olmasa yalnız rəqəm daxil edin.");
                    scanner.nextLine();
                    continue;
                }

                switch (option) {
                    case 1 -> createAccount(scanner, service);
                    case 2 -> depositMoney(scanner, service);
                    case 3 -> withdrawMoney(scanner, service);
                    case 4 -> findAccount(scanner, service);
                    case 5 -> showAccounts(repository);
                    case 6 -> demoCheckedException();
                    case 7 -> demoUncheckedException();
                    case 8 -> readFile(scanner, fileProcessor);
                    case 0 -> {
                        running = false;
                        System.out.println("Proqramdan çıxılır. Görüşənədək!");
                    }
                    default -> System.out.println("Yanlış seçim. Zəhmət olmasa menyudakı nömrələrdən birini seçin.");
                }

                System.out.println();
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                \n--- MENYU ---
                1. Yeni hesab yarat
                2. Hesaba pul yatır
                3. Hesabdan pul çıxart
                4. Hesabı nömrə ilə tap
                5. Bütün hesabları göstər
                6. Checked xəta nümunəsi
                7. Unchecked xəta nümunəsi
                8. Fayl oxu
                0. Çıxış
                """);
    }

    private static void createAccount(Scanner scanner, AccountService service) {
        try {
            System.out.print("Hesab nömrəsi: ");
            String accountNumber = scanner.nextLine();

            System.out.print("Sahib adı: ");
            String ownerName = scanner.nextLine();

            System.out.print("Başlanğıc balans: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();

            service.createAccount(accountNumber, ownerName, balance, AccountStatus.ACTIVE);
            System.out.println("Hesab uğurla yaradıldı.");
        } catch (DuplicateAccountException | InvalidAmountException e) {
            System.out.println("Hesab yaratma xətası: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Balans rəqəm olmalıdır.");
            scanner.nextLine();
        }
    }

    private static void depositMoney(Scanner scanner, AccountService service) {
        try {
            System.out.print("Hesab nömrəsi: ");
            String accountNumber = scanner.nextLine();

            System.out.print("Yatırılacaq məbləğ: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            service.deposit(accountNumber, amount);
            System.out.println("Pul uğurla yatırıldı.");
        } catch (AccountNotFoundException | InvalidAmountException e) {
            System.out.println("Pul yatırma xətası: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Məbləğ rəqəm olmalıdır.");
            scanner.nextLine();
        }
    }

    private static void withdrawMoney(Scanner scanner, AccountService service) {
        try {
            System.out.print("Hesab nömrəsi: ");
            String accountNumber = scanner.nextLine();

            System.out.print("Çıxarılacaq məbləğ: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            service.withdraw(accountNumber, amount);
            System.out.println("Pul uğurla çıxarıldı.");
        } catch (AccountNotFoundException | InvalidAmountException | InsufficientBalanceException e) {
            System.out.println("Pul çıxarma xətası: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Məbləğ rəqəm olmalıdır.");
            scanner.nextLine();
        }
    }

    private static void findAccount(Scanner scanner, AccountService service) {
        try {
            System.out.print("Axtarılan hesab nömrəsi: ");
            String accountNumber = scanner.nextLine();

            System.out.println(service.findAccount(accountNumber));
        } catch (AccountNotFoundException e) {
            System.out.println("Axtarış xətası: " + e.getMessage());
        }
    }

    private static void showAccounts(AccountRepository repository) {
        System.out.println("HashSet hesab nömrələri (sırasız): " + repository.getHashSetAccountNumbers());
        System.out.println("TreeSet hesab nömrələri (sıralı): " + repository.getTreeSetAccountNumbers());
    }

    private static void demoCheckedException() {
        try {
            throw new BusinessValidationException("Bu, demo məqsədli yoxlama xətasıdır.");
        } catch (BusinessValidationException e) {
            System.out.println("Tutulan xəta: " + e.getMessage());
        }
    }

    private static void demoUncheckedException() {
        try {
            throw new BusinessValidationException("Bu, unchecked xəta nümunəsidir.");
        } catch (BusinessValidationException e) {
            System.out.println("Tutulan xəta: " + e.getMessage());
        }
    }

    private static void readFile(Scanner scanner, FileProcessor fileProcessor) {
        try {
            System.out.print("Fayl adı daxil edin: ");
            String fileName = scanner.nextLine();

            fileProcessor.readFile(fileName);
            System.out.println("Fayl uğurla oxundu.");
        } catch (InvalidFileFormatException e) {
            System.out.println("Fayl formatı xətası: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Fayl oxuma xətası: " + e.getMessage());
        }
    }
}
