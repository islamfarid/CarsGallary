package com.example.islam.carsviewertask.built_dates.presenter;

import com.example.islam.carsviewertask.built_dates.BuiltDataContract;
import com.example.islam.carsviewertask.built_dates.bussiness.BuiltDatesBusiness;
import com.example.islam.carsviewertask.common.Constants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by islam on 08/12/16.
 */
public class BuiltDatesPresenterTest {
    BuiltDatesPresenter mBuiltDatesPresenter;
    @Mock
    private BuiltDatesBusiness mBuiltDatesBusiness;
    @Mock
    private BuiltDataContract.View mBuiltDatesView;
    private String anyManufacture;
    private String anyMainType;

    @Before
    public void setupTasksPresenter() {
        MockitoAnnotations.initMocks(this);
        anyManufacture = "any manufacture";
        anyMainType = "any main type";
        mBuiltDatesPresenter = new BuiltDatesPresenter(mBuiltDatesView);
        mBuiltDatesPresenter.setBusiness(mBuiltDatesBusiness);
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @Test
    public void testWhenGetBuitDates_BuiltDatesViewShowProgressISCalled() {
        when(mBuiltDatesBusiness.getBuitDates(anyManufacture, anyMainType, Constants.WK_KEY)).thenReturn(Observable.create(sub -> {
            //do nothing we just test if showLoading is called
        }));
        mBuiltDatesPresenter.getBuiltDates(anyManufacture, anyMainType);
        verify(mBuiltDatesView, times(1)).showLoading();
    }


    @Test
    public void testWhenGetBuitDatesError_BuiltDatesViewHideLoadingISCalled() {
        doNothing().when(mBuiltDatesView).showLoading();
        when(mBuiltDatesBusiness.getBuitDates(anyManufacture, anyMainType, Constants.WK_KEY)).thenReturn(Observable.create(sub -> {
            sub.onError(new Throwable());
            verify(mBuiltDatesView, times(1)).hideLoading();
        }));
        mBuiltDatesPresenter.getBuiltDates(anyManufacture, anyMainType);
    }

    @Test
    public void testWhenGetBuitDatesCompleted_BuiltDatesViewHideLoadingISCalled() {
        doNothing().when(mBuiltDatesView).showLoading();
        when(mBuiltDatesBusiness.getBuitDates(anyManufacture, anyMainType, Constants.WK_KEY)).thenReturn(Observable.create(sub -> {
            sub.onCompleted();
            verify(mBuiltDatesView, times(1)).hideLoading();
        }));
        mBuiltDatesPresenter.getBuiltDates(anyManufacture, anyMainType);
    }

    @Test
    public void testWhenGetBuitDatesWithNoErrors_BuiltDatesViewshowBuiltDatesISCalled() {
        doNothing().when(mBuiltDatesView).showLoading();
        when(mBuiltDatesBusiness.getBuitDates(anyManufacture, anyMainType, Constants.WK_KEY)).thenReturn(Observable.create(sub -> {
            sub.onNext(null);
            sub.onCompleted();
            verify(mBuiltDatesView, times(1)).showBuiltDates(null);
        }));
        mBuiltDatesPresenter.getBuiltDates(anyManufacture, anyMainType);
    }

    @After
    public void teardown() {
        RxAndroidPlugins.getInstance().reset();
    }

}