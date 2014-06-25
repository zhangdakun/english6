// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.widget.*;

import com.android.adlib.ADbaseActivity;
import com.myanns.simple.english.R;
import com.simple.english.db.MDbUtil;
import com.simple.english.widget.PageAdapter;
import com.simple.english.widget.PageBar;

// Referenced classes of package com.simple.english:
//            Utils, WordActivity, LearnGridAdapter

public class LearnActivity extends ADbaseActivity
    implements android.view.GestureDetector.OnGestureListener, com.simple.english.widget.PageBar.MOnItemClickListener, android.widget.AdapterView.OnItemClickListener
{

    public LearnActivity()
    {
    }

    private void init()
    {
        int i = 1;
        if(Utils.dbUtil == null)
            Utils.onResume(this);
        int j = Utils.dbUtil.getTotalCount();
        Utils.totalWords = j;
        int k = j / 41;
        int l;
        int i1;
        int j1;
        int k1;
        PageAdapter pageadapter;
        int l1;
        if(j % 41 == 0)
            l = 0;
        else
            l = i;
        i1 = k + l;
        Utils.totalUnits = i1;
        j1 = i1 / 16;
        if(i1 % 16 == 0)
            i = 0;
        k1 = j1 + i;
        pageadapter = new PageAdapter(this, k1);
        pb.setPageAdapter(pageadapter);
        l1 = 0;
        do
        {
            if(l1 >= k1)
            {
                int i2 = Utils.lastLearnUnit / 16;
                flipper.setDisplayedChild(i2);
                pb.setPage(i2);
                if(Utils.autoEnterLearnUnit)
                    lastLearnUnit();
                else
                if(!Utils.isFirst)
                    Toast.makeText(this, (new StringBuilder("\u4E0A\u6B21\u5B66\u4E60\u5230")).append(1 + Utils.lastLearnUnit).append("\u5355\u5143").toString(), 0).show();
                return;
            }
            if(l1 < k1 - 1)
                flipper.addView(newGridView(1 + l1 * 16, 16 * (l1 + 1)));
            else
                flipper.addView(newGridView(1 + l1 * 16, i1));
            l1++;
        } while(true);
    }

    private void lastLearnUnit()
    {
        Intent intent = new Intent();
        intent.setClass(this, WordActivity.class);
        intent.putExtra("unit", Utils.lastLearnUnit);
        if(Utils.lastLearnWord > 41 * Utils.lastLearnUnit && Utils.lastLearnWord < 41 * (1 + Utils.lastLearnUnit))
            intent.putExtra("word", Utils.lastLearnWord - 41 * Utils.lastLearnUnit);
        startActivity(intent);
    }

    private void updateUI()
    {
        int i = flipper.getChildCount();
        int j = 0;
        do
        {
            if(j >= i)
                return;
            GridView gridview = (GridView)flipper.getChildAt(j);
            String as[] = ((String)gridview.getTag()).split(":");
            gridview.setAdapter(new LearnGridAdapter(this, Integer.parseInt(as[0]), Integer.parseInt(as[1])));
            j++;
        } while(true);
    }

    public GridView newGridView(int i, int j)
    {
        Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(0x7f020024)).getBitmap();
        int k = (Utils.screenWidth - 4 * bitmap.getWidth()) / 5;
        int l = (Utils.screenHeight - pageBarHeight - 4 * bitmap.getHeight() - Utils.adjustDensity(this, 50F)) / 5;
        GridView gridview = new GridView(this);
        gridview.setVerticalSpacing(l);
        gridview.setHorizontalSpacing(k);
        gridview.setNumColumns(4);
        gridview.setAdapter(new LearnGridAdapter(this, i, j));
        gridview.setOnItemClickListener(this);
        gridview.setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionevent)
            {
                return detector.onTouchEvent(motionevent);
            }

