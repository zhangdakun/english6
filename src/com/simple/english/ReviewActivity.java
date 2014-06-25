// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import android.app.Activity;
import android.content.Intent;
import android.os.*;
import android.view.View;
import android.widget.*;

import com.android.adlib.ADbaseActivity;
import com.simple.english.db.DateUtil;
import com.simple.english.db.MDbUtil;
import com.simple.english.db.Word;
import java.util.*;

// Referenced classes of package com.simple.english:
//            Utils, WordDetailActivity

public class ReviewActivity extends ADbaseActivity
    implements android.view.View.OnClickListener, android.widget.AdapterView.OnItemClickListener, Runnable
{

    public ReviewActivity()
    {
        handler = new Handler(new android.os.Handler.Callback() {

            public boolean handleMessage(Message message)
            {
                if(message.what == 1)
                {
                    ListView listview = reviewListView;
                    ReviewActivity reviewactivity = ReviewActivity.this;
                    ArrayList arraylist = dataList;
                    String as[] = new String[2];
                    as[0] = "name";
                    as[1] = "mean";
                    int ai[] = new int[2];
                    ai[0] = 0x7f0a0044;
                    ai[1] = 0x7f0a0045;
                    listview.setAdapter(new SimpleAdapter(reviewactivity, arraylist, 0x7f030012, as, ai));
                    reviewListView.setEmptyView(findViewById(0x7f0a003f));
                }
                return false;
            }

//            final ReviewActivity this$0;
//
//            
//            {
//                this$0 = ReviewActivity.this;
//                super();
//            }
        }
);
    }

    private void genAdapterData(ArrayList arraylist)
    {
        dataList = new ArrayList();
        int i = 0;
        int j = arraylist.size();
        do
        {
            if(i >= j)
                return;
            HashMap hashmap = new HashMap();
            Word word = (Word)arraylist.get(i);
            hashmap.put("id", Integer.valueOf(word.getWordId()));
            hashmap.put("name", word.getName());
            hashmap.put("mean", word.getMeaning().replaceAll("#", ";"));
            dataList.add(hashmap);
            i++;
        } while(true);
    }

    private ArrayList genWords()
    {
        ArrayList arraylist = new ArrayList();
        Date date = new Date();
        MDbUtil mdbutil = Utils.dbUtil;
        Date date1 = new Date(mdbutil.getMinDate());
        date1.setHours(0);
        Date date2 = DateUtil.privDay(date);
        if(date2.after(date1))
        {
            arraylist.addAll(mdbutil.getWordByDay(date2));
            Date date3 = DateUtil.privWeekDay(date);
            if(date3.after(date1))
            {
                arraylist.addAll(mdbutil.getWordByDay(date3));
                Date date4 = DateUtil.privWeekDay(date3);
                if(date4.after(date1))
                {
                    arraylist.addAll(mdbutil.getWordByDay(date4));
                    Date date5 = DateUtil.privWeekDay(DateUtil.privWeekDay(date4));
                    if(date5.after(date1))
                        arraylist.addAll(mdbutil.getWordByDay(date5));
                }
            }
        }
        return arraylist;
    }

    public void onClick(View view)
    {
        if(view == returnView)
            finish();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030010);
        reviewListView = (ListView)findViewById(0x7f0a003e);
        reviewListView.setOnItemClickListener(this);
        returnView = (ImageView)findViewById(0x7f0a003c);
        returnView.setOnClickListener(this);
        (new Thread(this)).start();
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        int j = ((Integer)((HashMap)dataList.get(i)).get("id")).intValue();
        Intent intent = new Intent();
        intent.putExtra("word_id", j);
        intent.setClass(this,  WordDetailActivity.class);
        startActivity(intent);
    }

    public void run()
    {
        genAdapterData(genWords());
        handler.sendEmptyMessage(1);
    }

    private static final int OVER = 1;
    private ArrayList dataList;
    private Handler handler;
    private ImageView returnView;
    private ListView reviewListView;


}
