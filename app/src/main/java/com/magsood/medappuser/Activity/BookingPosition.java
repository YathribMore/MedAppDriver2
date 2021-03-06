package com.magsood.medappuser.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.magsood.medappuser.Adapter.AdapterServicess;
import com.magsood.medappuser.Model.ModelServices;
import com.magsood.medappuser.R;
import com.magsood.medappuser.Service.BookingService;
import com.magsood.medappuser.SharedPrefrense.UserPreferences;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class BookingPosition extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener {
    EditText hospital_name,doctor_name;
    RelativeLayout bookingButton;

    private Button mPickDateButton;
    DatePickerDialog datePickerDialog ;
    AdapterServicess adapterServicess;
    TextView selectedDays;
    RecyclerView services ;
    String docInfoID;
    String lab_id;
    UserPreferences userPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_position);
      init();


      if(userPreferences.getChoice().equals("ho")){
        mPickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = DatePickerDialog.newInstance(BookingPosition.this, year, month, day);

                datePickerDialog.setThemeDark(false);

                datePickerDialog.showYearPickerFirst(false);

                datePickerDialog.setAccentColor(Color.parseColor("#0072BA"));

                datePickerDialog.setTitle("Select Date From DatePickerDialog");

                datePickerDialog.show(getSupportFragmentManager(), "DatePickerDialog");
//                ArrayList<String> days = (ArrayList<String>) getIntent().getSerializableExtra("days");
//                Calendar[] disabledDays = new Calendar[days.size()];








//
//
//                for (int i = 0;i<days.size();i++){
//                    OpeningHours.Period.OpenClose.DayOfWeek d = sdf.parse(bankHolidays[i]);
//
//                    disabledDays[i]=days.get(i).;
//
//
//
//                }
//                datePickerDialog.setDisabledDays(Calendar [] days);
            }
        });}
//      bookingButton.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//
//
//
//            booking();
//              //methoud for reservation
//
//
//
//
//          }
//
//
//      });


//      mPickDateButton.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//              Calendar calendar = Calendar.getInstance();
//              int year=calendar.get(Calendar.YEAR);
//              int month=calendar.get(Calendar.MONTH);
//              int day=calendar.get(Calendar.DAY_OF_MONTH);
//
//
//
//
//              DatePickerDialog datePickerDialog = new DatePickerDialog(BookingPosition.this, new DatePickerDialog.OnDateSetListener()
//              {
//                  @Override
//                  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
//                  {   ArrayList<String> days = (ArrayList<String>) getIntent().getSerializableExtra("days");
//                      Log.d("daysUser",days.toString());
//                      Log.d("dayInstatnt", String.valueOf(dayOfMonth));
//
//
//
//
//
//
//
//
//
//
//
//                  }
//              }, year, month, day);
//
//              datePickerDialog.show();
//
//          }
//      });


    }



    @SuppressLint("SetTextI18n")
    private void init() {
        userPreferences = new UserPreferences(this);
        services = findViewById(R.id.recycle_service);
        String arrayAsString = getIntent().getExtras().getString("services");
        List<ModelServices> modelServices = Arrays.asList(new Gson().fromJson(arrayAsString, ModelServices[].class));
        bookingButton = findViewById(R.id.booking_button);
        hospital_name = findViewById(R.id.hospitalName);
        doctor_name = findViewById(R.id.doctorName);
        TextView availableDays = (TextView) findViewById(R.id.days);
        mPickDateButton = findViewById(R.id.pick_date_button);


        if(userPreferences.getChoice().equals("ho")) {


            selectedDays = findViewById(R.id.text_date);

            // ArrayList<ModelServices> modelServices = (ArrayList<ModelServices>) getIntent().getSerializableExtra("services");


            Log.d("modelService", String.valueOf(modelServices));
            docInfoID = getIntent().getExtras().getString("docInfoID");


            if(modelServices.size()>0){

            adapterServicess = new AdapterServicess(this, modelServices, bookingButton, selectedDays, docInfoID, this);
            services.setAdapter(adapterServicess);}





//        spin.setOnItemSelectedListener(this);




            ArrayList<String> days = (ArrayList<String>) getIntent().getSerializableExtra("days");
            Log.d("daysUser", days.toString());


            for (int i = 0; i < days.size(); i++) {

                availableDays.append(days.get(i) + "   ");
            }






            hospital_name.setText(getIntent().getStringExtra("hospital_name"));
            doctor_name.setText(getIntent().getStringExtra("doctor_name"));
        }


        if (userPreferences.getChoice().equals("lap")){
            hospital_name.setText(getIntent().getStringExtra("lab_name"));

            doctor_name.setVisibility(View.GONE);
            lab_id = getIntent().getStringExtra("lab_id");
            String labDiagnosisID = getIntent().getStringExtra("labDiagnosisID");

            availableDays.setVisibility(View.GONE);

            if(modelServices.size()>0){
            adapterServicess = new AdapterServicess(this, modelServices, bookingButton, lab_id,labDiagnosisID ,this);
            services.setAdapter(adapterServicess);}

            else
                bookingButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        booking();

                }
                });
            mPickDateButton.setVisibility(View.GONE);






        }
    }

    private void booking() {


        BookingService bookingService = new BookingService();

        lab_id = getIntent().getStringExtra("lab_id");
        String labDiagnosisID = getIntent().getStringExtra("labDiagnosisID");
        String arrayAsString = getIntent().getExtras().getString("services");
        List<ModelServices> modelServices = Arrays.asList(new Gson().fromJson(arrayAsString, ModelServices[].class));
        ArrayList<String>temp = new ArrayList<>();

        bookingService.booking(this,lab_id,labDiagnosisID,temp);
    }


    @Override
    public void onDateSet(DatePickerDialog view, int Year, int Month, int Day) {

        String date =Day + "-" + Month + "-" + Year;

        selectedDays.setText(date);



        Toast.makeText(BookingPosition.this, date, Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
//        getMenuInflater().inflate(R.menu.abc_main_menu, menu);
        return true;
    }

}