//            final LearnActivity this$0;
//
//            
//            {
//                this$0 = LearnActivity.this;
//                super();
//            }
        }
);
        gridview.setTag((new StringBuilder(String.valueOf(i))).append(":").append(j).toString());
        gridview.setGravity(1);
        gridview.setPadding(k, 0, k, 0);
        gridview.setHorizontalScrollBarEnabled(false);
        gridview.setVerticalScrollBarEnabled(true);
        gridview.setSelector(0x7f020025);
        return gridview;
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030007);
        
        LinearLayout adLayout = (LinearLayout) findViewById(R.id.adLayout);
        AdView adView = new AdView(this, AdSize.FIT_SCREEN);
        adLayout.addView(adView);
        
        if(Utils.dbUtil == null)
            Utils.onResume(this);
        detector = new GestureDetector(this);
        flipper = (ViewFlipper)findViewById(0x7f0a001d);
        pb = (PageBar)findViewById(0x7f0a001e);
        pb.setItemClickListener(this);
        returnView = (ImageView)findViewById(0x7f0a001b);
        returnView.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                finish();
            }

//            final LearnActivity this$0;
//
//            
//            {
//                this$0 = LearnActivity.this;
//                super();
//            }
        }
);
        pageBarHeight = ((BitmapDrawable)getResources().getDrawable(0x7f02002c)).getBitmap().getHeight() + Utils.adjustDensity(this, 10F);
        init();
    }

    public boolean onDown(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        boolean flag = true;
        if(motionevent.getX() - motionevent1.getX() > 120F)
        {
            flipper.setInAnimation(AnimationUtils.loadAnimation(this, 0x7f040000));
            flipper.setOutAnimation(AnimationUtils.loadAnimation(this, 0x7f040001));
            flipper.showNext();
            pb.setPage(flipper.getDisplayedChild());
        } else
        if(motionevent.getX() - motionevent1.getX() < -120F)
        {
            flipper.setInAnimation(AnimationUtils.loadAnimation(this, 0x7f040002));
            flipper.setOutAnimation(AnimationUtils.loadAnimation(this, 0x7f040003));
            flipper.showPrevious();
            pb.setPage(flipper.getDisplayedChild());
        } else
        {
            flag = false;
        }
        return flag;
    }

    public void onItemClick(int i)
    {
        int j;
        j = flipper.getDisplayedChild();
//        if(j <= i)
//            break MISSING_BLOCK_LABEL_60;
//_L3:
//        if(j != i) goto _L2; else goto _L1
//_L1:
//        return;
//_L2:
//        flipper.setInAnimation(AnimationUtils.loadAnimation(this, 0x7f040002));
//        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, 0x7f040003));
//        flipper.showPrevious();
//        j--;
//          goto _L3
//        if(j < i)
//            while(j != i) 
//            {
//                flipper.setInAnimation(AnimationUtils.loadAnimation(this, 0x7f040000));
//                flipper.setOutAnimation(AnimationUtils.loadAnimation(this, 0x7f040001));
//                flipper.showNext();
//                j++;
//            }
//          goto _L1
        
        if(j <= i ) {
          while(j != i) 
          {
              flipper.setInAnimation(AnimationUtils.loadAnimation(this, 0x7f040000));
              flipper.setOutAnimation(AnimationUtils.loadAnimation(this, 0x7f040001));
              flipper.showNext();
              j++;
          }
        }
        
        while(j != i) {
          flipper.setInAnimation(AnimationUtils.loadAnimation(this, 0x7f040002));
          flipper.setOutAnimation(AnimationUtils.loadAnimation(this, 0x7f040003));
          flipper.showPrevious();
          j--;
        }
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        Intent intent = new Intent();
        intent.setClass(this, WordActivity.class);
        int j = i + 16 * flipper.getDisplayedChild();
        if(j == Utils.lastLearnUnit && Utils.lastLearnWord > 41 * Utils.lastLearnUnit && Utils.lastLearnWord < 41 * (1 + Utils.lastLearnUnit))
            intent.putExtra("word", Utils.lastLearnWord - 41 * Utils.lastLearnUnit);
        intent.putExtra("unit", j);
        startActivity(intent);
    }

    public void onLongPress(MotionEvent motionevent)
    {
    }

    protected void onResume()
    {
        super.onResume();
        updateUI();
    }

    public boolean onScroll(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        return false;
    }

    public void onShowPress(MotionEvent motionevent)
    {
    }

    public boolean onSingleTapUp(MotionEvent motionevent)
    {
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        return detector.onTouchEvent(motionevent);
    }

    private GestureDetector detector;
    private ViewFlipper flipper;
    private int pageBarHeight;
    private PageBar pb;
    private ImageView returnView;

}
