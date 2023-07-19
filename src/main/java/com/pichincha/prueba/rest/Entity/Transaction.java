package com.pichincha.prueba.rest.Entity;

import com.pichincha.prueba.rest.Utils.Classes.Enums.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    private BigDecimal amount;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    public Transaction() {}
    public Transaction(int id, LocalDateTime date, TransactionType type, BigDecimal amount, BigDecimal balance, Account account) {
        super();
        this.id = id;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
