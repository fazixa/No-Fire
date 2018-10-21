package com.nofire.network;

import android.content.Context;
import android.support.annotation.Nullable;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class RequestManager {

    private RequestQueue requestQueue;
    public static final String DOMAIN = "";


    public RequestManager(Context context) {
        if (this.requestQueue == null)
            requestQueue = Volley.newRequestQueue(context);
    }

    @SuppressWarnings("unchecked")
    public void addRequest(Request request, String tag) {
        request.addMarker(tag);
        this.requestQueue.add(request);
    }

    public void cancelAll(String tag) {
        requestQueue.cancelAll(tag);
    }

    public Cache getCache() {
        return requestQueue.getCache();
    }

    public int getSequenceNumber() {
        return requestQueue.getSequenceNumber();
    }

    @Nullable
    public String getResponseString(byte[] bytes){
        if (bytes == null || bytes.length == 0)
            return null;
        try {
            return new String(bytes , "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public JSONObject getResponseJsonObject(byte[] bytes){
        String response = getResponseString(bytes);
        try {
            return response == null ? null : new JSONObject(response);
        } catch (JSONException e){
            return null;
        }
    }

    @Nullable
    public JSONArray getResponseJsonArray(byte[] bytes){
        String response = getResponseString(bytes);
        try {
            return response == null ? null : new JSONArray(response);
        } catch (JSONException e){
            return null;
        }
    }

}
