package com.example.alex.howmanymore.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.ArrayAdapter;

import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.app.App;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;


/**
 * Created by alex on 19.11.17.
 */

public class SexPickerFragment extends DialogFragment {
    private List<String> mSexesMapKey, mSexesMapValue;

    private IOnSelectedSexListener mListener;

    @Inject
    TreeMap<String, String> mSexMap;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onAttach(Context context) {
        App.getComponent().injectsSexPicker(this);

        mSexesMapKey = new ArrayList<String>();

        mSexesMapValue = new ArrayList<String>();

        for (Map.Entry<String, String> e : mSexMap.entrySet()
             ) {
            mSexesMapKey.add(e.getKey());
            mSexesMapValue.add(e.getValue());
        }

        mListener = (IOnSelectedSexListener) context;

        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(getString(R.string.input_screen_sex));
        dialog.setSingleChoiceItems(
                new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice,
                        mSexesMapValue),
                -1,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onChooseSex(mSexesMapKey.get(which));
                    }
                });
        return dialog.create();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
