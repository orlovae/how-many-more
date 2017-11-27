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
import com.example.alex.howmanymore.adapter.RecyclerViewTouchListener;
import com.example.alex.howmanymore.model.Country;

import java.util.List;

import static com.example.alex.howmanymore.constants.Keys.COUNTRY_PICKER_BIRTHDAY;

/**
 * Created by alex on 14.11.17.
 */

public class CountryPickerFragment extends DialogFragment {
    private List<Country> mCountries;
    private RecyclerView mRVCountry;

    private IOnSelectedCountryListener mListener;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onAttach(Context context) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCountries = bundle.getParcelableArrayList(COUNTRY_PICKER_BIRTHDAY);
        }

        mListener = (IOnSelectedCountryListener) context;

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
        View view = inflater.inflate(R.layout.dialog_fragment_country, null);

        initViews(view);

        initRecyclerView();

        return view;
    }

    private void initViews(View view) {
        mRVCountry = (RecyclerView) view.findViewById(R.id.recycler_view_country);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRVCountry.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));

        mRVCountry.setAdapter(new RecyclerViewDialogFragment(getActivity(), mCountries));
        mRVCountry.setLayoutManager(layoutManager);

        mRVCountry.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), mRVCountry,
                new IRecyclerViewClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        mListener.onChooseCountry(mCountries.get(position).getFlag());
                        dismiss();
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }
}
