// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import com.android.adlib.ADbaseActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

// Referenced classes of package com.simple.english:
//            SelfTestActivity, Utils

public class SfBeginActivity extends ADbaseActivity
    implements android.view.View.OnClickListener
{

    public SfBeginActivity()
    {
    }

    private void genTipDialog(String s)
    {
        AlertDialog alertdialog = (new android.app.AlertDialog.Builder(this)).setTitle(0x7f070002).setMessage(s).setPositiveButton(0x7f070005, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
                finish();
            }

//            final SfBeginActivity this$0;
//
//            
//            {
//                this$0 = SfBeginActivity.this;
//                super();
//            }
        }
).create();
        alertdialog.setCancelable(false);
        alertdialog.show();
    }

    public void onClick(View view)
    {
        goView.setEnabled(false);
        Intent intent = new Intent();
        intent.setClass(this, SelfTestActivity.class);
        startActivity(intent);
        finish();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if(Utils.maxUnit <= 0)
        {
            genTipDialog("\u81F3\u5C11\u5B66\u4E60\u5B8C\u4E00\u4E2A\u5355\u5143\u518D\u6765\u5427");
        } else
        {
            setContentView(0x7f030015);
            content = (TextView)findViewById(0x7f0a0072);
            content.setText("\u81EA\u6211\u6D4B\u8BD5\u6A21\u5F0F\u4E2D,\u6BCF\u8F6E\u6D4B\u8BD5\u4F1A\u9012\u589E\u8BCD\u6C47\u6570,\u6BCF\u9053\u6D4B\u8BD5\u9898\u76EE\u4E2D\u8BF7\u9009\u62E9\u6B63\u786E\u7684\u5355\u8BCD\u91CA\u4E49");
            goView = (ImageView)findViewById(0x7f0a0073);
            goView.setOnClickListener(this);
        }
    }

    private TextView content;
    private ImageView goView;
}
