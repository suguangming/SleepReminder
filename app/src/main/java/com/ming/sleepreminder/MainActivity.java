package com.ming.sleepreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String nowTimeStr = year+"年"+month+"月"+day+"日"+hour+":"+minute;
        timeTextView1.setText(nowTimeStr);

        int hoursLeft = 0;
        int minutesLeft = 0;
        if (hour > 7 ){
            hoursLeft = 24-hour+7-1;
        } else {
            hoursLeft = 7-hour-1;
        }
        minutesLeft = hoursLeft * 60 + 60 - minute;
        double oneCycle = 90.0;
        double cyclesLeft =minutesLeft/oneCycle;

        timeTextView2.setText(String.format("%s",hoursLeft));
        timeTextView3.setText((new DecimalFormat("0.00")).format(cyclesLeft));
    }
}
