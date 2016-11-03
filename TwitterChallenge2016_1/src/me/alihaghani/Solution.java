package me.alihaghani;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static TreeMap<Date, DataPoint> data = new TreeMap<Date, DataPoint>(new Comparator<Date>() {
        @Override
        public int compare(Date o1, Date o2) {
            return o1.after(o2) ? 1 : -1;
        }
    });
    static TreeMap<Date, ArrayList> results = new TreeMap<Date, ArrayList>(new Comparator<Date>() {
        @Override
        public int compare(Date o1, Date o2) {
            return o1.after(o2) ? 1 : -1;
        }
    });

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner sc = new Scanner(System.in);

        // getting date range
        String queryRangeWithSpaces = sc.nextLine();
        String queryRange = queryRangeWithSpaces.replaceAll("\\s", "");
        String[] queryRangeArr = queryRange.split(",");
        String fromDateStr = queryRangeArr[0];
        String toDateStr = queryRangeArr[1];
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm");
        Date fromDate = dateFormat.parse(fromDateStr);
        Date toDate = dateFormat.parse(toDateStr);

        sc.nextLine();

        while (sc.hasNextLine()){
            parseData(sc.nextLine());
        }


        Map withinRangeData = data.subMap(fromDate, toDate);

        Set withinRangeSet = withinRangeData.entrySet();
        Iterator withinRangeSetIterator = withinRangeSet.iterator();

        while(withinRangeSetIterator.hasNext()){
            Map.Entry<Date, DataPoint> entry = (Map.Entry)withinRangeSetIterator.next();
            Date dateOriginal = entry.getKey();
            DateFormat df = new SimpleDateFormat("yyyy-mm");
            String dateString = df.format(dateOriginal);
            Date date = df.parse(dateString);

            final DataPoint currData = entry.getValue();

            if (!results.containsKey(date)){ // new date, insert data with array of one datapoint
                results.put(date, new ArrayList<DataPoint>() {{add(currData);}});
            } else { // date already in results
                ArrayList<DataPoint> currDataPointsArr = results.get(date);
                if (currDataPointsArr.contains(currData.category)){
                    int indexOfCat = currDataPointsArr.indexOf(currData.category);
                    int currCatValue = (currDataPointsArr.get(indexOfCat)).number;
                    int updatedCatValue = currCatValue + currData.number;
                    DataPoint updatedDataPoint = new DataPoint(currDataPointsArr.get(indexOfCat).category, updatedCatValue);
                    currDataPointsArr.add(indexOfCat, updatedDataPoint);
                } else {
                    currDataPointsArr.add(currData);
                }
                results.put(date, currDataPointsArr);
            }
        }

        for (Map.Entry<Date, ArrayList> entry: results.entrySet()){
            Date date = entry.getKey();
            ArrayList<DataPoint> dataArr = entry.getValue();

            DateFormat df = new SimpleDateFormat("yyyy-mm");
            String dateString = df.format(date);

            System.out.print(dateString);

            for (int i = 0; i < dataArr.size(); i++){
                System.out.print(", " + dataArr.get(i).category);
                System.out.print(", " + dataArr.get(i).number);
            }

            System.out.println();
        }


    }


    private static void parseData(String line){
        String lineNoSpaces = line.replaceAll("\\s", "");
        String[] lineArr = lineNoSpaces.split(","); // array of [date, category, value]
        Date lineDate = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String lineDateString = lineArr[0]; // Date String format
        try {
            lineDate = dateFormat.parse(lineDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String lineCategory = lineArr[1];
        int lineInt = Integer.parseInt(lineArr[2]);

        DataPoint dataPoint = new DataPoint(lineCategory, lineInt);

        data.put(lineDate, dataPoint);
    }

    static class DataPoint {
        private String category;
        private int number;

        public DataPoint(String category, int number){
            this.category = category;
            this.number = number;
        }

        public String category(){
            return category;
        }

        public int value(){
            return number;
        }
    }
}



