package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Transaction;
import com.example.demo.service.TransactionService;

@RequestMapping("/transactions")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService)
    {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions()
    {
        return transactionService.getAllTransactions();
    }

    @PostMapping("/addFund")
    public Transaction addFund(@RequestBody Transaction transaction)
    {
        return transactionService.addFund(transaction);
    }



    @PostMapping("/deductBalance")
    public Transaction deductBalance(@RequestBody Transaction transaction)
    {
        return transactionService.deductBalance(transaction);
    }

    
}
