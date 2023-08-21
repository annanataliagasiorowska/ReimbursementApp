package com.recruitment.model;

import java.time.LocalDate;
import java.util.List;

public class Reimbursement {
    private long id;
    private final User user;
    private LocalDate tripDate;
    private int duration;
    private LocalDate endTripDate;
    private List<Receipt> receiptList;
    private long mileage;
    private double totalReimbursementLimit;
    private long mileageLimit;

    public Reimbursement(User user, LocalDate tripDate, int duration, List<Receipt> receiptList, long mileage) {
        this.user = user;
        this.tripDate = tripDate;
        this.duration = duration;
        this.receiptList = receiptList;
        this.mileage = mileage;
    }

    public Reimbursement(User user, LocalDate tripDate, LocalDate endTripDate, List<Receipt> receiptList, long mileage) {
        this.user = user;
        this.tripDate = tripDate;
        this.endTripDate = endTripDate;
        this.receiptList = receiptList;
        this.mileage = mileage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getTripDate() {
        return tripDate;
    }

    public LocalDate getEndTripDate() {
        return endTripDate;
    }

    public int getDuration() {
        return duration;
    }

    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public long getMileage() {
        return mileage;
    }

    public User getUser() {
        return user;
    }

    public void setTripDate(LocalDate tripDate) {
        this.tripDate = tripDate;
    }

    public void setEndTripDate(LocalDate endTripDate) {
        this.endTripDate = endTripDate;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }
    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public double getReimbursementLimit() {
        return totalReimbursementLimit;
    }

    public void setReimbursementLimit(double reimbursementLimit) {
        this.totalReimbursementLimit = reimbursementLimit;
    }

    public long getMileageLimit() {
        return mileageLimit;
    }

    public void setMileageLimit(long mileageLimit) {
        this.mileageLimit = mileageLimit;
    }
}
