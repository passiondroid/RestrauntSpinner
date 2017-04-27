package com.app.restrauntspinner.home;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Home on 4/23/17.
 */

@Module
public class HomePresenterModule {

    private final HomeContract.View view;

    public HomePresenterModule(HomeContract.View view) {
        this.view = view;
    }

    @Provides
    HomeContract.View provideHomeContractView(){
        return view;
    }
}
