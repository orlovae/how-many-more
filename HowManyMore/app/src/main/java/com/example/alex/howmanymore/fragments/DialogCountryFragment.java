package com.example.alex.howmanymore.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.adapter.RecyclerViewDialogFragment;
import com.example.alex.howmanymore.model.Country;

import java.util.List;

/**
 * Created by alex on 14.11.17.
 */

public class DialogCountryFragment extends DialogFragment {
    private List<Country> mCountries;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onAttach(Context context) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCountries = bundle.getParcelableArrayList("country");
        }
        Log.d(TAG, "onAttach: " + mCountries.size());
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setStyle(STYLE_NO_TITLE, 0);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getDialog();
        View view = inflater.inflate(R.layout.dialog_fragment_country, null);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_country);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));

        RecyclerViewDialogFragment adapter = new RecyclerViewDialogFragment(getActivity(), mCountries);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

//        spinner.setSelection(presenter.getPositionSpinner());

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
