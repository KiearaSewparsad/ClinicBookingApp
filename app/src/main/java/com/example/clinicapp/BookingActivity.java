package com.example.clinicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {

    private EditText NameEditText;
    private EditText SurnameEditText;
    private EditText numberEditText;
    private EditText dateEditText;
    private EditText timeEditText;
    private Spinner clinicSelect;
    private Button bookButton;
    private AppointmentDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);


        NameEditText = findViewById(R.id.name_edit_text);
        SurnameEditText = findViewById(R.id.surname_edit_text);
        numberEditText = findViewById(R.id.number_edit_text);
        dateEditText = findViewById(R.id.date_edit_text);
        timeEditText = findViewById(R.id.time_edit_text);
        clinicSelect = findViewById(R.id.select_clinic);
        bookButton = findViewById(R.id.book_button);

        dbHelper = new AppointmentDbHelper(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.clinic_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clinicSelect.setAdapter(adapter);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = NameEditText.getText().toString().trim();
                String lastName = SurnameEditText.getText().toString().trim();
                String number = numberEditText.getText().toString().trim();
                String date = dateEditText.getText().toString().trim();
                String time = timeEditText.getText().toString().trim();
                String clinic = clinicSelect.getSelectedItem().toString();
                if (firstName.isEmpty()) {
                    Toast.makeText(BookingActivity.this, "Please enter a first name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (lastName.isEmpty()) {
                    Toast.makeText(BookingActivity.this, "Please enter a last name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (number.isEmpty() || !number.matches("0\\d{9}")) {
                    Toast.makeText(BookingActivity.this, "Please enter a valid phone number starting with 0", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!number.matches("\\d{10}")) {
                    Toast.makeText(BookingActivity.this, "Phone number has to be 10 digits", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (date.isEmpty()) {
                    Toast.makeText(BookingActivity.this, "Please enter a date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (time.isEmpty()) {
                    Toast.makeText(BookingActivity.this, "Please enter a time", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (clinic.isEmpty()){
                    Toast.makeText(BookingActivity.this, "Please select a clinic", Toast.LENGTH_SHORT).show();
                }

                if (!dbHelper.isTimeAvailable(time)) {
                    Toast.makeText(BookingActivity.this, "This time is already booked", Toast.LENGTH_SHORT).show();
                    return;
                }

                Appointment appointment = new Appointment(firstName, lastName, number, date, time, clinic);
                dbHelper.addAppointment(appointment);
                Toast.makeText(BookingActivity.this, "Appointment booked successfully", Toast.LENGTH_SHORT).show();
                finish();

                Intent intent = new Intent(BookingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}