package com.example.islam.carsviewertask.main_type.presenter;


import android.support.annotation.NonNull;

import com.example.islam.carsviewertask.common.Constants;
import com.example.islam.carsviewertask.main_type.MainTypeContract;
import com.example.islam.carsviewertask.main_type.bussiness.MainTypeBusiness;
import com.example.islam.carsviewertask.utils.EspressoIdlingResource;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by islam on 03/12/16.
 */

public class MainTypePresenter implements MainTypeContract.Presenter {
    @NonNull
    private MainTypeContract.View mManufactureView;
    @NonNull
    private MainTypeBusiness mMainTypeBusiness;
    @NonNull
    private CompositeSubscription mSubscriptions;

    @Inject
    public MainTypePresenter(MainTypeContract.View mManufactureView) {
        this.mManufactureView = mManufactureView;
        mSubscriptions = new CompositeSubscription();
    }


    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    /**
     * Method injection is used here to safely reference {@code this} after the object is created.
     * For more information, see Java Concurrency in Practice.
     */
    @Inject
    public void setupListeners() {
        mManufactureView.setPresenter(this);
    }

    @Inject
    public void setBusiness(MainTypeBusiness MainTypeBusiness) {
        this.mMainTypeBusiness = MainTypeBusiness;
    }

    @Override
    public void getMoreMainTypes(int page, int pageSize, String manufacture) {
        mManufactureView.showLoading();
        EspressoIdlingResource.increment();
        mSubscriptions.add(mMainTypeBusiness
                .loadMoreMainTypes(page, pageSize, manufacture, Constants.WK_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                        carModel -> {
                            mManufactureView.showMainTypes(carModel);
                        }, // onError
                        exception -> {
                            mManufactureView.showErrorMessage(exception.getMessage());
                        },//onComplete
                        () -> {
                            mManufactureView.hideLoading();
                            if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                                EspressoIdlingResource.decrement(); // Set app as idle.
                            }
                        }));
    }
}
