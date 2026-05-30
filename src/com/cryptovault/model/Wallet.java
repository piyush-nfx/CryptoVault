package com.cryptovault.model;

public abstract class Wallet {
    private final String walletAddress;
    private double balance;

    public Wallet(String walletAddress, double initialBalance) {
        this.walletAddress = walletAddress;
        this.balance = initialBalance;
    }

    public String getWalletAddress() { return walletAddress; }
    public double getBalance() { return balance; }

    protected void adjustBalance(double amount) {
        this.balance += amount;
    }

    public abstract boolean verifyCredentials(String securityToken);
}