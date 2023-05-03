package com.example.clinicapp;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class NotificationsActivity extends AppCompatActivity implements Notifications2Activity {

    private int notificationId = 1;
    Button button, setBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        setBtn = findViewById(R.id.setBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        findViewById(R.id.setBtn).setOnClickListener((View.OnClickListener) this);
        findViewById(R.id.cancelBtn).setOnClickListener((View.OnClickListener) this);



        button = findViewById(R.id.click);
        final long [] vibe = {0,500};
        final Uri notificationsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder mbuilder = (NotificationCompat.Builder)
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.b1,10)
                                .setSound(notificationsound)
                                .setVibrate(vibe)
                                .setContentTitle("DUT Clinic")
                                .setContentText("Your appointment booking notification");

                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0,mbuilder.build());
            }
        });
    }



    @Override
    public void onClick(View view){
        EditText editText = findViewById(R.id.alarmEditTex);
        TimePicker timePicker = findViewById(R.id.timePicker);


        Intent intent = new Intent(NotificationsActivity.this, AlarmReceiver.class);
        startActivity(intent);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("todo", editText.getText().toString());

        PendingIntent alarmIntent = PendingIntent.getBroadcast(NotificationsActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch (view.getId()){
            case R.id.setBtn:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelBtn:
                alarm.cancel(alarmIntent);
                Toast.makeText(this, "Canceled!", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}