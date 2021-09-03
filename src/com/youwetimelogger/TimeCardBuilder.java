package com.youwetimelogger;

public class TimeCardBuilder {
    private final TimeCardHeaderBuilder timeCardHeaderBuilder;
    protected TimeCard currentTimeCard;

    private boolean isCardComplete;
    private TimeCard lastTimeCard;
    private TimeCard nextTimeCard;

    public TimeCardBuilder() {
        this.timeCardHeaderBuilder = new TimeCardHeaderBuilder();
    }

    public boolean isCardComplete() {
        return isCardComplete;
    }

    public void clearContent() {
        this.currentTimeCard = null;
        this.isCardComplete = false;
    }

    public TimeCard getCurrentTimeCard() {
        return this.currentTimeCard;
    }

    public TimeCard getNextTimeCard() {
        return this.nextTimeCard;
    }

    public void addLine(String line) throws Exception {
        if (this.nextTimeCard != null) {
            this.currentTimeCard = this.nextTimeCard;
        }
        if (this.currentTimeCard == null) {
            this.currentTimeCard = new TimeCard();
        }

        boolean isHeader = this.timeCardHeaderBuilder.isTimeCardHeader(line);
        if (this.currentTimeCard.isHeaderIsSet() && isHeader) {
            // TODO: Can be abstracted by making a setHeader with a type or something
            this.nextTimeCard = this.timeCardHeaderBuilder.build(line);
            this.nextTimeCard.addLine(line);
            this.isCardComplete = true;
        } else if (isHeader) {
            this.currentTimeCard = this.timeCardHeaderBuilder.build(line);
            this.currentTimeCard.addLine(line);
        } else {
            this.currentTimeCard.addLine(line);
        }
    }
}
