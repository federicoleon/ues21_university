package com.ues21.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.GregorianCalendar

abstract class DateUtils {

    public static Map getCurrentDateParams(Date date) {
        if(!date) {
            return null
        }
        Calendar calendar = new GregorianCalendar()
        calendar.setTime(date)
        return [
            day: calendar.get(Calendar.DAY_OF_MONTH),
            month: calendar.get(Calendar.MONTH),
            year: calendar.get(Calendar.YEAR)
        ]
    }

    public static String getCurrentDateMV() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        return dateFormat.format(Calendar.getInstance().getTime()).toString()
    }

    public static String getDateMV(Date date) {
        if(!date) {
            return getCurrentDateMV()
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        return dateFormat.format(date).toString()
    }

    public static String getOnlyDateMV(Date date) {
        if(!date) {
            return getCurrentDateMV()
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy")
        return dateFormat.format(date).toString()
    }

    public static Date getDateFromString(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date)
        } catch(Exception e) {
            return new Date()
        }
    }

    public static Date addMinutesToCurrent(int minutes) {
        Calendar calendar = new GregorianCalendar()
        calendar.setTime(new Date())
        calendar.add(Calendar.MINUTE, minutes)
        return calendar.getTime()
    }
}