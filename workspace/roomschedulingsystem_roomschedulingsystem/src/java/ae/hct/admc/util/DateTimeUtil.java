/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.hct.admc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Saeed
 */
public class DateTimeUtil {

    private static String DEFAULT_PATTERN = "dd-MM-yyyy";
    private static SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);

    public static Date getWeekStartDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(cal.DAY_OF_WEEK) != cal.SATURDAY) {
            cal.set(cal.DAY_OF_YEAR, -cal.get(cal.DAY_OF_WEEK));
        }
        return cal.getTime();
    }

    public static Date getWeekEndDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(cal.DAY_OF_WEEK) != cal.FRIDAY) {
            cal.add(cal.DAY_OF_YEAR, (cal.SATURDAY - 1));
        }
        return cal.getTime();
    }

    public static int getNumberOfWeeksBetweenDates(Date startDate, Date endDate) {
        int numberOfWeeks = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);

        while (cal.getTime().before(endDate)) {
            numberOfWeeks++;
            cal.add(cal.DAY_OF_YEAR, 7);
        }
        return numberOfWeeks;
    }

    public static void addDaysToCalendar(Calendar c, int numberOfDays) {
        c.add(c.DAY_OF_YEAR, numberOfDays);
    }

    public static String getDayOfWeekName(int dayNumber) {

        switch (dayNumber) {
            case Calendar.SATURDAY:
                return "Sat";
            case Calendar.SUNDAY:
                return "Sun";
            case Calendar.MONDAY:
                return "Mon";
            case Calendar.TUESDAY:
                return "Tue";
            case Calendar.WEDNESDAY:
                return "Wed";
            case Calendar.THURSDAY:
                return "Thu";
            case Calendar.FRIDAY:
                return "Fri";
        }

        return "Unknown";
    }

    public static String getFormatedDate(Date date, String pattern) {
        if (pattern != null) {
            sdf.applyPattern(pattern);
        }
        return getFormatedDate(date);
    }

    public static String getFormatedDate(Date date) {
        String formatedDateString = null;
        if (date != null) {
            formatedDateString = sdf.format(date);
            sdf.applyPattern(DEFAULT_PATTERN);
        }
        return formatedDateString;
    }

    public static Date parseDateString(String dateString) {
        Date date = null;
        if(dateString == null)return null;
        try {
            sdf.setLenient(false);
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            date = null;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date");
            date = null;
        }
        sdf.applyPattern(DEFAULT_PATTERN);
        return date;
    }
    
    public static Date parseDateString(String dateString, String pattern) {
        sdf.applyPattern(pattern);
        return parseDateString(dateString);
    }
    
    public static String getStringFromDate(Date date) {
        String dateString = "";
        if (date != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            dateString = sdf.format(date);
        }
        sdf.applyPattern(DEFAULT_PATTERN);
        return dateString;
    }
    
    public static String getStringFromDate(Date date, String pattern) {
        sdf.applyPattern(pattern);
        return getStringFromDate(date);
    }
    
}
