package com.example.clinicapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AppointmentAdapter extends BaseAdapter {
    private List<Appointment> appointments;
    private LayoutInflater inflater;

    public AppointmentAdapter(Context context, List<Appointment> appointments) {
        this.appointments = appointments;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return appointments.size();
    }

    @Override
    public Object getItem(int position) {
        return appointments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.appointment_list_item, parent, false);
        }

        Appointment appointment = (Appointment) getItem(position);

        TextView timeTextView = convertView.findViewById(R.id.time_text_view);
        TextView clinicTextView = convertView.findViewById(R.id.clinic_text_view);
        TextView nameTextView = convertView.findViewById(R.id.name_text_view);

        timeTextView.setText(appointment.getTime());
        clinicTextView.setText(appointment.getClinic());
        nameTextView.setText(appointment.getName() + " " + appointment.getSurname());

        return convertView;
    }
}