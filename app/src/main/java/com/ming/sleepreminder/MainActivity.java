package com.ming.sleepreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //get elements
        TextView dateTextView = findViewById(R.id.dateTextView);
        TextView timeTextView = findViewById(R.id.timeTextView);
        TextView hoursLeftTextView = findViewById(R.id.hoursLeftTextView);
        TextView cyclesLeftTextView = findViewById(R.id.cyclesLeftTextView);


        //get time
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String hourStr;
        String minuteStr;
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);


        //set time
        if (hour < 10){
            hourStr = "0"+hour;
        } else {
            hourStr = String.format("%s",hour);
        }
        if (minute < 10){
            minuteStr = "0"+minute;
        } else {
            minuteStr = String.format("%s",minute);
        }

        String nowDateStr = year+"年"+month+"月"+day+"日";
        String nowTimeStr = hourStr+":"+minuteStr;
        dateTextView.setText(nowDateStr);
        timeTextView.setText(nowTimeStr);

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

        hoursLeftTextView.setText(String.format("%s",hoursLeft));
        cyclesLeftTextView.setText((new DecimalFormat("0.00")).format(cyclesLeft));
    }
}
