package com.cryptovault.model;
import com.cryptovault.service.Transactionservice;
import java.util.ArrayList;
import java.util.List;
public class cryptowallet extends Wallet implements Transactionservice {
    private final String privatePin;
    private final List<Transaction> transactionHistory;

    public cryptowallet(String walletAddress, double initialBalance, String privatePin) {
        super(walletAddress, initialBalance);
        this.privatePin = privatePin;
        this.transactionHistory = new ArrayList<>();
    }

    @Override
    public boolean verifyCredentials(String securityToken) {
        return this.privatePin.equals(securityToken);
    }

    @Override
    public boolean transfer(Wallet destination, double amount) {
        if (amount <= 0) {
            System.out.println("❌ Error: Transfer amount must be positive.");
            return false;
        }
        if (this.getBalance() < amount) {
            System.out.println("❌ Error: Insufficient funds.");
            return false;
        }

        this.adjustBalance(-amount);
        destination.adjustBalance(amount);

        Transaction tx = new Transaction(this.getWalletAddress(), destination.getWalletAddress(), amount);
        this.transactionHistory.add(tx);

        System.out.println("✨ Successfully transferred $" + amount + " to " + destination.getWalletAddress());
        return true;
    }

    public void printHistory() {
        System.out.println("\n📜 Transaction History for " + getWalletAddress() + ":");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction tx : transactionHistory) {
                System.out.println(tx);
            }
        }
    }
}
