package com.example.alex.howmanymore.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.howmanymore.R;
import com.example.alex.howmanymore.model.Country;

import java.util.List;

/**
 * Created by alex on 15.11.17.
 */

public class RecyclerViewDialogFragment extends RecyclerView.Adapter<RecyclerViewDialogFragment.ViewHolder> {
    private Context mContext;
    private List<Country> mCountries;

    public RecyclerViewDialogFragment(Context context, List<Country> countries) {
        mContext = context;
        mCountries = countries;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_country,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Country itemCountry = mCountries.get(position);

        holder.countryFlag.setImageDrawable(ContextCompat.getDrawable(mContext, itemCountry.getFlag()));
        holder.countryName.setText(itemCountry.getNameRUS());
    }

    @Override
    public int getItemCount() {
        return (mCountries == null) ? 0 : mCountries.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView countryFlag;
        TextView countryName;

        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            countryFlag = (ImageView) itemView.findViewById(R.id.country_flag);
            countryName = (TextView) itemView.findViewById(R.id.country_name);
        }
    }
}
