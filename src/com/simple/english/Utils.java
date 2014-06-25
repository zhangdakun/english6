// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import com.simple.english.db.MDbUtil;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

// Referenced classes of package com.simple.english:
//            Tuple

public class Utils
{

    public Utils()
    {
    }

    public static int adjustDensity(Context context, float f)
    {
        return (int)(0.5F + f * context.getResources().getDisplayMetrics().density);
    }

    public static boolean canAccessNetwork(Context context)
    {
        boolean flag;
        ConnectivityManager connectivitymanager;
        flag = false;
        connectivitymanager = (ConnectivityManager)context.getApplicationContext().getSystemService("connectivity");
//        if(connectivitymanager != null) goto _L2; else goto _L1
//_L1:
//        return flag;
//_L2:
//        NetworkInfo networkinfo = connectivitymanager.getActiveNetworkInfo();
//        if(networkinfo != null && networkinfo.isAvailable())
//            flag = true;
//        if(true) goto _L1; else goto _L3
//_L3:f
        if(connectivitymanager == null) {
        	return flag;
        }
        
      NetworkInfo networkinfo = connectivitymanager.getActiveNetworkInfo();
      if(networkinfo != null && networkinfo.isAvailable())
          flag = true;
      
      return flag;
        
    }

    public static void closeIme(Activity activity)
    {
        InputMethodManager inputmethodmanager = (InputMethodManager)activity.getSystemService("input_method");
        if(inputmethodmanager.isActive())
            inputmethodmanager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
    }

    public static int getStatusbarHeight(Context context)
    {
        int i = 0;
        int k;
        try {
			

        Class class1 = Class.forName("com.android.internal.R$dimen");
        Object obj = class1.newInstance();
        int j = Integer.parseInt(class1.getField("status_bar_height").get(obj).toString());
        k = context.getResources().getDimensionPixelSize(j);
        i = k;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//_L2:
//        return i;
//        Exception exception;
//        exception;
//        exception.printStackTrace();
//        if(true) goto _L2; else goto _L1
//_L1:
        
        return i;
    }

    public static void initSize(Context context)
    {
        Display display = ((Activity)context).getWindow().getWindowManager().getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight() - getStatusbarHeight(context);
        density = context.getResources().getDisplayMetrics().density;
    }

    public static boolean isStrNull(String s)
    {
        boolean flag;
        if(s != null && !s.trim().equals(""))
            flag = false;
        else
            flag = true;
        return flag;
    }

    private static String listToString(List list, String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int i = 0;
        do
        {
            if(i >= list.size())
            {
                String s1;
                if(stringbuilder.length() > 2)
                    s1 = stringbuilder.toString().substring(0, -1 + stringbuilder.toString().length());
                else
                    s1 = stringbuilder.toString();
                return s1;
            }
            stringbuilder.append((String)list.get(i)).append(s);
            i++;
        } while(true);
    }

    public static void loadFamous(Context context)
    {
        BufferedReader bufferedreader;
        String s;
        boolean flag;
        ArrayList arraylist;
        bufferedreader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(0x7f050001)));
        s = null;
        flag = false;
        arraylist = new ArrayList(250);
//_L3:
//        String s1 = bufferedreader.readLine();
//        if(s1 != null) goto _L2; else goto _L1
//_L1:
//        famousList = arraylist;
//        return;
//_L2:
//        if(!flag)
//            break MISSING_BLOCK_LABEL_87;
//        arraylist.add(new Tuple(s, s1));
//_L4:
//        if(flag)
//            flag = false;
//        else
//            flag = true;
//          goto _L3
//        s = s1;
//          goto _L4
//        IOException ioexception;
//        ioexception;
//        ioexception.printStackTrace();
//          goto _L1
        
        String s1 = null;
        
