// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english.db;

import java.util.*;

public class DateUtil
{

    public DateUtil()
    {
    }

    public static long formatDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static Date getMondayOFWeek(Date date)
    {
        int i = getMondayPlus(date);
        GregorianCalendar gregoriancalendar = new GregorianCalendar();
        gregoriancalendar.setTime(date);
        gregoriancalendar.add(5, i);
        return gregoriancalendar.getTime();
    }

    public static int getMondayPlus(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = -1 + calendar.get(7);
        int j;
        if(i == 1)
            j = 0;
        else
            j = 1 - i;
        return j;
    }

    public static Date getSundayOFWeek(Date date)
    {
        int i = getMondayPlus(date);
        GregorianCalendar gregoriancalendar = new GregorianCalendar();
        gregoriancalendar.setTime(date);
        gregoriancalendar.add(5, i + 6);
        return gregoriancalendar.getTime();
    }

    public static Date nextDay(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, 1);
        return calendar.getTime();
    }

    public static Date nextWeekDay(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, 7);
        return calendar.getTime();
    }

    public static Date privDay(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static Date privWeekDay(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, -7);
        return calendar.getTime();
    }
}
