package com.bestsoft32.mapclusters.activities;

import android.os.Bundle;
import android.util.Log;

import com.bestsoft32.mapclusters.R;
import com.bestsoft32.mapclusters.model.LocationDataItem;
import com.bestsoft32.mapclusters.model.Person;
import com.bestsoft32.mapclusters.model.Response;
import com.bestsoft32.mapclusters.server.Constants;
import com.bestsoft32.mapclusters.server.NetworkManager;
import com.bestsoft32.mapclusters.server.Params;
import com.bestsoft32.mapclusters.server.VolleyListener;
import com.bestsoft32.mapclusters.utils.InternetCheck;
import com.bestsoft32.mapclusters.utils.Progress;
import com.bestsoft32.mapclusters.utils.SnackAlert;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.maps.android.clustering.ClusterManager;


public class MainActivity extends BaseGoogleMapsActivity implements VolleyListener {

    private ClusterManager<Person> mClusterManager;
    private Progress progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress = new Progress(MainActivity.this);
        progress.showProgressDialog();

    }

    //network request to get data from api
    private void networkRequestion() {
        if (!InternetCheck.isNetworkAvailable(MainActivity.this)) {
            new SnackAlert(MainActivity.this, getString(R.string.no_internet_connection)).error();
        } else {
            NetworkManager.getInstance().post(Constants.baseUrl, Params.showData(Constants.userId, Constants.apiKey), MainActivity.this);
        }

    }

    //request sent got success response
    @Override
    public void onVolleySuccess(String url, String response) {
        if (url.equalsIgnoreCase(Constants.baseUrl)) {
            //gson for parsing json got from internet
            Gson gson = new Gson();
            Response responseData = gson.fromJson(response, Response.class);
            Log.d("JsonTestData", responseData.getLocationData().get(0).getCountry());

            for (LocationDataItem dataItem : responseData.getLocationData()) {
                Double lat = Double.valueOf(dataItem.getLatitude());
                Double lng = Double.valueOf(dataItem.getLongitude());
                Person item = new Person(lat, lng, dataItem.getName());
                mClusterManager.addItem(item);
            }

            //cancel progress after data load completed
            progress.cancelProgressDialog();

        }
    }


    //request sent got error response
    @Override
    public void onVolleyError(String url, String error) {
        new SnackAlert(MainActivity.this, error).error();
    }

    //request sent got failed response
    @Override
    public void onVolleyFailed(String url, String error) {
        new SnackAlert(MainActivity.this, error).error();
    }

    //request sent but no internet
    @Override
    public void onVolleyNoConnection() {
        new SnackAlert(MainActivity.this, getString(R.string.no_internet_connection)).error();
    }

    // onMapReady
    @Override
    public void onMapReady(GoogleMap googleMap) {
        setupMap(googleMap);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-26.167616, 28.079329), 10));

        mClusterManager = new ClusterManager<>(this, googleMap);
        googleMap.setOnCameraIdleListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);
        googleMap.setOnInfoWindowClickListener(mClusterManager);
        networkRequestion();
        mClusterManager.cluster();
    }
}
