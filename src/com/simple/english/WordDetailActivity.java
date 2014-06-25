// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myanns.simple.english.R;
import com.simple.english.db.MDbUtil;
import com.simple.english.db.Word;

// Referenced classes of package com.simple.english:
//            Utils

public class WordDetailActivity extends Activity
{

    public WordDetailActivity()
    {
    }

    private void initData(Word word)
    {
        if(word != null)
        {
            nameView.setText(word.getName());
            symbolView.setText((new StringBuilder("[")).append(word.getSymbol()).append("]").toString());
            String s = word.getMeaning().replaceAll("\\|", "").replaceAll("#", "\\\n");
            baseView.setText(s);
            String s1 = word.getEx().replaceAll("#", "\\\n").replaceAll("\\|", "\\\n");
            exampleView.setText(s1);
            noteView.setText(word.getNote());
        }
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.word_detail);
        
        LinearLayout adLayout = (LinearLayout) findViewById(R.id.adLayout);
        AdView adView = new AdView(this, AdSize.FIT_SCREEN);
        adLayout.addView(adView);
        
        if(Utils.dbUtil == null)
            Utils.onResume(this);
        nameView = (TextView)findViewById(0x7f0a009e);
        symbolView = (TextView)findViewById(0x7f0a009f);
        baseView = (TextView)findViewById(0x7f0a0095);
        exampleView = (TextView)findViewById(0x7f0a0097);
        noteView = (TextView)findViewById(0x7f0a0099);
        returnView = (ImageView)findViewById(0x7f0a009b);
        returnView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                finish();
            }

//            final WordDetailActivity this$0;
//
//            
//            {
//                this$0 = WordDetailActivity.this;
//                super();
//            }
        }
);
        int i = getIntent().getIntExtra("word_id", 1);
        initData(Utils.dbUtil.getWordById(i));
    }

    private TextView baseView;
    private TextView exampleView;
    private TextView nameView;
    private TextView noteView;
    private ImageView returnView;
    private TextView symbolView;
}
