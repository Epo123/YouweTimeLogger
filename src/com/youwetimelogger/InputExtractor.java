package com.youwetimelogger;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;

public class InputExtractor {
    private final String input;
    private final ArrayList<TimeCard> report;
    private final TimeCardBuilder timeCardBuilder;

    public InputExtractor(String input) {
        this.input = input;
        this.report = new ArrayList<TimeCard>();
        this.timeCardBuilder = new TimeCardBuilder();
    }

    public ArrayList<TimeCard> getReport() throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader(this.input));
        String line = null;

        while((line=reader.readLine()) != null) {
            this.timeCardBuilder.addLine(line);
            if (this.timeCardBuilder.isCardComplete()) {
                this.report.add(this.timeCardBuilder.getCurrentTimeCard());
                this.timeCardBuilder.clearContent();
            }
        }
        TimeCard lastTimeCard = this.timeCardBuilder.getNextTimeCard();
        if (lastTimeCard != null) {
            this.report.add(lastTimeCard);
        }
        
        return this.report;
    }
}
