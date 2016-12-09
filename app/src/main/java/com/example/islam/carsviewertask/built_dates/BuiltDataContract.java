package com.example.islam.carsviewertask.built_dates;

import com.example.islam.carsviewertask.BasePresenter;
import com.example.islam.carsviewertask.BaseView;
import com.example.islam.carsviewertask.data.models.CarModel;

/**
 * Created by islam on 04/12/16.
 */

public interface BuiltDataContract {
    interface View extends BaseView<BuiltDataContract.Presenter> {
        void showBuiltDates(CarModel carModel);

        void showLoading();

        void showErrorMessage(String errorMsg);

        void hideLoading();
    }

    interface Presenter extends BasePresenter {
        void getBuiltDates(String manufacture, String mainType);
    }
}
