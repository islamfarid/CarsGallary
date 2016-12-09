package com.example.islam.carsviewertask.built_dates.dagger;


import com.example.islam.carsviewertask.built_dates.view.BuiltDatesViewGroup;
import com.example.islam.carsviewertask.data.dagger.CarsViewersRepositoryComponent;
import com.example.islam.carsviewertask.utils.FragmentScoped;

import dagger.Component;

/**
 * Created by islam on 03/12/16.
 */

@FragmentScoped
@Component(dependencies = CarsViewersRepositoryComponent.class,
        modules = BuiltDatesModule.class)
public interface BuiltDatesComponent {
    void inject(BuiltDatesViewGroup builtDatesViewGroup);
}
