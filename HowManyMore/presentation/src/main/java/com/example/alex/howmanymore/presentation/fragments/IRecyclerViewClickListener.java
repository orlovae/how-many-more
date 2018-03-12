package com.example.alex.howmanymore.presentation.fragments;

import android.view.View;

/**
 * Created by alex on 19.11.17.
 */

public interface IRecyclerViewClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
