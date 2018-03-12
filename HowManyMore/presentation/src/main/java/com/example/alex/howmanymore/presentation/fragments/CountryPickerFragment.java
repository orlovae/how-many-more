package com.example.alex.howmanymore.presentation.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.alex.howmanymore.presentation.R;
import com.example.alex.howmanymore.presentation.adapter.RecyclerViewDialogFragment;
import com.example.alex.howmanymore.presentation.adapter.RecyclerViewTouchListener;
import com.example.alex.howmanymore.presentation.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.WINDOW_SERVICE;
import static com.example.alex.howmanymore.presentation.constants.Keys.COUNTRY_PICKER_LIST;

/**
 * Created by alex on 14.11.17.
 */

public class CountryPickerFragment extends DialogFragment {
    private List<CountryModel> countryModels;
    private List<CountryModel> selectedCountriesModelList = new ArrayList<>();

    private RecyclerViewDialogFragment adapter;

    private EditText mSearchEditText;
    private RecyclerView recyclerView;

    private IOnSelectedCountryModelListener countryListener;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onAttach(Context context) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            countryModels = bundle.getParcelableArrayList(COUNTRY_PICKER_LIST);
        }

        countryListener = (IOnSelectedCountryModelListener) context;

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

        selectedCountriesModelList = new ArrayList<>(countryModels.size());
        selectedCountriesModelList.addAll(countryModels);

        adapter = new RecyclerViewDialogFragment(getActivity(), selectedCountriesModelList);

        int width = getResources().getDimensionPixelSize(R.dimen.cp_dialog_width);
        int height = getResources().getDimensionPixelSize(R.dimen.cp_dialog_height);
        getDialog().getWindow().setLayout(width, height);

        initRecyclerView();

        return view;
    }

    private void initViews(View view) {
        mSearchEditText = (EditText) view.findViewById(R.id.country_code_picker_search);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_country);

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
        selectedCountriesModelList.clear();
        for (CountryModel countryModel : countryModels) {
            if (countryModel.getNameRUS().toLowerCase().contains(text.toLowerCase())) {
                selectedCountriesModelList.add(countryModel);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), recyclerView,
                new IRecyclerViewClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        countryListener.onChooseCountryModel(selectedCountriesModelList.get(position));
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
        countryListener = null;
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();

        Point size = new Point();
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getSize(size);
        int widthScreen = size.x;
        int heightScreen = size.y;

        Window window = getDialog().getWindow();
        window.setLayout(widthScreen, heightScreen);
        window.setGravity(Gravity.CENTER);
    }
}
