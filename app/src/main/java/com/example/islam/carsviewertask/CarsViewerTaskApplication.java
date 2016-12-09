package com.example.islam.carsviewertask;

import android.app.Application;

import com.example.islam.carsviewertask.data.dagger.CarsViewersRepositoryComponent;
import com.example.islam.carsviewertask.data.dagger.CarsViewersRepositoryModule;
import com.example.islam.carsviewertask.data.dagger.DaggerCarsViewersRepositoryComponent;


public class CarsViewerTaskApplication extends Application {

    private CarsViewersRepositoryComponent mCarsViewersRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mCarsViewersRepositoryComponent = DaggerCarsViewersRepositoryComponent.builder()
                .applicationModule(new ApplicationModule((getApplicationContext()))).carsViewersRepositoryModule(new CarsViewersRepositoryModule()).build();
    }

    public CarsViewersRepositoryComponent getCarsRepositoryComponent() {
        return mCarsViewersRepositoryComponent;
    }

}
