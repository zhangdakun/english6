// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english.widget;

import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PageAdapter extends BaseAdapter
{

    public PageAdapter(Context context, int i)
    {
        ctx = context;
        indexArray = new int[i];
        int j = 0;
        do
        {
            if(j >= i)
                return;
            indexArray[j] = j + 1;
            j++;
        } while(true);
    }

    public int getCount()
    {
        return indexArray.length;
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
        View view1 = LayoutInflater.from(ctx).inflate(0x7f03000f, null);
        ((TextView)view1.findViewById(0x7f0a003a)).setText((new StringBuilder()).append(indexArray[i]).toString());
        return view1;
    }

    private Context ctx;
    private int indexArray[];
}
