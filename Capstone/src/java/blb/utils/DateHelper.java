/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Sebastian Wild
 */
public class DateHelper {
    public DateHelper() {}
    
    public String nextDate(String date) throws ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMMM d, y");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        c.add(Calendar.DATE, 1);
       
        return sdf.format(c.getTime());
    }
    
    public String prevDate(String date) throws ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMMM d, y");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        c.add(Calendar.DATE, -1);
       
        return sdf.format(c.getTime());
    }
    
    public String prevWeekStart(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMM. d, yyyy", Locale.ENGLISH);
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        c.add(Calendar.DATE, -7);
        return new SimpleDateFormat("EEEE MMM d").format(c.getTime());
    }
    
    public String prevWeekEnd(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMM. d, yyyy", Locale.ENGLISH);
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        c.add(Calendar.DATE, -7);
        return sdf.format(c.getTime());
    }
    
    public String nextWeekStart(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMM. d, yyyy", Locale.ENGLISH);
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        c.add(Calendar.DATE, 7);
        return new SimpleDateFormat("EEEE MMM d").format(c.getTime());
    }
    public String nextWeekEnd(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMM. d, yyyy", Locale.ENGLISH);
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));
        c.add(Calendar.DATE, 7);
        return sdf.format(c.getTime());
    }
    
    public String weekStartDate(String date) throws ParseException {
        Date startDate = new SimpleDateFormat("EEE MMM dd yyyy '00:00:00' 'GMT'Z '('zzzz')'", Locale.ENGLISH).parse(date);
        return new SimpleDateFormat("EEEE MMM d").format(startDate);
    }
    public String weekEndDate(String date) throws ParseException {
        Date endDate = new SimpleDateFormat("EEE MMM dd yyyy '00:00:00' 'GMT'Z '('zzzz')'", Locale.ENGLISH).parse(date);
        return new SimpleDateFormat("EEEE MMM d, y").format(endDate);
    }
    public String weekStartToDatabase(String date) throws ParseException {
        Date newDate = new SimpleDateFormat("EEEE MMM. d", Locale.ENGLISH).parse(date);
        return new SimpleDateFormat("EEEE d, MMMM y").format(newDate);
    }
    public String weekEndToDatabase(String date) throws ParseException {
        Date newDate = new SimpleDateFormat("EEEE MMM. d, yyyy", Locale.ENGLISH).parse(date);
        return new SimpleDateFormat("EEEE d, MMMM y").format(newDate);
    }
    
    
    public String[] nextMonth(String month, String year) throws ParseException {
        Date date = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH).parse(month + year);
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        String newMonth = Month.of(cal.get(Calendar.MONTH) + 1).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String newYear = String.valueOf(cal.get(Calendar.YEAR));
        String[] result = {newMonth, newYear};
        
        return result;
    }
    
    public String[] prevMonth(String month, String year) throws ParseException {
        Date date = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH).parse(month + year);
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        String newMonth = Month.of(cal.get(Calendar.MONTH) + 1).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String newYear = String.valueOf(cal.get(Calendar.YEAR));
        String[] result = {newMonth, newYear};
        
        return result;
    }
}
