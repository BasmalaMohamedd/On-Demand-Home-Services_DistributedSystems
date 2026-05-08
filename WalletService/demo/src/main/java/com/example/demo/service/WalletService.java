package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.dto.BookingRequest;
import com.example.demo.dto.CompletionRequest;
import com.example.demo.dto.RollbackRequest;
import com.example.demo.model.Transaction;
import com.example.demo.model.Wallet;
import com.example.demo.repo.WalletRepo;


@Service
public class WalletService {
    private final WalletRepo repo;
    private final RabbitTemplate rabbitTemplate;
    private TransactionService transactionService;
    
    private WalletService(WalletRepo repo,RabbitTemplate rabbitTemplate)
    {
        this.repo = repo;
        this.rabbitTemplate = rabbitTemplate;
        this.transactionService = TransactionService.getTransactionService();
    }

    // private Wallet getWalletById(Long userId)
    // {
    //     return repo.findById(userId).orElse(null);
    // }

    public Wallet addFund(Long userId, int value)
    {
        Wallet wallet = repo.findByUserId(userId).orElseThrow(() -> 
            new IllegalArgumentException("Wallet not found for user ID: " + userId)
        );
        wallet.setBalance(wallet.getBalance() + value);
        return repo.save(wallet);
    }

    public Wallet deductBalance(Long userId, int value)
    {
        Wallet wallet = repo.findByUserId(userId).orElseThrow(() -> 
            new IllegalArgumentException("Wallet not found for user ID: " + userId)
        );
        wallet.setBalance(wallet.getBalance() - value);
        return repo.save(wallet);
    }

    

    public Wallet addWallet(Wallet wallet)
    {
        return repo.save(wallet);
    }

    public Integer getWalletBalance(Long userId)
    {
        return repo.findByUserId(userId).orElse(null).getBalance();
    }


    @RabbitListener(queues = RabbitMQConfig.WALLET_QUEUE)
    public void processBooking(BookingRequest bookingRequest)
    {
        System.out.println("received Message: " +  bookingRequest);
        Integer balance = this.getWalletBalance(bookingRequest.getCustomerId());
        if(balance >= bookingRequest.getPrice())
        {
            deductBalance(bookingRequest.getCustomerId(), bookingRequest.getPrice());
            Transaction transaction = new Transaction(bookingRequest.getCustomerId(), bookingRequest.getProviderId(), bookingRequest.getPrice(), "DEDUCT", bookingRequest.getBookingId());
            transactionService.addTransaction(transaction);
            CompletionRequest completionRequest = new CompletionRequest(bookingRequest.getBookingId());
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "routing_key_completion", completionRequest);
        }
        else
        {
            RollbackRequest rollbackRequest = new RollbackRequest(bookingRequest.getBookingId());
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, "routing_key_rollback", rollbackRequest);
        }
    }

    // public Wallet findWalletByToken(String token, String SECRET_KEY)
    // {
    //     try {
    //         SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
            
    //         Claims claims = Jwts.parser()
    //                 .verifyWith(key)
    //                 .build()
    //                 .parseSignedClaims(token)
    //                 .getPayload();

            
    //         Long userId = claims.get("id", Long.class);
    //         Wallet wallet = this.getWalletById(userId);

    //         return wallet;
    //     } catch (JwtException e) {
    //         throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid authentication token: " + e.getMessage());
    //     }
    // }


}
