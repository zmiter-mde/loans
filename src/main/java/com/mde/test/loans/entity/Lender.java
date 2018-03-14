package com.mde.test.loans.entity;

public class Lender {

    private final String name;
    private final double rate;
    // It wasn't stated that the amount can only be integer
    private final double amountAvailable;
    private final long lenderNumber;

    public Lender(String name, double rate, double amountAvailable, long lenderNumber) {
        this.name = name;
        this.rate = rate;
        this.amountAvailable = amountAvailable;
        this.lenderNumber = lenderNumber;
    }

    // No need for setters as lenders never change in the task

    public String getName() {
        return name;
    }

    public Double getRate() {
        return rate;
    }

    public Double getAmountAvailable() {
        return amountAvailable;
    }

    @Override
    public String toString() {
        return "Lender " + lenderNumber + ". '" +
                name + "' : " +
                rate + " rate : " +
                amountAvailable + " amount available";
    }

}
