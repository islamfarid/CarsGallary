package com.example.islam.carsviewertask.built_dates.dagger;


import com.example.islam.carsviewertask.built_dates.BuiltDataContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 03/12/16.
 */
@Module
public class BuiltDatesModule {
    private final BuiltDataContract.View mView;


    public BuiltDatesModule(BuiltDataContract.View view) {
        this.mView = view;
    }

    @Provides
    BuiltDataContract.View provideManufactureView() {
        return mView;
    }

}
