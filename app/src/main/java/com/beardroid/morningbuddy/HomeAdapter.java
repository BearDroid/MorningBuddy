package com.beardroid.morningbuddy;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Max on 12/23/2014.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.AlarmHolder> {
    @Override
    public AlarmHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AlarmHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class AlarmHolder extends RecyclerView.ViewHolder {

        public AlarmHolder(View v) {
            super(v);
        }
    }
}
