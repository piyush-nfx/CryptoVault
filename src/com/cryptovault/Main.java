package com.cryptovault;

import com.cryptovault.model.cryptowallet;
import com.cryptovault.model.cryptowallet;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize two wallets for our interactive ecosystem
        cryptowallet alice = new cryptowallet("0xAlice123", 1000.0, "1234");
        cryptowallet bob = new cryptowallet("0xBob789", 500.0, "5678");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=====================================");
        System.out.println("    Welcome to CryptoVault Wallet    ");
        System.out.println("=====================================");

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Check Wallet Balances");
            System.out.println("2. Transfer Crypto (Alice -> Bob)");
            System.out.println("3. View Alice's Transaction History");
            System.out.println("4. Save State & Exit");
            System.out.print("Select an option (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the leftover newline character

            switch (choice) {
                case 1:
                    System.out.println("\n💵 Current Balances:");
                    System.out.println("Alice (" + alice.getWalletAddress() + "): $" + alice.getBalance());
                    System.out.println("Bob (" + bob.getWalletAddress() + "): $" + bob.getBalance());
                    break;

                case 2:
                    System.out.print("\nEnter transfer amount: $");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter security PIN for Alice: ");
                    String pin = scanner.nextLine();

                    System.out.println("\n🔄 Authenticating & Processing...");
                    if (alice.verifyCredentials(pin)) {
                        alice.transfer(bob, amount);
                    } else {
                        System.out.println("❌ Authentication Failed: Invalid PIN.");
                    }
                    break;

                case 3:
                    alice.printHistory();
                    break;

                case 4:
                    System.out.println("\n💾 Saving wallet states and backing up system...");
                    saveBackup(alice, bob);
                    System.out.println("👋 Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("❌ Invalid option. Please select a number between 1 and 4.");
            }
        }
        scanner.close();
    }

    private static void saveBackup(cryptowallet alice, cryptowallet bob) {
        try (FileWriter fw = new FileWriter("wallet_backup.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.printf("Backup State -> Alice: $%s | Bob: $%s%n", alice.getBalance(), bob.getBalance());
        } catch (IOException e) {
            System.err.println("❌ Backup failed: " + e.getMessage());
        }
    }
}