package com.example.islam.carsviewertask.main_type.view;

import android.app.Fragment;
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
import com.example.islam.carsviewertask.main_type.MainTypeContract;
import com.example.islam.carsviewertask.main_type.view.adapter.MainTypeAdapter;

import java.util.ArrayList;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by islam on 03/12/16.
 */

public class MainTypeFragment extends Fragment implements MainTypeContract.View {
    @Bind(R.id.main_type_recyclerview)
    RecyclerView mMainTypeRecyclerView;
    @Bind(R.id.progressBar)
    ProgressBar loadingBar;
    private int currentPage = -1;
    private int totalPagesCount = -1;
    private MainTypeContract.Presenter mPresenter;
    private MainTypeAdapter mainTypeAdapter;
    private KeyValue mManufactureKeyValue;

    public static MainTypeFragment newInstance() {
        return new MainTypeFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_type, container, false);
        ButterKnife.bind(this, root);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        mManufactureKeyValue = getArguments().getParcelable(Constants.MANUFACTURE);
        init();
        return root;
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mMainTypeRecyclerView.setLayoutManager(linearLayoutManager);
        mainTypeAdapter = new MainTypeAdapter(getActivity(), new ArrayList<>(), mManufactureKeyValue.getKey());
        mMainTypeRecyclerView.setAdapter(mainTypeAdapter);
        mPresenter.getMoreMainTypes(++currentPage, Constants.pageSize, mManufactureKeyValue.getKey());
        mMainTypeRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (totalPagesCount < 0 || totalPagesCount > currentPage) {
                    showLoading();
                    mPresenter.getMoreMainTypes(++currentPage, Constants.pageSize, mManufactureKeyValue.getKey());
                }
            }
        });

    }

    @Override
    public void setPresenter(MainTypeContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showMainTypes(CarModel carModel) {
        Set<String> keys = carModel.getCarData().keySet();
        ArrayList<KeyValue> keyValues = new ArrayList<>();
        for (String key : keys) {
            keyValues.add(new KeyValue(key, carModel.getCarData().get(key)));
        }
        totalPagesCount = carModel.getTotalPageCount();
        mainTypeAdapter.addNewMainTypes(keyValues);
        mainTypeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String errorMsg) {
        Snackbar.make(mMainTypeRecyclerView, errorMsg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }
}
