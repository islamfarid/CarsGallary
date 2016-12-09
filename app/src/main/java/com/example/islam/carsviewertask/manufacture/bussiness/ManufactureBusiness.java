package com.example.islam.carsviewertask.manufacture.bussiness;


import com.example.islam.carsviewertask.data.CarsViewerRepository;
import com.example.islam.carsviewertask.data.models.CarModel;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by islam on 03/12/16.
 */

public class ManufactureBusiness {
    CarsViewerRepository mCarsRepository;

    @Inject
    public ManufactureBusiness(CarsViewerRepository carsRepository) {
        mCarsRepository = carsRepository;
    }

    public Observable<CarModel> loadMoreManufactures(int page, int pageSize, String waKey) {
        return mCarsRepository.loadMoreManufactures(page, pageSize, waKey);
    }
}
