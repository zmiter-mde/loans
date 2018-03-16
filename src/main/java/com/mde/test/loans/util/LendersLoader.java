package com.mde.test.loans.util;

import com.mde.test.loans.entity.Lender;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

public class LendersLoader {

    private final String lendersFilename;

    public LendersLoader(String lendersFilename) {
        this.lendersFilename = lendersFilename;
    }

    public List<Lender> getLenders() throws IOException, NumberFormatException {
        List<Lender> lenders = new LinkedList<>();

        CSVParser parser = parseData();

        parser.getRecords()
                .forEach((record) -> lenders.add(getLender(record)));

        return lenders;
    }

    public CSVParser parseData() throws IOException {
        Reader in = new FileReader(lendersFilename);
        return CSVFormat.DEFAULT
                .withSkipHeaderRecord()
                .withHeader(LendersHeader.class)
                .parse(in);
    }

    private Lender getLender(CSVRecord record) throws NumberFormatException{
        String lenderName = record.get(LendersHeader.Lender);
        Double rate = Double.parseDouble(record.get(LendersHeader.Rate));
        Double amountAvailable = Double.parseDouble(record.get(LendersHeader.Available));
        return new Lender(lenderName, rate, amountAvailable, record.getRecordNumber());
    }

    public String getLendersFilename() {
        return lendersFilename;
    }
}
