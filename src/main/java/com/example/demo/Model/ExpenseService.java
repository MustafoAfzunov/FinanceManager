package com.example.demo.Model;

import com.example.demo.Model.Expense;
import com.example.demo.Model.ExpenseRepository;
import com.example.demo.Model.MyAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getExpensesByUser(MyAppUser user) {
        return expenseRepository.findByUser(user);
    }
}
