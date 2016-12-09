package com.example.islam.carsviewertask.data;


import com.example.islam.carsviewertask.data.models.CarModel;

import rx.Observable;

public interface CarsViewerDataSource {
    Observable<CarModel> loadMoreManufactures(int page,
                                              int pageSize, String waKey);

    Observable<CarModel> loadMoreMainTypes(int page,
                                           int pageSize, String manufacture, String waKey);

    Observable<CarModel> getBuiltDates(String manufacture, String mainType, String waKey);
}
