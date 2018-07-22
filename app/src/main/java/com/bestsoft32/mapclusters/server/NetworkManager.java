package com.bestsoft32.mapclusters.server;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bestsoft32.mapclusters.MyApplication;
import com.bestsoft32.mapclusters.R;
import com.bestsoft32.ttvolleylib.TTVolleyRX;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NetworkManager {
    private static final String TAG = "NetworkManager";
    //for Volley API
    public static RequestQueue requestQueue;
    private static NetworkManager instance = null;
    private final Context mContext;
    private Boolean noInternet = false;

    private NetworkManager(Context context) {
        mContext = context;
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized NetworkManager getInstance(Context context) {
        if (null == instance)
            instance = new NetworkManager(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized NetworkManager getInstance() {
        if (null == instance) {
            throw new IllegalStateException(NetworkManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }


    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void post(final String url, final Map<String, String> params, final VolleyListener<String> listener) {
        if (!isNetworkAvailable(mContext)) {
            listener.onVolleyNoConnection();
            noInternet = true;
        } else {
            noInternet = false;
        }
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonResponse = null;
                        try {
                            jsonResponse = new JSONObject(response);
                            listener.onVolleySuccess(url, response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onVolleyError(url, MyApplication.getMyAppContext().getString(R.string.response_msg_server_error));
                        }

                        // Result handling
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (!noInternet) {

                            if (null != error.networkResponse) {
                                Log.d(TAG + ": ", "Error Response code: " + error.networkResponse.statusCode);

                                listener.onVolleyFailed(url, MyApplication.getMyAppContext().getString(R.string.response_msg_server_error));
                            } else {
                                listener.onVolleyFailed(url, MyApplication.getMyAppContext().getString(R.string.response_msg_server_down));
                            }
                        }
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        postRequest.setShouldCache(false);
        postRequest.setRetryPolicy(new DefaultRetryPolicy(35000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }

}
