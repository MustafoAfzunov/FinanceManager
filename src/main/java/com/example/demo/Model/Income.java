package com.example.demo.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source; // e.g., Salary, Freelance, etc.

    private Double amount;

    private LocalDate date;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyAppUser user;

    // Constructors

    public Income() {}

    public Income(String source, Double amount, LocalDate date, MyAppUser user) {
        this.source = source;
        this.amount = amount;
        this.date = date;
        this.user = user;
    }

    public void setUser(MyAppUser user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    // Getters and Setters
    // ... (include all getters and setters)

}
