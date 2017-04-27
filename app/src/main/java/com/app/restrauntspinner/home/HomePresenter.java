package com.app.restrauntspinner.home;

import com.app.restrauntspinner.util.Constants;

import javax.inject.Inject;

/**
 * Created by Home on 4/23/17.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;

    @Inject
    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Inject
    void setUpListeners(){
        view.setPresenter(this);
    }

    @Override
    public void onBreakfastClicked() {
        view.startSpinnerActivity(Constants.BREAKFAST);
    }

    @Override
    public void onLunchClicked() {
        view.startSpinnerActivity(Constants.LUNCH);
    }

    @Override
    public void onDinnerClicked() {
        view.startSpinnerActivity(Constants.DINNER);
    }
}
