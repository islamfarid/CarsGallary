package com.example.islam.carsviewertask.data;


import com.example.islam.carsviewertask.data.models.CarModel;
import com.example.islam.carsviewertask.data.remote.CarsViewerRemoteViewerDataSource;
import com.example.islam.carsviewertask.data.remote.Remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;


@Singleton
public class CarsViewerRepository implements CarsViewerDataSource {

    private final CarsViewerRemoteViewerDataSource carsViewerRemoteViewerDataSource;

    @Inject
    CarsViewerRepository(@Remote CarsViewerRemoteViewerDataSource CarViewersRemoteDataSource) {
        carsViewerRemoteViewerDataSource = CarViewersRemoteDataSource;
    }


    @Override
    public Observable<CarModel> loadMoreManufactures(int page, int pageSize, String waKey) {
        return carsViewerRemoteViewerDataSource.loadMoreManufactures(page, pageSize, waKey);
    }

    @Override
    public Observable<CarModel> loadMoreMainTypes(int page, int pageSize, String manufacture, String waKey) {
        return carsViewerRemoteViewerDataSource.loadMoreMainTypes(page, pageSize, manufacture, waKey);
    }

    @Override
    public Observable<CarModel> getBuiltDates(String manufacture, String mainType, String waKey) {
        return carsViewerRemoteViewerDataSource.getBuiltDates(manufacture, mainType, waKey);
    }
}
