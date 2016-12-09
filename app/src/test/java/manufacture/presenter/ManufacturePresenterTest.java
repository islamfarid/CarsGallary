package manufacture.presenter;

import com.example.islam.carsviewertask.common.Constants;
import com.example.islam.carsviewertask.manufacture.ManufacturerContract;
import com.example.islam.carsviewertask.manufacture.bussiness.ManufactureBusiness;
import com.example.islam.carsviewertask.manufacture.presenter.ManufacturePresenter;

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

public class ManufacturePresenterTest {
    ManufacturePresenter mManufacturePresenter;
    @Mock
    private ManufactureBusiness mManufactureBusiness;
    @Mock
    private ManufacturerContract.View mManufactureView;

    @Before
    public void setupTasksPresenter() {
        MockitoAnnotations.initMocks(this);
        mManufacturePresenter = new ManufacturePresenter(mManufactureView);
        mManufacturePresenter.setBusiness(mManufactureBusiness);
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @Test
    public void testWhenLoadMoreManufacture_ManufactureViewShowProgressISCalled() {
        when(mManufactureBusiness.loadMoreManufactures(0, Constants.pageSize, Constants.WK_KEY)).thenReturn(Observable.create(sub -> {
            //do nothing we just test if showLoading is called
        }));
        mManufacturePresenter.getMoreManufactures(0, Constants.pageSize);
        verify(mManufactureView, times(1)).showLoading();
    }

    @Test
    public void testWhenLoadMoreManufacturebError_ManufactureViewHideLoadingISCalled() {
        doNothing().when(mManufactureView).showLoading();
        when(mManufactureBusiness.loadMoreManufactures(0, Constants.pageSize, Constants.WK_KEY)).thenReturn(Observable.create(sub -> {
            sub.onError(new Throwable());
            verify(mManufactureView, times(1)).hideLoading();
        }));
        mManufacturePresenter.getMoreManufactures(0, Constants.pageSize);
    }

    @Test
    public void testWhenLoadMoreManufacturebCompleted_ManufactureViewHideLoadingISCalled() {
        doNothing().when(mManufactureView).showLoading();
        when(mManufactureBusiness.loadMoreManufactures(0, Constants.pageSize, Constants.WK_KEY)).thenReturn(Observable.create(sub -> {
            sub.onCompleted();
            verify(mManufactureView, times(1)).hideLoading();
        }));
        mManufacturePresenter.getMoreManufactures(0, Constants.pageSize);
    }

    @Test
    public void testWhenLoadMoreManufactureWithNoErrors_ManufactureViewShowManufacturesISCalled() {
        doNothing().when(mManufactureView).showLoading();
        when(mManufactureBusiness.loadMoreManufactures(0, Constants.pageSize, Constants.WK_KEY)).thenReturn(Observable.create(sub -> {
            sub.onNext(null);
            sub.onCompleted();
            verify(mManufactureView, times(1)).showManufactures(null);
        }));
        mManufacturePresenter.getMoreManufactures(0, Constants.pageSize);
    }

    @After
    public void teardown() {
        RxAndroidPlugins.getInstance().reset();
    }
}