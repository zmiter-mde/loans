package com.mde.test.loans.service;

import com.mde.test.loans.entity.Lender;
import com.mde.test.loans.entity.LenderBuilder;
import com.mde.test.loans.util.LendersLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LenderServiceTest {
    /*
        @InjectMocks
        private LenderService lenderService;

        @Mock
        private LendersLoader lendersLoader;
    */
    private String filename;
    private List<Lender> lenders;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(LenderService.class);
        //lendersLoader = mock(LendersLoader.class);
        //filename = "test.filename";
        //lenders = initLenders();
        //lenderService = new LenderService(filename);
        //lenderService.setLenders(lenders);
    }

    private List<Lender> initLenders() {
        List<Lender> lenders = new ArrayList<>(3);

        Lender lender1 = new LenderBuilder()
                .withLenderNumber(1)
                .withAmountAvailable(500.0)
                .withName("First")
                .withRate(10.0)
                .build();

        Lender lender2 = new LenderBuilder()
                .withLenderNumber(2)
                .withAmountAvailable(500.0)
                .withName("Second")
                .withRate(5.0)
                .build();

        Lender lender3 = new LenderBuilder()
                .withLenderNumber(3)
                .withAmountAvailable(500.0)
                .withName("Third")
                .withRate(20.0)
                .build();

        lenders.add(lender1);
        lenders.add(lender2);
        lenders.add(lender3);

        return lenders;
    }

    @Test
    public void testHasEnoughMoney()  {
        //System.out.println(lenderService.maxLoan);
        /*
        lenderService.setMaxLoan(1500.0);
        assertTrue(lenderService.hasEnoughMoney(1000));
        assertFalse(lenderService.hasEnoughMoney(1501));
        */
    }

}
