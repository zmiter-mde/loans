package com.mde.test.loans.service;

import com.mde.test.loans.entity.Lender;
import com.mde.test.loans.util.LendersLoader;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class LenderService {

    protected List<Lender> lenders;
    protected LendersLoader lendersLoader;
    protected Double maxLoan;
    private final String lendersFilename;

    public LenderService(String lendersFilename) {
        this.lendersFilename = lendersFilename;
    }

    public void loadLenders() throws IOException, NumberFormatException {
        loadLenders(true);
    }

    public void loadLenders(boolean orderByRateAsc) throws IOException, NumberFormatException {
        lendersLoader = new LendersLoader(lendersFilename);
        lenders = lendersLoader.getLenders();
        maxLoan = calculateMaxLoan();
        if (orderByRateAsc) {
            orderLendersByRateAsc();
        }
    }

    protected Double calculateMaxLoan() {
        double loan = 0;

        for (Lender lender : lenders) {
            loan += lender.getAmountAvailable();
        }

        return loan;
    }

    public boolean hasEnoughMoney(Integer requestedLoan) {
        return maxLoan.compareTo(requestedLoan.doubleValue()) > 0;
    }

    protected void orderLendersByRateAsc() {
        lenders.sort(Comparator.comparing(Lender::getRate));
    }

    public List<Lender> getLenders() {
        return lenders;
    }

    protected void setLenders(List<Lender> lenders) {
        this.lenders = lenders;
    }

    protected void setMaxLoan(double maxLoan) {
        this.maxLoan = maxLoan;
    }

    public double getMaxLoan() {
        return maxLoan;
    }

}
