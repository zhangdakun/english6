// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.simple.english;

import com.android.adlib.ADbaseActivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

// Referenced classes of package com.simple.english:
//            Utils

public class SettingActivity extends ADbaseActivity
    implements android.widget.CompoundButton.OnCheckedChangeListener, android.view.View.OnClickListener
{

    public SettingActivity()
    {
    }

    private void init()
    {
        int i;
        Spinner spinner;
        i = 0;
        reviewRemindCheck.setChecked(Utils.reviewRemind);
        remindTimeView.setEnabled(Utils.reviewRemind);
        remindTimeView.setText(Utils.remindTime);
        autoSoundCheck.setChecked(Utils.autoSound);
        autoEnterLearnUnitCheck.setChecked(Utils.autoEnterLearnUnit);
        lazyShowCheck.setChecked(Utils.isLazyShow);
        lazyTimeView.setEnabled(Utils.isLazyShow);
        spinner = lazyTimeView;
//        if(Utils.lazyTime != 300) goto _L2; else goto _L1
//_L1:
//        spinner.setSelection(i);
//        translateEngineSpinner.setSelection(Utils.translateEngine);
//        highLightShow.setChecked(Utils.wordHighLight);
//        return;
//_L2:
//        if(Utils.lazyTime == 500)
//            i = 1;
//        else
//        if(Utils.lazyTime == 1000)
//            i = 2;
//        else
//        if(Utils.lazyTime == 2000)
//            i = 3;
//        if(true) goto _L1; else goto _L3
//_L3:
        
		if (Utils.lazyTime != 300) {
			if (Utils.lazyTime == 500)
				i = 1;
			else if (Utils.lazyTime == 1000)
				i = 2;
			else if (Utils.lazyTime == 2000)
				i = 3;
		} else {
			spinner.setSelection(i);
			translateEngineSpinner.setSelection(Utils.translateEngine);
			highLightShow.setChecked(Utils.wordHighLight);
		}
    }

    private void save()
    {
        Utils.reviewRemind = reviewRemindCheck.isChecked();
        Utils.remindTime = remindTimeView.getText().toString();
        Utils.autoSound = autoSoundCheck.isChecked();
        Utils.isLazyShow = lazyShowCheck.isChecked();
        Utils.lazyTime = Integer.valueOf(lazyTimeView.getSelectedItem().toString()).intValue();
        Utils.wordHighLight = highLightShow.isChecked();
        Utils.translateEngine = translateEngineSpinner.getSelectedItemPosition();
        Utils.autoEnterLearnUnit = autoEnterLearnUnitCheck.isChecked();
        Utils.updateSettingPrefer(this);
        if(Utils.reviewRemind)
        {
            stopService(new Intent("com.cet6.ReviewService"));
            startService(new Intent("com.cet6.ReviewService"));
        } else
        {
            stopService(new Intent("com.cet6.ReviewService"));
        }
    }

    public void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
    {
        int i = compoundbutton.getId();
//        if(i != reviewRemindCheck.getId()) goto _L2; else goto _L1
//_L1:
//        remindTimeView.setEnabled(flag);
//_L4:
//        return;
//_L2:
//        if(i == lazyShowCheck.getId())
//            lazyTimeView.setEnabled(flag);
//        if(true) goto _L4; else goto _L3
//_L3:
        
        if(i != reviewRemindCheck.getId()) {
          if(i == lazyShowCheck.getId())
          lazyTimeView.setEnabled(flag);        	
        } else {
        	remindTimeView.setEnabled(flag);
        }
    }

    public void onClick(View view)
    {
//        if(view != remindTimeView) goto _L2; else goto _L1
//_L1:
//        String as[] = remindTimeView.getText().toString().split(":");
//        (new TimePickerDialog(this, new android.app.TimePickerDialog.OnTimeSetListener() {
//
//            public void onTimeSet(TimePicker timepicker, int i, int j)
//            {
//                if(j < 10)
//                    remindTimeView.setText((new StringBuilder(String.valueOf(i))).append(":0").append(j).toString());
//                else
//                    remindTimeView.setText((new StringBuilder(String.valueOf(i))).append(":").append(j).toString());
//            }
//
//            final SettingActivity this$0;
//
//            
//            {
//                this$0 = SettingActivity.this;
//                super();
//            }
//        }
//, Integer.parseInt(as[0]), Integer.parseInt(as[1]), true)).show();
//_L4:
//        return;
//_L2:
//        if(view == returnView)
//            finish();
//        if(true) goto _L4; else goto _L3
//_L3:
    	
		if (view != remindTimeView) {
			if (view == returnView)
				finish();
		} else {
			String as[] = remindTimeView.getText().toString().split(":");
			(new TimePickerDialog(this,
					new android.app.TimePickerDialog.OnTimeSetListener() {

						public void onTimeSet(TimePicker timepicker, int i,
								int j) {
							if (j < 10)
								remindTimeView.setText((new StringBuilder(
										String.valueOf(i))).append(":0")
										.append(j).toString());
							else
								remindTimeView.setText((new StringBuilder(
										String.valueOf(i))).append(":")
										.append(j).toString());
						}

					}, Integer.parseInt(as[0]), Integer.parseInt(as[1]), true))
					.show();
		}
	}

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030014);
        if(Utils.dbUtil == null)
            Utils.onResume(this);
        reviewRemindCheck = (CheckBox)findViewById(0x7f0a0066);
        reviewRemindCheck.setOnCheckedChangeListener(this);
        autoSoundCheck = (CheckBox)findViewById(0x7f0a0069);
        autoEnterLearnUnitCheck = (CheckBox)findViewById(0x7f0a006a);
        lazyShowCheck = (CheckBox)findViewById(0x7f0a006b);
        lazyShowCheck.setOnCheckedChangeListener(this);
        highLightShow = (CheckBox)findViewById(0x7f0a006d);
        remindTimeView = (TextView)findViewById(0x7f0a0067);
        remindTimeView.setOnClickListener(this);
        returnView = (ImageView)findViewById(0x7f0a0063);
        returnView.setOnClickListener(this);
        lazyTimeView = (Spinner)findViewById(0x7f0a006c);
        translateEngineSpinner = (Spinner)findViewById(0x7f0a006e);
        init();
    }

    protected void onDestroy()
    {
        super.onDestroy();
        save();
    }

    protected void onPause()
    {
        super.onPause();
        save();
    }

    protected void onResume()
    {
        super.onResume();
        init();
    }

    private CheckBox autoEnterLearnUnitCheck;
    private CheckBox autoSoundCheck;
    private CheckBox highLightShow;
    private CheckBox lazyShowCheck;
    private Spinner lazyTimeView;
    private TextView remindTimeView;
    private ImageView returnView;
    private CheckBox reviewRemindCheck;
    private Spinner translateEngineSpinner;

}
