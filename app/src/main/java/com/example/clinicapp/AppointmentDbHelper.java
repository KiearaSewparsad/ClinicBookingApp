package com.example.clinicapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "appointments.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AppointmentEntry.TABLE_NAME + " (" +
                    AppointmentEntry._ID + " INTEGER PRIMARY KEY," +
                    AppointmentEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                    AppointmentEntry.COLUMN_SURNAME + " TEXT NOT NULL, " +
                    AppointmentEntry.COLUMN_NUMBER + " TEXT NOT NULL, " +
                    AppointmentEntry.COLUMN_DATE + " TEXT NOT NULL, " +
                    AppointmentEntry.COLUMN_TIME + " TEXT NOT NULL, " +
                    AppointmentEntry.COLUMN_CLINIC + " TEXT NOT NULL);";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AppointmentEntry.TABLE_NAME;

    public AppointmentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public boolean isTimeAvailable(String time) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                AppointmentEntry._ID
        };

        String selection = AppointmentEntry.COLUMN_TIME + " = ?";
        String[] selectionArgs = { time };

        Cursor cursor = db.query(
                AppointmentEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean isAvailable = cursor.getCount() == 0;
        cursor.close();

        return isAvailable;
    }

    public void addAppointment(Appointment appointment) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppointmentEntry.COLUMN_NAME, appointment.getName());
        values.put(AppointmentEntry.COLUMN_SURNAME, appointment.getSurname());
        values.put(AppointmentEntry.COLUMN_NUMBER, appointment.getNumber());
        values.put(AppointmentEntry.COLUMN_DATE, appointment.getDate());
        values.put(AppointmentEntry.COLUMN_TIME, appointment.getTime());
        values.put(AppointmentEntry.COLUMN_CLINIC, appointment.getClinic());
        db.insert(AppointmentEntry.TABLE_NAME, null, values);
        db.close();
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                AppointmentEntry._ID,
                AppointmentEntry.COLUMN_NAME,
                AppointmentEntry.COLUMN_SURNAME,
                AppointmentEntry.COLUMN_NUMBER,
                AppointmentEntry.COLUMN_DATE,
                AppointmentEntry.COLUMN_TIME,
                AppointmentEntry.COLUMN_CLINIC
        };

        Cursor cursor = db.query(
                AppointmentEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(AppointmentEntry._ID));
            String Name = cursor.getString(cursor.getColumnIndexOrThrow(AppointmentEntry.COLUMN_NAME));
            String Surname = cursor.getString(cursor.getColumnIndexOrThrow(AppointmentEntry.COLUMN_SURNAME));
            String number = cursor.getString(cursor.getColumnIndexOrThrow(AppointmentEntry.COLUMN_NUMBER));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(AppointmentEntry.COLUMN_DATE));
            String time = cursor.getString(cursor.getColumnIndexOrThrow(AppointmentEntry.COLUMN_TIME));
            String clinic = cursor.getString(cursor.getColumnIndexOrThrow(AppointmentEntry.COLUMN_CLINIC));
            Appointment appointment = new Appointment(Name, Surname, number, date, time, clinic);
            appointments.add(appointment);
        }

        cursor.close();

        return appointments;
    }

    public int deleteAppointment(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = AppointmentEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        int deletedRows = db.delete(AppointmentEntry.TABLE_NAME, selection, selectionArgs);
        return deletedRows;
    }

    public static class AppointmentEntry implements BaseColumns {
        public static final String TABLE_NAME = "appointments";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_CLINIC = "clinic";
    }
}
