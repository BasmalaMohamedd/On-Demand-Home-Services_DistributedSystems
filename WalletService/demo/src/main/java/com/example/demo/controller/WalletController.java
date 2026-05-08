package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Wallet;
import com.example.demo.service.WalletService;
@RequestMapping("/wallets")
@RestController
public class WalletController {
    private final WalletService walletService;
    public WalletController(WalletService walletService)
    {
        this.walletService = walletService;
    }

    @PostMapping("/create")
    public Wallet createWallet(@RequestBody Wallet wallet)
    {
        return walletService.addWallet(wallet);
    }

    @GetMapping("/getBalance/{id}")
    public ResponseEntity<Integer> getBalance(@PathVariable Long id) {
        return ResponseEntity.ok(walletService.getWalletBalance(id));
    }
    
    
}
