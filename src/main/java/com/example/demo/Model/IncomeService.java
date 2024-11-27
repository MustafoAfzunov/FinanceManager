package com.example.demo.Model;

import com.example.demo.Model.Income;
import com.example.demo.Model.IncomeRepository;
import com.example.demo.Model.MyAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    public Income addIncome(Income income) {
        return incomeRepository.save(income);
    }

    public List<Income> getIncomesByUser(MyAppUser user) {
        return incomeRepository.findByUser(user);
    }
}
