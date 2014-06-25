// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.*;
import com.simple.english.db.MDbUtil;
import com.simple.english.db.Word;

// Referenced classes of package com.simple.english:
//            Utils

public class NewWordAddActivity extends Activity
    implements android.view.View.OnClickListener
{

    public NewWordAddActivity()
    {
    }

    private int addChange(String s, String s1)
    {
        Word word = new Word();
        word.setName(s);
        word.setEx(s1);
        word.setType(3);
        Utils.dbUtil.save(word);
        return word.getWordId();
    }

    public void onClick(View view)
    {
//        if(view != returnView && view != cancelBtn) goto _L2; else goto _L1
//_L1:
//        finish();
//_L4:
//        return;
//_L2:
    	
    	// start
    	if(view != returnView && view != cancelBtn) {
    		
    	} else {
    		finish();
    		return;
    	}
    	//end
        if(view == confirmBtn)
        {
            String s = nameView.getText().toString();
            String s1 = contentView.getText().toString();
            if(Utils.isStrNull(s))
                Toast.makeText(this, "\u8BCD\u6C47\u540D\u79F0\u4E0D\u80FD\u4E3A\u7A7A", 0).show();
            else
            if(Utils.isStrNull(s1))
            {
                Toast.makeText(this, "\u8BCD\u6C47\u91CA\u4E49\u4E0D\u80FD\u4E3A\u7A7A", 0).show();
            } else
            {
                int i = addChange(s, s1);
                Intent intent = new Intent();
                intent.putExtra("name", s);
                intent.putExtra("ex", s1);
                intent.putExtra("id", i);
                setResult(10, intent);
                finish();
            }
        }
//        if(true) goto _L4; else goto _L3
//_L3:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03000b);
        returnView = (ImageView)findViewById(0x7f0a002d);
        returnView.setOnClickListener(this);
        confirmBtn = (Button)findViewById(0x7f0a0035);
        confirmBtn.setOnClickListener(this);
        cancelBtn = (Button)findViewById(0x7f0a0036);
        cancelBtn.setOnClickListener(this);
        nameView = (EditText)findViewById(0x7f0a0031);
        contentView = (EditText)findViewById(0x7f0a0034);
    }

    private Button cancelBtn;
    private Button confirmBtn;
    private EditText contentView;
    private EditText nameView;
    private ImageView returnView;
}
