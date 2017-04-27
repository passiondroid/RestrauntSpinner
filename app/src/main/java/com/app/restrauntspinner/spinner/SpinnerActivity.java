package com.app.restrauntspinner.spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.app.restrauntspinner.R;
import com.app.restrauntspinner.application.AppModule;
import com.app.restrauntspinner.network.NetworkModule;
import com.app.restrauntspinner.util.ActivityUtils;
import com.app.restrauntspinner.util.Constants;

import javax.inject.Inject;

public class SpinnerActivity extends AppCompatActivity {

    @Inject
    SpinnerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        int type = getIntent().getIntExtra(Constants.TYPE,1);

        SpinnerFragment spinnerFragment = (SpinnerFragment) getSupportFragmentManager().findFragmentById(R.id.container);

        if(spinnerFragment == null){
            spinnerFragment = SpinnerFragment.newInstance(type);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),spinnerFragment,R.id.container);
        }

        DaggerSpinnerComponent.builder()
                .spinnerPresenterModule(new SpinnerPresenterModule(spinnerFragment))
                .appModule(new AppModule(getApplication()))
                .networkModule(new NetworkModule(Constants.BASE_URL)).build().inject(this);
    }

}
