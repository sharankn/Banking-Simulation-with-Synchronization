package bankingSimulationwithSynchronization;

import java.util.Scanner;

// Bank Account class
class Account {

    int balance = 1000;

    // synchronized method to avoid race condition
    synchronized void withdraw(int amount) {

        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);

            balance = balance - amount;

            System.out.println("Remaining balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " - Insufficient balance");
        }
    }
}

// Customer thread class
class Customer extends Thread {

    Account acc;
    int amount;

    Customer(Account acc, int amount) {
        this.acc = acc;
        this.amount = amount;
    }

    public void run() {
        acc.withdraw(amount);
    }
}

// Main class
public class BankingSimulationwithSynchronization {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Account acc = new Account();

        System.out.print("Enter withdrawal amount for Customer-1: ");
        int amount1 = sc.nextInt();

        System.out.print("Enter withdrawal amount for Customer-2: ");
        int amount2 = sc.nextInt();

        Customer c1 = new Customer(acc, amount1);
        Customer c2 = new Customer(acc, amount2);

        c1.setName("Customer-1");
        c2.setName("Customer-2");

        c1.start();
        c2.start();

        sc.close();
    }
}