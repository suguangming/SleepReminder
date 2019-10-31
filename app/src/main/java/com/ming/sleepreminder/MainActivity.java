package com.ming.sleepreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView timeTextView1 = findViewById(R.id.timeTextView1);
        TextView timeTextView2 = findViewById(R.id.timeTextView2);
        TextView timeTextView3 = findViewById(R.id.timeTextView3);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date nowTime = new Date(System.currentTimeMillis());
        String nowTimeStr = formatter.format(nowTime);
        timeTextView1.setText(nowTimeStr);


    }
}
