package com.example.islam.carsviewertask.manufacture.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.islam.carsviewertask.CarsViewerTaskApplication;
import com.example.islam.carsviewertask.R;
import com.example.islam.carsviewertask.manufacture.dagger.DaggerManufactureComponent;
import com.example.islam.carsviewertask.manufacture.dagger.ManufactureModule;
import com.example.islam.carsviewertask.manufacture.presenter.ManufacturePresenter;
import com.example.islam.carsviewertask.utils.ActivityUtils;

import javax.inject.Inject;


public class ManufacturerActivity extends AppCompatActivity {
    @Inject
    ManufacturePresenter mManufacturePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacture);
        ManufactureFragment manufactureFragment =
                (ManufactureFragment) getFragmentManager().findFragmentById(R.id.contentFrame);
        if (manufactureFragment == null) {
            // Create the fragment
            manufactureFragment = ManufactureFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getFragmentManager(), manufactureFragment, R.id.contentFrame);
        }
        setTitle(getResources().getString(R.string.manufactures));
        DaggerManufactureComponent.builder()
                .carsViewersRepositoryComponent(((CarsViewerTaskApplication) getApplication()).getCarsRepositoryComponent())
                .manufactureModule(new ManufactureModule(manufactureFragment)).build()
                .inject(this);

    }
}
