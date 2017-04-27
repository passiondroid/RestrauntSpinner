package com.app.restrauntspinner.spinner;

import com.app.restrauntspinner.model.FourSquareResponse;
import com.app.restrauntspinner.network.FourSquareService;
import com.app.restrauntspinner.util.Constants;
import java.util.HashMap;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Home on 4/24/17.
 */

public class SpinnerPresenter implements SpinnerContract.Presenter {

    private SpinnerContract.View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    FourSquareService fourSquareService;

    @Inject
    public SpinnerPresenter(SpinnerContract.View view) {
        this.view = view;
    }

    @Inject
    public void setUpListeners(){
        view.setPresenter(this);
    }

    @Override
    public void onWheelClick(double latitude, double longitude, int type, String radius) {

        HashMap<String,String> params = new HashMap<>();
        params.put(Constants.LL,Double.toString(latitude)+","+Double.toString(longitude));
        //params.put(Constants.LL,"28.6315,77.2167"); //for testing Connaught place
        //params.put(Constants.LL,"40.7831,-73.9712"); //for testing Manhattan, New York
        //params.put(Constants.LL,"51.503325,-0.119543"); //for testing London Eye, london
        params.put(Constants.RADIUS,radius);
        params.put(Constants.SECTION,findSection(type));
        params.put(Constants.LIMIT,Constants.LIMIT_NUMBER);
        params.put(Constants.VENUE_PHOTOS,Constants.ONE);
        params.put(Constants.Client_Id, Constants.CLIENT_ID);
        params.put(Constants.Client_Secret, Constants.CLIENT_SECRET);
        params.put(Constants.V, Constants.API_DATE);
        params.put(Constants.M, Constants.FOURSQUARE);

        DisposableObserver d = getDisposableObserver();
        compositeDisposable.add(d);

        fourSquareService.getNearbyRestraunts(params)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(d);
    }

    @Override
    public void dispose() {
        compositeDisposable.dispose();
    }

    DisposableObserver<FourSquareResponse> getDisposableObserver(){
        return new DisposableObserver<FourSquareResponse>() {
            @Override
            public void onNext(FourSquareResponse value) {
                //TODO:check that the response is valid
                view.startResultsActivity(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private String findSection(int type){
        switch (type){
            case Constants.BREAKFAST:
                return Constants.COFFEE;
            case Constants.LUNCH:
                return Constants.FOOD;
            case Constants.DINNER:
                return Constants.FOOD;
        }
        return Constants.FOOD;
    }

}
