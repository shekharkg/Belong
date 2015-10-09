package com.shekharkg.belong.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.shekharkg.belong.beans.Data;

import org.apache.http.Header;

/**
 * Created by ShekharKG on 10/9/2015.
 */
public class NetworkCLient {

  public static final String FIRST_API = "http://api.buyingiq.com/v1/search";

  public void getRideAvailability(Context context, final CallBack callBack, final RequestParams requestParams) {

    AsyncHttpClient client = new AsyncHttpClient();
    requestParams.add("tags", "mobiles");
    requestParams.add("facet", "1");
    requestParams.add("page", "1");

    client.get(context, FIRST_API, requestParams, new AsyncHttpResponseHandler() {

      @Override
      public void onFailure(int statusCode, Header[] headers, byte[] responseBody,
                            Throwable throwable) {
        try {
          String responseJson = new String(responseBody);
          Log.e("Belong -> onFailure", responseJson);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      @Override
      public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        if (statusCode == 200) {
          try {
            String responseJson = new String(responseBody);
            Log.e("Belong -> onSuccess", responseJson);
            Data data = new Gson().fromJson(responseJson, Data.class);
            callBack.successOperation(data);
          } catch (Exception e) {
            e.printStackTrace();
            onFailure(statusCode, headers, responseBody, null);
          }
        }
      }
    });
  }

}
