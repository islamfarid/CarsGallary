package com.example.islam.carsviewertask.built_dates.bussiness;

import com.example.islam.carsviewertask.data.CarsViewerRepository;
import com.example.islam.carsviewertask.data.models.CarModel;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by islam on 04/12/16.
 */

public class BuiltDatesBusiness {
    CarsViewerRepository mCarsRepository;

    @Inject
    public BuiltDatesBusiness(CarsViewerRepository carsRepository) {
        mCarsRepository = carsRepository;
    }

    public Observable<CarModel> getBuitDates(String manufacture, String mainType, String waKey) {
        return mCarsRepository.getBuiltDates(manufacture, mainType, waKey);
    }
}
