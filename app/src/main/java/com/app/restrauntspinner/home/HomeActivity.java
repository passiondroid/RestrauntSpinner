package com.app.restrauntspinner.home;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.app.restrauntspinner.BuildConfig;
import com.app.restrauntspinner.Manifest;
import com.app.restrauntspinner.R;
import com.app.restrauntspinner.util.ActivityUtils;
import com.app.restrauntspinner.util.RuntimePermissionHelper;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {

    @Inject HomePresenter homePresenter;

    private FrameLayout container;

    private RuntimePermissionHelper runtimePermissionHelper;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        container = (FrameLayout) findViewById(R.id.container);

        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if( homeFragment == null ){
            homeFragment = HomeFragment.newInstance();
        }

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),homeFragment,R.id.container);

        DaggerHomeComponent.builder()
                .homePresenterModule(new HomePresenterModule(homeFragment)).build().inject(this);

        askPermission();
    }

    private void askPermission(){
        if (Build.VERSION.SDK_INT >= 23) {
            runtimePermissionHelper = RuntimePermissionHelper.getInstance(this);
            if (runtimePermissionHelper.isPermissionAvailable(RuntimePermissionHelper.PERMISSION_ACCESS_FINE_LOCATION)) {
                //Do Nothing
            }else{
                runtimePermissionHelper.setActivity(this);
                runtimePermissionHelper.requestPermissionIfDenied(RuntimePermissionHelper.PERMISSION_ACCESS_FINE_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean permissionGranted = true;
        for(int i : grantResults){
            if( i != PackageManager.PERMISSION_GRANTED){
                permissionGranted = false;
                break;
            }
        }
        if(!permissionGranted){
            if(count > 4){
                Toast.makeText(this, R.string.location_permission_toast, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + BuildConfig.APPLICATION_ID)));
            }
            runtimePermissionHelper.requestPermissionIfDenied(RuntimePermissionHelper.PERMISSION_ACCESS_FINE_LOCATION);
        }
        count++;
    }
}
