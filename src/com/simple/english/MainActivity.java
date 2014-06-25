// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;

import com.android.adlib.ADbaseActivity;
import com.myanns.simple.english.R;
import com.umeng.analytics.MobclickAgent;
//import com.zl.jf.last1.PManager;
import java.util.*;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;

// Referenced classes of package com.simple.english:
//            LearnActivity, ReviewActivity, SfBeginActivity, SearchActivity, 
//            TranslateActivity, FamousActivity, NewWordsActivity, DailyOralActivity, 
//            SettingActivity, Utils, Tuple

public class MainActivity extends ADbaseActivity
    implements Runnable, android.widget.AdapterView.OnItemClickListener
{

    public MainActivity()
    {
        isExit = false;
    }

    private void mStartActivity(Class class1)
    {
        Intent intent = new Intent();
        intent.setClass(this, class1);
        startActivity(intent);
    }

    public ArrayList genGridData()
    {
        Object aobj[][] = new Object[9][];
        Object aobj1[] = new Object[3];
        aobj1[0] = Integer.valueOf(0x7f02000c);
        aobj1[1] = "\u5F00\u59CB\u5B66\u4E60";
        aobj1[2] =  LearnActivity.class;
        aobj[0] = aobj1;
        Object aobj2[] = new Object[3];
        aobj2[0] = Integer.valueOf(0x7f02000e);
        aobj2[1] = "\u5355\u8BCD\u590D\u4E60";
        aobj2[2] =  ReviewActivity.class;
        aobj[1] = aobj2;
        Object aobj3[] = new Object[3];
        aobj3[0] = Integer.valueOf(0x7f02000b);
        aobj3[1] = "\u81EA\u6211\u6D4B\u8BD5";
        aobj3[2] = SfBeginActivity.class;
        aobj[2] = aobj3;
        Object aobj4[] = new Object[3];
        aobj4[0] = Integer.valueOf(0x7f02000d);
        aobj4[1] = "\u8BCD\u6C47\u68C0\u7D22";
        aobj4[2] = SearchActivity.class;
        aobj[3] = aobj4;
        Object aobj5[] = new Object[3];
        aobj5[0] = Integer.valueOf(0x7f020010);
        aobj5[1] = "\u5728\u7EBF\u7FFB\u8BD1";
        aobj5[2] = TranslateActivity.class;
        aobj[4] = aobj5;
        Object aobj6[] = new Object[3];
        aobj6[0] = Integer.valueOf(0x7f020009);
        aobj6[1] = "\u7ECF\u5178\u540D\u53E5";
        aobj6[2] = FamousActivity.class;
        aobj[5] = aobj6;
        Object aobj7[] = new Object[3];
        aobj7[0] = Integer.valueOf(0x7f02000a);
        aobj7[1] = "\u81EA\u5B9A\u751F\u8BCD";
        aobj7[2] = NewWordsActivity.class;
        aobj[6] = aobj7;
        Object aobj8[] = new Object[3];
        aobj8[0] = Integer.valueOf(0x7f020008);
        aobj8[1] = "\u65E5\u5E38\u53E3\u8BED";
        aobj8[2] = DailyOralActivity.class;
        aobj[7] = aobj8;
        Object aobj9[] = new Object[3];
        aobj9[0] = Integer.valueOf(0x7f02000f);
        aobj9[1] = "\u7CFB\u7EDF\u8BBE\u7F6E";
        aobj9[2] = SettingActivity.class;
        aobj[8] = aobj9;
        ArrayList arraylist = new ArrayList(9);
        int i = 0;
        do
        {
            if(i >= 9)
                return arraylist;
            HashMap hashmap = new HashMap();
            hashmap.put("img", aobj[i][0]);
            hashmap.put("title", aobj[i][1]);
            hashmap.put("cls", aobj[i][2]);
            arraylist.add(hashmap);
            i++;
        } while(true);
    }

    public void onAttachedToWindow()
    {
        super.onAttachedToWindow();
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if(Utils.dbUtil == null)
            Utils.onResume(this);
        Utils.initSize(this);
        setContentView(0x7f030009);
        
      LinearLayout adLayout = (LinearLayout) findViewById(R.id.adLayout);
      AdView adView = new AdView(this, AdSize.FIT_SCREEN);
      adLayout.addView(adView);
        mainEnView = (TextView)findViewById(0x7f0a0025);
        mainEnView.setFocusable(true);
        mainEnView.setFocusableInTouchMode(true);
        mainZhView = (TextView)findViewById(0x7f0a0026);
        mainGridView = (GridView)findViewById(0x7f0a0023);
        mainGridView.setOnItemClickListener(this);
        Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(0x7f020012)).getBitmap();
        int i = (Utils.screenWidth - 3 * bitmap.getWidth()) / 4;
        mainGridView.setHorizontalSpacing(i);
        ((android.widget.RelativeLayout.LayoutParams)mainGridView.getLayoutParams()).setMargins(i, 0, i, 0);
        GridView gridview = mainGridView;
        ArrayList arraylist = genGridData();
        String as[] = new String[2];
        as[0] = "title";
        as[1] = "img";
        int ai[] = new int[2];
        ai[0] = 0x7f0a0006;
        ai[1] = 0x7f0a0005;
        gridview.setAdapter(new SimpleAdapter(this, arraylist, 0x7f030001, as, ai));
        (new Thread(this)).start();
//        if(Utils.isFirst)
//            Toast.makeText(this, "\u6613\u5355\u8BCD\u7CFB\u5217\u5305\u542B\u56DB\u7EA7\u7BC7\u3001\u516D\u7EA7\u7BC7\u3001\u9AD8\u8003\u7BC7\u3001\u8003\u7814\u7BC7\u7B49\uFF0C\u6B22\u8FCE\u4E0B\u8F7D", 1).show();
//        PManager pmanager = PManager.getInstance(this);
//        pmanager.setFK(this, "e6484e33ddfbf6e135469807c5662587");
//        pmanager.setFC(this, "L0617-2");
//        pmanager.getMessage(this, true);
        if(Utils.reviewRemind)
            startService(new Intent("com.cet6.ReviewService"));
        else
            stopService(new Intent("com.cet6.ReviewService"));
    }

    protected void onDestroy()
    {
        super.onDestroy();
        boolean _tmp = Utils.isFirst;
        Process.killProcess(Process.myPid());
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        mStartActivity((Class)((HashMap)adapterview.getItemAtPosition(i)).get("cls"));
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(i == 4)
            if(!isExit)
            {
                isExit = true;
                Toast.makeText(this, "\u518D\u6309\u4E00\u6B21\u8FD4\u56DE\u952E\u9000\u51FA", 0).show();
                tExit = new Timer();
                tExit.schedule(new TimerTask() {

                    public void run()
                    {
                        isExit = false;
                        tExit.cancel();
                    }

//                    final MainActivity this$0;
//
//            
//            {
//                this$0 = MainActivity.this;
//                super();
//            }
                }
, 3000L);
            } else
            {
                finish();
                Process.killProcess(Process.myPid());
                System.gc();
            }
        return false;
    }

    protected void onPause()
    {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    protected void onResume()
    {
        super.onResume();
        MobclickAgent.onResume(this);
        if(Utils.dbUtil == null)
            Utils.onResume(this);
    }

    public void run()
    {
        Tuple tuple = (Tuple)Utils.famousList.get(Utils.famous);
        StringBuffer stringbuffer = new StringBuffer((String)tuple.e1);
        StringBuffer stringbuffer1 = new StringBuffer((String)tuple.e2);
        mainEnView.setText(stringbuffer.toString());
        mainZhView.setText(stringbuffer1.toString());
        savePreferences();
    }

    protected void savePreferences()
    {
        android.content.SharedPreferences.Editor editor = getSharedPreferences("SimpleCet6", 1).edit();
        int i;
        if(Utils.famous >= 189)
            i = 100;
        else
            i = 1 + Utils.famous;
        editor.putInt("famous", i);
        editor.commit();
    }

    private boolean isExit;
    private TextView mainEnView;
    private GridView mainGridView;
    private TextView mainZhView;
    Timer tExit;

}
