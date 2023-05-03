package com.example.clinicapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class ViewAppointmentsActivity extends AppCompatActivity {
    private ListView appointmentListView;
    private AppointmentDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointments);

        appointmentListView = findViewById(R.id.appointment_list);
        dbHelper = new AppointmentDbHelper(this);

        List<Appointment> appointments = dbHelper.getAllAppointments();
        AppointmentAdapter adapter = new AppointmentAdapter(this, appointments);
        appointmentListView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}