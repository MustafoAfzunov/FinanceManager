package com.example.demo.Controller;

import com.example.demo.Model.BalanceService;
import com.example.demo.Model.MyAppUser;
import com.example.demo.Model.MyAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private MyAppUserRepository userRepository;

    @GetMapping(path = "/get-balance")
    public ResponseEntity<Double> getBalance(Principal principal) {
        String username = principal.getName();
        MyAppUser user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        Double balance = balanceService.calculateBalance(user);
        return ResponseEntity.ok(balance);
    }
}
