package com.example.alex.howmanymore.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.adapter.RecyclerViewDialogFragment;
import com.example.alex.howmanymore.adapter.RecyclerViewTouchListener;
import com.example.alex.howmanymore.model.Country;

import java.util.ArrayList;
import java.util.List;

import static com.example.alex.howmanymore.constants.Keys.COUNTRY_PICKER_LIST;

/**
 * Created by alex on 14.11.17.
 */

public class CountryPickerFragment extends DialogFragment {
    private List<Country> mCountries;
    private List<Country> mSelectedCountriesList = new ArrayList<>();

    private RecyclerViewDialogFragment mAdapter;

    private EditText mSearchEditText;
    private RecyclerView mRVCountry;

    private IOnSelectedCountryListener mListener;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onAttach(Context context) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCountries = bundle.getParcelableArrayList(COUNTRY_PICKER_LIST);
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

        mSelectedCountriesList = new ArrayList<>(mCountries.size());
        mSelectedCountriesList.addAll(mCountries);

        mAdapter = new RecyclerViewDialogFragment(getActivity(), mSelectedCountriesList);

        int width = getResources().getDimensionPixelSize(R.dimen.cp_dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.cp_dialog_height);
        getDialog().getWindow().setLayout(width, height);

        initRecyclerView();

        return view;
    }

    private void initViews(View view) {
        mSearchEditText = (EditText) view.findViewById(R.id.country_code_picker_search);
        mRVCountry = (RecyclerView) view.findViewById(R.id.recycler_view_country);

        mSearchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                search(s.toString());
            }
        });
    }

    private void search(String text) {
        mSelectedCountriesList.clear();
        for (Country country : mCountries) {
            if (country.getNameRUS().toLowerCase().contains(text.toLowerCase())) {
                mSelectedCountriesList.add(country);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRVCountry.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));

        mRVCountry.setAdapter(mAdapter);
        mRVCountry.setLayoutManager(layoutManager);

        mRVCountry.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), mRVCountry,
                new IRecyclerViewClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        mListener.onChooseCountry(mSelectedCountriesList.get(position).getFlag());
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
