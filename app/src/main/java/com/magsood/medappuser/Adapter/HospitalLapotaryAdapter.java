package com.magsood.medappuser.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.magsood.medappuser.Activity.BookingPosition;
import com.magsood.medappuser.Activity.TestMapActvity;
import com.magsood.medappuser.Model.ModelSearchHospital;
import com.magsood.medappuser.Model.ModelSearchLap;
import com.magsood.medappuser.R;
import com.magsood.medappuser.SharedPrefrense.UserPreferences;

import java.io.Serializable;
import java.util.ArrayList;

public class HospitalLapotaryAdapter extends RecyclerView.Adapter<HospitalLapotaryAdapter.ViewHolder> {
    private static LayoutInflater inflater = null;
    ArrayList<ModelSearchHospital> modelSearchHospitals;

    ArrayList<ModelSearchLap> modelSearchLaps;

    private LayoutInflater mInflater;
    UserPreferences userPreferences;
    Context mContext;

    public HospitalLapotaryAdapter(Context mContext, ArrayList<ModelSearchHospital> modelSearchHospitals) {
        this.modelSearchHospitals = modelSearchHospitals;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        userPreferences= new UserPreferences(mContext);

    }

    public HospitalLapotaryAdapter(Activity mContext, ArrayList<ModelSearchLap> modelSearchLapArrayList) {

        this.modelSearchLaps = modelSearchLapArrayList;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        userPreferences= new UserPreferences(mContext);

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.search_item_hl, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder mHolder, int position) {


        if(userPreferences.getChoice().equals("ho")) {

            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final ModelSearchHospital item = modelSearchHospitals.get(position);

            mHolder.specialization.setText(item.getSpecialization());
            mHolder.hospitalName.setText(item.getHospital_name());
            mHolder.hospitalLocation.setText(String.format("%s %s %s", item.getHospital_city(), item.getState(), item.getHospital_address()));


            mHolder.txtShowMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, TestMapActvity.class);


                    intent.putExtra("tradeName", item.getHospital_name());


                    intent.putExtra("dropLng", item.getHospital_lng());
                    intent.putExtra("dropLat", item.getHospital_lat());

                    mContext.startActivity(intent);
                }
            });


            mHolder.booking.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, BookingPosition.class);
                    intent.putExtra("hospital_name",item.getHospital_name());
                    intent.putExtra("doctor_name",item.getDoctor_name());
                    intent.putExtra("docInfoID",item.getDocID());

                  //  intent.putExtra("services",item.getModelServices().toString());

                    String arrayAsString = new Gson().toJson(item.getModelServices());
                    intent.putExtra("services", arrayAsString);

                    intent.putExtra("days",item.getDays());

                    mContext.startActivity(intent);




                }
            });
        }


        else  if(userPreferences.getChoice().equals("lap")){
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final ModelSearchLap item = modelSearchLaps.get(position);



            mHolder.specialization.setText(item.getLap_name());
            mHolder.hospitalName.setText(item.getLap_name());
            mHolder.hospitalLocation.setText(String.format("%s %s %s", item.getCity(), item.getState(), item.getAddress()));

mHolder.booking.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {



        Intent intent = new Intent(mContext, BookingPosition.class);
        intent.putExtra("lab_name",item.getLap_name());

        intent.putExtra("lab_id",item.getLapID());
        intent.putExtra("labDiagnosisID",item.getLabDiagnosisID());

        //  intent.putExtra("services",item.getModelServices().toString());

        String arrayAsString = new Gson().toJson(item.getModelServices());
        intent.putExtra("services", arrayAsString);



        mContext.startActivity(intent);

    }
});







        }

    }

    @Override
    public int getItemCount() {
        int size = 0;
        if(userPreferences.getChoice().equals("ho"))
         size= modelSearchHospitals.size();
        if(userPreferences.getChoice().equals("lap"))
            size= modelSearchLaps.size();

        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView specialization, hospitalName,hospitalLocation,txtShowMap;
        LinearLayout booking;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            specialization = itemView.findViewById(R.id.specialization);
            hospitalName = itemView.findViewById(R.id.txtHospitalName);
            hospitalLocation = itemView.findViewById(R.id.location);
            txtShowMap = itemView.findViewById(R.id.txtShowMap);
            booking = itemView.findViewById(R.id.booking);




        }
    }

}
