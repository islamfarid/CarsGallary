package com.example.islam.carsviewertask.data.remote;


import com.example.islam.carsviewertask.data.models.CarModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface CarsViewerRemoteViewerDataSource {

    @GET("v1/car-types/manufacturer")
    Observable<CarModel> loadMoreManufactures(@Query("page") int page,
                                              @Query("pageSize") int pageSize, @Query("wa_key") String waKey);

    @GET("http://api.wkda-test.com/v1/car-types/main-types")
    Observable<CarModel> loadMoreMainTypes(@Query("page") int page,
                                           @Query("pageSize") int pageSize, @Query("manufacturer") String manufacture, @Query("wa_key") String waKey);

    @GET("v1/car-types/built-dates")
    Observable<CarModel> getBuiltDates(@Query("manufacturer") String manufacture, @Query("main-type") String mainType, @Query("wa_key") String waKey);
}
