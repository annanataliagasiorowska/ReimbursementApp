package com.recruitment;

import com.recruitment.model.Receipt;
import com.recruitment.model.Reimbursement;
import com.recruitment.model.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ReimbursementService {
    private final ExpensesConfig expensesConfig;
    private final ProjectRepository projectRepository;

    public ReimbursementService(ExpensesConfig expensesConfig, ProjectRepository projectRepository) {
        this.expensesConfig = expensesConfig;
        this.projectRepository = projectRepository;
    }

    public Reimbursement createReimbursement(
            User user, LocalDate date, int duration, List<Receipt> receiptList, long mileage) {
        Reimbursement reimbursement = new Reimbursement(user, date, duration, receiptList, mileage);
        projectRepository.addReimbursement(reimbursement);
        return reimbursement;
    }

    public double sumReimbursement(Reimbursement reimbursement) {
        double reimbursementSum = getTripLengthInDays(reimbursement) * expensesConfig.getDailyAllowance() +
                Math.min(reimbursement.getMileage(), expensesConfig.getDistanceLimit()) * expensesConfig.getCarMileage();
        reimbursement.setTotalReimbursement(reimbursementSum);
        return reimbursementSum;
    }

    public Reimbursement findReimbursement(Long id) {
        return projectRepository.findReimbursement(id);
    }

    public boolean addReceipt(Receipt receipt, long reimbursementId) {
        return projectRepository.addReceipt(receipt, reimbursementId);

    }

    public double sumTripExpenses(Reimbursement reimbursement) {
        double tripExpenses = sumReimbursement(reimbursement) +
                reimbursement.getReceiptList().stream().mapToDouble(Receipt::getAmount).sum();
        reimbursement.setTotalReimbursement(tripExpenses);
        return tripExpenses;
    }

    private int getTripLengthInDays(Reimbursement reimbursement) {
        long tripLength;
        if (reimbursement.getDuration() == 0) {
            tripLength = ChronoUnit.DAYS.between(reimbursement.getTripDate(), reimbursement.getEndTripDate());
        } else {
            tripLength = reimbursement.getDuration();
        }
        return (int)tripLength;
    }

}
