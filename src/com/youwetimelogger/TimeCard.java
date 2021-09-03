package com.youwetimelogger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCard {
    private String startTime;
    private String endTime;
    private String issueCode;
    private String headerMessage;
    private String content;
    private boolean headerIsSet;

    public TimeCard() {
        this.content = "";
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIssueCode() {
        return issueCode;
    }

    public void setIssueCode(String issueCode) {
        this.issueCode = issueCode;
    }

    public String getContent() {
        return content;
    }

    public void addLine(String line) {
        this.content += line + "\n";
    }

    public Double getDurationTotal() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        try {
            Date startTime = format.parse(this.startTime);
            Date endTime = format.parse(this.endTime);

            long diff = endTime.getTime() - startTime.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000);
            double diffJiraMinutes =  ((double) diffMinutes / (double) 60) * (double) 100;

            return Double.parseDouble((int) diffHours + "." + (int) diffJiraMinutes);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public String getDuration() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        try {
            Date startTime = format.parse(this.startTime);
            Date endTime = format.parse(this.endTime);

            long diff = endTime.getTime() - startTime.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000);
            double diffJiraMinutes =  ((double) diffMinutes / (double) 60) * (double) 100;

            return diffHours + "." + (int) diffJiraMinutes + " - " + diffHours + ":" + diffMinutes;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    };

    public String getHeaderMessage() {
        return headerMessage;
    }

    public void setHeaderMessage(String headerMessage) {
        this.headerMessage = headerMessage;
    }

    public boolean isHeaderIsSet() {
        return headerIsSet;
    }

    public void setHeaderIsSet(boolean headerIsSet) {
        this.headerIsSet = headerIsSet;
    }
}
