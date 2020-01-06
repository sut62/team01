package com.din.dinentity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * test
 */
public class test {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = new Date();
        System.out.println(d1);
        System.out.println(sdf.format(d1));
        // System.out.println("date1 : " + sdf.format(date1));
        // System.out.println("date2 : " + sdf.format(date2));
        // System.out.println("date1 vs date2 = " + date1.compareTo(date2));
        // if (date1.compareTo(date2) > 0) {
        // System.out.println("Date1 is after Date2");
        // } else if (date1.compareTo(date2) < 0) {
        // System.out.println("Date1 is before Date2");
        // } else if (date1.compareTo(date2) == 0) {
        // System.out.println("Date1 is equal to Date2");
        // } else {
        // System.out.println("How to get here?");
        // }

    }
}