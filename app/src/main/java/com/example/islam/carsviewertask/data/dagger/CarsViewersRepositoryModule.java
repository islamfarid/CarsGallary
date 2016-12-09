package com.example.islam.carsviewertask.data.dagger;


import com.example.islam.carsviewertask.data.CarsViewerRepository;
import com.example.islam.carsviewertask.data.remote.CarsViewerRemoteViewerDataSource;
import com.example.islam.carsviewertask.data.remote.Remote;
import com.example.islam.carsviewertask.data.remote.ServiceGenerator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This is used by Dagger to inject the required arguments into the {@link CarsViewerRepository}.
 */
@Module
public class CarsViewersRepositoryModule {

    @Singleton
    @Provides
    @Remote
    CarsViewerRemoteViewerDataSource provideTasksRemoteDataSource() {
        return ServiceGenerator.createService(CarsViewerRemoteViewerDataSource.class);
    }

}
