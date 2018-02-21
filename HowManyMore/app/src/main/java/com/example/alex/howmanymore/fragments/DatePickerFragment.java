package com.example.alex.howmanymore.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

import static com.example.alex.howmanymore.constants.Keys.DATE_PICKER_BIRTHDAY;
import static com.example.alex.howmanymore.constants.Keys.MAXIMUM_AGE;

/**
 * Created by alex on 12.07.17.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {
    private final String TAG = this.getClass().getSimpleName();

    private IOnSelectedDateListener mListener;
    private Bundle mBundle;

    @Override
    public void onAttach(Context context) {
        mBundle = getArguments();
        mListener = (IOnSelectedDateListener)context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        if (mBundle != null) {
            c.setTimeInMillis(mBundle.getLong(DATE_PICKER_BIRTHDAY));
        }

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this,
                year, month, day);

        Calendar calendarMin = Calendar.getInstance();
        calendarMin.set(year - MAXIMUM_AGE, month, day);

        Calendar calendarMax = Calendar.getInstance();

        datePickerDialog.getDatePicker().setMinDate(calendarMin.getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(calendarMax.getTimeInMillis());

        return datePickerDialog;
    }
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);

        long date = calendar.getTimeInMillis();

        mListener.onChooseDate(date);
    }
}
