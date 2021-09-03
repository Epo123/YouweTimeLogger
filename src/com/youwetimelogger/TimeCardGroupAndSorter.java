package com.youwetimelogger;

import java.util.*;
import java.util.stream.Collectors;

public class TimeCardGroupAndSorter {
    public ArrayList<TimeCard> groupAndSort(ArrayList<TimeCard> report) {
        ArrayList<TimeCard> newReport = new ArrayList<TimeCard>();
        for (TimeCard timeCard: report) {
            if (timeCard.getIssueCode() == null) {
                newReport.add(timeCard);
            }
        }
        List<String> issueCodes = new ArrayList<>();
        for (TimeCard timeCard: report) {
            if (timeCard.getIssueCode() != null) {
               issueCodes.add(timeCard.getIssueCode());
            }
        }

        Map<String, Long> result = issueCodes.stream().collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
        LinkedHashMap<String, Long> reverseSortedMap = new LinkedHashMap<>();

        result.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        for (Map.Entry<String, Long> entry : reverseSortedMap.entrySet()) {
            for (TimeCard timeCard: report) {
                if (timeCard.getIssueCode() != null) {
                    if (timeCard.getIssueCode().equalsIgnoreCase(entry.getKey())) {
                        newReport.add(timeCard);
                    }
                }
            }
        }

        return newReport;
    }
}
