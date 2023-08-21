package com.recruitment;

import com.recruitment.model.ReceiptKind;

public class AdminService {

    private ExpensesConfig expensesConfig;

    public AdminService(ExpensesConfig expensesConfig) {
        this.expensesConfig = expensesConfig;
    }

    public void addReceiptKind(ReceiptKind receiptKind) {
        expensesConfig.addReceiptKind(receiptKind);
    }

    public void deleteReceiptKind(ReceiptKind receiptKind) {
        expensesConfig.removeReceiptKind(receiptKind);
    }

    public void updateDailyAllowance(double dailyAllowance) {
        expensesConfig.setDailyAllowance(dailyAllowance);
    }

    public void updateCarMileage(double carMileage) {
        expensesConfig.setCarMileage(carMileage);
    }


}
