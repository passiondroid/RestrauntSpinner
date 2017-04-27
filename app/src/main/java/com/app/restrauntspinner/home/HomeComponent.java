package com.app.restrauntspinner.home;

import dagger.Component;

/**
 * Created by Home on 4/23/17.
 */

@Component(modules = HomePresenterModule.class)
public interface HomeComponent {

    void inject(HomeActivity activity);

}
