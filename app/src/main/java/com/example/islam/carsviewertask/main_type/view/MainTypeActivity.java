package com.example.islam.carsviewertask.main_type.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.example.islam.carsviewertask.CarsViewerTaskApplication;
import com.example.islam.carsviewertask.R;
import com.example.islam.carsviewertask.common.Constants;
import com.example.islam.carsviewertask.data.models.KeyValue;
import com.example.islam.carsviewertask.main_type.dagger.DaggerMainTypeComponent;
import com.example.islam.carsviewertask.main_type.dagger.MainTypeModule;
import com.example.islam.carsviewertask.main_type.presenter.MainTypePresenter;
import com.example.islam.carsviewertask.utils.ActivityUtils;

import javax.inject.Inject;


public class MainTypeActivity extends AppCompatActivity {
    @Inject
    MainTypePresenter mMainTypePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manufacture);
        MainTypeFragment mainTypeFragment =
                (MainTypeFragment) getFragmentManager().findFragmentById(R.id.contentFrame);
        setTitle(((KeyValue) getIntent().getExtras().getParcelable(Constants.MANUFACTURE)).getValue());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        if (mainTypeFragment == null) {
            // Create the fragment
            mainTypeFragment = MainTypeFragment.newInstance();
            mainTypeFragment.setArguments(getIntent().getExtras());
            ActivityUtils.addFragmentToActivity(
                    getFragmentManager(), mainTypeFragment, R.id.contentFrame);
        }
        DaggerMainTypeComponent.builder().
                carsViewersRepositoryComponent(((CarsViewerTaskApplication) getApplication()).
                        getCarsRepositoryComponent()).mainTypeModule(new MainTypeModule(mainTypeFragment)).build().inject(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
