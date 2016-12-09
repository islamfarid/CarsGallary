package com.example.islam.carsviewertask.manufacture.presenter;


import android.support.annotation.NonNull;

import com.example.islam.carsviewertask.common.Constants;
import com.example.islam.carsviewertask.manufacture.ManufacturerContract;
import com.example.islam.carsviewertask.manufacture.bussiness.ManufactureBusiness;
import com.example.islam.carsviewertask.utils.EspressoIdlingResource;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by islam on 03/12/16.
 */

public class ManufacturePresenter implements ManufacturerContract.Presenter {
    @NonNull
    private ManufacturerContract.View mManufactureView;
    @NonNull
    private ManufactureBusiness mManufactureBusiness;
    @NonNull
    private CompositeSubscription mSubscriptions;

    @Inject
    public ManufacturePresenter(ManufacturerContract.View mManufactureView) {
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
    public void setBusiness(ManufactureBusiness manufactureBusiness) {
        this.mManufactureBusiness = manufactureBusiness;
    }

    @Override
    public void getMoreManufactures(int page, int pageSize) {
        EspressoIdlingResource.increment();
        mManufactureView.showLoading();
        mSubscriptions.add(mManufactureBusiness
                .loadMoreManufactures(page, pageSize, Constants.WK_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        // onNext
                        carModel -> {
                            mManufactureView.showManufactures(carModel);
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
