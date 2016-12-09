package com.example.islam.carsviewertask.manufacture.dagger;


import com.example.islam.carsviewertask.manufacture.ManufacturerContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 03/12/16.
 */
@Module
public class ManufactureModule {
    private final ManufacturerContract.View mView;


    public ManufactureModule(ManufacturerContract.View view) {
        this.mView = view;
    }

    @Provides
    ManufacturerContract.View provideManufactureView() {
        return mView;
    }

}
