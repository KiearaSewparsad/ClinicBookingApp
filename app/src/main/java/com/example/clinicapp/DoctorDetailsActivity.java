package com.example.clinicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 = {
            {"Doctor Name: Nonkosi Dlamini", "Hospital Address: RITSON", "Experience: 5 yrs", "Mobile No: 031 269 3802", "-"},
            {"Doctor Name: Nqobile Buthelezi", "Hospital Address: RITSON", "Experience: 7 yrs", "Mobile No: 031 639 7502", "-"},
            {"Doctor Name: Trivesh Naidoo", "Hospital Address: RITSON", "Experience: 6.5 yrs", "Mobile No: 024 469 7811", "-"},
            {"Doctor Name: Michelle Govender", "Hospital Address: RITSON", "Experience: 3 yrs", "Mobile No: 031 225 3302", "-"},
            {"Doctor Name: Nandipha Msomi", "Hospital Address: RITSON", "Experience: 4 yrs", "Mobile No: 027 889 3409", "-"}
    };

    private String[][] doctor_details2 = {
            {"Doctor Name: Luthando Msomi", "Hospital Address: STEVE", "Experience: 4 yrs", "Mobile No: 027 189 3209", "-"},
            {"Doctor Name: Esethu Mtolo", "Hospital Address: STEVE", "Experience: 3 yrs", "Mobile No: 031 725 3222", "-"},
            {"Doctor Name: Norgan Khumalo", "Hospital Address: STEVE", "Experience: 8 yrs", "Mobile No: 024 129 7911", "-"},
            {"Doctor Name: Josh Smtih", "Hospital Address: STEVE", "Experience: 7 yrs", "Mobile No: 031 189 7502", "-"},
            {"Doctor Name: Mduduzi Shangase", "Hospital Address: STEVE", "Experience: 2.7 yrs", "Mobile No: 031 969 3666", "-"}
    };

    private String[][] doctor_details3 = {
            {"Doctor Name: Luthando Mtolo", "Hospital Address: DUT", "Experience: 5 yrs", "Mobile No: 067 269 3802", "-"},
            {"Doctor Name: Luthando Mtolo", "Hospital Address: DUT", "Experience: 5 yrs", "Mobile No: 067 269 3802", "-"},
            {"Doctor Name: Luthando Mtolo", "Hospital Address: DUT", "Experience: 5 yrs", "Mobile No: 067 269 3802", "-"},
            {"Doctor Name: Luthando Mtolo", "Hospital Address: DUT", "Experience: 5 yrs", "Mobile No: 067 269 3802", "-"},
            {"Doctor Name: Luthando Mtolo", "Hospital Address: DUT", "Experience: 5 yrs", "Mobile No: 067 269 3802", "-"}
    };

    private String[][] doctor_details4 = {
            {"Doctor Name: Luthando Mtolo", "Hospital Address: DUT", "Experience: 5 yrs", "Mobile No: 067 269 3802", "-"},
            {"Doctor Name: Luthando Mtolo", "Hospital Address: DUT", "Experience: 5 yrs", "Mobile No: 067 269 3802", "-"},
            {"Doctor Name: Luthando Mtolo", "Hospital Address: DUT", "Experience: 5 yrs", "Mobile No: 067 269 3802", "-"},
            {"Doctor Name: Luthando Mtolo", "Hospital Address: DUT", "Experience: 5 yrs", "Mobile No: 067 269 3802", "-"},
            {"Doctor Name: Luthando Mtolo", "Hospital Address: DUT", "Experience: 5 yrs", "Mobile No: 067 269 3802", "-"}
    };

    private String[][] doctor_details5 = {
            {"Doctor Name: Zwane Mbatha", "Hospital Address: Sparks, Durban", "Experience: 11 yrs", "Mobile No: 031 269 9902", "Click"},
            {"Doctor Name: Kaitlyn Johnson", "Hospital Address: Bulwer, Berea", "Experience: 9 yrs", "Mobile No: 031 565 3842", "Click"},
            {"Doctor Name: Siphamandla Ngcobo", "Hospital Address: Overport, Durban", "Experience: 16 yrs", "Mobile No: 031 129 3212", "Click"}
    };
    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);
        Intent it =getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Ritson Clinic")==0)
            doctor_details = doctor_details1;
        else
        if(title.compareTo("Steve Clinic")==0)
            doctor_details = doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class);
                startActivity(intent);
            }
        });

        list = new ArrayList<>();
        for(int i = 0; i < doctor_details.length; i++){
            item = new HashMap<String, String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Location:" + doctor_details[i][4] + "/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e,}
        );

        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);
    }
}