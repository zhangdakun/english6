// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.Editable;
import android.view.View;
import android.widget.*;
import java.io.*;
import java.net.*;
import org.json.JSONArray;
import org.json.JSONObject;

import com.android.adlib.ADbaseActivity;

// Referenced classes of package com.simple.english:
//            Utils

public class TranslateActivity extends ADbaseActivity
    implements android.view.View.OnClickListener
{

    public TranslateActivity()
    {
    }

    private String baidu(String s)
        throws Exception
    {
        BufferedReader bufferedreader;
        StringBuffer stringbuffer;
        HttpURLConnection httpurlconnection = (HttpURLConnection)(new URL((new StringBuilder("http://openapi.baidu.com/public/2.0/bmt/translate?client_id=lTC75sDiGW8WgFG2IZWSmQaL&from=auto&to=auto&q=")).append(URLEncoder.encode(s, "UTF-8")).toString())).openConnection();
        httpurlconnection.setConnectTimeout(4000);
        httpurlconnection.connect();
        bufferedreader = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream()));
        stringbuffer = new StringBuffer();
//_L3:
//        String s1 = bufferedreader.readLine();
//        if(s1 != null) goto _L2; else goto _L1
//_L1:
//        return URLDecoder.decode((new JSONObject(stringbuffer.toString())).getJSONArray("trans_result").getJSONObject(0).getString("dst"), "UTF-8");
//_L2:
//        stringbuffer.append(s1).append("\n");
//          goto _L3
//        IOException ioexception;
//        ioexception;
//        ioexception.printStackTrace();
//          goto _L1
        
        String s1 = null;
        while((s1 = bufferedreader.readLine()) != null) {
        	stringbuffer.append(s1).append("\n");
        }
        
        return URLDecoder.decode((new JSONObject(stringbuffer.toString())).getJSONArray("trans_result").getJSONObject(0).getString("dst"), "UTF-8");
    }

    private void copy(String s)
    {
        ((ClipboardManager)getSystemService("clipboard")).setText(s);
        Toast.makeText(this, "\u7ED3\u679C\u5DF2\u590D\u5236\u5230\u526A\u8D34\u677F", 0).show();
    }

    private void setting()
    {
        (new android.app.AlertDialog.Builder(this)).setTitle("\u8BF7\u9009\u62E9\u7FFB\u8BD1\u5F15\u64CE").setSingleChoiceItems(0x7f080002, Utils.translateEngine, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                Utils.translateEngine = i;
                dialoginterface.dismiss();
            }

//            final TranslateActivity this$0;
//
//            
//            {
//                this$0 = TranslateActivity.this;
//                super();
//            }
        }
).create().show();
    }

    private void share()
    {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", getTitle());
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("<");
        stringbuffer.append(getResources().getString(0x7f070000));
        stringbuffer.append(">\u5728\u7EBF\u4E2D\u82F1\u6587\u7FFB\u8BD1.");
        stringbuffer.append("\u7FFB\u8BD1\u6587\u5B57\uFF1A");
        stringbuffer.append(translateEditText.getText().toString());
        stringbuffer.append("\n");
        stringbuffer.append("\u7FFB\u8BD1\u7ED3\u679C\uFF1A");
        stringbuffer.append(resultView.getText().toString());
        intent.putExtra("android.intent.extra.TEXT", stringbuffer.toString());
        startActivity(Intent.createChooser(intent, "\u901A\u8FC7\u4EE5\u4E0B\u65B9\u5F0F\u5206\u4EAB\u7ED3\u679C..."));
    }

    private String translate(String s)
        throws Exception
    {
//        Utils.translateEngine;
//        JVM INSTR tableswitch 0 1: default 24
//    //                   0 26
//    //                   1 35;
//           goto _L1 _L2 _L3
//_L1:
//        return s;
//_L2:
//        s = baidu(s);
//        continue; /* Loop/switch isn't completed */
//_L3:
//        s = youdao(s);
//        if(true) goto _L1; else goto _L4
//_L4:
    	
    	switch (Utils.translateEngine) {
		case 0:
//			s = baidu(s);
			s = youdao(s);
			break;
		case 1:
			s = youdao(s);
			break;

		default:
			break;
			
			
		}
    	return s;
    }

    private String youdao(String s)
        throws Exception
    {
        BufferedReader bufferedreader;
        StringBuffer stringbuffer;
        HttpURLConnection httpurlconnection = (HttpURLConnection)(new URL((new StringBuilder("http://fanyi.youdao.com/openapi.do?keyfrom=limivideo&key=1746822210&type=data&doctype=json&version=1.1&q=")).append(URLEncoder.encode(s, "UTF-8")).toString())).openConnection();
        httpurlconnection.setConnectTimeout(4000);
        httpurlconnection.connect();
        bufferedreader = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream()));
        stringbuffer = new StringBuffer();
