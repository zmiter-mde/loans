package com.mde.test.loans.entity;

public class LenderBuilder {

    private String name;
    private double rate;
    private double amountAvailable;
    private long lenderNumber;

    public LenderBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public LenderBuilder withRate(double rate) {
        this.rate = rate;
        return this;
    }

    public LenderBuilder withAmountAvailable(double amountAvailable) {
        this.amountAvailable = amountAvailable;
        return this;
    }

    public LenderBuilder withLenderNumber(long lenderNumber) {
        this.lenderNumber = lenderNumber;
        return this;
    }

    public Lender build() {
        return new Lender(name, rate, amountAvailable, lenderNumber);
    }

}
