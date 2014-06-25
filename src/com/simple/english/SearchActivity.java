// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import com.android.adlib.ADbaseActivity;
import com.simple.english.db.MDbUtil;
import com.simple.english.db.Word;
import java.util.*;

// Referenced classes of package com.simple.english:
//            Utils, WordDetailActivity

public class SearchActivity extends ADbaseActivity
    implements android.widget.AdapterView.OnItemClickListener, android.view.View.OnClickListener
{

    public SearchActivity()
    {
    }

    private void genAdapterData()
    {
        listItems = new ArrayList();
        int i = 0;
        int j = wordList.size();
        do
        {
            if(i >= j)
                return;
            HashMap hashmap = new HashMap();
            Word word = (Word)wordList.get(i);
            hashmap.put("id", Integer.valueOf(word.getWordId()));
            hashmap.put("name", word.getName());
            hashmap.put("mean", word.getMeaning().replaceAll("#", ";"));
            listItems.add(hashmap);
            i++;
        } while(true);
    }

    private void makeListView()
    {
        genAdapterData();
        ArrayList arraylist = listItems;
        String as[] = new String[2];
        as[0] = "name";
        as[1] = "mean";
        int ai[] = new int[2];
        ai[0] = 0x7f0a0044;
        ai[1] = 0x7f0a0045;
        searchAdapter = new SimpleAdapter(this, arraylist, 0x7f030012, as, ai);
        searchListView.setAdapter(searchAdapter);
    }

    private void search()
    {
        String s = searchTextView.getText().toString();
        if(Utils.isStrNull(s))
            wordList = Utils.dbUtil.getWordInPage(0, 40);
        else
            wordList = Utils.dbUtil.getWordByNameLike(s);
        makeListView();
    }

    public void onClick(View view)
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030011);
        returnView = (ImageView)findViewById(0x7f0a0041);
        returnView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                finish();
            }

//            final SearchActivity this$0;
//
//            
//            {
//                this$0 = SearchActivity.this;
//                super();
//            }
        }
);
        searchListView = (ListView)findViewById(0x7f0a0043);
        searchListView.setOnItemClickListener(this);
        searchTextView = (EditText)findViewById(0x7f0a0042);
        searchTextView.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable editable)
            {
            }

            public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
            {
            }

            public void onTextChanged(CharSequence charsequence, int i, int j, int k)
            {
                search();
            }

//            final SearchActivity this$0;
//
//            
//            {
//                this$0 = SearchActivity.this;
//                super();
//            }
        }
);
        search();
    }

    protected void onDestroy()
    {
        super.onDestroy();
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        int j = ((Integer)((HashMap)((ListView)adapterview).getItemAtPosition(i)).get("id")).intValue();
        Intent intent = new Intent();
        intent.putExtra("word_id", j);
        intent.setClass(this, WordDetailActivity.class);
        startActivity(intent);
    }

    private ArrayList listItems;
    private ImageView returnView;
    private SimpleAdapter searchAdapter;
    private ListView searchListView;
    private EditText searchTextView;
    private List wordList;

}
