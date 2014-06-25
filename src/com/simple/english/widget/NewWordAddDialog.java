// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class NewWordAddDialog extends Dialog
{

    public NewWordAddDialog(Context context)
    {
        super(context);
    }

    public NewWordAddDialog(Context context, int i)
    {
        super(context, i);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f03000b);
    }
}
