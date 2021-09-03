package com.youwetimelogger;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeCardHeader {

    private final String inputLine;

    public TimeCardHeader(String inputLine) {
        this.inputLine = inputLine;
    }

    public boolean isTimeCardHeader() {
        Pattern pattern = Pattern.compile("(\\d+:\\d+) - (\\d+:\\d+) - ([A-Z]+\\d*-\\d+)");
        Matcher matcher = pattern.matcher(this.inputLine);
        boolean headerMatch = matcher.lookingAt();
        if(!headerMatch) {
            System.out.println("timeCardHeader says no to headerMatch on this line: " + this.inputLine);
        }
        return headerMatch;
    }

    public HashMap<String, String> getHeaderData() {
        HashMap<String, String> headerData = new HashMap<String, String>();
        Pattern pattern = Pattern.compile("(\\d+:\\d+) - (\\d+:\\d+) - ([A-Za-z]+\\d*-\\d+)");
        Matcher matcher = pattern.matcher(inputLine);
        if (matcher.lookingAt()) {
            headerData.put("startTime", matcher.group(1));
            headerData.put("endTime", matcher.group(2));
            headerData.put("issueCode", matcher.group(3));
        }

        return headerData;
    }
}
