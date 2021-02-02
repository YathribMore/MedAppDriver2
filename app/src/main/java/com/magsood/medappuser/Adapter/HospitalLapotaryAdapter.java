package com.magsood.medappuser.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.magsood.medappuser.Model.ModelSearchHospital;
import com.magsood.medappuser.Model.ModelSearchPharmacy;
import com.magsood.medappuser.R;

import java.util.ArrayList;

public class HospitalLapotaryAdapter extends RecyclerView.Adapter<HospitalLapotaryAdapter.ViewHolder> {
    private static LayoutInflater inflater = null;
    ArrayList<ModelSearchHospital> newsPaperArrayList;
    private LayoutInflater mInflater;
    Context mContext;

    public HospitalLapotaryAdapter(Context mContext, ArrayList<ModelSearchHospital> newsPaperArrayList) {
        this.newsPaperArrayList = newsPaperArrayList;
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
        final ModelSearchHospital item = newsPaperArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return newsPaperArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

}
