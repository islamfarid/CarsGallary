package com.example.islam.carsviewertask.manufacture.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.islam.carsviewertask.R;
import com.example.islam.carsviewertask.common.Constants;
import com.example.islam.carsviewertask.common.custom.EndlessRecyclerOnScrollListener;
import com.example.islam.carsviewertask.data.models.CarModel;
import com.example.islam.carsviewertask.data.models.KeyValue;
import com.example.islam.carsviewertask.main_type.view.MainTypeActivity;
import com.example.islam.carsviewertask.manufacture.ManufacturerContract;
import com.example.islam.carsviewertask.manufacture.view.adapter.ManufacturesAdapter;

import java.util.ArrayList;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by islam on 03/12/16.
 */

public class ManufactureFragment extends Fragment implements ManufacturerContract.View {
    @Bind(R.id.manufactures_recyclerview)
    RecyclerView mManufacturesRecyclerView;
    @Bind(R.id.progressBar)
    ProgressBar mLoadingBar;
    private int currentPage = -1;
    private int totalPagesCount = -1;
    private ManufacturerContract.Presenter mPresenter;
    private ManufacturesAdapter manufacturesAdapter;

    public static ManufactureFragment newInstance() {
        return new ManufactureFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_manufacture, container, false);
        ButterKnife.bind(this, root);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        init();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (manufacturesAdapter != null) {
            manufacturesAdapter.notifyDataSetChanged();
        }
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mManufacturesRecyclerView.setLayoutManager(linearLayoutManager);
        manufacturesAdapter = new ManufacturesAdapter(getActivity(), new ArrayList<>());
        mManufacturesRecyclerView.setAdapter(manufacturesAdapter);
        mPresenter.getMoreManufactures(++currentPage, Constants.pageSize);
        mManufacturesRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (totalPagesCount < 0 || totalPagesCount > currentPage) {
                    showLoading();
                    mPresenter.getMoreManufactures(++currentPage, Constants.pageSize);
                }
            }
        });

        manufacturesAdapter.setOnItemClickListener((view, position) ->
        {
            Intent mainTypeIntent = new Intent(getActivity(), MainTypeActivity.class);
            mainTypeIntent.putExtra(Constants.MANUFACTURE, manufacturesAdapter.getmManufactures().get(position));
            getActivity().startActivity(mainTypeIntent);
            getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        });
    }


    @Override
    public void setPresenter(ManufacturerContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showManufactures(CarModel carModel) {
        Set<String> keys = carModel.getCarData().keySet();
        ArrayList<KeyValue> keyValues = new ArrayList<>();
        for (String key : keys) {
            keyValues.add(new KeyValue(key, carModel.getCarData().get(key)));
        }
        totalPagesCount = carModel.getTotalPageCount();
        manufacturesAdapter.addNewManufatures(keyValues);
        manufacturesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        mLoadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String errorMsg) {
        Snackbar.make(mManufacturesRecyclerView, errorMsg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {
        mLoadingBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }
}
