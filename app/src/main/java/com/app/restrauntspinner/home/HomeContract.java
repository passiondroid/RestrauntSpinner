package com.app.restrauntspinner.home;

import com.app.restrauntspinner.util.Constants;

/**
 * Created by Home on 4/23/17.
 */

public interface HomeContract {

    interface View {

        void setPresenter(Presenter presenter);

        void startSpinnerActivity(@Constants.FoodType int type);
    }

    interface Presenter {

        void onBreakfastClicked();

        void onLunchClicked();

        void onDinnerClicked();
    }
}
