package com.example.islam.carsviewertask.manufacture.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.islam.carsviewertask.R;
import com.example.islam.carsviewertask.common.custom.OnItemClickListener;
import com.example.islam.carsviewertask.data.models.KeyValue;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by islam on 10/09/16.
 */
public class ManufacturesAdapter extends RecyclerView.Adapter {
    Context context;
    OnItemClickListener mItemClickListener;
    private ArrayList<KeyValue> mManufactures;
    private int selectedPosition = -1;

    public ManufacturesAdapter(Context context, ArrayList<KeyValue> mManufactures) {
        this.mManufactures = mManufactures;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.manufacture_list_item, parent, false);
        return new ManufactureItem(item);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ManufactureItem) {
            ((ManufactureItem) holder).manufactureTitle.setText(mManufactures.get(position).getValue());
            ((ManufactureItem) holder).numberInList.setText(String.valueOf(position + 1));
        }
        if (position % 2 == 0) {
            ((ViewGroup) ((ManufactureItem) holder).itemView).getChildAt(0).setBackgroundColor(context.getResources().getColor(R.color.color_cyan_300));
        } else {
            ((ViewGroup) ((ManufactureItem) holder).itemView).getChildAt(0).setBackgroundColor(context.getResources().getColor(R.color.color_green_300));
        }
        ((ManufactureItem) holder).manufactureTitle.setTextColor(context.getResources().getColor(android.R.color.black));
        if (selectedPosition >= 0 && position == selectedPosition) {
            ((ViewGroup) ((ManufactureItem) holder).itemView).getChildAt(0).setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
            ((ManufactureItem) holder).manufactureTitle.setTextColor(context.getResources().getColor(android.R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return mManufactures.size();
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public ArrayList<KeyValue> getmManufactures() {
        return mManufactures;
    }

    public void addNewManufatures(ArrayList<KeyValue> mManufactures) {
        this.mManufactures.addAll(mManufactures);
    }

    class ManufactureItem extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.title_textview)
        TextView manufactureTitle;
        @Bind(R.id.number_in_list_textview)
        TextView numberInList;

        public ManufactureItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                ((ViewGroup) v).getChildAt(0).setBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
                manufactureTitle.setTextColor(context.getResources().getColor(android.R.color.white));
                mItemClickListener.onItemClick(v, getLayoutPosition());
                selectedPosition = getLayoutPosition();
            }
        }
    }
}
