package com.mde.test.loans.util;

public class Constants {

    public static final int MONTHS_PERIOD = 36;
    public static final int MONTHS_IN_YEAR = 12;
    public static final int ACCURACY = 13;

    public static final String PAYMENT_FORMAT = "%.2f";
    public static final String REQUESTED_FORMAT = "%d";
    public static final String POUND = "\u00a3";

    public static final String REQUESTED_LOAN_FORMAT ="Requested amount: " + POUND + REQUESTED_FORMAT;
    public static final String MONTHLY_REPAYMENT_FORMAT ="Monthly repayment: " + POUND + PAYMENT_FORMAT;
    public static final String TOTAL_REPAYMENT_FORMAT ="Total repayment: " + POUND + PAYMENT_FORMAT;
    public static final String RATE_FORMAT = "Rate: %.1f%%";

    public static final String NOT_ENOUGH_MONEY = "It's not possible to provide the quote at the time";
}
