package com.mde.test.loans.service;

import org.apache.commons.math3.util.Precision;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static com.mde.test.loans.util.Constants.EPS;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoansCalculatorTest {

    @InjectMocks
    private LoansCalculator loansCalculator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateMonthlyPaymentWithZeroR() {
        double r = 0, p = 10, expected = 2;
        int n = 5;
        double actual = loansCalculator.calculateMonthlyPayment(r, p, n);
        assertEquals(expected, actual, EPS);
    }

    @Test
    public void testCalcualteMonthlyPayment() {
        double r = 0.069 / 12, p = 480, expected = 14.8;
        int n = 36;
        double actual = loansCalculator.calculateMonthlyPayment(r, p, n);
        assertEquals(expected, Precision.round(actual, 2), EPS);
    }

    @Test
    public void testGetRate() {
        double requestedLoan = 1000, monthlyPayment = 30.88;
        double rate = loansCalculator.getRate(requestedLoan, monthlyPayment);
        assertEquals(7.0, Precision.round(rate, 1), EPS);

    }

}
