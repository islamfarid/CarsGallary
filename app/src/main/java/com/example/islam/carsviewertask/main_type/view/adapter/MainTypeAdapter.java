package com.example.islam.carsviewertask.main_type.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.islam.carsviewertask.R;
import com.example.islam.carsviewertask.built_dates.view.BuiltDatesViewGroup;
import com.example.islam.carsviewertask.data.models.KeyValue;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by islam on 10/09/16.
 */
public class MainTypeAdapter extends RecyclerView.Adapter {
    Context context;
    private ArrayList<KeyValue> mainTypes;
    private String manufacture;

    public MainTypeAdapter(Context context, ArrayList<KeyValue> mainTypes, String manufacture) {
        this.mainTypes = mainTypes;
        this.context = context;
        this.manufacture = manufacture;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_type_list_item, parent, false);
        return new MainTypeItem(item);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MainTypeItem) {
            ((MainTypeItem) holder).carModel.setText(mainTypes.get(position).getValue());
            ((MainTypeItem) holder).builtDatesViewGroup.setBuiltDates(manufacture, mainTypes.get(position).getKey());
            if (position % 2 == 0) {
                ((ViewGroup) ((MainTypeItem) holder).itemView).getChildAt(0).setBackgroundColor(context.getResources().getColor(R.color.color_cyan_300));
            } else {
                ((ViewGroup) ((MainTypeItem) holder).itemView).getChildAt(0).setBackgroundColor(context.getResources().getColor(R.color.color_green_300));
            }

        }
    }

    @Override
    public int getItemCount() {
        return mainTypes.size();
    }

    public void addNewMainTypes(ArrayList<KeyValue> mainTypes) {
        this.mainTypes.addAll(mainTypes);
    }

    class MainTypeItem extends RecyclerView.ViewHolder {
        @Bind(R.id.car_model_title_textview)
        TextView carModel;
        @Bind(R.id.built_dates_viewgroup)
        BuiltDatesViewGroup builtDatesViewGroup;

        public MainTypeItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
