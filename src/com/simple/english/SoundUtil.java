// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class SoundUtil
{

    private SoundUtil()
    {
        mp = new MediaPlayer();
    }

    public static final SoundUtil getInstance()
    {
        return INSTANCE;
    }

    public void play(AssetFileDescriptor assetfiledescriptor)
    {
        mp.reset();
        try {
			mp.setDataSource(assetfiledescriptor.getFileDescriptor(), assetfiledescriptor.getStartOffset(), assetfiledescriptor.getLength());

	        mp.prepare();
	        mp.start();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//_L1:
//        return;
//        Exception exception;
//        exception;
//        exception.printStackTrace();
//          goto _L1
    }

    public void play(String s)
    {
        mp.reset();
        try {
			mp.setDataSource(s);

	        mp.prepare();
	        mp.start();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//_L1:
//        return;
//        Exception exception;
//        exception;
//        exception.printStackTrace();
//          goto _L1
    }

    private static final SoundUtil INSTANCE = new SoundUtil();
    private MediaPlayer mp;

}
