package com.example.demo.service;

import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.CreateWalletRequest;
import com.example.demo.dto.CreateWalletResponse;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class UserService {
    private final RestClient restClient;
    private final UserRepo repo;
    public UserService(UserRepo repo, RestClient.Builder restClientBuilder)
    {
        this.repo = repo;
        this.restClient = restClientBuilder.baseUrl("http://localhost:8081").build();
    }

    private User getUserById(Long id)
    {
        return repo.findById(id).orElse(null);
    }

    public List<User> getAllUsers(){
        return repo.findAll();
    }

    public User findUserByToken(String token, String SECRET_KEY)
    {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
            
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            
            Long userId = claims.get("id", Long.class);
            User user = this.getUserById(userId);

            return user;
        } catch (JwtException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid authentication token: " + e.getMessage());
        }
    }

    public User loginUser(String username, String password)
    {
        return repo.findByUsernameAndPassword(username, password)
                   .orElse(null);
    }

    public User addUser(User user)
    {
        User u = repo.findByUsername(user.getUsername()).orElse(null);
        if(u == null)
        {
            return repo.save(user);
        }
        else return null;
        
    }

    public void createCustomerWallet(Long userId, Integer balance)
    {
        CreateWalletRequest requestDTO = new CreateWalletRequest(userId, balance);
        System.out.println("Creating wallet for customer " + userId);
        try {
            CreateWalletResponse res = this.restClient.post()
                .uri("/wallets/create")
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDTO)
                .retrieve()
                .body(CreateWalletResponse.class);

        // Optional check: ensure res is not null
        if (res != null) {
            System.out.println("Successfully created wallet for customer ID: " + res.userID());
        } else {
            System.err.println("Warning: Received empty response from the wallet service.");
        }
        } catch (RestClientResponseException e) {
            System.err.println("API error: " + e.getResponseBodyAsString());
            throw new RuntimeException("Failed to create customer wallet due to an external API error.", e);
        }
    }

}
