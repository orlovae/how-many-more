package com.example.alex.howmanymore.presentation.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.howmanymore.presentation.R;
import com.example.alex.howmanymore.presentation.model.CountryModel;

import java.util.List;

/**
 * Created by alex on 15.11.17.
 */

public class RecyclerViewDialogFragment extends RecyclerView.Adapter<RecyclerViewDialogFragment.ViewHolder>
{
    private Context context;
    private List<CountryModel> countryModelList;

    public RecyclerViewDialogFragment(Context context, List<CountryModel> countryModelList) {
        this.context = context;
        this.countryModelList = countryModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_country,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CountryModel countryModel = countryModelList.get(position);

        final int flag = countryModel.getFlag();

        holder.countryFlag.setImageDrawable(ContextCompat.getDrawable(context, flag));
        holder.countryName.setText(countryModel.getNameRUS());
    }

    @Override
    public int getItemCount() {
        return (countryModelList == null) ? 0 : countryModelList.size();
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
