package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Transaction;
import com.example.demo.repo.TransactionRepo;

@Service
public class TransactionService {
    private static TransactionRepo repo;
    private static WalletService walletService;

    private TransactionService(TransactionRepo repo, WalletService walletService)
    {
        this.repo = repo;
        this.walletService = walletService;
    }

    public static TransactionService getTransactionService(){
        return new TransactionService(repo, walletService);
    }

    public Transaction addFund(Transaction transaction){
        walletService.addFund(transaction.getSenderid(), transaction.getAmount());
        return repo.save(transaction);
        
    }
    public Transaction deductBalance(Transaction transaction){
        walletService.deductBalance(transaction.getSenderid(), transaction.getAmount());
        return repo.save(transaction);
    }

    public Transaction addTransaction(Transaction transaction)
    {
        return repo.save(transaction);
    }
    public List<Transaction> getAllTransactions(){
        return repo.findAll();
    }


    
}
