// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import android.app.*;
import android.content.Intent;
import android.os.IBinder;
import com.simple.english.db.DateUtil;
import com.simple.english.db.MDbUtil;
import java.util.*;

// Referenced classes of package com.simple.english:
//            Utils, WelcomeActivity

public class ReviewService extends Service
{

    public ReviewService()
    {
    }

    private boolean hasReview()
    {
        boolean flag;
        Date date;
        MDbUtil mdbutil;
        Date date1;
        Date date2;
        flag = true;
        date = new Date();
        mdbutil = Utils.dbUtil;
        date1 = new Date(mdbutil.getMinDate());
        date1.setHours(0);
        date2 = DateUtil.privDay(date);
//        if(!date2.after(date1)) goto _L2; else goto _L1
//_L1:
//        if(!isNotNull(mdbutil.getWordByDay(date2))) goto _L4; else goto _L3
//_L3:
//        return flag;
//_L4:
//        Date date3 = DateUtil.privWeekDay(date);
//        if(date3.after(date1))
//        {
//            if(isNotNull(mdbutil.getWordByDay(date3)))
//                continue; /* Loop/switch isn't completed */
//            Date date4 = DateUtil.privWeekDay(date3);
//            if(date4.after(date1))
//            {
//                if(isNotNull(mdbutil.getWordByDay(date4)))
//                    continue; /* Loop/switch isn't completed */
//                Date date5 = DateUtil.privWeekDay(DateUtil.privWeekDay(date4));
//                if(date5.after(date1) && isNotNull(mdbutil.getWordByDay(date5)))
//                    continue; /* Loop/switch isn't completed */
//            }
//        }
//_L2:
//        flag = false;
//        if(true) goto _L3; else goto _L5
//_L5:
        
        if(!date2.after(date1)) {
        	flag = false;
        } else {
        	 if(!isNotNull(mdbutil.getWordByDay(date2))) {
        		 
        	 } else {
               Date date3 = DateUtil.privWeekDay(date);
               if(date3.after(date1))
               {
                   if(isNotNull(mdbutil.getWordByDay(date3)))  {
//                       continue; /* Loop/switch isn't completed */
	                   Date date4 = DateUtil.privWeekDay(date3);
	                   if(date4.after(date1))
	                   {
	                       if(isNotNull(mdbutil.getWordByDay(date4))) {
//	                           continue; /* Loop/switch isn't completed */
	                       Date date5 = DateUtil.privWeekDay(DateUtil.privWeekDay(date4));
	                       if(date5.after(date1) && isNotNull(mdbutil.getWordByDay(date5))) {
//	                           continue; /* Loop/switch isn't completed */
	                    	   
	                    	   flag = false;// i guess
	                       }
	                       }
	                   }
                   }
               } 
        	 }
        }
        
       return flag;
    }

    private boolean isNotNull(List list)
    {
        boolean flag;
        if(list != null && list.size() > 0)
            flag = true;
        else
            flag = false;
        return flag;
    }

    private void showNotification()
    {
        Notification notification = new Notification(0x7f020022, "", System.currentTimeMillis());
        notification.flags = 16;
        notification.defaults = 1;
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.setFlags(0x14000000);
        PendingIntent pendingintent = PendingIntent.getActivity(this, 0, intent, 0);
        notification.setLatestEventInfo(this, getString(0x7f070000), "\u4ECA\u5929\u6709\u5355\u8BCD\u9700\u8981\u590D\u4E60\u54E6", pendingintent);
        mNotificationManager.notify(0x7f070000, notification);
    }

    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void onCreate()
    {
        super.onCreate();
        mNotificationManager = (NotificationManager)getSystemService("notification");
        if(timer != null)
        {
            timer.cancel();
            timer = null;
        }
        if(Utils.dbUtil == null)
        {
            Utils.screenHeight = 480;
            Utils.onResume(this);
        }
        String as[] = Utils.remindTime.split(":");
        int i = Integer.parseInt(as[0]);
        int j = Integer.parseInt(as[1]);
        Date date = new Date();
        if(date.getHours() > i || date.getHours() == i && date.getMinutes() >= j)
            date = DateUtil.nextDay(date);
        date.setHours(i);
        date.setMinutes(j);
        timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run()
            {
                if(hasReview())
                    showNotification();
            }

//            final ReviewService this$0;
//
//            
//            {
//                this$0 = ReviewService.this;
//                super();
//            }
        }
, date, 0x5265c00L);
    }

    public void onDestroy()
    {
        super.onDestroy();
        mNotificationManager.cancel(0x7f070000);
        if(timer != null)
        {
            timer.cancel();
            timer = null;
        }
    }

    private NotificationManager mNotificationManager;
    private Timer timer;


}
