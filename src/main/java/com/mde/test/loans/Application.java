package com.mde.test.loans;

import com.mde.test.loans.service.LenderService;
import com.mde.test.loans.service.LoansCalculator;

import java.io.IOException;
import java.util.Locale;

import static com.mde.test.loans.util.Constants.*;

public class Application {

    public static void main(String[] args) throws IOException {

        // DI is probably overhead here
        LenderService lenderService = new LenderService(args[0]);
        lenderService.loadLenders();

        /*
        // Output some info
        lenderService.getLenders().forEach(System.out::println);
        System.out.println("Arguments provided: \"" + args[0] + "\", \"" + args[1] + "\"");
        System.out.println(String.format("Max possible loan: " + PAYMENT_FORMAT,  lenderService.getMaxLoan()));
        */

        Integer requestedLoan = Integer.parseInt(args[1]);

        if (!lenderService.hasEnoughMoney(requestedLoan)) {
            System.out.println(NOT_ENOUGH_MONEY);
        } else {
            LoansCalculator calculator = new LoansCalculator(requestedLoan, MONTHS_PERIOD, lenderService.getLenders());
            calculator.getLoans().forEach(System.out::println);
            System.out.println(String.format(Locale.ROOT, REQUESTED_LOAN_FORMAT, requestedLoan));
            System.out.println(String.format(Locale.ROOT, RATE_FORMAT, calculator.getRate(calculator.getRequestedLoan(), calculator.getMonthlyPayment())));
            System.out.println(String.format(Locale.ROOT, MONTHLY_REPAYMENT_FORMAT, calculator.getMonthlyPayment()));
            System.out.println(String.format(Locale.ROOT, TOTAL_REPAYMENT_FORMAT, calculator.getTotalPayment()));
        }

    }

}
