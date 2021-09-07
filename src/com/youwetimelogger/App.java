package com.youwetimelogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class App {
    private JButton show;
    private JPanel panelMain;
    private JTextArea input;
    private JTextArea output;
    private JScrollPane inputScrollPane;
    private JScrollPane outputScrollPane;
    private JScrollBar inputScrollBar;
    private JScrollBar outputScrollBar;

    public App() {
        outputScrollBar.setModel(inputScrollBar.getModel());

        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processInput(input.getText());
            }
        });
    }

    public void processInput(String input) {
        InputExtractor inputExtractor = new InputExtractor(input);
        try {
            StringBuilder output = new StringBuilder();
            Double totalDuration = 0.0;
            ArrayList<TimeCard> report = inputExtractor.getReport();
            for (TimeCard timeCard : report) {
                output.append(timeCard.getDuration()).append(" - ").append(timeCard.getContent());
                totalDuration += timeCard.getDurationTotal();
            }
            this.output.setText(output.toString());
            this.output.setText(this.output.getText() + "\n\nTotal duration: " + totalDuration);

            TimeCardGroupAndSorter timeCardGrouper = new TimeCardGroupAndSorter();
            report = timeCardGrouper.groupAndSort(report);

            output = new StringBuilder();

            this.output.setText(this.output.getText() + "\n\n================\n");
            String lastIssueCode = "";
            Double totalDurationPerGroup = 0.0;
            for (TimeCard timeCard: report) {
                if (timeCard.getIssueCode() != null &&
                        !timeCard.getIssueCode().equalsIgnoreCase(lastIssueCode)
                ) {
                    output.append("\n").append(lastIssueCode).append(" - ").append("Total duration: ").append(totalDurationPerGroup).append("\n\n");
                    totalDurationPerGroup = timeCard.getDurationTotal();
                    lastIssueCode = timeCard.getIssueCode();
                } else {
                    totalDurationPerGroup += timeCard.getDurationTotal();
                }
                output.append(timeCard.getDurationJiraMinutes()).append(" - ")
                        .append(timeCard.getStartTime()).append(" - ")
                        .append(timeCard.getEndTime()).append(" - ")
                        .append(timeCard.getIssueCode()).append(" - ")
                        .append(timeCard.getHeaderMessage())
                        .append("\n");
            }
            output.append("\n").append(lastIssueCode).append(" - ").append("Total duration: ").append(totalDurationPerGroup).append("\n\n");
            this.output.setText(this.output.getText() + output.toString());
        } catch (Exception e) {
            e.printStackTrace();
            this.output.setText("ERROR! " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("YouweTimeLogger");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setVisible(true);
    }
}
