package com.magsood.medappuser.Service;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.magsood.medappuser.Adapter.AdapterSearchResult;
import com.magsood.medappuser.Adapter.HospitalLapotaryAdapter;
import com.magsood.medappuser.Constants;
import com.magsood.medappuser.Model.ModelSearchHospital;
import com.magsood.medappuser.Model.ModelSearchLap;
import com.magsood.medappuser.Model.ModelSearchPharmacy;
import com.magsood.medappuser.Model.ModelServices;
import com.magsood.medappuser.R;
import com.magsood.medappuser.SharedPrefrense.UserPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SearchService {
    UserPreferences userPreferences;
    RecyclerView recyclerView;
    ArrayList<ModelSearchPharmacy> modelSearchPharmacyArrayList;
    AdapterSearchResult adapterSearchResult;

    ArrayList<ModelSearchHospital> modelSearchHospitalArrayList;

    List<ModelServices> modelServices;


    ArrayList<ModelSearchLap> modelSearchLapArrayList;
    HospitalLapotaryAdapter adapterSearchHospitalResult;
    String message;



    String TAG = "RESPONSE";

    public void searchPharmacy(Activity activity, String searchString) {



        userPreferences = new UserPreferences(activity );

        recyclerView = activity.findViewById(R.id.recycler);




        Map<String, String> params = new HashMap<>();
        params.put("searchString", searchString);

        final KProgressHUD progressDialog;// Validation
        progressDialog = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("الرجاء الانتظار")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Constants.SEARCH_URL+"?token="+userPreferences.getToken(), new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();


                        try {
                            Log.e("response",response.get("data").toString());
                            JSONArray jsonArray = response.getJSONArray("data");

                            Log.e("response",jsonArray.toString());

                        modelSearchPharmacyArrayList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            ModelSearchPharmacy modelSearchPharmacy = new ModelSearchPharmacy();
                            modelSearchPharmacy.setId(data.getString("id"));
                            modelSearchPharmacy.setPharmacyID(data.getString("pharmacyID"));
                            modelSearchPharmacy.setMedicineID(data.getString("medicineID"));
                            modelSearchPharmacy.setTradeName(data.getString("tradeName"));
                            modelSearchPharmacy.setPublicName(data.getString("publicName"));
                            modelSearchPharmacy.setPrice(data.getString("price"));
                            modelSearchPharmacy.setPharmacyName(data.getString("name"));
                            modelSearchPharmacy.setDescription(data.getString("description"));
                            modelSearchPharmacy.setCompnayName(data.getString("compnayName"));
                            modelSearchPharmacy.setPhoneNumber(data.getString("phoneNumber"));
                            modelSearchPharmacy.setLocation(data.getString("location"));
                            modelSearchPharmacy.setLng(data.getString("lng"));
                            modelSearchPharmacy.setLat(data.getString("lat"));
                            modelSearchPharmacy.setState(data.getString("state"));


                            modelSearchPharmacyArrayList.add(modelSearchPharmacy);
                        }
                        adapterSearchResult = new AdapterSearchResult(activity, modelSearchPharmacyArrayList);
                        recyclerView.setAdapter(adapterSearchResult);
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
                        message = "المنتج غير موجود";
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
                    message = "غير موجود";
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
                return params;
            }


        };
//
        VolleySingleton.getInstance(activity).addToRequestQueue(jsonObjReq);

    }



