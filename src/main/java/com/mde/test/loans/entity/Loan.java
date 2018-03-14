package com.mde.test.loans.entity;

public class Loan {

    private Lender lender;
    private double monthlyPayment;
    private int months;

    public Loan(Lender lender, double monthlyPayment, int months) {
        this.lender = lender;
        this.monthlyPayment = monthlyPayment;
        this.months = months;
    }

    public double getTotalPayment() {
        return monthlyPayment * months;
    }

    public Lender getLender() {
        return lender;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public int getMonths() {
        return months;
    }

    @Override
    public String toString() {
        return monthlyPayment + " monthly, " + lender;
    }
}
