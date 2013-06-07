package com.shema.aoka;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

public class DateDialogFragment extends DialogFragment {

    public static String TAG = "DateDialogFragment";

    static Context sContext;
    static Calendar sDate;
    static DateDialogFragmentListener sListener;

    public static DateDialogFragment newInstance(Context context, int titleResource, Calendar date){
        DateDialogFragment dialog  = new DateDialogFragment();
        sContext = context;
        sDate = date;
        Bundle args = new Bundle();
        args.putInt("title", titleResource);
        dialog.setArguments(args);
        return dialog;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        sContext.toString();
        dateSetListener.toString();
        sDate.toString();
        DatePickerDialog dpd = new DatePickerDialog(sContext, dateSetListener,
                sDate.get(Calendar.YEAR), sDate.get(Calendar.MONTH), sDate.get(Calendar.DAY_OF_MONTH));
        dpd.getDatePicker().setMinDate(sDate.getTimeInMillis()+1000);
        return dpd;
    }
    public void setDateDialogFragmentListener(DateDialogFragmentListener listener){
        sListener = listener;
    }
    public interface DateDialogFragmentListener{
        public void dateDialogFragmentDateSet(Calendar date);
    }
    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    //create new Calendar object for date chosen
                    //this is done simply combine the three args into one
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);
                    //call back to the DateDialogFragment listener
                    sListener.dateDialogFragmentDateSet(newDate);
                }
            };
}