//hospital Search

    public void searchHospital(Activity activity, String searchString) {



        userPreferences = new UserPreferences(activity );

        recyclerView = activity.findViewById(R.id.recycler);




//        Map<String, String> params = new HashMap<>();
//        params.put("searchString", searchString);

        final KProgressHUD progressDialog;// Validation
        progressDialog = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("الرجاء الانتظار")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Constants.SEARCH_DOCTOR_URL+"?search="+searchString,null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();


                        try {
                            Log.e("response",response.get("data").toString());
                            JSONArray jsonArray = response.getJSONArray("data");

                            Log.e("response",jsonArray.toString());


                            modelSearchHospitalArrayList = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                ModelSearchHospital modelSearchHospital = new ModelSearchHospital();

                                JSONObject doctor = data.getJSONObject("doctor");
                                JSONObject hospitalInfo = data.getJSONObject("hospital");
                                JSONObject doctorInfo = data.getJSONObject("doc_info");

                                modelSearchHospital.setDocID(doctorInfo.getString("id"));
                                modelSearchHospital.setPrice(doctorInfo.getString("interviewPrice"));
                                modelSearchHospital.setSpecialization(doctorInfo.getString("specialization"));



                                modelServices = new ArrayList<>();
                                JSONArray servicess = hospitalInfo.getJSONArray("hospital_services");

                                for (int j=0 ; j<servicess.length();j++){

                                    JSONObject serviceData = servicess.getJSONObject(j);
                                    ModelServices services = new ModelServices();
                                    JSONObject info = serviceData.getJSONObject("services");
                                    services.setService_id(info.getString("id"));
                                    services.setName(info.getString("name"));
                                    services.setPrice(info.getString("price"));
                                    services.setNote(info.getString("note"));
                                    modelServices.add(services);
                                }

                                modelSearchHospital.setModelServices(modelServices);





                                modelSearchHospital.setDoctor_name(doctor.getString("fullName"));
                                modelSearchHospital.setDoctor_phone(doctor.getString("phone"));
                                Log.d("response",doctor.getString("fullName"));
                                modelSearchHospital.setHospital_address(hospitalInfo.getString("address"));
                                modelSearchHospital.setHospital_city(hospitalInfo.getString("city"));
                                modelSearchHospital.setState(hospitalInfo.getString("state"));
                                modelSearchHospital.setHospital_lat(hospitalInfo.getString("lat"));
                                modelSearchHospital.setHospital_lng(hospitalInfo.getString("lng"));
                                modelSearchHospital.setHospital_name(hospitalInfo.getString("name"));

                                JSONObject doc_schedule  =  data.getJSONObject("doc_schedule");
                                Log.d("doc_schedule",doc_schedule.toString());
                                Iterator<?> keys = doc_schedule.keys();
                                ArrayList<String>days = new ArrayList<>();

 for (int temp=0 ;temp<9;temp++) {
     if (keys.hasNext()) {

         String key = (String) keys.next();

         Log.d("doc_schedule2", String.valueOf(doc_schedule.get(key)));
         if(temp>1) {
             if (doc_schedule.get(key).equals(0)) {
                 days.add(key);
                 Log.d("doc_schedule3", String.valueOf(doc_schedule.get(key)));


                 // do what ever you want with the JSONObject.....
             }
         }

     }
 }
                                Log.d("daysUser",days.toString());
                                modelSearchHospital.setDays(days);


                                modelSearchHospitalArrayList.add(modelSearchHospital);
                            }
                            adapterSearchHospitalResult = new HospitalLapotaryAdapter(activity, modelSearchHospitalArrayList);
                            recyclerView.setAdapter(adapterSearchHospitalResult);
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
                        Log.e("responseError",res.toString());
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        Log.e("responseError",obj.toString());
                        Log.e("response",userPreferences.getToken());
                        message = "المنتج غير موجود";
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
                if (error instanceof NetworkError) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        Log.e("responseError",obj.toString());
                        Log.e("response",userPreferences.getToken());
                        message = "المنتج غير موجود";
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }

                    message="الرجاء التاكد من الانترنت";
                } else if (error instanceof AuthFailureError) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        Log.e("responseError",obj.toString());
                        Log.e("response",userPreferences.getToken());
                        message = "المنتج غير موجود";
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                    message = "غير موجود";
                } else if (error instanceof ParseError) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        Log.e("responseError",obj.toString());
                        Log.e("response",userPreferences.getToken());
                        message = "المنتج غير موجود";
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                    message="الرجاء التاكد من الانترنت";
                } else if (error instanceof NoConnectionError) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        Log.e("responseError",obj.toString());
                        Log.e("response",userPreferences.getToken());
                        message = "المنتج غير موجود";
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                    message="الرجاء التاكد من الانترنت";
                } else if (error instanceof TimeoutError) {
                    //                        String res = new String(response.data,
//                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
//                        // Now you can use any deserializer to make sense of data
//                        JSONObject obj = new JSONObject(res);
                    Log.e("responseError",error.toString());
                    Log.e("response",userPreferences.getToken());
                    message = "المنتج غير موجود";
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


        {
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));



        }


