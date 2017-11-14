package com.example.alex.howmanymore.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.howmanymore.R;

/**
 * Created by alex on 14.11.17.
 */

public class DialogCountryFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getDialog();
        View view = inflater.inflate(R.layout.dialog_fragment_country, null);
        ImageView countryFlag = (ImageView) view.findViewById(R.id.country_flag);
        TextView countryName = (TextView) view.findViewById(R.id.country_name);

        countryFlag.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.flag_ac));
        countryName.setText("Bretan");

        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }
}
