package com.example.islam.carsviewertask.manufacture.dagger;


import com.example.islam.carsviewertask.data.dagger.CarsViewersRepositoryComponent;
import com.example.islam.carsviewertask.manufacture.view.ManufacturerActivity;
import com.example.islam.carsviewertask.utils.FragmentScoped;

import dagger.Component;

/**
 * Created by islam on 03/12/16.
 */

@FragmentScoped
@Component(dependencies = CarsViewersRepositoryComponent.class,
        modules = ManufactureModule.class)
public interface ManufactureComponent {
    void inject(ManufacturerActivity manufacturerActivity);
}
