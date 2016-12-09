package com.example.islam.carsviewertask.built_dates.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.islam.carsviewertask.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by islam on 10/09/16.
 */
public class BuiltDatesAdapter extends RecyclerView.Adapter {
    Context context;
    private ArrayList<String> builtTypes;

    public BuiltDatesAdapter(Context context, ArrayList<String> builtTypes) {
        this.builtTypes = builtTypes;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.built_dates_list_item, parent, false);
        return new MainTypeItem(item);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MainTypeItem) {
            ((MainTypeItem) holder).yearTextView.setText(builtTypes.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return builtTypes.size();
    }

    public void addNewManufatures(ArrayList<String> mManufactures) {
        this.builtTypes.addAll(mManufactures);
    }

    class MainTypeItem extends RecyclerView.ViewHolder {
        @Bind(R.id.built_types_year_textview)
        TextView yearTextView;

        public MainTypeItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
