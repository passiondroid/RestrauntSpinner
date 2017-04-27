package com.app.restrauntspinner.network;

import com.app.restrauntspinner.model.FourSquareResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Home on 4/25/17.
 */

public interface FourSquareService {

    @GET("v2/venues/explore")
    Observable<FourSquareResponse> getNearbyRestraunts(@QueryMap Map<String, String> options);

}
