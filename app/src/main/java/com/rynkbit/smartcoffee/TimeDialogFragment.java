package com.rynkbit.smartcoffee;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeDialogFragment extends Dialog {
    private TimePickerDialog.OnTimeSetListener mListener;

    public void setTimeSetListener(TimePickerDialog.OnTimeSetListener mListener) {
        this.mListener = mListener;
    }

    public TimeDialogFragment(Context context){
        super(context, true, null);
    }

    public void show(){
        Calendar calendar = new GregorianCalendar();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(
                getContext(),
                mListener,
                hourOfDay,
                minute,
                true
        );
        dialog.show();
    }
}
