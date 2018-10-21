package com.nofire;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.nofire.Item.Tweet;
import com.nofire.adapter.TweetsAdapter;
import com.nofire.network.RequestManager;

import java.util.ArrayList;
import java.util.List;

public class TwitActivity extends AppCompatActivity {

    private Context mContext;
    private RequestManager mRequestManager;
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mActivitiesRecyclerView;
    private ArrayList<Tweet> mTweets = new ArrayList<>();

    public static int REQUEST_LOCATION_SETTINGS = 1;
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twits);
        mContext = getApplicationContext();

        mActivitiesRecyclerView = findViewById(R.id.rec_twit);
        mTweets.add(new Tweet("California is under fire"));
        mTweets.add(new Tweet("fire has endangered different animals in California jungles"));
        mTweets.add(new Tweet("In california and In Los Angles we have seen fire rises "));
        mTweets.add(new Tweet("huge amount of smoke has been seen above the sky in New York"));
        mTweets.add(new Tweet("Arizona jungles are on fire"));
        mTweets.add(new Tweet("Los vegas major trade center is on fire"));
        mTweets.add(new Tweet("Boston in Northeastern university library is on fire"));

        TweetsAdapter adapter = new TweetsAdapter(mTweets);
        mActivitiesRecyclerView.setAdapter(adapter);
        mActivitiesRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
//        if (mCurrentLocation == null)
//            initLocation();
    }

    private void initLocation(){
        if (hasLocationPermission())
            init();
        else
            requestLocationPermission();
    }

    private void init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);
        mSettingsClient = LocationServices.getSettingsClient(mContext);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                mCurrentLocation = locationResult.getLastLocation();
                requestData();
            }
        };


        mLocationRequest = new LocationRequest();
        long UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
        long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 2000;
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
        startLocationUpdates();
    }

    private void startLocationUpdates() {
        mSettingsClient
                .checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(locationSettingsResponse -> {
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestLocationPermission();
                        return;
                    }
                    mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                    requestData();
                })
                .addOnFailureListener(e -> {

                    int statusCode = ((ApiException) e).getStatusCode();
                    switch (statusCode) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            Log.i("onFailure", "Location settings are not satisfied. Attempting to upgrade " +
                                    "location settings ");
                            try {
                                ResolvableApiException rae = (ResolvableApiException) e;
                                rae.startResolutionForResult(this, REQUEST_LOCATION_SETTINGS);
                            } catch (IntentSender.SendIntentException sie) {
                                Log.i("onFailure2", "PendingIntent unable to execute request.");
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            String errorMessage = "Location settings are inadequate, and cannot be " +
                                    "fixed here. Fix in Settings.";

                            Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();
                    }

                    requestData();

                });
    }

    private boolean hasLocationPermission() {
        String accessCoarseLocation = Manifest.permission.ACCESS_COARSE_LOCATION;
        String accessFineLocation = Manifest.permission.ACCESS_FINE_LOCATION;
        int accessCoarseLocationRes = ContextCompat.checkSelfPermission(mContext, accessCoarseLocation);
        int accessFineLocationRes = ContextCompat.checkSelfPermission(mContext, accessFineLocation);
        return (accessCoarseLocationRes == PackageManager.PERMISSION_GRANTED &&
                accessFineLocationRes == PackageManager.PERMISSION_GRANTED
        );
    }

    private void requestLocationPermission() {

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        init();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void requestData(){
        if (mCurrentLocation == null)
            return;
        requestTweets();
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        mCurrentLocation = null;
    }

    private void requestTweets(){
        String location = String.valueOf(mCurrentLocation.getLatitude()) + "," + String.valueOf(mCurrentLocation.getLongitude());
        String url = RequestManager.DOMAIN + "/nearby/" + location;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {},
                error -> {});
        mRequestManager.addRequest(request,TAG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (REQUEST_LOCATION_SETTINGS) {
            case 1:
                switch (resultCode) {
                    case RESULT_OK:
                        Log.i("RESULT_OK", "OK");
                        startLocationUpdates();
                        break;
                    case RESULT_CANCELED:
                        break;
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mRequestManager != null)
            mRequestManager.cancelAll(TAG);
    }

    @Override
    protected void onDestroy() {
        if (mContext != null) mContext = null;
        if (mRequestManager != null) mRequestManager = null;
        if (mActivitiesRecyclerView != null) mActivitiesRecyclerView = null;
        if (mTweets != null) mTweets = null;

        if (mFusedLocationClient != null) mFusedLocationClient = null;
        if (mSettingsClient != null) mSettingsClient = null;
        if (mLocationRequest != null) mLocationRequest = null;
        if (mLocationSettingsRequest != null) mLocationSettingsRequest = null;
        if (mLocationCallback != null) mLocationCallback = null;
        if (mCurrentLocation != null) mCurrentLocation = null;
        super.onDestroy();
    }

}
