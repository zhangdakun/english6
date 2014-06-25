// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.android.adlib.ADbaseActivity;
import com.simple.english.db.MDbUtil;
import com.simple.english.db.Word;
import java.util.*;

// Referenced classes of package com.simple.english:
//            NewWordAddActivity, Utils

public class NewWordsActivity extends ADbaseActivity
    implements android.view.View.OnClickListener, android.widget.AdapterView.OnItemLongClickListener
{

    public NewWordsActivity()
    {
    }

    private void addNewWord()
    {
        Intent intent = new Intent();
        intent.setClass(this,  NewWordAddActivity.class);
        startActivityForResult(intent, 100);
    }

    private void genAdapterData()
    {
        listItems = new ArrayList();
        List list = Utils.dbUtil.getAllNewWords();
        int i = 0;
        int j = list.size();
        do
        {
            if(i >= j)
            {
                ArrayList arraylist = listItems;
                String as[] = new String[2];
                as[0] = "name";
                as[1] = "ex";
                int ai[] = new int[2];
                ai[0] = 0x7f0a0044;
                ai[1] = 0x7f0a0045;
                listAdapter = new SimpleAdapter(this, arraylist, 0x7f030012, as, ai);
                newWordsListView.setAdapter(listAdapter);
                return;
            }
            HashMap hashmap = new HashMap();
            Word word = (Word)list.get(i);
            hashmap.put("name", word.getName());
            hashmap.put("ex", word.getEx());
            hashmap.put("id", Integer.valueOf(word.getWordId()));
            listItems.add(hashmap);
            i++;
        } while(true);
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        if(j == 10)
        {
            HashMap hashmap = new HashMap();
            hashmap.put("name", intent.getExtras().getString("name"));
            hashmap.put("ex", intent.getExtras().getString("ex"));
            hashmap.put("id", Integer.valueOf(intent.getExtras().getInt("id")));
            listItems.add(hashmap);
            ArrayList arraylist = listItems;
            String as[] = new String[2];
            as[0] = "name";
            as[1] = "ex";
            int ai[] = new int[2];
            ai[0] = 0x7f0a0044;
            ai[1] = 0x7f0a0045;
            listAdapter = new SimpleAdapter(this, arraylist, 0x7f030012, as, ai);
            newWordsListView.setAdapter(listAdapter);
        }
        super.onActivityResult(i, j, intent);
    }

    public void onClick(View view)
    {
//        if(view != addView) goto _L2; else goto _L1
//_L1:
//        addNewWord();
//_L4:
//        return;
//_L2:
//        if(view == returnView)
//            finish();
//        if(true) goto _L4; else goto _L3
//_L3:
    	if(view != addView) {
          if(view == returnView)
          finish();
    	} else {
    		addNewWord();
    	}
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03000a);
        if(Utils.dbUtil == null)
            Utils.onResume(this);
        newWordsListView = (ListView)findViewById(0x7f0a002a);
        newWordsListView.setOnItemLongClickListener(this);
        addView = (ImageView)findViewById(0x7f0a002b);
        addView.setOnClickListener(this);
        returnView = (ImageView)findViewById(0x7f0a0028);
        returnView.setOnClickListener(this);
        genAdapterData();
        Toast.makeText(this, "\u957F\u6309\u5220\u9664\u751F\u8BCD\u6761\u76EE", 0).show();
    }

    public boolean onItemLongClick(AdapterView adapterview, View view, int i, long l)
    {
        HashMap hashmap = (HashMap)listItems.get(i);
        listItems.remove(i);
        listAdapter.notifyDataSetChanged();
        Utils.dbUtil.delete(hashmap.get("id"));
        return false;
    }

    private ImageView addView;
    private BaseAdapter listAdapter;
    private ArrayList listItems;
    private ListView newWordsListView;
    private ImageView returnView;
}
