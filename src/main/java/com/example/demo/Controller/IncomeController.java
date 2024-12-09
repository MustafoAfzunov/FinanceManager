package com.example.demo.Controller;

import com.example.demo.Model.Income;
import com.example.demo.Model.IncomeService;
import com.example.demo.Model.MyAppUser;
import com.example.demo.Model.MyAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private MyAppUserRepository userRepository;

    @PostMapping(path="/add-income")
    public ResponseEntity<Income> addIncome(@RequestBody Income income, Principal principal) {
        String username = principal.getName();
        MyAppUser user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        income.setUser(user);
        Income savedIncome = incomeService.addIncome(income);
        return ResponseEntity.ok(savedIncome);
    }

    @GetMapping(path = "/list-incomes")
    public ResponseEntity<List<Income>> getIncomes(Principal principal) {
        String username = principal.getName();
        MyAppUser user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Income> incomes = incomeService.getIncomesByUser(user);
        return ResponseEntity.ok(incomes);
    }
}
