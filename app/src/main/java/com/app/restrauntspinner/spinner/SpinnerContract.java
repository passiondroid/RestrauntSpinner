package com.app.restrauntspinner.spinner;

import com.app.restrauntspinner.model.FourSquareResponse;

/**
 * Created by Home on 4/24/17.
 */

public interface SpinnerContract {

    interface View{

        void setPresenter(Presenter presenter);

        void startResultsActivity(FourSquareResponse fourSquareResponse);
    }

    interface Presenter{

        void onWheelClick(double lt, double lo, int type, String radius);

        void dispose();

    }
}
