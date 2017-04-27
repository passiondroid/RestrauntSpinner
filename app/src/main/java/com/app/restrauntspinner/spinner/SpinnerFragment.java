package com.app.restrauntspinner.spinner;


import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.app.restrauntspinner.R;
import com.app.restrauntspinner.custom.SeekbarWithIntervals;
import com.app.restrauntspinner.model.FourSquareResponse;
import com.app.restrauntspinner.results.ResultsActivity;
import com.app.restrauntspinner.util.Constants;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.ArrayList;
import java.util.List;

import static dagger.internal.Preconditions.checkNotNull;

public class SpinnerFragment extends Fragment implements SpinnerContract.View,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private ImageView wheelIV;

    private SpinnerContract.Presenter mPresenter;

    private boolean isAnimating;

    private GoogleApiClient mGoogleApiClient;

    private Location mLastLocation;

    private Double latitude, longitude;

    private LocationRequest mLocationRequest;

    private SeekbarWithIntervals seekbar;

    private static final String TAG = "SpinnerFragment";

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private int type;

    private Animation animation;

    public SpinnerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment SpinnerFragment.
     */
    public static SpinnerFragment newInstance(int type) {
        SpinnerFragment fragment = new SpinnerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.TYPE,type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spinner, container, false);
        seekbar = (SeekbarWithIntervals) view.findViewById(R.id.seekBar);
        seekbar.setIntervals(getIntervals());
        seekbar.setProgress(3);

        wheelIV = (ImageView) view.findViewById(R.id.wheelIV);

        type = getArguments().getInt(Constants.TYPE);

        wheelIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAnimating) {
                    animation = AnimationUtils.loadAnimation(getActivity(), R.anim.roatate_indefinatley);
                    wheelIV.startAnimation(animation);
                    isAnimating = true;
                    mGoogleApiClient.connect();
                }
            }
        });

        initGoogleAPIClient();

        return view;
    }

    private void initGoogleAPIClient() {
        if (mGoogleApiClient == null) {

            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(20 * 1000); // 20 seconds, in milliseconds
    }

    public List<String> getIntervals() {
        List<String> intervals = new ArrayList<>();
        String[] intervalDistances = getResources().getStringArray(R.array.distances);
        for (String value : intervalDistances) {
            intervals.add(value);
        }
        return intervals;
    }

    @Override
    public void setPresenter(SpinnerContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void startResultsActivity(FourSquareResponse fourSquareResponse) {
        Intent intent = new Intent(getActivity(), ResultsActivity.class);
        intent.putExtra(Constants.RESPONSE, fourSquareResponse);
        startActivity(intent);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        PendingResult<LocationSettingsResult> pendingResult =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient,
                        builder.build());

        pendingResult.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates locationSettingsStates = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can
                        // initialize location requests here.

                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(
                                    getActivity(),
                                    123);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way
                        // to fix the settings so we won't show the dialog.
                        break;
                }
            }
        });

        getLocation();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED ) {

            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLastLocation != null) {
                latitude = mLastLocation.getLatitude();
                longitude = mLastLocation.getLongitude();
                if(isAnimating){
                    String radius = getRadius(seekbar.getProgress());
                    mPresenter.onWheelClick(latitude,longitude,type, radius);
                }
            }else{
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(getActivity(), CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }

    }

    @Override
    public void onStop() {
        if(mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
        animation.cancel();
        mPresenter.dispose();
        super.onStop();
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        if(isAnimating){
            String radius = getRadius(seekbar.getProgress());
            mPresenter.onWheelClick(latitude,longitude,type, radius);
        }
    }

    private String getRadius(int progress){
        int distnceInMiles = 0;
        switch (progress){
            case 0:
                distnceInMiles = 5;
                break;
            case 1:
                distnceInMiles = 10;
                break;
            case 2:
                distnceInMiles = 15;
                break;
            case 3:
                distnceInMiles = 20;
                break;
            case 4:
                distnceInMiles = 25;
                break;
        }
        double distanceInMetres = distnceInMiles * 1609.344;
        return Double.toString(distanceInMetres);

    }
}
