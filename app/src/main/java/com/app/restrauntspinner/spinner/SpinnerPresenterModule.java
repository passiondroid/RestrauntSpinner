package com.app.restrauntspinner.spinner;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Home on 4/24/17.
 */

@Module
public class SpinnerPresenterModule {

    private final SpinnerContract.View view;

    public SpinnerPresenterModule(SpinnerContract.View view) {
        this.view = view;
    }

    @Provides
    SpinnerContract.View provideSpinnerContractView(){
        return view;
    }
}
