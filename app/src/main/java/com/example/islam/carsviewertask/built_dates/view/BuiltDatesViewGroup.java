package com.example.islam.carsviewertask.built_dates.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.islam.carsviewertask.CarsViewerTaskApplication;
import com.example.islam.carsviewertask.R;
import com.example.islam.carsviewertask.built_dates.BuiltDataContract;
import com.example.islam.carsviewertask.built_dates.dagger.BuiltDatesModule;
import com.example.islam.carsviewertask.built_dates.dagger.DaggerBuiltDatesComponent;
import com.example.islam.carsviewertask.built_dates.presenter.BuiltDatesPresenter;
import com.example.islam.carsviewertask.built_dates.view.adapter.BuiltDatesAdapter;
import com.example.islam.carsviewertask.data.models.CarModel;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by islam on 04/12/16.
 */

public class BuiltDatesViewGroup extends RelativeLayout implements BuiltDataContract.View {

    @Bind(R.id.built_dates_recyclerview)
    RecyclerView mBuiltDatesRecyclerView;
    @Bind(R.id.built_dates_textview)
    TextView mBuiltDatesTextView;
    @Bind(R.id.progressBar)
    ProgressBar mLoadingView;
    @Inject
    BuiltDatesPresenter mPresenter;
    private int columnNumbers = 3;

    public BuiltDatesViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_built_dates, this, true);
        ButterKnife.bind(this, view);
        DaggerBuiltDatesComponent.builder().carsViewersRepositoryComponent((((CarsViewerTaskApplication) ((Activity) getContext()).getApplication()).
                getCarsRepositoryComponent())).builtDatesModule(new BuiltDatesModule(this)).build().inject(this);
    }

    public BuiltDatesViewGroup(Context context) {
        this(context, null);
    }


    public void setBuiltDates(String manufacture, String mainType) {
        mPresenter.getBuiltDates(manufacture, mainType);
    }


    @Override
    public void showBuiltDates(CarModel carModel) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), columnNumbers);
        mBuiltDatesRecyclerView.setLayoutManager(gridLayoutManager);
        BuiltDatesAdapter builtDatesAdapter = new BuiltDatesAdapter(getContext(), new ArrayList<>(carModel.getCarData().keySet()));
        mBuiltDatesRecyclerView.setAdapter(builtDatesAdapter);
    }

    @Override
    public void showLoading() {
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String errorMsg) {
        mBuiltDatesTextView.setText(errorMsg);
    }

    @Override
    public void hideLoading() {
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void setPresenter(BuiltDataContract.Presenter presenter) {
        //do no thing because it will be a field injection
    }
}
