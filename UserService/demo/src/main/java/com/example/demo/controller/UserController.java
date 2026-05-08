package com.example.demo.controller;
import java.util.List;
import javax.crypto.SecretKey;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.Admin;
import com.example.demo.model.Customer;
import com.example.demo.model.ServiceProvider;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final String SECRET_KEY = "YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXoxMjM0NTY=";
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    // @GetMapping("/token")
    // public User getUserByToken(@RequestHeader(value = "Authorization", required = false) String authHeader)
    // {
    //     if (authHeader == null || !authHeader.startsWith("Bearer ")) {
    //         throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization token");
    //     }
    //     String token = authHeader.substring(7);
    //     return userService.findUserByToken(token, SECRET_KEY);
    // }

    @GetMapping("/all")
    public List<User> getAllUsers(@RequestHeader(value = "Authorization", required = false) String authHeader){
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization token");
        }
        String token = authHeader.substring(7);
        User user = userService.findUserByToken(token, SECRET_KEY);
        if (!"ADMIN".equals(user.getRole())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied: You are not authorized to view all users.");
        }

        return userService.getAllUsers();

        
    }
    

    @PostMapping("/register/admin")
    public ResponseEntity<User> registerUser(@RequestBody Admin admin)
    {
        User u = userService.addUser(admin);
        if(u == null) return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        System.out.println("Admin created with ID: " + u.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }
    @PostMapping("/register/customer")
    public ResponseEntity<User> registerUser(@RequestBody Customer customer)
    {
        User u = userService.addUser(customer);
        if(u == null) return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        System.out.println("Customer created with ID: " + u.getId());
        userService.createCustomerWallet(u.getId(), customer.getBalance());
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }
    @PostMapping("/register/service_provider")
    public ResponseEntity<User> registerUser(@RequestBody ServiceProvider serviceProvider)
    {
        User u = userService.addUser(serviceProvider);
        if(u == null) return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        System.out.println("Customer created with ID: " + u.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
        
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userService.loginUser(loginRequest.username(), loginRequest.password());
        
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
        
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
        
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("id", user.getId()) 
                .signWith(key)
                .compact();
        }

        @GetMapping("/token")
        public ResponseEntity<User> getUserByToken(@RequestHeader(value = "Authorization", required = false) String authHeader){
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization token");
            }
            String token = authHeader.substring(7);
            User user = userService.findUserByToken(token, SECRET_KEY);
            return ResponseEntity.status(HttpStatus.FOUND).body(user);

        }
    }


    


    

