package com.mde.test.loans.service;

import com.mde.test.loans.entity.Lender;
import com.mde.test.loans.entity.Loan;
import org.apache.commons.math3.util.Precision;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static com.mde.test.loans.util.Constants.ACCURACY;
import static com.mde.test.loans.util.Constants.MONTHS_IN_YEAR;
import static com.mde.test.loans.util.Constants.MONTHS_PERIOD;

public class LoansCalculator {

    private final double requestedLoan; // the amount borrowed, known as the loan's principal.
    private final int months; // the number of monthly payments, called the loan's term, and
    private List<Loan> loans;
    private double totalPayment;
    private double monthlyPayment;


    public LoansCalculator(double requestedLoan, int months, List<Lender> lenders) {
        this.requestedLoan = requestedLoan;
        this.months = months;
        this.loans = new LinkedList<>();
        // Assuming here the lenders have enough to cover the requested amount
        // It's validated outside
        calculateLoans(lenders);
    }

    // Assuming that lenders are already ordered by ascending rate (LenderService.orderLendersByRateAsc())
    private void calculateLoans(List<Lender> lenders) {
        ListIterator<Lender> iterator = lenders.listIterator();
        double loanLeft = requestedLoan, currentLoan = 0;
        while (iterator.hasNext() && loanLeft > 0) {
            Lender lender = iterator.next();
            currentLoan = Math.min(loanLeft, lender.getAmountAvailable()); // borrow as much as we can

            Loan loan = createLoan(lender, currentLoan);
            loans.add(loan);
            totalPayment += loan.getTotalPayment();
            monthlyPayment += loan.getMonthlyPayment();

            loanLeft = Math.max(0, loanLeft - lender.getAmountAvailable()); // what's left to borrow
        }
    }

    private Loan createLoan(Lender lender, double currentLoan) {
        double monthlyRate = lender.getRate() / MONTHS_IN_YEAR;

        double monthlyPayment = calculateMonthlyPayment(monthlyRate, currentLoan, months);

        return new Loan(lender, monthlyPayment, months);
    }

    /*
    * Taken from here https://en.wikipedia.org/wiki/Mortgage_calculator#Monthly_payment_formula
    * */
    private double calculateMonthlyPayment(double r, double p, int n) {
        if (r == 0) {
            return p / n;
        }
        double result = (r * p) / (1 - Math.pow(1 + r, -n));
        return Precision.round(result, ACCURACY);
    }

    /*
    * I have lost hope to find a formula, so god bless dichotomy
    * */
    public double getRate() {
        double l = 0, r = 1, x = 0, current = 0, p = requestedLoan, eps = 0.00001;
        int n = MONTHS_PERIOD;

        while (Math.abs(calculateMonthlyPayment(r, p, n) -
                        calculateMonthlyPayment(l, p, n)) > eps) {
            x = (r + l) / 2;
            current = calculateMonthlyPayment(x, p, n);
            if (current - monthlyPayment > 0) {
                r = x;
            } else {
                l = x;
            }
        }

        return x * 12 * 100;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }
}
