package com.ming.sleepreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.TimeZone;

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
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
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
        double oneCycle = 90.0;
        double cyclesLeft = 0;
        if (hour > 7 ){
            hoursLeft = 24-hour+7-1;
        } else {
            hoursLeft = 7-hour-1;
        }
        minutesLeft = hoursLeft * 60 + 60 - minute;
        cyclesLeft =minutesLeft/oneCycle;
//        hoursLeftTextView.setText(String.format("%s",hoursLeft));
//        cyclesLeftTextView.setText((new DecimalFormat("0.00")).format(cyclesLeft));

        if (cyclesLeft < 4.0)
            cyclesLeftTextView.setTextColor(Color.RED);
        else if (cyclesLeft < 4.3)
            cyclesLeftTextView.setTextColor(Color.YELLOW);
        else
            cyclesLeftTextView.setTextColor(0xff006400);//darkgreen


        //Notification
        NotificationManager manager;
        NotificationChannel channel;
        String CHANNEL_ID = "default";
        String CHANNEL_NAME = "Default Channel";
        String CHANNEL_DESCRIPTION = "this is default channel!";
        manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription(CHANNEL_DESCRIPTION);
        manager.createNotificationChannel(channel);
        Notification notification =new NotificationCompat.Builder(this,"default")
                .setContentTitle(String.format("%s",hoursLeft)+" hours left.")
                .setContentText((new DecimalFormat("0.0")).format(cyclesLeft) + " cycles left.")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.small_bed_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.bed_icon))
                .setTimeoutAfter(15000)
                .build();
        manager.notify(1,notification);
        hoursLeftTextView.setText(String.format("%s",hoursLeft));
        cyclesLeftTextView.setText((new DecimalFormat("0.00")).format(cyclesLeft));
    }
}
