package com.app.restrauntspinner.spinner;

import com.app.restrauntspinner.application.AppModule;
import com.app.restrauntspinner.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Home on 4/24/17.
 */

@Singleton
@Component(modules = {SpinnerPresenterModule.class, AppModule.class, NetworkModule.class})
public interface SpinnerComponent {

    void inject(SpinnerActivity activity);
}
