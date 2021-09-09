package com.grapefruit.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.grapefruit.alarm.databinding.ActivityMainBinding;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AlarmManager am;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        am = (AlarmManager) getSystemService(ALARM_SERVICE);
        calendar = Calendar.getInstance();

        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.one:
                    // 1분 후
                    calendar.setTime(new Date());
                    calendar.add(Calendar.MINUTE, 1);
                    break;
                case R.id.two:
                    // 2분 후
                    calendar.setTime(new Date());
                    calendar.add(Calendar.MINUTE, 2);
                    break;
                case R.id.five:
                    // 5분 후
                    calendar.setTime(new Date());
                    calendar.add(Calendar.MINUTE, 5);
                    break;
                case R.id.ten:
                    // 10분 후
                    calendar.setTime(new Date());
                    calendar.add(Calendar.MINUTE, 10);
                    break;
                case R.id.hour:
                    // 1시간 후
                    calendar.setTime(new Date());
                    calendar.add(Calendar.HOUR, 1);
                    break;
                case R.id.date:
                    // 하루 뒤
                    calendar.setTime(new Date());
                    calendar.add(Calendar.DATE, 1);
                    break;
            }
        });

        binding.alarm.setOnClickListener(v -> setAlarm());
    }

    private void setAlarm() {
        Intent receiverIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, receiverIntent, 0);
        am.set(AlarmManager.RTC, calendar.getTimeInMillis(), pIntent);
        Toast.makeText(this, calendar.get(Calendar.HOUR) + "시 " + calendar.get(Calendar.MINUTE) + "분에 알림이 울립니다.", Toast.LENGTH_SHORT).show();
    }
}