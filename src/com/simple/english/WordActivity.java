// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.adlib.ADbaseActivity;
import com.myanns.simple.english.R;
import com.simple.english.db.Word;

// Referenced classes of package com.simple.english:
//            Utils, SoundUtil

public class WordActivity extends ADbaseActivity
    implements android.view.View.OnClickListener, android.widget.AdapterView.OnItemClickListener, android.widget.RatingBar.OnRatingBarChangeListener, Runnable
{
    public class MOnPageChangeListener
        implements android.support.v4.view.ViewPager.OnPageChangeListener
    {

        public void onPageScrollStateChanged(int i)
        {
        }

        public void onPageScrolled(int i, float f, int j)
        {
        }

        public void onPageSelected(int i)
        {
            TranslateAnimation translateanimation = null;
//            i;
//            JVM INSTR tableswitch 0 1: default 24
//        //                       0 56
//        //                       1 117;
//               goto _L1 _L2 _L3
//_L1:
//            currIndex = i;
//            translateanimation.setFillAfter(true);
//            translateanimation.setDuration(300L);
//            cursor.startAnimation(translateanimation);
//            return;
//_L2:
//          if(currIndex == 1)
//          translateanimation = new TranslateAnimation(one, 0.0F, 0.0F, 0.0F);
//      isUnit = false;
//      word1Title.setText("\u8BCD\u6C47\u5B66\u4E60");
//      forwardView.setVisibility(0);
//            continue; /* Loop/switch isn't completed */
//_L3:
//            if(currIndex == 0)
//                translateanimation = new TranslateAnimation(offset, one, 0.0F, 0.0F);
//            isUnit = true;
//            word1Title.setText("\u5355\u5143\u8BCD\u6C47");
//            forwardView.setVisibility(8);
//            if(true) goto _L1; else goto _L4
//_L4:
            
            switch (i) {
			case 0:
				if (currIndex == 1)
					translateanimation = new TranslateAnimation(one, 0.0F,
							0.0F, 0.0F);
				isUnit = false;
				word1Title.setText("\u8BCD\u6C47\u5B66\u4E60");
				forwardView.setVisibility(0);
				break;
			case 1:
				if (currIndex == 0)
					translateanimation = new TranslateAnimation(offset, one,
							0.0F, 0.0F);
				isUnit = true;
				word1Title.setText("\u5355\u5143\u8BCD\u6C47");
				forwardView.setVisibility(8);
				break;
			default:
				break;
			}
            
          currIndex = i;
          translateanimation.setFillAfter(true);
          translateanimation.setDuration(300L);
          cursor.startAnimation(translateanimation);
        }

        int one;
//        final WordActivity this$0;

        public MOnPageChangeListener()
        {
//            this$0 = WordActivity.this;
            super();
            one = 2 * offset + bmpW;
        }
    }

    public class MPagerAdapter extends PagerAdapter
    {

        public void destroyItem(View view, int i, Object obj)
        {
            ((ViewPager)view).removeView((View)mListViews.get(i));
        }

        public void finishUpdate(View view)
        {
        }

        public int getCount()
        {
            return mListViews.size();
        }

        public Object instantiateItem(View view, int i)
        {
            ((ViewPager)view).addView((View)mListViews.get(i), 0);
            return mListViews.get(i);
        }

        public boolean isViewFromObject(View view, Object obj)
        {
            boolean flag1;
            if(view == obj)
                flag1 = true;
            else
                flag1 = false;
            return flag1;
        }

        public void restoreState(Parcelable parcelable, ClassLoader classloader)
        {
        }

        public Parcelable saveState()
        {
            return null;
        }

        public void startUpdate(View view)
        {
        }

        public List mListViews;
//        final WordActivity this$0;

        public MPagerAdapter(List list)
        {
//            this$0 = WordActivity.this;
            super();
            mListViews = list;
        }
    }


    public WordActivity()
    {
        offset = 0;
        currIndex = 0;
        resume = false;
        flag = false;
        soundIng = false;
        handler = new Handler() {

            public void handleMessage(Message message)
            {
//                message.what;
//                JVM INSTR tableswitch 1 2: default 28
//            //                           1 29
//            //                           2 45;
//                   goto _L1 _L2 _L3
//_L1:
//                return;
//_L2:
//                Toast.makeText(WordActivity.this, "\u53D1\u97F3\u9700\u8981\u60A8\u7684\u624B\u673A\u8FDE\u63A5\u7F51\u7EDC!", 0).show();
//                continue; /* Loop/switch isn't completed */
//_L3:
//                Toast.makeText(WordActivity.this, "\u8BE5\u8BCD\u6C47\u65E0\u53D1\u97F3\u4FE1\u606F!", 0).show();
//                if(true) goto _L1; else goto _L4
//_L4:
            	switch (message.what) {
				case 1:
					Toast.makeText(WordActivity.this, "\u53D1\u97F3\u9700\u8981\u60A8\u7684\u624B\u673A\u8FDE\u63A5\u7F51\u7EDC!", 0).show();
					break;
				case 2:
					Toast.makeText(WordActivity.this, "\u8BE5\u8BCD\u6C47\u65E0\u53D1\u97F3\u4FE1\u606F!", 0).show();
					break;
				default:
					break;
				}
            }

//            final WordActivity this$0;
//
//            
//            {
//                this$0 = WordActivity.this;
//                super();
//            }
        }
;
    }

    private void changeDiff(int i)
    {
//        if(i != 1) goto _L2; else goto _L1
//_L1:
//        diffView.setText("\u5341\u5206\u7B80\u5355");
//_L4:
//        return;
//_L2:
    	
    	//lieb start
    	
    	if(i == 1) {
    		diffView.setText("\u5341\u5206\u7B80\u5355");
    		return;
    	}
    	//lieb end
        if(i == 2)
            diffView.setText("\u7B80\u5355");
        else
        if(i == 3)
            diffView.setText("\u666E\u901A");
        else
        if(i == 4)
            diffView.setText("\u6613\u5FD8");
        else
        if(i == 5)
            diffView.setText("\u6781\u5176\u56F0\u96BE");
        else
        if(i == 0)
            diffView.setText("\u5FFD\u7565\u8BE5\u8BCD\u6C47");
//        if(true) goto _L4; else goto _L3
//_L3:
    }

    private void changeSoundIcon()
    {
        if(isMute)
            wordSoundView.setImageResource(0x7f02002b);
        else
            wordSoundView.setImageResource(0x7f020035);
    }

    private void editNote()
    {
        final EditText editText = new EditText(this);
        editText.setHeight(200);
        editText.setText(word.getNote());
        (new android.app.AlertDialog.Builder(this)).setTitle("\u5B66\u4E60\u7B14\u8BB0").setView(editText).setPositiveButton(0x7f070005, new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
                String s = editText.getText().toString();
                word.setNote(s);
                Utils.dbUtil.updateNote(word.getWordId(), s);
                noteView.setText(s);
            }

//            final WordActivity this$0;
//            private final EditText val$editText;
//
//            
//            {
//                this$0 = WordActivity.this;
//                editText = edittext;
//                super();
//            }
        }
).setNegativeButton(0x7f070006, null).create().show();
    }

    private ArrayList genData()
    {
        wordList = Utils.dbUtil.getWordInPage(startIndex, 41);
        ArrayList arraylist = new ArrayList(30);
        int i = 0;
        int j = wordList.size();
        do
        {
            if(i >= j)
                return arraylist;
            HashMap hashmap = new HashMap();
            Word word1 = (Word)wordList.get(i);
            hashmap.put("id", Integer.valueOf(word1.getWordId()));
            hashmap.put("name", word1.getName());
            hashmap.put("mean", word1.getMeaning().replaceAll("\\|", "").replaceAll("#", "\\\n"));
            arraylist.add(hashmap);
            i++;
        } while(true);
    }

    private void initData()
    {
        unit = getIntent().getIntExtra("unit", 0);
        startIndex = 41 * unit;
        wordIndex = getIntent().getIntExtra("word", -1);
        if(resume)
            mResume();
        Utils.lastLearnUnit = unit;
        int i;
        ArrayList arraylist;
        String as[];
        int ai[];
        SimpleAdapter simpleadapter;
        boolean flag1;
        if(Utils.maxUnit > unit)
            i = Utils.maxUnit;
        else
            i = unit;
        Utils.maxUnit = i;
        if(!Utils.learnedUnits.contains((new StringBuilder()).append(unit).toString()))
        {
            Utils.learnedUnits.add((new StringBuilder()).append(unit).toString());
            if(!Utils.isFirst)
            {
//                SAdManager sadmanager = SAdManager.getInstance(this, "e6484e33ddfbf6e135469807c5662587", "L0617-2");
//                sadmanager.setMode(false, 3);
//                sadmanager.showAds(this);
            }
        }
        arraylist = genData();
        as = new String[2];
        as[0] = "name";
        as[1] = "mean";
        ai = new int[2];
        ai[0] = 0x7f0a0082;
        ai[1] = 0x7f0a0083;
        simpleadapter = new SimpleAdapter(this, arraylist, 0x7f030018, as, ai);
        unitListView.setAdapter(simpleadapter);
        if(Utils.autoSound)
            flag1 = false;
        else
            flag1 = true;
        isMute = flag1;
        changeSoundIcon();
        isUnit = false;
        next();
    }

    private void initImageView()
    {
        cursor = (ImageView)findViewById(0x7f0a0092);
        bmpW = BitmapFactory.decodeResource(getResources(), 0x7f020000).getWidth();
        offset = (Utils.screenWidth - 2 * bmpW) / 4;
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0.0F);
        cursor.setImageMatrix(matrix);
    }

    private void initViewPager()
    {
        mViewPager = (ViewPager)findViewById(0x7f0a0093);
        viewList = new ArrayList();
        LayoutInflater layoutinflater = getLayoutInflater();
        View view = layoutinflater.inflate(0x7f03001a, null);
        nameView = (TextView)view.findViewById(0x7f0a0085);
        nameView.setOnClickListener(this);
        diffView = (TextView)view.findViewById(0x7f0a0087);
        symbolView = (TextView)view.findViewById(0x7f0a0088);
        soundView = (ImageView)view.findViewById(0x7f0a0089);
        soundView.setOnClickListener(this);
        baseView = (TextView)view.findViewById(0x7f0a0095);
        exampleView = (TextView)view.findViewById(0x7f0a0097);
        noteView = (TextView)view.findViewById(0x7f0a0099);
        wordRatingBar = (RatingBar)view.findViewById(0x7f0a0086);
        wordRatingBar.setOnRatingBarChangeListener(this);
        wordSoundView = (ImageView)view.findViewById(0x7f0a0089);
        wordSoundView.setOnClickListener(this);
        menu1 = (ImageView)view.findViewById(0x7f0a0000);
        menu1.setOnClickListener(this);
        menu2 = (ImageView)view.findViewById(0x7f0a0001);
        menu2.setOnClickListener(this);
        menu3 = (ImageView)view.findViewById(0x7f0a0002);
        menu3.setOnClickListener(this);
        menu4 = (ImageView)view.findViewById(0x7f0a0003);
        menu4.setOnClickListener(this);
        viewList.add(view);
        View view1 = layoutinflater.inflate(0x7f030017, null);
        unitListView = (ListView)view1.findViewById(0x7f0a0081);
        unitListView.setOnItemClickListener(this);
        viewList.add(view1);
        mViewPager.setAdapter(new MPagerAdapter(viewList));
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(new MOnPageChangeListener());
    }

    private void mPause()
    {
        android.content.SharedPreferences.Editor editor = getSharedPreferences("SimpleCet6", 1).edit();
        editor.putInt("word_unit", unit);
        editor.putInt("word_index", -1 + wordIndex);
        editor.commit();
    }

    private void mResume()
    {
        SharedPreferences sharedpreferences = getSharedPreferences("SimpleCet6", 1);
        unit = sharedpreferences.getInt("word_unit", 0);
        wordIndex = sharedpreferences.getInt("word_index", -1);
        startIndex = 41 * unit;
    }

    private void makeSound()
    {
        String s = word.getSound();
        if(Utils.isStrNull(s))
            handler.sendEmptyMessage(2);
        else
        if(Utils.canAccessNetwork(this))
            SoundUtil.getInstance().play(s);
        else
            handler.sendEmptyMessage(1);
    }

    private void next()
    {
        if(unit < -1 + Utils.totalUnits && wordIndex >= 40 || unit == -1 + Utils.totalUnits && startIndex + wordIndex >= -1 + Utils.totalWords)
        {
            (new android.app.AlertDialog.Builder(this)).setTitle(0x7f070002).setMessage("\u5DF2\u7ECF\u672C\u5355\u5143\u6700\u540E\u4E00\u4E2A\u8BCD\u6C47\u5566,\u662F\u5426\u9000\u51FA\u672C\u5355\u5143\u5B66\u4E60?").setPositiveButton(0x7f070003, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    int j;
                    int k;
                    if(1 + unit > Utils.lastLearnUnit)
                        j = 1 + unit;
                    else
                        j = Utils.lastLearnUnit;
                    Utils.lastLearnUnit = j;
                    if(Utils.maxUnit > 1 + unit)
                        k = Utils.maxUnit;
                    else
                        k = 1 + unit;
                    Utils.maxUnit = k;
                    dialoginterface.dismiss();
                    finish();
                }

//                final WordActivity this$0;
//
//            
//            {
//                this$0 = WordActivity.this;
//                super();
//            }
            }
).setNegativeButton(0x7f070004, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                }

