// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.*;
import android.widget.Toast;
import com.simple.english.db.MDbUtil;
import java.io.*;

import net.youmi.android.AdManager;

// Referenced classes of package com.simple.english:
//            Utils, MainActivity

public class WelcomeActivity extends Activity
{

    public WelcomeActivity()
    {
        handler = new Handler() {

            public void handleMessage(Message message)
            {
//                message.what;
//                JVM INSTR tableswitch 1 1: default 24
//            //                           1 25;
//                   goto _L1 _L2
//_L1:
//                return;
//_L2:
//              Toast.makeText(WelcomeActivity.this, "\u65E0\u6CD5\u52A0\u8F7DSD\u5361!", 0).show();
//              finish();
//                if(true) goto _L1; else goto _L3
//_L3:
            	switch (message.what) {
				case 1:
	              Toast.makeText(WelcomeActivity.this, "\u65E0\u6CD5\u52A0\u8F7DSD\u5361!", 0).show();
	              finish();
					break;

				default:
					break;
				}
            }

//            final WelcomeActivity this$0;
//
//            
//            {
//                this$0 = WelcomeActivity.this;
//                super();
//            }
        }
;
    }

    private void transFile(String s)
        throws IOException
    {
        InputStream inputstream = getResources().openRawResource(0x7f050000);
        FileOutputStream fileoutputstream = new FileOutputStream(s);
        byte abyte0[] = new byte[8192];
        do
        {
            int i = inputstream.read(abyte0);
            if(i <= 0)
            {
                fileoutputstream.close();
                inputstream.close();
                return;
            }
            fileoutputstream.write(abyte0, 0, i);
        } while(true);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030019);
        
        
        AdManager.getInstance(this).init("f02bae6ceda71ace",
        	    "a34065b535889238", false);
        
    }

    protected void onDestroy()
    {
        super.onDestroy();
    }

    protected void onStart()
    {
        super.onStart();
        (new Thread() {

            public void run()
            {
                try
                {
                    if(!Environment.getExternalStorageState().equals("mounted"))
                    {
                        handler.sendEmptyMessage(1);
                    } else
                    {
                        String s = (new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()))).append("/yidanci").toString();
                        String s1 = (new StringBuilder(String.valueOf(s))).append("/cet6.db3").toString();
                        File file = new File(s);
                        if(!file.exists())
                            file.mkdir();
                        Utils.loadPref(WelcomeActivity.this);
                        if(!(new File(s1)).exists() || Utils.version < 122)
                        {
                            transFile(s1);
                            android.content.SharedPreferences.Editor editor = getSharedPreferences("SimpleCet6", 1).edit();
                            editor.putInt("version", 122);
                            editor.commit();
                        }
                        Utils.dbUtil = new MDbUtil();
                        Utils.loadFamous(WelcomeActivity.this);
                        Utils.updateFirst(WelcomeActivity.this);
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch(Exception exception) { }
            }

//            final WelcomeActivity this$0;
//
//            
//            {
//                this$0 = WelcomeActivity.this;
//                super();
//            }
        }
).start();
    }

    private static final int NO_SDCARD = 1;
    private Handler handler;


}
