package com.example.axu1.richarddawkinsalarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.axu1.richarddawkinsalarmclock.util.PreferenceUtil;

import java.util.Random;

public class PlaySoundActivity extends AppCompatActivity implements AccelerometerListener {
    Button stop;
    int randnum;
    TextView textView;
    TextView textView3;
    EditText editText;
    int count = 0;
    Context context;
    private Integer shakeCount;
    AlarmManager alarmManager;
    private PendingIntent pending_intent;
    private TextView alarmTextView;
    MainActivity inst;

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
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    // ダイアログ表示など特定の処理を行いたい場合はここに記述
                    // 親クラスのdispatchKeyEvent()を呼び出さずにtrueを返す
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

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
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_sound);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        this.context = this;
        final Intent myIntent = new Intent(this.context, AlarmReceiver.class);
        shakeCount = 0;
        alarmTextView = (TextView) findViewById(R.id.alarmText);

        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        final int Level = data.getInt("LevelSave", 1); // 第２引数は値がなかったときのデフォルト

        //ShakeGestureManager mGestureManager = new ShakeGestureManager(this, mListener);
        //mGestureManager.startSensing();

        startService(new Intent(this, PlaySoundService.class));
        Random rand = new Random();
        randnum = rand.nextInt(10);
        //  randnum = Level;
        textView3 = (TextView) findViewById(R.id.textView3);
        textView = (TextView) findViewById(R.id.textView2);
        editText = (EditText) findViewById(R.id.editText);

        if (Level == 1) {
            // 低の問題
            if (randnum == 0) {
                textView.setText("１１×１１");//121
            } else if (randnum == 1) {
                textView.setText("１＋２＋３＋４＋５＋６＋７＋８＋９");//45
            } else if (randnum == 2) {
                textView.setText("３３×３３");//1089
            } else if (randnum == 3) {
                textView.setText("１＋３＋９＋２７＋８１＋２４３");//364
            } else if (randnum == 4) {
                textView.setText("(－４)－(－５)");//1
            } else if (randnum == 5) {
                textView.setText("(－３)－(－２)");//-1
            } else if (randnum == 6) {
                textView.setText("０－(－５)");//5
            } else if (randnum == 7) {
                textView.setText("５－(＋２)");//3
            } else if (randnum == 8) {
                textView.setText("(－４)－(＋６)");//-10
            } else if (randnum == 9) {
                textView.setText("(＋２)－(－５)");//7
            } else if (randnum == 10) {
                textView.setText("９－(－５)");//14
            }
        }
        if (Level == 2) {
            // 中の問題
            if (randnum == 0) {
                textView.setText("１１１×１１１");//12321
            } else if (randnum == 1) {
                textView.setText("(１００－１３)×(１００＋１３)");//9831
            } else if (randnum == 2) {
                textView.setText("２×(－５)");//-10
            } else if (randnum == 3) {
                textView.setText("(－２)×(－５)");//10
            } else if (randnum == 4) {
                textView.setText("(－４)÷２");//-2
            } else if (randnum == 5) {
                textView.setText("９÷(－３)");//-3
            } else if (randnum == 6) {
                textView.setText("－１０÷(－５)");//2
            } else if (randnum == 7) {
                textView.setText("(－１２)÷(－４/３)");//9
            }
        }

        if (Level == 3) {
            // 高の問題
            if (randnum == 0) {
                textView.setText("９９×(８＋２８７－２２)×０");//0
            } else if (randnum == 1) {
                textView.setText("１１×１１÷１２１");//1
            } else if (randnum == 2) {
                textView.setText("(－２)＾３");//-8
            } else if (randnum == 3) {
                textView.setText("(－１)＾２０１７");//-1
            } else if (randnum == 4) {
                textView.setText("５ｘ－２＝３ｘ－８");//-3
            } else if (randnum == 5) {
                textView.setText("８：１２＝６：ｘ");//9
            }
        }
        final Intent intent = new Intent(this, MainActivity.class);

        if (count == 10) {
            stopsound();
        }

        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Level == 1) {
                    // 低の問題
                    if (randnum == 0 && editText.getText().toString().equals("121")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 1 && editText.getText().toString().equals("45")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 2 && editText.getText().toString().equals("1089")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 3 && editText.getText().toString().equals("364")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 4 && editText.getText().toString().equals("1")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 5 && editText.getText().toString().equals("-1")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 6 && editText.getText().toString().equals("5")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 7 && editText.getText().toString().equals("3")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 8 && editText.getText().toString().equals("-10")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 9 && editText.getText().toString().equals("7")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 10 && editText.getText().toString().equals("14")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (editText.getText().toString().equals("")) {
                        Toast.makeText(PlaySoundActivity.this, "数字を入力してください", Toast.LENGTH_LONG).show();
                    }
                }
                if (Level == 2) {
                    // 中の問題
                    if (randnum == 0 && editText.getText().toString().equals("12321")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 1 && editText.getText().toString().equals("9831")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 2 && editText.getText().toString().equals("-10")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 3 && editText.getText().toString().equals("10")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 4 && editText.getText().toString().equals("-2")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 5 && editText.getText().toString().equals("-3")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 6 && editText.getText().toString().equals("2")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 7 && editText.getText().toString().equals("9")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (editText.getText().toString().equals("")) {
                        Toast.makeText(PlaySoundActivity.this, "数字を入力してください", Toast.LENGTH_LONG).show();
                    }

                }
                if (Level == 3) {
                    // 高の問題
                    if (randnum == 0 && editText.getText().toString().equals("0")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 1 && editText.getText().toString().equals("1")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 2 && editText.getText().toString().equals("-8")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 3 && editText.getText().toString().equals("-1")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 4 && editText.getText().toString().equals("-3")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (randnum == 5 && editText.getText().toString().equals("9")) {
                        stopsound();
                        textView3.setText("正解!!");
                        finish();
                    } else if (editText.getText().toString().equals("")) {
                        Toast.makeText(PlaySoundActivity.this, "数字を入力してください", Toast.LENGTH_LONG).show();
                    }
                }
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

    public void stopsound() {
        stopService(new Intent(PlaySoundActivity.this, PlaySoundService.class));
        PreferenceUtil pref = new PreferenceUtil(PlaySoundActivity.this);
        pref.delete(com.example.axu1.richarddawkinsalarmclock.MainActivity.ALARM_TIME);
    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }

    private void stopAlarm() {

    }



    /*@Override
    public void onStart() {
        super.onStart();
        inst = this;
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (AccelerometerManager.isListening()) {
            AccelerometerManager.stopListening();

            //Toast.makeText(this, "onDestroy Accelerometer Stopped", Toast.LENGTH_SHORT).show();
        }

        Log.e("MyActivity", "on Destroy");
    }
}
