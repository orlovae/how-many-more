package com.example.alex.howmanymore.presentation.manager;

import android.content.Context;

import com.example.alex.howmanymore.domain.ResourceManager;

/**
 * Created by alex on 09.03.18.
 */

public class AndroidResourceManager implements ResourceManager {
    private Context context;

    public AndroidResourceManager(Context context) {
        this.context = context;
    }

    @Override
    public String getString(int resourceId) {
        return context.getResources().getString(resourceId);
    }
}
