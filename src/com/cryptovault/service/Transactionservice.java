package com.cryptovault.service;
import com.cryptovault.model.Wallet;
public interface Transactionservice {
    boolean transfer(Wallet destination, double amount);
}
