package com.example.pctruong.apporderfood.FragmentApp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.pctruong.apporderfood.R;

import java.util.Calendar;

/**
 * Created by PCTRUONG on 12/31/2016.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,day,month,year);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText editText= (EditText) getActivity().findViewById(R.id.edtNgaySinh);
        String ngaysinh=dayOfMonth+"/"+(month+1)+"/"+year;
        editText.setText(ngaysinh);

    }
}
