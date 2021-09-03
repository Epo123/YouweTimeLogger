package com.youwetimelogger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeCardHeaderBuilder {
    public TimeCard build(String inputLine) throws Exception {
        boolean headerMatch = isIssueCodeHeaderWithMessage(inputLine);
        if(headerMatch) {
            System.out.println(inputLine + " Detected isIssueCodeHeaderWithMessage");
            return this.createIssueCodeHeaderWithMessageTimeCard(inputLine);
        }

        headerMatch = isIssueCodeHeader(inputLine);
        if(headerMatch) {
            System.out.println(inputLine + " Detected isIssueCodeHeader");
            return this.createIssueCodeTimeCard(inputLine);
        }

        headerMatch = isTimeCardHeaderWithMessage(inputLine);
        if(headerMatch) {
            System.out.println(inputLine + "Detected isTimeCardHeaderWithMessage");
            return this.createTimeCardWithMessage(inputLine);
        }

        headerMatch = isTimeCardHeader(inputLine);
        if(headerMatch) {
            System.out.println(inputLine + " Detected isTimeCardHeader");
            return this.createTimeCard(inputLine);
        }
        throw new Exception("Unable to build this kind of timecard.");
    }

    private TimeCard createTimeCardWithMessage(String inputLine) {
        TimeCard timeCard = new TimeCard();
        Pattern pattern = Pattern.compile("(?<startTime>\\d+:\\d+) - (?<endTime>\\d+:\\d+) - (?<message>.+)");
        Matcher matcher = pattern.matcher(inputLine);
        matcher.find();

        timeCard.setStartTime(matcher.group("startTime"));
        timeCard.setEndTime(matcher.group("endTime"));
        timeCard.setHeaderMessage(matcher.group("message"));
        timeCard.setHeaderIsSet(true);

        return timeCard;
    }

    private boolean isTimeCardHeaderWithMessage(String inputLine) {
        Pattern pattern = Pattern.compile("(?<startTime>\\d+:\\d+) - (?<endTime>\\d+:\\d+) - (?<message>.+)");
        Matcher matcher = pattern.matcher(inputLine);
        return matcher.find();
    }

    private TimeCard createTimeCard(String inputLine) {
        TimeCard timeCard = new TimeCard();
        Pattern pattern = Pattern.compile("(?<startTime>\\d+:\\d+) - (?<endTime>\\d+:\\d+)");
        Matcher matcher = pattern.matcher(inputLine);
        matcher.find();

        timeCard.setStartTime(matcher.group("startTime"));
        timeCard.setEndTime(matcher.group("endTime"));
        timeCard.setHeaderIsSet(true);

        return timeCard;
    }

    public boolean isTimeCardHeader(String inputLine) {
        Pattern pattern = Pattern.compile("(?<startTime>\\d+:\\d+) - (?<endTime>\\d+:\\d+)");
        Matcher matcher = pattern.matcher(inputLine);
        boolean headerMatch = matcher.find();
        return headerMatch;
    }

    private TimeCard createIssueCodeTimeCard(String inputLine) {
        TimeCard timeCard = new TimeCard();
        Pattern pattern = Pattern.compile("(?<startTime>\\d+:\\d+) - (?<endTime>\\d+:\\d+) - (?<issueCode>[A-Za-z]+\\d*-\\d+)");
        Matcher matcher = pattern.matcher(inputLine);
        matcher.find();

        timeCard.setStartTime(matcher.group("startTime"));
        timeCard.setEndTime(matcher.group("endTime"));
        timeCard.setIssueCode(matcher.group("issueCode"));
        timeCard.setHeaderIsSet(true);

        return timeCard;
    }

    private boolean isIssueCodeHeader(String inputLine) {
        Pattern pattern = Pattern.compile("(?<startTime>\\d+:\\d+) - (?<endTime>\\d+:\\d+) - (?<issueCode>[A-Za-z]+\\d*-\\d+)");
        Matcher matcher = pattern.matcher(inputLine);
        boolean headerMatch = matcher.find();
        return headerMatch;
    }

    private TimeCard createIssueCodeHeaderWithMessageTimeCard(String inputLine) {
        TimeCard timeCard = new TimeCard();
        Pattern pattern = Pattern.compile("(?<startTime>\\d+:\\d+) - (?<endTime>\\d+:\\d+) - (?<issueCode>[A-Za-z]+\\d*-\\d+) - (?<message>.+)");
        Matcher matcher = pattern.matcher(inputLine);
        matcher.find();

        timeCard.setStartTime(matcher.group("startTime"));
        timeCard.setEndTime(matcher.group("endTime"));
        timeCard.setIssueCode(matcher.group("issueCode"));
        timeCard.setHeaderMessage(matcher.group("message"));
        timeCard.setHeaderIsSet(true);

        return timeCard;
    }

    private boolean isIssueCodeHeaderWithMessage(String inputLine) {
        Pattern pattern = Pattern.compile("(?<startTime>\\d+:\\d+) - (?<endTime>\\d+:\\d+) - (?<issueCode>[A-Za-z]+\\d*-\\d+) - (?<message>.+)");
        Matcher matcher = pattern.matcher(inputLine);
        boolean headerMatch = matcher.find();
        return headerMatch;
    }
}
