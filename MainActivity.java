package com.example.axu1.richarddawkinsalarmclock;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

import com.example.axu1.richarddawkinsalarmclock.util.PreferenceUtil;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements AccelerometerListener {
    public static final String ALARM_TIME = "alarm_time";

    AlarmManager alarmManager;
    private PendingIntent pending_intent;

    private TimePicker alarmTimePicker;
    private TextView alarmTextView;
    private TextView countTextView;

    private AlarmReceiver alarm;

    private Integer shakeCount;


    MainActivity inst;
    Context context;
    PreferenceUtil pref;

    @Override
    protected void onResume() {
        super.onResume();
        if (AccelerometerManager.isSupported(this)) {
            AccelerometerManager.startListening(this);
        }
    }

    @Override
    public void onAccelerationChanged(float x, float y, float z) {

    }

    @Override
    public void onShake(float force) {
        //stopAlarm();
        shakeCount++;
        //countTextView = (TextView) findViewById(R.id.txtShakeCount);
        Toast.makeText(this, shakeCount.toString() + "回", Toast.LENGTH_SHORT).show();


        if (shakeCount >= 10) {

            //------------------
            this.context = this;
            final Intent myIntent = new Intent(this.context, AlarmReceiver.class);

            int min = 1;
            int max = 9;

            Random r = new Random();
            int random_number = r.nextInt(max - min + 1) + min;
            Log.e("random number is ", String.valueOf(random_number));

            myIntent.putExtra("extra", "no");
            sendBroadcast(myIntent);

            alarmManager.cancel(pending_intent);
            setAlarmText("アラーム終了しました");
            //setAlarmText("You clicked a " + " canceled");
            shakeCount = 0;
        }


        //countTextView.setText(shakeCount.toString());


        //------------------

    }

    @Override
    public void onStop() {
        super.onStop();

//Check device supported Accelerometer senssor or not
        if (AccelerometerManager.isListening()) {

//Start Accelerometer Listening
            AccelerometerManager.stopListening();

            //Toast.makeText(this, "onStop Accelerometer Stopped", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendButton = (Button) findViewById(R.id.nextButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SubActivity.class);
                startActivity(intent);
            }
        });

        this.context = this;
        final Intent myIntent = new Intent(this.context, AlarmReceiver.class);
        shakeCount = 0;


        //alarm = new AlarmReceiver();
        alarmTextView = (TextView) findViewById(R.id.alarmText);


        // Get the alarm manager service
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // set the alarm to the time that you picked
        final Calendar calendar = Calendar.getInstance();

        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);


        Button start_alarm = (Button) findViewById(R.id.start_alarm);
        start_alarm.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)

            @Override
            public void onClick(View v) {

                calendar.add(Calendar.SECOND, 2);
                //setAlarmText("You clicked a button");

                final int hour = alarmTimePicker.getCurrentHour();
                final int minute = alarmTimePicker.getCurrentMinute();

                Log.e("MyActivity", "In the receiver with " + hour + " and " + minute);
                setAlarmText("You clicked a " + hour + " and " + minute);


                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

                myIntent.putExtra("extra", "yes");
                pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);


                // now you should change the set Alarm text so it says something nice


                setAlarmText(hour + ":" + minute + "のアラームをセットしました");
                //Toast.makeText(getApplicationContext(), "You set the alarm", Toast.LENGTH_SHORT).show();
            }

        });


        Button stop_alarm = (Button) findViewById(R.id.stop_alarm);
        stop_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int min = 1;
                int max = 9;

                Random r = new Random();
                int random_number = r.nextInt(max - min + 1) + min;
                Log.e("random number is ", String.valueOf(random_number));

                myIntent.putExtra("extra", "no");
                sendBroadcast(myIntent);

                alarmManager.cancel(pending_intent);
                setAlarmText("アラーム終了");
                //setAlarmText("You clicked a " + " canceled");
            }
        });


    }

    private void stopAlarm() {

    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }


    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (AccelerometerManager.isListening()) {
            AccelerometerManager.stopListening();

            //Toast.makeText(this, "onDestroy Accelerometer Stopped", Toast.LENGTH_SHORT).show();
        }

        Log.e("MyActivity", "on Destroy");
    }

    // 登録
    @SuppressLint("ObsoleteSdkInt")
    private void register(long alarmTimeMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = getPendingIntent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(alarmTimeMillis, null), pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTimeMillis, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTimeMillis, pendingIntent);
        }
        // 保存
        pref.setLong(ALARM_TIME, alarmTimeMillis);
    }

    // 解除
    private void unregister() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(getPendingIntent());
        pref.delete(ALARM_TIME);
    }
    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(this, com.example.axu1.richarddawkinsalarmclock.AlarmCheckActivity.class);
        intent.setClass(this, com.example.axu1.richarddawkinsalarmclock.AlarmCheckActivity.class);
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

}
