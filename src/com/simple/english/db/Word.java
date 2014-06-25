// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english.db;

import java.util.Date;

public class Word
{

    public Word()
    {
    }

    public int getCount()
    {
        return count;
    }

    public String getEx()
    {
        return ex;
    }

    public Date getLearnDate()
    {
        return learnDate;
    }

    public String getMeaning()
    {
        return meaning;
    }

    public String getName()
    {
        return name;
    }

    public String getNote()
    {
        return note;
    }

    public String getSound()
    {
        return sound;
    }

    public int getStar()
    {
        return star;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public int getType()
    {
        return type;
    }

    public int getWordId()
    {
        return wordId;
    }

    public void setCount(int i)
    {
        count = i;
    }

    public void setEx(String s)
    {
        ex = s;
    }

    public void setLearnDate(Date date)
    {
        learnDate = date;
    }

    public void setMeaning(String s)
    {
        meaning = s;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setNote(String s)
    {
        note = s;
    }

    public void setSound(String s)
    {
        sound = s;
    }

    public void setStar(int i)
    {
        star = i;
    }

    public void setSymbol(String s)
    {
        symbol = s;
    }

    public void setType(int i)
    {
        type = i;
    }

    public void setWordId(int i)
    {
        wordId = i;
    }

    private int count;
    private String ex;
    private Date learnDate;
    private String meaning;
    private String name;
    private String note;
    private String sound;
    private int star;
    private String symbol;
    private int type;
    private int wordId;
}
