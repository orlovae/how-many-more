package com.example.alex.howmanymore.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.model.Country;

import java.util.List;

/**
 * Created by alex on 10.11.17.
 */

public class CountryAdapter extends BaseAdapter implements AdapterView.OnItemSelectedListener {
    private Context mContext;
    private List<Country> mCountries;
    LayoutInflater mInflater;

    public CountryAdapter(Context context, List<Country> mCountries) {
        super();
        this.mContext = context;
        this.mCountries = mCountries;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mCountries.size();
    }

    @Override
    public Object getItem(int position) {
        return mCountries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Country item = mCountries.get(position);

        if (view == null) {
            view = mInflater.inflate(R.layout.spinner_country, null);
        }

        if (view != null) {
            ImageView flag = (ImageView) view.findViewById(R.id.country_flag);
            TextView country = (TextView) view.findViewById(R.id.country_name);

            if (flag != null) {
                if (item.getFlag() > 0) {
                    flag.setBackground(ContextCompat.getDrawable(mContext, item.getFlag()));
                } else flag.setVisibility(View.GONE);
            }
            if (country != null) {
                country.setText(item.getNameRUS());
            }
        }
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
