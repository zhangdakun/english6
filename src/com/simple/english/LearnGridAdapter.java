// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import android.content.Context;
import android.content.res.Resources;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

// Referenced classes of package com.simple.english:
//            Utils

public class LearnGridAdapter extends BaseAdapter
{

    public LearnGridAdapter(Context context, int i, int j)
    {
        ctx = context;
        int k = 1 + (j - i);
        names = new int[k];
        int l = 0;
        do
        {
            if(l >= k)
                return;
            names[l] = l + i;
            l++;
        } while(true);
    }

    public int getCount()
    {
        return names.length;
    }

    public Object getItem(int i)
    {
        return Integer.valueOf(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if(view == null)
        {
            int j = names[i];
            view = LayoutInflater.from(ctx).inflate(0x7f030008, null);
            ((TextView)view.findViewById(0x7f0a001f)).setText((new StringBuilder()).append(j).toString());
            TextView textview = (TextView)view.findViewById(0x7f0a0020);
            if(Utils.learnedUnits.size() > 0 && Utils.learnedUnits.contains((new StringBuilder()).append(j - 1).toString()))
                textview.setBackgroundColor(-256);
            else
                textview.setBackgroundColor(ctx.getResources().getColor(0x7f060005));
        }
        return view;
    }

    private Context ctx;
    private int names[];
}
