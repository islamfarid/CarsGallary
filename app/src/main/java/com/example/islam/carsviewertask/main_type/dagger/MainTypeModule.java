package com.example.islam.carsviewertask.main_type.dagger;


import com.example.islam.carsviewertask.main_type.MainTypeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by islam on 03/12/16.
 */
@Module
public class MainTypeModule {
    private final MainTypeContract.View mView;


    public MainTypeModule(MainTypeContract.View view) {
        this.mView = view;
    }

    @Provides
    MainTypeContract.View provideManufactureView() {
        return mView;
    }

}
