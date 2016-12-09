package com.example.islam.carsviewertask.manufacture;


import com.example.islam.carsviewertask.BasePresenter;
import com.example.islam.carsviewertask.BaseView;
import com.example.islam.carsviewertask.data.models.CarModel;

/**
 * Created by islam on 02/12/16.
 */

public interface ManufacturerContract {

    interface View extends BaseView<Presenter> {
        void showManufactures(CarModel carModel);

        void showLoading();

        void showErrorMessage(String errorMsg);

        void hideLoading();
    }

    interface Presenter extends BasePresenter {
        void getMoreManufactures(int page, int pageSize);
    }
}
