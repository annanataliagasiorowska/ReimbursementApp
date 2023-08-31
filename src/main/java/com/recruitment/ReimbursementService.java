package com.recruitment;

import com.recruitment.model.Receipt;
import com.recruitment.model.ReceiptKind;
import com.recruitment.model.Reimbursement;
import com.recruitment.model.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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
        double reimbursementSum = Math.min((getTripLengthInDays(reimbursement) * expensesConfig.getDailyAllowance() +
                Math.min(reimbursement.getMileage(), expensesConfig.getDistanceLimit()) * expensesConfig.getCarMileage()), expensesConfig.reimbursementLimit);
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
        double tripExpenses = Math.min((sumReimbursement(reimbursement) +
                reimbursement.getReceiptList().stream().mapToDouble(Receipt::getAmount).sum()), expensesConfig.reimbursementLimit);
        reimbursement.setTotalReimbursement(tripExpenses);
        return tripExpenses;
    }

    public boolean checkIfReceiptKindExists(String name) {
        Optional<ReceiptKind> foundReceipt = expensesConfig.getReceiptKindSet().stream().filter(receiptKind -> receiptKind.getName().equalsIgnoreCase(name)).findAny();
        return foundReceipt.isPresent();
    }

    public void deleteReceiptKind(String name) {
        if (checkIfReceiptKindExists(name)) {
            expensesConfig.getReceiptKindSet().removeIf(receiptKind -> receiptKind.getName().equalsIgnoreCase(name));
        }
    }

    public void addReceiptKind(String name) {
        if (!checkIfReceiptKindExists(name)) {
            expensesConfig.getReceiptKindSet().add(new ReceiptKind(name));
        }
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
