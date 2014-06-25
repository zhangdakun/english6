// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.*;
import java.util.*;

import com.android.adlib.ADbaseActivity;

// Referenced classes of package com.simple.english:
//            Utils, Tuple

public class FamousActivity extends ADbaseActivity
    implements android.widget.AdapterView.OnItemClickListener
{

    public FamousActivity()
    {
    }

    private ArrayList genData()
    {
        list = new ArrayList();
        List list1 = Utils.famousList.subList(0, 100);
        int i = 0;
        int j = list1.size();
        do
        {
            if(i >= j)
                return list;
            HashMap hashmap = new HashMap();
            Tuple tuple = (Tuple)list1.get(i);
            hashmap.put("en", tuple.e1);
            hashmap.put("zh", tuple.e2);
            list.add(hashmap);
            i++;
        } while(true);
    }

    private void mFinish()
    {
        Utils.famousIndex = famousListView.getFirstVisiblePosition();
        Utils.updateLearnPrefer(this);
        finish();
    }

    private void share(String s, String s1)
    {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", 0x7f070000);
        intent.putExtra("android.intent.extra.TEXT", (new StringBuilder("\u6613\u5355\u8BCD\u7CFB\u5217\u5206\u4EAB\uFF1A")).append(s).append(";").append(s1).toString());
        startActivity(Intent.createChooser(intent, "\u6613\u5355\u8BCD\u7ECF\u5178\u540D\u53E5\u5206\u4EAB"));
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if(Utils.famousList == null)
            Utils.onResume(this);
        setContentView(0x7f030005);
        famousListView = (ListView)findViewById(0x7f0a0016);
        ArrayList arraylist = genData();
        String as[] = new String[2];
        as[0] = "en";
        as[1] = "zh";
        int ai[] = new int[2];
        ai[0] = 0x7f0a0018;
        ai[1] = 0x7f0a0019;
        SimpleAdapter simpleadapter = new SimpleAdapter(this, arraylist, 0x7f030006, as, ai);
        famousListView.setAdapter(simpleadapter);
        famousListView.setSelection(Utils.famousIndex);
        famousListView.setOnItemClickListener(this);
        returnView = (ImageView)findViewById(0x7f0a0014);
        returnView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                mFinish();
            }

//            final FamousActivity this$0;
//
//            
//            {
//                this$0 = FamousActivity.this;
//                super();
//            }
        }
);
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        HashMap hashmap = (HashMap)list.get(i);
        share((String)hashmap.get("en"), (String)hashmap.get("zh"));
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag;
        if(i == 4)
        {
            mFinish();
            flag = false;
        } else
        {
            flag = super.onKeyDown(i, keyevent);
        }
        return flag;
    }

    private ListView famousListView;
    private ArrayList list;
    private ImageView returnView;

}
