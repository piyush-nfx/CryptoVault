package com.cryptovault;

import com.cryptovault.model.cryptowallet;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        cryptowallet alice = new cryptowallet("0xAlice123", 1000.0, "alicePin");
         cryptowallet bob = new cryptowallet("0xBob789", 200.0, "bobPin");

        System.out.println("🔄 Processing transactions...");
        alice.transfer(bob, 250.0);
        alice.transfer(bob, 50.0);

        alice.printHistory();

        // Simple backup logic built into main for ease
        try (FileWriter fw = new FileWriter("wallet_backup.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.printf("Wallet: %s | Balance: $%s%n", alice.getWalletAddress(), alice.getBalance());
            pw.printf("Wallet: %s | Balance: $%s%n", bob.getWalletAddress(), bob.getBalance());
            System.out.println("\n💾 System state backed up to wallet_backup.txt");
        } catch (IOException e) {
            System.err.println("❌ Backup failed: " + e.getMessage());
        }
    }
}
