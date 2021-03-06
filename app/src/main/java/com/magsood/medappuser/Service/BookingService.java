package com.magsood.medappuser.Service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.magsood.medappuser.Activity.Login;
import com.magsood.medappuser.Constants;
import com.magsood.medappuser.SharedPrefrense.UserPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookingService {

    UserPreferences userPreferences;
    String TAG = "RESPONSE";
    String message;

    String url;

    public void booking(Context activity, String atDay, String hospitalInfoID, ArrayList<String> servicesArray){

        userPreferences = new UserPreferences(activity);

        JSONObject jsonObject = new JSONObject();


        if(userPreferences.getChoice().equals("ho")) {
            JSONArray jsServiceArray = new JSONArray(servicesArray);
            try {
                jsonObject.put("hospitalInfoID", hospitalInfoID);
                jsonObject.put("servicesArray", jsServiceArray);
                jsonObject.put("note", "");
                jsonObject.put("atDay", atDay);
                url = Constants.RESERVITION_DOCTOR;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        else if(userPreferences.getChoice().equals("lap")){

            JSONArray jsServiceArray = new JSONArray(servicesArray);
            try {
                jsonObject.put("labID", atDay);
                jsonObject.put("labDiagnosisID", hospitalInfoID);
              jsonObject.put("services",jsServiceArray);
              url = Constants.RESERVITION_LAB;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        Log.d("reservationJson", String.valueOf(jsonObject));


        final KProgressHUD progressDialog;// Validation
        progressDialog = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("جاري ارسال الطلب")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
               url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.d(TAG, response.toString());

                        Log.e("response", String.valueOf(response));

                        try {
                            if(response.getString("success").equals("true")){

                                Toast.makeText(activity,response.getString("message"),Toast.LENGTH_SHORT).show();



                            }
                            else if(response.getString("success").equals("false")){

                                Toast.makeText(activity,response.getString("error"),Toast.LENGTH_SHORT).show();



                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                // As of f605da3 the following should work
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        Log.e("responseError",obj.toString());
                        Log.e("response",userPreferences.getToken());
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
                if (error instanceof NetworkError) {
                    message="الرجاء التاكد من الانترنت";
                } else if (error instanceof AuthFailureError) {
                    message="الرجاء التاكد من الانترنت";
                } else if (error instanceof ParseError) {
                    message="الرجاء التاكد من الانترنت";
                } else if (error instanceof NoConnectionError) {
                    message="الرجاء التاكد من الانترنت";
                } else if (error instanceof TimeoutError) {
                    message="الرجاء التاكد من الانترنت";
                }
                Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();

            }
        }) {




            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                // Removed this line if you dont need it or Use application/json
                params.put("Content-Type", "application/json");

                params.put("Authorization", "Bearer " + userPreferences.getToken());

                return params;
            }


        };
//
        VolleySingleton.getInstance(activity).addToRequestQueue(jsonObjReq);




    }
}
