package com.recruitment;

import com.recruitment.model.Receipt;
import com.recruitment.model.Reimbursement;
import com.recruitment.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * This class plays role of database
 */
public class ProjectRepository {
    List<Reimbursement> reimbursementList = new ArrayList<>();
    AtomicLong reimbursementId = new AtomicLong();
    /* Map of receipts for reimbursementId */
    Map<Long, List<Receipt>> receiptMap = new HashMap<>();

    public void addReimbursement(Reimbursement reimbursement) {
        reimbursement.setId(reimbursementId.incrementAndGet());
        reimbursementList.add(reimbursement);
    }

    public List<Reimbursement> getUserReimbursements(User user) {
        return reimbursementList.stream()
                .filter(reimbursement -> reimbursement.getUser().getName().equals(user.getName()))
                .collect(Collectors.toList());
    }

    public Reimbursement findReimbursement(long id) {
        return reimbursementList
                .stream()
                .filter(reimbursement -> reimbursement.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public boolean addReceipt(Receipt receipt, long reimbursementId) {
        // just to verify there's corresponding reimbursement defined
        Reimbursement reimbursement = findReimbursement(reimbursementId);
        if ((reimbursement.getTripDate().isBefore(receipt.getDate()) ||
                reimbursement.getTripDate().isEqual(receipt.getDate())) &&
                (reimbursement.getTripDate().plusDays(reimbursement.getDuration() - 1).isAfter(receipt.getDate()) ||
                        reimbursement.getTripDate().plusDays(reimbursement.getDuration() - 1).isEqual(receipt.getDate()))) {
            List<Receipt> receipts = receiptMap.get(reimbursementId);
            if (receipts == null) {
                receipts = new ArrayList<>();
                receiptMap.put(reimbursementId, receipts);
                reimbursement.setReceiptList(receipts);
            }
            receipts.add(receipt);
            return true;
        }
        return false;
    }


}
