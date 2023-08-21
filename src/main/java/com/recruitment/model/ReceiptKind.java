package com.recruitment.model;

public class ReceiptKind {
    private String name;
    private double receiptKindLimit;

    public ReceiptKind(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getReceiptKindLimit() {
        return receiptKindLimit;
    }

    public void setReceiptKindLimit(double receiptKindLimit) {
        this.receiptKindLimit = receiptKindLimit;
    }
}
