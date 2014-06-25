// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import java.io.File;
import java.util.*;

// Referenced classes of package com.simple.english.db:
//            Word, DateUtil

public class MDbUtil
{

    public MDbUtil()
    {
        dbFile = (new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()))).append("/yidanci/cet6.db3").toString();
        db = SQLiteDatabase.openDatabase(dbFile, null, 0);
    }

    private Word cursorToWord(Cursor cursor)
    {
        Word word = new Word();
        word.setWordId(cursor.getInt(cursor.getColumnIndex("word_id")));
        word.setName(cursor.getString(cursor.getColumnIndex("name")));
        word.setSymbol(cursor.getString(cursor.getColumnIndex("symbol")));
        word.setMeaning(cursor.getString(cursor.getColumnIndex("meaning")));
        word.setEx(cursor.getString(cursor.getColumnIndex("ex")));
        long l = cursor.getLong(cursor.getColumnIndex("learn_date"));
        if(l > 0L)
            word.setLearnDate(new Date(l));
        word.setCount(cursor.getInt(cursor.getColumnIndex("count")));
        word.setType(cursor.getInt(cursor.getColumnIndex("type")));
        word.setNote(cursor.getString(cursor.getColumnIndex("note")));
        word.setStar(cursor.getInt(cursor.getColumnIndex("star")));
        word.setSound(cursor.getString(cursor.getColumnIndex("sound")));
        return word;
    }

    public void close()
    {
        db.close();
    }

    public void delete(Object obj)
    {
        db.beginTransaction();
        SQLiteDatabase sqlitedatabase = db;
        Object aobj[] = new Object[1];
        aobj[0] = obj;
        sqlitedatabase.execSQL("delete from word where word_id = ?", aobj);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List getAll()
    {
        ArrayList arraylist = new ArrayList();
        Cursor cursor = db.rawQuery("select * from word where type != 3", null);
        do
        {
            if(!cursor.moveToNext())
            {
                cursor.close();
                return arraylist;
            }
            arraylist.add(cursorToWord(cursor));
        } while(true);
    }

    public List getAllHighFreq()
    {
        ArrayList arraylist = new ArrayList();
        Cursor cursor = db.rawQuery("select * from word where type = 2", null);
        do
        {
            if(!cursor.moveToNext())
            {
                cursor.close();
                return arraylist;
            }
            arraylist.add(cursorToWord(cursor));
        } while(true);
    }

    public List getAllNewWords()
    {
        ArrayList arraylist = new ArrayList();
        Cursor cursor = db.rawQuery("select * from word where type = 3", null);
        do
        {
            if(!cursor.moveToNext())
            {
                cursor.close();
                return arraylist;
            }
            arraylist.add(cursorToWord(cursor));
        } while(true);
    }

    public int getMaxWordId()
    {
        Cursor cursor = db.rawQuery("select max(word_id) from word", null);
        cursor.moveToFirst();
        int i = cursor.getInt(0);
        cursor.close();
        return i;
    }

    public long getMinDate()
    {
        Cursor cursor = db.rawQuery("select min(learn_date) from word where type != 3 and learn_date > 0", null);
        cursor.moveToFirst();
        long l = cursor.getLong(0);
        cursor.close();
        return l;
    }

    public int getTotalCount()
    {
        Cursor cursor = db.rawQuery("select count(*) from word where type != 3", null);
        cursor.moveToFirst();
        int i = cursor.getInt(0);
        cursor.close();
        return i;
    }

    public List getWordByDay(Date date)
    {
        ArrayList arraylist = new ArrayList();
        SQLiteDatabase sqlitedatabase = db;
        String as[] = new String[2];
        as[0] = "3";
        as[1] = String.valueOf(DateUtil.formatDate(date));
        Cursor cursor = sqlitedatabase.rawQuery("select * from word where type != ? and learn_date = ?", as);
        do
        {
            if(!cursor.moveToNext())
            {
                cursor.close();
                return arraylist;
            }
            arraylist.add(cursorToWord(cursor));
        } while(true);
    }

    public Word getWordById(int i)
    {
        Cursor cursor;
        SQLiteDatabase sqlitedatabase = db;
        String as[] = new String[1];
        as[0] = String.valueOf(i);
        cursor = sqlitedatabase.rawQuery("select * from word where word_id = ?", as);
//        if(!cursor.moveToNext()) goto _L2; else goto _L1
//_L1:
//        Word word1 = cursorToWord(cursor);
//        Word word;
//        word = word1;
//        cursor.close();
//_L4:
//        return word;
//        Exception exception;
//        exception;
//        cursor.close();
//        throw exception;
//_L2:
//        cursor.close();
//        word = null;
//        if(true) goto _L4; else goto _L3
//_L3:
        
        Word word;
        if(!cursor.moveToNext()) {
          cursor.close();
          word = null;
        }else {
          Word word1 = cursorToWord(cursor);
         
          word = word1;
          cursor.close();
        }
        return word;
    }

    public List getWordByNameLike(String s)
    {
        ArrayList arraylist = new ArrayList();
        SQLiteDatabase sqlitedatabase = db;
        String as[] = new String[2];
        as[0] = "3";
        as[1] = (new StringBuilder(String.valueOf(s))).append("%").toString();
        Cursor cursor = sqlitedatabase.rawQuery("select * from word where type != ? and name like ?", as);
        do
        {
            if(!cursor.moveToNext())
            {
                cursor.close();
                return arraylist;
            }
            arraylist.add(cursorToWord(cursor));
        } while(true);
    }

    public Word getWordByNameOnly(String s)
    {
        Cursor cursor;
        SQLiteDatabase sqlitedatabase = db;
        String as[] = new String[2];
        as[0] = s;
        as[1] = "3";
        cursor = sqlitedatabase.rawQuery("select * from word where name = ? and type != ?", as);
//        if(!cursor.moveToNext()) goto _L2; else goto _L1
//_L1:
//        Word word1 = cursorToWord(cursor);
//        Word word;
//        word = word1;
//        cursor.close();
//_L4:
//        return word;
//        Exception exception;
//        exception;
//        cursor.close();
//        throw exception;
//_L2:
//        cursor.close();
//        word = null;
//        if(true) goto _L4; else goto _L3
//_L3:
        Word word;
        if(cursor.moveToNext()) {
          Word word1 = cursorToWord(cursor);
         
          word = word1;
          cursor.close();
        } else {
          cursor.close();
          word = null;
        }
        
        return word;
    }

    public List getWordInPage(int i, int j)
    {
        ArrayList arraylist = new ArrayList();
        SQLiteDatabase sqlitedatabase = db;
        String as[] = new String[3];
        as[0] = String.valueOf(3);
        as[1] = String.valueOf(i);
        as[2] = String.valueOf(j);
        Cursor cursor = sqlitedatabase.rawQuery("select * from word where type != ? limit ?, ?", as);
        do
        {
            if(!cursor.moveToNext())
            {
                cursor.close();
                return arraylist;
            }
            arraylist.add(cursorToWord(cursor));
        } while(true);
    }

    public long save(Word word)
    {
        long l = 1 + getMaxWordId();
        db.beginTransaction();
        SQLiteDatabase sqlitedatabase = db;
        Object aobj[] = new Object[10];
        aobj[0] = word.getName();
        aobj[1] = word.getSymbol();
        aobj[2] = word.getMeaning();
        aobj[3] = word.getEx();
        aobj[4] = Integer.valueOf(-1);
        aobj[5] = Integer.valueOf(word.getCount());
        aobj[6] = Integer.valueOf(word.getType());
        aobj[7] = word.getNote();
        aobj[8] = Integer.valueOf(word.getStar());
        aobj[9] = Long.valueOf(l);
        sqlitedatabase.execSQL("insert into word (name, symbol, meaning, ex, learn_date, count, type, note, star, word_id) values (?,?,?,?,?,?,?,?,?,?)", aobj);
        db.setTransactionSuccessful();
        db.endTransaction();
        return l;
    }

    public void update(Word word)
    {
        db.beginTransaction();
        SQLiteDatabase sqlitedatabase = db;
        Object aobj[] = new Object[9];
        aobj[0] = word.getName();
        aobj[1] = word.getSymbol();
        aobj[2] = word.getMeaning();
        aobj[3] = word.getEx();
        aobj[4] = Long.valueOf(DateUtil.formatDate(word.getLearnDate()));
        aobj[5] = Integer.valueOf(word.getCount());
        aobj[6] = word.getNote();
        aobj[7] = Integer.valueOf(word.getStar());
        aobj[8] = Integer.valueOf(word.getWordId());
        sqlitedatabase.execSQL("update word set name = ?, symbol = ?, meaning = ?, ex = ?, learn_date = ?, count = ?, note = ?, star = ? where word_id = ?", aobj);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void updateDiff(int i, int j)
    {
        db.beginTransaction();
        SQLiteDatabase sqlitedatabase = db;
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(j);
        aobj[1] = Integer.valueOf(i);
        sqlitedatabase.execSQL("update word set star = ? where word_id = ?", aobj);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void updateNote(int i, String s)
    {
        db.beginTransaction();
        SQLiteDatabase sqlitedatabase = db;
        Object aobj[] = new Object[2];
        aobj[0] = s;
        aobj[1] = Integer.valueOf(i);
        sqlitedatabase.execSQL("update word set note = ? where word_id = ?", aobj);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    private SQLiteDatabase db;
    private String dbFile;
}
