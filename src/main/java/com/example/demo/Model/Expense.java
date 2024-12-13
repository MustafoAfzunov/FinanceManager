package com.example.demo.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category; // e.g., Food, Rent, etc.

    private Double amount;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private MyAppUser user;

    // Constructors

    public Expense() {}

    public Expense(String category, Double amount, LocalDate date, MyAppUser user) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.user = user;
    }
    @PrePersist
    public void prePersist() {
        if (this.date == null) {
            this.date = LocalDate.now(); // Automatically set the current date
        }
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
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    // Getters and Setters
    // ... (include all getters and setters)
}
