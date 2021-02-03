package com.magsood.medappuser.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.magsood.medappuser.Activity.TestMapActvity;
import com.magsood.medappuser.Model.ModelSearchHospital;
import com.magsood.medappuser.R;

import java.util.ArrayList;

public class HospitalLapotaryAdapter extends RecyclerView.Adapter<HospitalLapotaryAdapter.ViewHolder> {
    private static LayoutInflater inflater = null;
    ArrayList<ModelSearchHospital> modelSearchHospitals;
    private LayoutInflater mInflater;
    Context mContext;

    public HospitalLapotaryAdapter(Context mContext, ArrayList<ModelSearchHospital> modelSearchHospitals) {
        this.modelSearchHospitals = modelSearchHospitals;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.search_item_hl, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder mHolder, int position) {
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ModelSearchHospital item = modelSearchHospitals.get(position);


        mHolder.hospitalName.setText(item.getHospital_name());
        mHolder.headerHospitalName.setText(item.getHospital_name());
        mHolder.hospitalLocation.setText(String.format("%s %s %s", item.getHospital_city(), item.getState(), item.getHospital_address()));


        mHolder.txtShowMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TestMapActvity.class);


                intent.putExtra("tradeName",item.getHospital_name());




                intent.putExtra("dropLng",item.getHospital_lng());
                intent.putExtra("dropLat",item.getHospital_lat());

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelSearchHospitals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView hospitalName,headerHospitalName,hospitalLocation,txtShowMap;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hospitalName = itemView.findViewById(R.id.hospitalName);
            headerHospitalName = itemView.findViewById(R.id.txtHospitalName);
            hospitalLocation = itemView.findViewById(R.id.location);
            txtShowMap = itemView.findViewById(R.id.txtShowMap);




        }
    }

}
