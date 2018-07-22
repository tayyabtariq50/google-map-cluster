package com.bestsoft32.mapclusters.server;


public interface VolleyListener<T> {
    public void onVolleySuccess(String url, String response);

    public void onVolleyError(String url, String error);

    public void onVolleyFailed(String url, String error);

    public void onVolleyNoConnection();



}