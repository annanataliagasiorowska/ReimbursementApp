package com.recruitment;

import com.recruitment.model.ReceiptKind;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpensesConfig {
    private double dailyAllowance = 15.0;
    private double carMileage = 0.3;
    double reimbursementLimit = Integer.MAX_VALUE;
    int distanceLimit = Integer.MAX_VALUE;
    Set<ReceiptKind> receiptKindSet = new HashSet<>(List.of(
            new ReceiptKind("Taxi"),
            new ReceiptKind("Hotel"),
            new ReceiptKind("Plain"),
            new ReceiptKind("Train")
    ));

    public double getDailyAllowance() {
        return dailyAllowance;
    }

    public void setDailyAllowance(double dailyAllowance) {
        this.dailyAllowance = dailyAllowance;
    }

    public double getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(double carMileage) {
        this.carMileage = carMileage;
    }

    public Set<ReceiptKind> getReceiptKindSet() {
        return receiptKindSet;
    }

    public double getReimbursementLimit() {
        return reimbursementLimit;
    }

    public void setReimbursementLimit(double reimbursementLimit) {
        this.reimbursementLimit = reimbursementLimit;
    }

    public int getDistanceLimit() {
        return distanceLimit;
    }

    public void setDistanceLimit(int distanceLimit) {
        this.distanceLimit = distanceLimit;
    }
}