//                final WordActivity this$0;
//
//            
//            {
//                this$0 = WordActivity.this;
//                super();
//            }
            }
).show();
        } else
        {
            wordIndex = 1 + wordIndex;
            updateWord();
        }
    }

    private void pre()
    {
        if(wordIndex <= 0)
        {
            (new android.app.AlertDialog.Builder(this)).setTitle(0x7f070002).setMessage("\u5DF2\u7ECF\u662F\u672C\u5355\u5143\u7B2C\u4E00\u4E2A\u8BCD\u6C47\u5566,\u662F\u5426\u9000\u51FA\u672C\u5355\u5143\u5B66\u4E60?").setPositiveButton(0x7f070003, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                    finish();
                }

//                final WordActivity this$0;
//
//            
//            {
//                this$0 = WordActivity.this;
//                super();
//            }
            }
).setNegativeButton(0x7f070004, new android.content.DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialoginterface, int i)
                {
                    dialoginterface.dismiss();
                }

//                final WordActivity this$0;
//
//            
//            {
//                this$0 = WordActivity.this;
//                super();
//            }
            }
).show();
        } else
        {
            wordIndex = -1 + wordIndex;
            updateWord();
        }
    }

    private void shareWord()
    {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", 0x7f070000);
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("\u6613\u5355\u8BCD\u7CFB\u5217\u5206\u4EAB:");
        stringbuffer.append(word.getName());
        stringbuffer.append(",\u97F3\u6807[");
        stringbuffer.append(word.getSymbol());
        stringbuffer.append("],\u57FA\u672C\u91CA\u4E49[");
        stringbuffer.append(word.getMeaning().replaceAll("#", "\\.").replaceAll("\\|", " "));
        stringbuffer.append("],\u793A\u4F8B[");
        stringbuffer.append(word.getEx().replaceAll("#", " ").replaceAll("\\|", " "));
        stringbuffer.append("]");
        intent.putExtra("android.intent.extra.TEXT", stringbuffer.toString());
        startActivity(Intent.createChooser(intent, "\u53D1\u9001\u5230..."));
    }

    private void updateWord()
    {
        word = (Word)wordList.get(wordIndex);
        String s = word.getName();
        nameView.setText(s);
        if(!Utils.isStrNull(word.getSymbol()))
            symbolView.setText((new StringBuilder("[")).append(word.getSymbol()).append("]").toString());
        int i = word.getStar();
        wordRatingBar.setProgress(i);
        changeDiff(i);
        String s1 = word.getMeaning().replaceAll("\\|", "").replaceAll("#", "\\\n");
        baseView.setText(s1);
        String s2 = word.getEx();
        if(Utils.isStrNull(s2))
        {
            exampleView.setText("\u8BCD\u6C47\u6682\u65E0\u793A\u4F8B!");
        } else
        {
            String s3 = s2.replaceAll("#", "\\\n").replaceAll("\\|", "\\\n");
            if(Utils.wordHighLight && s.length() > 1)
            {
                String s4 = s3.replaceAll("#", "\\\n").replaceAll("\\|", "\\\n");
                SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder(s4);
                int j = s4.indexOf(s);
                if(j > 0)
                {
                    spannablestringbuilder.setSpan(new ForegroundColorSpan(0xff0000ff), j, j + s.length(), 33);
                    int k = s4.indexOf(s, j + s.length());
                    if(k > 0)
                    {
                        spannablestringbuilder.setSpan(new ForegroundColorSpan(0xff0000ff), k, k + s.length(), 33);
                        int l = s4.indexOf(s, k + s.length());
                        if(l > 0)
                            spannablestringbuilder.setSpan(new ForegroundColorSpan(0xff0000ff), l, l + s.length(), 33);
                    }
                }
                exampleView.setText(spannablestringbuilder);
            } else
            {
                exampleView.setText(s3);
            }
        }
        if(!isMute)
            soundIng = true;
        noteView.setText(word.getNote());
        word.setLearnDate(new Date());
        word.setCount(1 + word.getCount());
        Utils.dbUtil.update(word);
        unitListView.setSelection(wordIndex);
    }

    public void onClick(View view)
    {
        boolean flag1 = false;
//        if(view != menu1) goto _L2; else goto _L1
//_L1:
//        pre();
//_L4:
//        return;
//_L2:
        //lieb start
        if(view == menu1) {
        	pre();
        	return;
        }
        //lieb end
        if(view == menu2)
            next();
        else
        if(view == menu3)
            editNote();
        else
        if(view == menu4)
            shareWord();
        else
        if(view == wordSoundView)
        {
            if(!isMute)
                flag1 = true;
            isMute = flag1;
            changeSoundIcon();
        } else
        if(view == returnView)
        {
            if(isUnit)
            {
                isUnit = false;
                word1Title.setText("\u8BCD\u6C47\u5B66\u4E60");
                mViewPager.setCurrentItem(0);
                forwardView.setVisibility(0);
            } else
            {
                finish();
            }
        } else
        if(view == forwardView)
        {
            isUnit = true;
            word1Title.setText("\u5355\u5143\u8BCD\u6C47");
            mViewPager.setCurrentItem(1);
            forwardView.setVisibility(8);
        } else
        if(view == nameView)
            soundIng = true;
//        if(true) goto _L4; else goto _L3
//_L3:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if(Utils.dbUtil == null)
        {
            Utils.onResume(this);
            resume = true;
        }
        setContentView(R.layout.word1);
        
        LinearLayout adLayout = (LinearLayout) findViewById(R.id.adLayout);
        AdView adView = new AdView(this, AdSize.FIT_SCREEN);
        adLayout.addView(adView);
        
        returnView = (ImageView)findViewById(0x7f0a008e);
        returnView.setOnClickListener(this);
        forwardView = (ImageView)findViewById(0x7f0a0090);
        forwardView.setOnClickListener(this);
        word1Title = (TextView)findViewById(0x7f0a008f);
        initImageView();
        initViewPager();
        initData();
    }

    protected void onDestroy()
    {
        super.onDestroy();
        if(Utils.lastLearnUnit == unit && startIndex + wordIndex > Utils.lastLearnWord)
            Utils.lastLearnWord = startIndex + wordIndex;
        Utils.updateLearnPrefer(this);
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        wordIndex = i;
        updateWord();
        mViewPager.setCurrentItem(0);
    }

    protected void onPause()
    {
        super.onPause();
        flag = true;
        mPause();
    }

    public void onRatingChanged(RatingBar ratingbar, float f, boolean flag1)
    {
        int i = (int)f;
        changeDiff(i);
        word.setStar(i);
        Utils.dbUtil.updateDiff(word.getWordId(), i);
    }

    protected void onResume()
    {
        super.onResume();
        flag = false;
        (new Thread(this)).start();
    }

    public void run()
    {
        do
        {
            if(flag)
                return;
            if(soundIng)
            {
                makeSound();
                soundIng = false;
            }
            try
            {
                Thread.sleep(150L);
            }
            catch(InterruptedException interruptedexception) { }
        } while(true);
    }

    public static final int NO_SOUND = 2;
    public static final int UNLINE = 1;
    private TextView baseView;
    private int bmpW;
    private int currIndex;
    private ImageView cursor;
    private TextView diffView;
    private TextView exampleView;
    private boolean flag;
    private ImageView forwardView;
    private Handler handler;
    private boolean isMute;
    private boolean isUnit;
    private ViewPager mViewPager;
    private ImageView menu1;
    private ImageView menu2;
    private ImageView menu3;
    private ImageView menu4;
    private TextView nameView;
    private TextView noteView;
    private int offset;
    private boolean resume;
    private ImageView returnView;
    private boolean soundIng;
    private ImageView soundView;
    private int startIndex;
    private TextView symbolView;
    private int unit;
    private ListView unitListView;
    private List viewList;
    private Word word;
    private TextView word1Title;
    private int wordIndex;
    private List wordList;
    private RatingBar wordRatingBar;
    private ImageView wordSoundView;











}