//_L3:
//        String s2 = bufferedreader.readLine();
//        if(s2 != null) goto _L2; else goto _L1
//_L1:
//        JSONObject jsonobject = new JSONObject(stringbuffer.toString());
//        IOException ioexception;
//        if(jsonobject.getInt("errorCode") == 0)
//        {
//            String s1 = URLDecoder.decode(jsonobject.getString("translation"), "UTF-8");
//            s = s1.substring(2, -2 + s1.length());
//        }
//        return s;
//_L2:
//        stringbuffer.append(s2).append("\n");
//          goto _L3
//        ioexception;
//        ioexception.printStackTrace();
//          goto _L1
        
        String s2 = null;
        while((s2 = bufferedreader.readLine()) != null) {
        	stringbuffer.append(s2).append("\n");
        }
        
      JSONObject jsonobject = new JSONObject(stringbuffer.toString());
      IOException ioexception;
      if(jsonobject.getInt("errorCode") == 0)
      {
          String s1 = URLDecoder.decode(jsonobject.getString("translation"), "UTF-8");
          s = s1.substring(2, -2 + s1.length());
      }
      return s;
    }

    public void onClick(View view)
    {
//        if(view != transClear) goto _L2; else goto _L1
//_L1:
//        translateEditText.setText("");
//        resultView.setText("");
//_L4:
//        return;
//_L2:
    	
    	//liebe s
    	if(view == transClear) {
          translateEditText.setText("");
          resultView.setText("");
          return;
    	}
    	//lieb endn
        if(view == transGo)
        {
            String s = translateEditText.getText().toString();
            if(s == null || s.trim().equals(""))
                Toast.makeText(this, "\u7FFB\u8BD1\u5185\u5BB9\u4E0D\u80FD\u4E3A\u7A7A!", 0).show();
            else
            if(!Utils.canAccessNetwork(this))
            {
                Toast.makeText(this, "\u8BF7\u786E\u4FDD\u624B\u673A\u80FD\u591F\u8FDE\u63A5\u7F51\u7EDC!", 0).show();
            } else
            {
                Utils.closeIme(this);
                try
                {
                    String s1 = translate(translateEditText.getText().toString().replaceAll("\\\n", " "));
                    resultView.setText(s1);
                }
                catch(Exception exception)
                {
                    resultView.setText("\u7FFB\u8BD1\u5931\u8D25,\u8BF7\u68C0\u67E5\u7F51\u7EDC\u662F\u5426\u8FDE\u63A5");
                }
            }
        } else
        if(view == transShare)
        {
            if(!resultView.getText().equals(""))
                share();
            else
                Toast.makeText(this, "\u8BF7\u7FFB\u8BD1\u540E\u518D\u5206\u4EAB\u5427!", 0).show();
        } else
        if(view == transCopy)
        {
            if(!resultView.getText().equals(""))
                copy(resultView.getText().toString());
            else
                Toast.makeText(this, "\u8BF7\u7FFB\u8BD1\u540E\u518D\u8FDB\u884C\u590D\u5236!", 0).show();
        } else
        if(view == returnView)
            finish();
        else
        if(view == transSetting)
            setting();
//        if(true) goto _L4; else goto _L3
//_L3:
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030016);
        translateEditText = (EditText)findViewById(0x7f0a0078);
        resultView = (TextView)findViewById(0x7f0a007b);
        transGo = (ImageView)findViewById(0x7f0a007a);
        transGo.setOnClickListener(this);
        transClear = (ImageView)findViewById(0x7f0a0079);
        transClear.setOnClickListener(this);
        transCopy = (ImageView)findViewById(0x7f0a007f);
        transCopy.setOnClickListener(this);
        transShare = (ImageView)findViewById(0x7f0a0080);
        transShare.setOnClickListener(this);
        returnView = (ImageView)findViewById(0x7f0a0075);
        returnView.setOnClickListener(this);
        transSetting = (ImageView)findViewById(0x7f0a0076);
        transSetting.setOnClickListener(this);
    }

    protected void onDestroy()
    {
        super.onDestroy();
        Utils.updateSettingPrefer(this);
    }

    protected void onResume()
    {
        super.onResume();
        if(Utils.dbUtil == null)
            Utils.onResume(this);
    }

    private TextView resultView;
    private ImageView returnView;
    private ImageView transClear;
    private ImageView transCopy;
    private ImageView transGo;
    private ImageView transSetting;
    private ImageView transShare;
    private EditText translateEditText;
}
