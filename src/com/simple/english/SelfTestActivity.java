// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.simple.english.db.MDbUtil;
import com.simple.english.db.Word;
import java.util.*;

// Referenced classes of package com.simple.english:
//            Utils, WordDetailActivity

public class SelfTestActivity extends Activity
    implements android.view.View.OnClickListener
{

    public SelfTestActivity()
    {
        isAnswer = false;
        String as[] = new String[4];
        as[0] = "A";
        as[1] = "B";
        as[2] = "C";
        as[3] = "D";
        answerArray = as;
        lastLearnWord = 41 * (1 + Utils.maxUnit);
    }

    private String genChenhao(int i)
    {
        String s;
        if(i == 100)
            s = "\u606D\u559C\u4F60\uFF0C\u83B7\u5F97\u4E86\u79F0\u53F7\uFF1A\u4E3A\u6211\u6240\u63A7";
        else
        if(i >= 95)
            s = "\u606D\u559C\u4F60\uFF0C\u83B7\u5F97\u79F0\u53F7\uFF1A\u638C\u63A7\u53EA\u5DEE\u4E00\u6B65";
        else
        if(i >= 80)
            s = "\u83B7\u5F97\u4E86\u79F0\u53F7\uFF1A\u5982\u6B64\u7B80\u5355";
        else
        if(i >= 60)
            s = "\u83B7\u5F97\u79F0\u53F7\uFF1A\u8FD8\u9700\u518D\u63A5\u518D\u5389";
        else
        if(i >= 20)
            s = "\u83B7\u5F97\u79F0\u53F7\uFF1A\u7981\u6B62\u9171\u6CB9";
        else
        if(i > 0)
            s = "\u83B7\u53D6\u79F0\u53F7\uFF1A\u5B9E\u5728\u7CDF\u7CD5\u900F\u4E86";
        else
        if(i == 0)
            s = "\u83B7\u5F97\u79F0\u53F7\uFF1A\u4E16\u754C\u5DF2\u7ECF\u62EF\u6551\u4E0D\u4E86\u4F60\u4E86";
        else
            s = "";
        return s;
    }

    private void genTestWord()
    {
        Integer ainteger[];
        int k;
        int i = 50;
        if(mainIndex >= mainCount)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("\u672C\u8F6E\u6D4B\u8BD5\u5B8C\u6BD5! ");
            stringbuffer.append(rightCount);
            stringbuffer.append("/");
            stringbuffer.append(mainCount);
            stringbuffer.append(", ");
            stringbuffer.append(genChenhao((100 * rightCount) / mainCount));
            int j;
            Word word;
            if(mainCount < i)
                i = 10 + mainCount;
            mainCount = i;
            stringbuffer.append("; \u4E0B\u8F6E\u9898\u76EE\u6570:");
            stringbuffer.append(mainCount);
            genTipDialog(stringbuffer.toString(), false);
            if(type == 1)
                j = 2;
            else
                j = 1;
            type = j;
            rightCount = 0;
            mainIndex = 0;
        }
        wordIds.clear();
        words.clear();
//_L3:
//        if(wordIds.size() < 4) goto _L2; else goto _L1
//_L1:
//        ainteger = (Integer[])wordIds.toArray(new Integer[4]);
//        curWordIndex = random.nextInt(4);
//        k = 0;
//_L4:
//        if(k >= 4)
//        {
//            word = (Word)words.get(curWordIndex);
//            nameView.setText(word.getName());
//            symbolView.setText((new StringBuilder("[")).append(word.getSymbol()).append("]").toString());
//            answer1.setText(((Word)words.get(0)).getMeaning().replaceAll("#", ";"));
//            answer2.setText(((Word)words.get(1)).getMeaning().replaceAll("#", ";"));
//            answer3.setText(((Word)words.get(2)).getMeaning().replaceAll("#", ";"));
//            answer4.setText(((Word)words.get(3)).getMeaning().replaceAll("#", ";"));
//            mainIndex = 1 + mainIndex;
//            return;
//        }
//        break MISSING_BLOCK_LABEL_434;
//_L2:
//        wordIds.add(Integer.valueOf(random.nextInt(lastLearnWord)));
//          goto _L3
//        words.add(Utils.dbUtil.getWordById(1 + ainteger[k].intValue()));
//        k++;
//          goto _L4
        
        while(wordIds.size() < 4) {
        	wordIds.add(Integer.valueOf(random.nextInt(lastLearnWord)));
        }
        
      ainteger = (Integer[])wordIds.toArray(new Integer[4]);
      curWordIndex = random.nextInt(4);
        
      for(k =0;k<4;k++) {
    	  words.add(Utils.dbUtil.getWordById(1 + ainteger[k].intValue()));
      }
      
      Word word = (Word)words.get(curWordIndex);
    nameView.setText(word.getName());
    symbolView.setText((new StringBuilder("[")).append(word.getSymbol()).append("]").toString());
    answer1.setText(((Word)words.get(0)).getMeaning().replaceAll("#", ";"));
    answer2.setText(((Word)words.get(1)).getMeaning().replaceAll("#", ";"));
    answer3.setText(((Word)words.get(2)).getMeaning().replaceAll("#", ";"));
    answer4.setText(((Word)words.get(3)).getMeaning().replaceAll("#", ";"));
    mainIndex = 1 + mainIndex;
    }

    private void genTipDialog(String s, final boolean quit)
    {
        AlertDialog alertdialog = (new android.app.AlertDialog.Builder(this)).setTitle(0x7f070002).setMessage(s).setPositiveButton(0x7f070005, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                dialoginterface.dismiss();
                if(quit)
                    finish();
            }

//            final SelfTestActivity this$0;
//            private final boolean val$quit;
//
//            
//            {
//                this$0 = SelfTestActivity.this;
//                quit = flag;
//                super();
//            }
        }
).create();
        alertdialog.setCancelable(false);
        alertdialog.show();
    }

    private void seeWordDetail(int i)
    {
        Intent intent = new Intent();
        intent.putExtra("word_id", ((Word)words.get(i)).getWordId());
        intent.setClass(this, WordDetailActivity.class);
        startActivity(intent);
    }

    private void validateAnswer(int i)
    {
        if(i == curWordIndex)
        {
            answerTipView.setText("\u606D\u559C\u4F60,\u56DE\u7B54\u6B63\u786E");
            answerTipImg.setImageResource(0x7f02002f);
            rightCount = 1 + rightCount;
        } else
        {
            answerTipView.setText((new StringBuilder("\u56DE\u7B54\u9519\u8BEF,\u6B63\u786E\u7B54\u6848\uFF1A")).append(answerArray[curWordIndex]).toString());
            answerTipImg.setImageResource(0x7f02001d);
        }
        isAnswer = true;
        sfBtn1.setImageResource(0x7f020001);
        sfBtn2.setImageResource(0x7f020001);
        sfBtn3.setImageResource(0x7f020001);
        sfBtn4.setImageResource(0x7f020001);
        answerLayout.setVisibility(0);
    }

    public void onClick(View view)
    {
//        if(view != returnView) goto _L2; else goto _L1
//_L1:
//        finish();
//_L4:
//        return;
//_L2:
    	// start
    	if(view != returnView) {
    		
    	}else {
    		 finish();
    		 return;
    	}
    	//end
        if(view == nextView)
        {
            genTestWord();
            isAnswer = false;
            sfBtn1.setImageDrawable(null);
            sfBtn2.setImageDrawable(null);
            sfBtn3.setImageDrawable(null);
            sfBtn4.setImageDrawable(null);
            answerLayout.setVisibility(8);
        } else
        if(view == menu1)
        {
            if(isAnswer)
                seeWordDetail(0);
            else
                validateAnswer(0);
        } else
        if(view == menu2)
        {
            if(isAnswer)
                seeWordDetail(1);
            else
                validateAnswer(1);
        } else
        if(view == menu3)
        {
            if(isAnswer)
                seeWordDetail(2);
            else
                validateAnswer(2);
        } else
        if(view == menu4) {
            if(isAnswer)
                seeWordDetail(3);
            else
                validateAnswer(3);
        }
//        if(true) goto _L4; else goto _L3
//_L3:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030013);
        random = new Random();
        wordIds = new HashSet(4);
        words = new ArrayList(4);
        returnView = (ImageView)findViewById(0x7f0a0047);
        returnView.setOnClickListener(this);
        nextView = (ImageView)findViewById(0x7f0a0061);
        nextView.setOnClickListener(this);
        nameView = (TextView)findViewById(0x7f0a004a);
        symbolView = (TextView)findViewById(0x7f0a004b);
        answerTipView = (TextView)findViewById(0x7f0a005f);
        answer1 = (TextView)findViewById(0x7f0a004e);
        answer2 = (TextView)findViewById(0x7f0a0052);
        answer3 = (TextView)findViewById(0x7f0a0056);
        answer4 = (TextView)findViewById(0x7f0a005a);
        sfBtn1 = (ImageView)findViewById(0x7f0a004f);
        sfBtn2 = (ImageView)findViewById(0x7f0a0053);
        sfBtn3 = (ImageView)findViewById(0x7f0a0057);
        sfBtn4 = (ImageView)findViewById(0x7f0a005b);
        menu1 = (RelativeLayout)findViewById(0x7f0a004c);
        menu1.setOnClickListener(this);
        menu2 = (RelativeLayout)findViewById(0x7f0a0050);
        menu2.setOnClickListener(this);
        menu3 = (RelativeLayout)findViewById(0x7f0a0054);
        menu3.setOnClickListener(this);
        menu4 = (RelativeLayout)findViewById(0x7f0a0058);
        menu4.setOnClickListener(this);
        answerLayout = (RelativeLayout)findViewById(0x7f0a005c);
        answerTipImg = (ImageView)findViewById(0x7f0a005e);
        mainCount = 10;
        mainIndex = 0;
        type = 1;
        genTestWord();
    }

    protected void onDestroy()
    {
        super.onDestroy();
    }

    private TextView answer1;
    private TextView answer2;
    private TextView answer3;
    private TextView answer4;
    private String answerArray[];
    private RelativeLayout answerLayout;
    private ImageView answerTipImg;
    private TextView answerTipView;
    private int curWordIndex;
    private boolean isAnswer;
    private final int lastLearnWord;
    private int mainCount;
    private int mainIndex;
    private RelativeLayout menu1;
    private RelativeLayout menu2;
    private RelativeLayout menu3;
    private RelativeLayout menu4;
    private TextView nameView;
    private ImageView nextView;
    private Random random;
    private ImageView returnView;
    private int rightCount;
    private ImageView sfBtn1;
    private ImageView sfBtn2;
    private ImageView sfBtn3;
    private ImageView sfBtn4;
    private TextView symbolView;
    private int type;
    private HashSet wordIds;
    private ArrayList words;
}
