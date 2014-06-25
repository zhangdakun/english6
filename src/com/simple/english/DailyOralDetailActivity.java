// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package com.simple.english:
//            Utils

public class DailyOralDetailActivity extends Activity
{

    public DailyOralDetailActivity()
    {
    }

    private ArrayList genData(int i)
        throws IOException
    {
        ArrayList arraylist = new ArrayList();
        HashMap hashmap = null;
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(getAssets().open((new StringBuilder("oral/")).append(i).append(".txt").toString())));
        int j = 0;
        do
        {
            String s = bufferedreader.readLine();
            if(s == null || s.trim().equals(""))
                return arraylist;
            if(j % 2 == 0)
            {
                hashmap = new HashMap();
                hashmap.put("en", s);
            } else
            {
                hashmap.put("zh", s);
                arraylist.add(hashmap);
            }
            j++;
        } while(true);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030003);
        if(Utils.dbUtil == null)
            Utils.onResume(this);
        String s = getIntent().getStringExtra("name");
        ((TextView)findViewById(0x7f0a000e)).setText(s);
        int i = getIntent().getIntExtra("index", 1);
        ListView listview = (ListView)findViewById(0x7f0a0010);
        try
        {
            ArrayList arraylist = genData(i);
            String as[] = new String[2];
            as[0] = "en";
            as[1] = "zh";
            int ai[] = new int[2];
            ai[0] = 0x7f0a0011;
            ai[1] = 0x7f0a0012;
            listview.setAdapter(new SimpleAdapter(this, arraylist, 0x7f030004, as, ai));
        }
        catch(IOException ioexception) { }
        returnView = (ImageView)findViewById(0x7f0a000d);
        returnView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                finish();
            }

//            final DailyOralDetailActivity this$0;
//
//            
//            {
//                this$0 = DailyOralDetailActivity.this;
//                super();
//            }
        }
);
    }

    private ImageView returnView;
}
