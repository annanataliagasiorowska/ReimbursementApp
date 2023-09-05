package com.recruitment;

import com.recruitment.model.ReceiptKind;

import java.util.Optional;

public class AdminService {

    private final ExpensesConfig expensesConfig;

    public AdminService(ExpensesConfig expensesConfig) {
        this.expensesConfig = expensesConfig;
    }


    public boolean checkIfReceiptKindExists(String name) {
        Optional<ReceiptKind> foundReceipt = expensesConfig.getReceiptKindSet().stream().filter(receiptKind -> receiptKind.getName().equalsIgnoreCase(name)).findAny();
        return foundReceipt.isPresent();
    }

    public void addReceiptKind(String name) {
        if (!checkIfReceiptKindExists(name)) {
            expensesConfig.getReceiptKindSet().add(new ReceiptKind(name));
        }
    }

    public void deleteReceiptKind(String name) {
        if (checkIfReceiptKindExists(name)) {
            expensesConfig.getReceiptKindSet().removeIf(receiptKind -> receiptKind.getName().equalsIgnoreCase(name));
        }
    }

    public void updateDailyAllowance(double dailyAllowance) {
        expensesConfig.setDailyAllowance(dailyAllowance);
    }
    public void updateCarMileage(double carMileage) {
        expensesConfig.setCarMileage(carMileage);
    }
    public void updateReimbursementLimit(double reimbursementLimit) { expensesConfig.setReimbursementLimit(reimbursementLimit); }


}
