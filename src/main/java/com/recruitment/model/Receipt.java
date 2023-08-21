package com.recruitment.model;

import java.time.LocalDate;

public class Receipt {

    private final Reimbursement reimbursement;
    private LocalDate date;
    private ReceiptType receiptType;
    private double amount;
    private String description;

    public Receipt(Reimbursement reimbursement, LocalDate date, ReceiptType receiptType, double amount, String description) {
        this.reimbursement = reimbursement;
        this.date = date;
        this.receiptType = receiptType;
        this.amount = amount;
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setReceiptType(ReceiptType receiptType) {
        this.receiptType = receiptType;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reimbursement getReimbursement() {
        return reimbursement;
    }

    public LocalDate getDate() {
        return date;
    }

    public ReceiptType getReceiptType() {
        return receiptType;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
