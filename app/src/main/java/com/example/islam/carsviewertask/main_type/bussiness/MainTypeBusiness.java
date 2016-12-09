package com.example.islam.carsviewertask.main_type.bussiness;


import com.example.islam.carsviewertask.data.CarsViewerRepository;
import com.example.islam.carsviewertask.data.models.CarModel;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by islam on 03/12/16.
 */

public class MainTypeBusiness {
    CarsViewerRepository mCarsRepository;

    @Inject
    public MainTypeBusiness(CarsViewerRepository carsRepository) {
        mCarsRepository = carsRepository;
    }

    public Observable<CarModel> loadMoreMainTypes(int page, int pageSize, String manufacture, String waKey) {
        return mCarsRepository.loadMoreMainTypes(page, pageSize, manufacture, waKey);
    }
}
