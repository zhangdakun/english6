// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.android.adlib.ADbaseActivity;

// Referenced classes of package com.simple.english:
//            Utils, DailyOralDetailActivity

public class DailyOralActivity extends ADbaseActivity
    implements android.widget.AdapterView.OnItemClickListener
{

    public DailyOralActivity()
    {
    }

    private ArrayList genData()
    {
        ArrayList arraylist = new ArrayList();
        String as[] = getResources().getStringArray(0x7f080000);
        int i = 0;
        int j = as.length;
        do
        {
            if(i >= j)
                return arraylist;
            HashMap hashmap = new HashMap();
            hashmap.put("t", as[i]);
            arraylist.add(hashmap);
            i++;
        } while(true);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030002);
        if(Utils.dbUtil == null)
            Utils.onResume(this);
        oralListView = (ListView)findViewById(0x7f0a000a);
        ListView listview = oralListView;
        ArrayList arraylist = genData();
        String as[] = new String[1];
        as[0] = "t";
        int ai[] = new int[1];
        ai[0] = 0x7f0a0039;
        listview.setAdapter(new SimpleAdapter(this, arraylist, 0x7f03000e, as, ai));
        oralListView.setOnItemClickListener(this);
        returnView = (ImageView)findViewById(0x7f0a0008);
        returnView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                finish();
            }

//            final DailyOralActivity this$0;
//
//            
//            {
//                this$0 = DailyOralActivity.this;
//                super();
//            }
        }
);
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        HashMap hashmap = (HashMap)((ListView)adapterview).getItemAtPosition(i);
        Intent intent = new Intent();
        intent.putExtra("index", i + 1);
        intent.putExtra("name", (String)hashmap.get("t"));
        intent.setClass(this, DailyOralDetailActivity.class);
        startActivity(intent);
    }

    private ListView oralListView;
    private ImageView returnView;
}
