package com.cryptovault.model;
import java.time.LocalDateTime;
public class Transaction {
    private final String transactionId;
    private final String senderAddress;
    private final String receiverAddress;
    private final double amount;
    private final LocalDateTime timestamp;

    public Transaction(String senderAddress, String receiverAddress, double amount) {
        this.transactionId = "TXN" + System.currentTimeMillis();
        this.senderAddress = senderAddress;
        this.receiverAddress = receiverAddress;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
}
    @Override
    public String toString() {
        return String.format("[%s] ID: %s | %s sent $%s to %s",
                timestamp, transactionId, senderAddress, amount, receiverAddress);
    }
}
