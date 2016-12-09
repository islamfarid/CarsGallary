package presenter;

import com.example.islam.carsviewertask.common.Constants;
import com.example.islam.carsviewertask.main_type.MainTypeContract;
import com.example.islam.carsviewertask.main_type.bussiness.MainTypeBusiness;
import com.example.islam.carsviewertask.main_type.presenter.MainTypePresenter;

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
public class MainTypePresenterTest {
    MainTypePresenter mMainTypePresenter;
    String anyManufacture;
    @Mock
    private MainTypeBusiness mMainTypeBusiness;
    @Mock
    private MainTypeContract.View mMainTypeView;

    @Before
    public void setupTasksPresenter() {
        MockitoAnnotations.initMocks(this);
        anyManufacture = "any manufacture";
        mMainTypePresenter = new MainTypePresenter(mMainTypeView);
        mMainTypePresenter.setBusiness(mMainTypeBusiness);
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @Test
    public void testWhenLoadMoreMainTypes_MainTypeViewShowProgressISCalled() {

        when(mMainTypeBusiness.loadMoreMainTypes(0, Constants.pageSize, anyManufacture, Constants.WK_KEY)).thenReturn(Observable.create(sub -> {
            //do nothing we just test if showLoading is called
        }));
        mMainTypePresenter.getMoreMainTypes(0, Constants.pageSize, anyManufacture);
        verify(mMainTypeView, times(1)).showLoading();
    }

    @Test
    public void testWhenLoadMoreMainTypesError_MainTypeViewHideLoadingISCalled() {
        doNothing().when(mMainTypeView).showLoading();
        when(mMainTypeBusiness.loadMoreMainTypes(0, Constants.pageSize, anyManufacture, Constants.WK_KEY)).thenReturn(Observable.create(sub -> {
            sub.onError(new Throwable());
            verify(mMainTypeView, times(1)).hideLoading();
        }));
        mMainTypePresenter.getMoreMainTypes(0, Constants.pageSize, anyManufacture);
    }

    @Test
    public void testWhenLoadMoreMainTypesCompleted_MainTypeViewHideLoadingISCalled() {
        doNothing().when(mMainTypeView).showLoading();
        when(mMainTypeBusiness.loadMoreMainTypes(0, Constants.pageSize, anyManufacture, Constants.WK_KEY)).thenReturn(Observable.create(sub -> {
            sub.onCompleted();
            verify(mMainTypeView, times(1)).hideLoading();
        }));
        mMainTypePresenter.getMoreMainTypes(0, Constants.pageSize, anyManufacture);
    }

    @Test
    public void testWhenLoadMoreMainTypesWithNoErrors_MainTypeViewShowMainTypesISCalled() {
        doNothing().when(mMainTypeView).showLoading();
        when(mMainTypeBusiness.loadMoreMainTypes(0, Constants.pageSize, anyManufacture, Constants.WK_KEY)).thenReturn(Observable.create(sub -> {
            sub.onNext(null);
            sub.onCompleted();
            verify(mMainTypeView, times(1)).showMainTypes(null);
        }));
        mMainTypePresenter.getMoreMainTypes(0, Constants.pageSize, anyManufacture);
    }

    @After
    public void teardown() {
        RxAndroidPlugins.getInstance().reset();
    }
}