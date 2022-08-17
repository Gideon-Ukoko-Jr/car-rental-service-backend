package com.group.crservice.domain.entities;

import com.group.crservice.domain.enums.ERole;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private float amount;
    private String transactionType;
    private String transactionDescription;
    public LocalDateTime transactionDate;

    public TransactionEntity() {
    }

    public TransactionEntity(long id, float amount, String transactionType, String transactionDescription, LocalDateTime transactionDate) {
        this.id = id;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDescription = transactionDescription;
        this.transactionDate = transactionDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float ggtAmount() {
        return amount;
    }
    public void setAmount(float amount) {this.amount = amount;}

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDescription(String transactionDescription){ return transactionDescription;}

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }
    public LocalDateTime getTransactionDate(){ return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate){ this.transactionDate = transactionDate}
}