        try {
			while((s1 = bufferedreader.readLine()) != null) {
				if(flag) {
					 arraylist.add(new Tuple(s, s1));
				}
		        if(flag)
		        	flag = false;
		        else
		        	flag = true;
		        s = s1;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        famousList = arraylist;
    }

    public static void loadPref(Context context)
    {
        int i = 100;
        SharedPreferences sharedpreferences = context.getSharedPreferences("SimpleCet6", 1);
        version = sharedpreferences.getInt("version", 0);
        famous = sharedpreferences.getInt("famous", i);
        famousIndex = sharedpreferences.getInt("famousIndex", 0);
        String s;
        if(famous >= i && famous <= 189)
            i = famous;
        famous = i;
        reviewRemind = sharedpreferences.getBoolean("reviewRemind", true);
        remindTime = sharedpreferences.getString("remindTime", "19:30");
        autoSound = sharedpreferences.getBoolean("autoSound", false);
        autoEnterLearnUnit = sharedpreferences.getBoolean("autoEnterLearnUnit", true);
        isLazyShow = sharedpreferences.getBoolean("isLazyShow", false);
        lazyTime = sharedpreferences.getInt("lazyTime", 500);
        wordHighLight = sharedpreferences.getBoolean("wordHighLight", true);
        translateEngine = sharedpreferences.getInt("translateEngine", 1);
        isLock = sharedpreferences.getBoolean("isLock", true);
        lastLearnWord = sharedpreferences.getInt("lastLearnWord", 0);
        lastLearnUnit = sharedpreferences.getInt("lastLearnUnit", 0);
        isFirst = sharedpreferences.getBoolean("isFirst", true);
        maxUnit = sharedpreferences.getInt("maxUnit", 0);
        s = sharedpreferences.getString("learnedUnits", null);
        learnedUnits = new ArrayList();
        if(s != null)
            learnedUnits.addAll(Arrays.asList(s.split(",")));
    }

    public static void onPause(Context context)
    {
        updateLearnPrefer(context);
        updateSettingPrefer(context);
    }

    public static void onResume(Context context)
    {
        loadPref(context);
        dbUtil = new MDbUtil();
        loadFamous(context);
        if(screenHeight == 0)
            initSize(context);
    }

    public static void updateFirst(Context context)
    {
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences("SimpleCet6", 1).edit();
        editor.putBoolean("isFirst", false);
        editor.commit();
    }

    public static void updateLearnPrefer(Context context)
    {
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences("SimpleCet6", 1).edit();
        editor.putInt("lastLearnWord", lastLearnWord);
        editor.putInt("lastLearnUnit", lastLearnUnit);
        editor.putInt("maxUnit", maxUnit);
        editor.putString("learnedUnits", listToString(learnedUnits, ","));
        editor.putBoolean("isLock", isLock);
        editor.putInt("famousIndex", famousIndex);
        editor.commit();
    }

    public static void updateSettingPrefer(Context context)
    {
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences("SimpleCet6", 1).edit();
        editor.putBoolean("reviewRemind", reviewRemind);
        editor.putString("remindTime", remindTime);
        editor.putBoolean("autoSound", autoSound);
        editor.putBoolean("autoEnterLearnUnit", autoEnterLearnUnit);
        editor.putBoolean("isLazyShow", isLazyShow);
        editor.putInt("lazyTime", lazyTime);
        editor.putBoolean("wordHighLight", wordHighLight);
        editor.putInt("translateEngine", translateEngine);
        editor.commit();
    }

    public static final int ADD_COUNT = 10;
    public static final int MAX_COUNT = 50;
    public static final int MIN_COUNT = 10;
    public static final int NAME_EN = 1;
    public static final int NAME_ZH = 2;
    public static final int NEW_WORD_ADD_RESULT = 10;
    public static final String PREF = "SimpleCet6";
    public static final int UNIT_PER_PAGE = 16;
    public static final int WORD_HIGH_FREQ = 2;
    public static final int WORD_NEW = 3;
    public static final int WORD_NORMAL = 1;
    public static final int WORD_PER_UNIT = 41;
    public static boolean autoEnterLearnUnit;
    public static boolean autoSound;
    public static MDbUtil dbUtil;
    public static float density;
    public static int famous;
    public static int famousIndex;
    public static List famousList;
    public static boolean isFirst;
    public static boolean isLazyShow;
    public static boolean isLock;
    public static int lastLearnUnit = 0;
    public static int lastLearnWord = 0;
    public static int lazyTime;
    public static ArrayList learnedUnits;
    public static int maxUnit = 0;
    public static String remindTime;
    public static boolean reviewRemind;
    public static int screenHeight;
    public static int screenWidth;
    public static int totalUnits;
    public static int totalWords;
    public static int translateEngine;
    public static int version;
    public static boolean wordHighLight;

}
