// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

// Referenced classes of package com.simple.english.widget:
//            PageAdapter

public class PageBar extends LinearLayout
{
    public static interface MOnItemClickListener
    {

        public abstract void onItemClick(int i);
    }


    public PageBar(Context context)
    {
        super(context);
    }

    public PageBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    private void changeCheck(View view)
    {
        if(view != currentView)
        {
            currentView.setBackgroundResource(0x7f02002d);
            currentView.invalidate();
            view.setBackgroundResource(0x7f02002c);
            currentView = view;
        }
    }
class BtnListener implements OnClickListener {
int index;
View view;
	public BtnListener(int index, View view) {
	super();
	this.index = index;
	this.view = view;
}
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
      itemClickListener.onItemClick(index);
      changeCheck(view);
	}
	
}
    private void fillLinearLayout()
    {
    	int cIndex = 0;
        int i = pageAdapter.getCount();
        do
        {
            if(cIndex >= i)
                return;
            View view = pageAdapter.getView(cIndex, null, null);
            if(cIndex == 0)
            {
                currentView = view;
                view.setBackgroundResource(0x7f02002c);
            }
            BtnListener listener = new BtnListener(cIndex,view);
            view.setOnClickListener(listener);
//            view.setOnClickListener(new android.view.View.OnClickListener() {
//
//                public void onClick(View view1)
//                {
//                    itemClickListener.onItemClick(cIndex);
//                    changeCheck(view);
//                }
//
//            }
//);
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-2, -2);
            layoutparams.leftMargin = 5;
            addView(view, layoutparams);
            cIndex++;
        } while(true);
    }

    public void setItemClickListener(MOnItemClickListener monitemclicklistener)
    {
        itemClickListener = monitemclicklistener;
    }

    public void setPage(int i)
    {
        changeCheck(getChildAt(i));
    }

    public void setPageAdapter(PageAdapter pageadapter)
    {
        pageAdapter = pageadapter;
        fillLinearLayout();
    }

    private View currentView;
    private MOnItemClickListener itemClickListener;
    private PageAdapter pageAdapter;


}
