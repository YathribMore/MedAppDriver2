package com.magsood.medappuser.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.magsood.medappuser.Activity.BookingPosition;
import com.magsood.medappuser.Model.ModelSearchPharmacy;
import com.magsood.medappuser.Model.ModelServices;
import com.magsood.medappuser.R;
import com.magsood.medappuser.Service.BookingService;
import com.magsood.medappuser.SharedPrefrense.UserPreferences;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AdapterServicess extends RecyclerView.Adapter<AdapterServicess.ViewHolder> {
    private static LayoutInflater inflater = null;
    List<ModelServices> modelServices;
    private LayoutInflater mInflater;
    Context mContext;
    ArrayList<String>service_check_array;


    RelativeLayout bookingButton;
    TextView selectedDays;
    String docInfoID;
    Activity activity;
    String lab_id;
    String labDiagnosisID;
    UserPreferences userPreferences;


    public AdapterServicess(Context mContext, List<ModelServices> modelServices, RelativeLayout bookingButton, TextView selectedDays, String docInfoID, Activity activity) {
        this.modelServices = modelServices;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        service_check_array = new ArrayList<>();
        this.bookingButton = bookingButton;
        this.selectedDays= selectedDays;
        this.docInfoID = docInfoID;
        this.activity = activity;
        userPreferences = new UserPreferences(mContext);




    }

    public AdapterServicess(Context mContext, List<ModelServices> modelServices, RelativeLayout bookingButton, String lab_id, String labDiagnosisID, Activity activity) {
        this.modelServices = modelServices;
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        service_check_array = new ArrayList<>();
        this.bookingButton = bookingButton;
        this.activity = activity;
        this.lab_id = lab_id;
        this.labDiagnosisID = labDiagnosisID;

        userPreferences = new UserPreferences(mContext);
    }

    @NonNull
    @Override
    public AdapterServicess.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_services, parent, false);

        return new AdapterServicess.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(AdapterServicess.ViewHolder mHolder, int position) {
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ModelServices item = modelServices.get(position);

Log.d("item_servicese",item.getName()+item.getService_id());
        mHolder.name.setText(item.getName());
        mHolder.price.setText(item.getPrice());







mHolder.service_check.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        if(mHolder.service_check.isChecked()){

            service_check_array.add(item.getService_id());
            Log.d("service_check",service_check_array.toString());


        }
        if(!mHolder.service_check.isChecked()){

            service_check_array.remove(item.getService_id());
            Log.d("service_check",service_check_array.toString());
        }

    }
});


bookingButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        if(userPreferences.getChoice().equals("ho")){

        BookingService bookingService = new BookingService();

        bookingService.booking(mContext,selectedDays.getText().toString(),docInfoID,service_check_array);}



        else if (userPreferences.getChoice().equals("lap")){


            BookingService bookingService = new BookingService();

            bookingService.booking(mContext,lab_id,labDiagnosisID,service_check_array);

        }

    }
});



    }

    @Override
    public int getItemCount() {
        return modelServices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name ,price ;
        CheckBox service_check;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);

            service_check = itemView.findViewById(R.id.service_check);
            bookingButton = activity.findViewById(R.id.booking_button);



        }
    }

}

