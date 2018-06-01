package com.nayra.maraiina.views;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.fastaccess.permission.base.PermissionHelper;
import com.fastaccess.permission.base.callback.OnPermissionCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nayra.maraiina.Constants;
import com.nayra.maraiina.LocationTrack;
import com.nayra.maraiina.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends AppCompatActivity implements OnPermissionCallback, OnMapReadyCallback
        , GoogleMap.OnMapClickListener {

    private GoogleMap map;
    private LocationTrack locationTrack;
    private double latitude = 23.4241;
    private double longitude = 53.8478;
    private PermissionHelper permissionHelper;

    final String[] PERMISSION = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ButterKnife.bind(this);

        initMap();
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    private void getCurrentLocation() {
        permissionHelper = PermissionHelper.getInstance(this);
        permissionHelper.setForceAccepting(false).request(PERMISSION);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapClickListener(this);
        getCurrentLocation();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        latitude = latLng.latitude;
        longitude = latLng.longitude;

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        map.clear();
        //map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        map.addMarker(markerOptions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void onPermissionGranted(@NonNull String[] permissionName) {
        Log.e("nahmed", "onPermissionGranted");
        locationTrack = new LocationTrack(this);

        latitude = locationTrack.getLatitude();
        longitude = locationTrack.getLongitude();
        setMarkerLocation();
    }

    @Override
    public void onPermissionDeclined(@NonNull String[] permissionName) {
        Log.e("nahmed", "onPermissionDeclined");
        cancel();
    }


    @Override
    public void onPermissionPreGranted(@NonNull String permissionsName) {
        Log.e("nahmed", "onPermissionPreGranted");
        locationTrack = new LocationTrack(this);

        latitude = locationTrack.getLatitude();
        longitude = locationTrack.getLongitude();
        setMarkerLocation();

    }

    @Override
    public void onPermissionNeedExplanation(@NonNull String permissionName) {
        Log.e("nahmed", "onPermissionNeedExplanation");

    }

    @Override
    public void onPermissionReallyDeclined(@NonNull String permissionName) {
        Log.e("nahmed", "onPermissionReallyDeclined");

    }

    @Override
    public void onNoPermissionNeeded() {
        Log.e("nahmed", "onNoPermissionNeeded");
        locationTrack = new LocationTrack(this);

        latitude = locationTrack.getLatitude();
        longitude = locationTrack.getLongitude();
        setMarkerLocation();
    }

    private void setMarkerLocation() {
        if (map != null) {
            LatLng latLng = new LatLng(latitude, longitude);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            map.clear();
            //map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));

            map.addMarker(markerOptions);
        }
    }

    @OnClick(R.id.btDone)
    public void done() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(Constants.LATITUDE, latitude);
        returnIntent.putExtra(Constants.LONGITUDE, longitude);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    private void cancel() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        cancel();
    }
}
