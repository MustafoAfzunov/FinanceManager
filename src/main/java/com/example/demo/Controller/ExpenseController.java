package com.example.demo.Controller;

import com.example.demo.Model.Expense;
import com.example.demo.Model.ExpenseService;
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
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private MyAppUserRepository userRepository;

    @PostMapping(path = "/add-expense")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense, Principal principal) {
        String username = principal.getName();
        MyAppUser user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        expense.setUser(user);
        Expense savedExpense = expenseService.addExpense(expense);
        return ResponseEntity.ok(savedExpense);
    }

    @GetMapping(path ="/list-expenses" )
    public ResponseEntity<List<Expense>> getExpenses(Principal principal) {
        String username = principal.getName();
        MyAppUser user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Expense> expenses = expenseService.getExpensesByUser(user);
        return ResponseEntity.ok(expenses);
    }
}