//
        VolleySingleton.getInstance(activity).addToRequestQueue(jsonObjReq);

    }




    public void searchLap(Activity activity, String searchString) {



        userPreferences = new UserPreferences(activity );

        recyclerView = activity.findViewById(R.id.recycler);




//        Map<String, String> params = new HashMap<>();
//        params.put("searchString", searchString);

        final KProgressHUD progressDialog;// Validation
        progressDialog = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("الرجاء الانتظار")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Constants.SEARCH_LAP_URL+"?search="+searchString,null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();


                        try {
                            Log.e("response",response.get("data").toString());
                            JSONArray jsonArray = response.getJSONArray("data");

                            Log.e("response",jsonArray.toString());

                            modelSearchLapArrayList = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                ModelSearchLap modelSearchLap = new ModelSearchLap();
                                modelSearchLap.setLapID(data.getString("labID"));
                                modelSearchLap.setPrice(data.getString("price"));
                                JSONObject lapInfo = data.getJSONObject("lab");
                                modelSearchLap.setLabDiagnosisID(data.getString("id"));


                                modelSearchLap.setLap_name(lapInfo.getString("name"));

                                modelSearchLap.setAddress(lapInfo.getString("address"));
                                modelSearchLap.setCity(lapInfo.getString("city"));
                                modelSearchLap.setState(lapInfo.getString("state"));
                                modelSearchLap.setLapPhone(lapInfo.getString("phone"));
                                modelServices = new ArrayList<>();
                                JSONArray lapService = data.getJSONArray("lab_services");

                                for (int j=0 ; j<lapService.length();j++){

                                    JSONObject serviceData = lapService.getJSONObject(j);
                                    ModelServices services = new ModelServices();

                                    services.setService_id(serviceData.getString("labID"));
                                    services.setName(serviceData.getString("name"));
                                    services.setPrice(serviceData.getString("price"));
                                    services.setNote(serviceData.getString("note"));
                                    modelServices.add(services);
                                }

                                modelSearchLap.setModelServices(modelServices);




                                modelSearchLapArrayList.add(modelSearchLap);
                            }
                            adapterSearchHospitalResult = new HospitalLapotaryAdapter(activity, modelSearchLapArrayList);
                            recyclerView.setAdapter(adapterSearchHospitalResult);
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
                        Log.e("responseError",res.toString());
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        Log.e("responseError",obj.toString());
                        Log.e("response",userPreferences.getToken());
                        message = "المنتج غير موجود";
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
                if (error instanceof NetworkError) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        Log.e("responseError",obj.toString());
                        Log.e("response",userPreferences.getToken());
                        message = "المنتج غير موجود";
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }

                    message="الرجاء التاكد من الانترنت";
                } else if (error instanceof AuthFailureError) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        Log.e("responseError",obj.toString());
                        Log.e("response",userPreferences.getToken());
                        message = "المنتج غير موجود";
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                    message = "غير موجود";
                } else if (error instanceof ParseError) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        Log.e("responseError",obj.toString());
                        Log.e("response",userPreferences.getToken());
                        message = "المنتج غير موجود";
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                    message="الرجاء التاكد من الانترنت";
                } else if (error instanceof NoConnectionError) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        Log.e("responseError",obj.toString());
                        Log.e("response",userPreferences.getToken());
                        message = "المنتج غير موجود";
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                    message="الرجاء التاكد من الانترنت";
                } else if (error instanceof TimeoutError) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        Log.e("responseError",obj.toString());
                        Log.e("response",userPreferences.getToken());
                        message = "المنتج غير موجود";
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                    Log.e("responseError",error.toString());
                    Log.e("response",userPreferences.getToken());
                    message = "المنتج غير موجود";
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


        {
            jsonObjReq.setRetryPolicy(new RetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return 50000;
                }

                @Override
                public int getCurrentRetryCount() {
                    return 50000;
                }

                @Override
                public void retry(VolleyError error) throws VolleyError {

                }
            });


        }

        {
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));



        }

//
        VolleySingleton.getInstance(activity).addToRequestQueue(jsonObjReq);

    }



    public ArrayList<String> getMedicine(Activity activity) {

        userPreferences = new UserPreferences(activity );

        recyclerView = activity.findViewById(R.id.recycler);


        ArrayList<String> medicine = new ArrayList<>();


        final KProgressHUD progressDialog;// Validation
        progressDialog = KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("الرجاء الانتظار")
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Constants.GET_MEDICINE+"?token="+userPreferences.getToken(), null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.e("response",response.toString());

                        try {


                            JSONArray data = response.getJSONArray("data");

                            for (int i = 0 ;i<data.length();i++){
                                JSONObject jsonObject = data.optJSONObject(i);
                                medicine.add(jsonObject.getString("tradeName"));
                                medicine.add(jsonObject.getString("publicName"));



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
                } else if (error instanceof ServerError) {
                    message = "الخادم غير موجود";
                } else if (error instanceof AuthFailureError) {
                    message = "الرجاء تسجيل الدخول";
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
                return params;
            }


        };


        {
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));



        }
//
        VolleySingleton.getInstance(activity).addToRequestQueue(jsonObjReq);

        return medicine;
    }

}
