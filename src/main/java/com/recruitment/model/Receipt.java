package com.recruitment.model;

import java.time.LocalDate;

public class Receipt {

    private final Reimbursement reimbursement;
    private LocalDate date;
//    private ReceiptType receiptType;
    private ReceiptKind receiptKind;
    private double amount;
    private String description;

    public Receipt(Reimbursement reimbursement, LocalDate date, ReceiptKind receiptKind, double amount, String description) {
        this.reimbursement = reimbursement;
        this.date = date;
        this.receiptKind = receiptKind;
        this.amount = amount;
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setReceiptType(ReceiptKind receiptKind) {
        this.receiptKind = receiptKind;
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

    public ReceiptKind getReceiptKind() {
        return receiptKind;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
