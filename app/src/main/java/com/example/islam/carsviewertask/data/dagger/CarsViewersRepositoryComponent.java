package com.example.islam.carsviewertask.data.dagger;


import com.example.islam.carsviewertask.ApplicationModule;
import com.example.islam.carsviewertask.data.CarsViewerRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {CarsViewersRepositoryModule.class, ApplicationModule.class})
public interface CarsViewersRepositoryComponent {

    CarsViewerRepository getTasksRepository();
}
