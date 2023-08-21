package com.recruitment;

import com.recruitment.model.ReceiptKind;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpensesConfig {
    private double dailyAllowance = 15.0;
    private double carMileage = 0.3;
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

    public void addReceiptKind(ReceiptKind receiptKind) {
        receiptKindSet.add(receiptKind);
    }

    public void removeReceiptKind(ReceiptKind receiptKind) {
        receiptKindSet.remove(receiptKind);
    }
}
