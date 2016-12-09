package com.example.islam.carsviewertask.main_type.dagger;


import com.example.islam.carsviewertask.data.dagger.CarsViewersRepositoryComponent;
import com.example.islam.carsviewertask.main_type.view.MainTypeActivity;
import com.example.islam.carsviewertask.utils.FragmentScoped;

import dagger.Component;

/**
 * Created by islam on 03/12/16.
 */

@FragmentScoped
@Component(dependencies = CarsViewersRepositoryComponent.class,
        modules = MainTypeModule.class)
public interface MainTypeComponent {
    void inject(MainTypeActivity mainTypeActivity);
}
