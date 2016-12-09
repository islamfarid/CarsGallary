package com.example.islam.carsviewertask.main_type;


import com.example.islam.carsviewertask.BasePresenter;
import com.example.islam.carsviewertask.BaseView;
import com.example.islam.carsviewertask.data.models.CarModel;

/**
 * Created by islam on 02/12/16.
 */

public interface MainTypeContract {

    interface View extends BaseView<Presenter> {
        void showMainTypes(CarModel carModel);

        void showLoading();

        void showErrorMessage(String errorMsg);

        void hideLoading();
    }

    interface Presenter extends BasePresenter {
        void getMoreMainTypes(int page, int pageSize, String manufacture);
    }
}
