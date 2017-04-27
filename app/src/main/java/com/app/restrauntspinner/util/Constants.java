package com.app.restrauntspinner.util;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Home on 4/23/17.
 */

public interface Constants {

    String TYPE = "TYPE";
    int BREAKFAST = 1;
    int LUNCH = 2;
    int DINNER = 3;
    String LL = "ll";
    String RADIUS = "radius";
    String SECTION = "section";
    String LIMIT = "limit";
    String VENUE_PHOTOS = "venuePhotos";
    String Client_Id = "client_id";
    String Client_Secret = "client_secret";
    String V = "v";
    String M = "m";
    String FOURSQUARE = "foursquare";
    String API_DATE = "20170425";
    String RESPONSE = "Response";
    String COFFEE = "coffee";
    String FOOD = "food";
    String LIMIT_NUMBER = "25";
    String ONE = "1";

    @IntDef({Constants.BREAKFAST,Constants.DINNER,Constants.LUNCH})
    @Retention(RetentionPolicy.SOURCE)
    @interface FoodType{}

    String BASE_URL = "https://api.foursquare.com/";
    String CLIENT_ID = "CQFFG0O4B1CD3I3SVDQURRFQ4UMR2L04GIGQLBHSHC4M5QD1";
    String CLIENT_SECRET = "MVA5K5XRDXFZGB04K4AOHUKUEDZDJSJHY32S5S3CJVWNC41K";

}
