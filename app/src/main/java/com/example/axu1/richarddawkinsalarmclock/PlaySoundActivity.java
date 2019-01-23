package com.example.axu1.richarddawkinsalarmclock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.axu1.richarddawkinsalarmclock.util.PreferenceUtil;

import java.util.Random;

//import orz.kassy.shakegesture.ShakeGestureManager;

public class PlaySoundActivity extends AppCompatActivity {
    Button stop;
    int randnum;
    TextView textView;
    TextView textView3;
    EditText editText;
    int count = 0;

     @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction()==KeyEvent.ACTION_DOWN) {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_sound);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);



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
        editText= (EditText) findViewById(R.id.editText);

       if(Level == 1){
            // 低の問題
            if(randnum==0){
                textView.setText("１１×１１");//121
            }else if(randnum==1){
                textView.setText("１＋２＋３＋４＋５＋６＋７＋８＋９");//45
            }else if(randnum==2){
                textView.setText("３３×３３");//1089
            }else if(randnum==3){
                textView.setText("１＋３＋９＋２７＋８１＋２４３");//364
            }else if(randnum==4) {
                textView.setText("(－４)－(－５)");//1
            }else if(randnum==5) {
                textView.setText("(－３)－(－２)");//-1
            }else if(randnum==6) {
                textView.setText("０－(－５)");//5
            }else if(randnum==7) {
                textView.setText("５－(＋２)");//3
            }else if(randnum==8) {
                textView.setText("(－４)－(＋６)");//-10
            }else if(randnum==9) {
                textView.setText("(＋２)－(－５)");//7
            }else if(randnum==10) {
                textView.setText("９－(－５)");//14
            }
        }
        if(Level == 2){
            // 中の問題
            if(randnum==0){
                textView.setText("１１１×１１１");//12321
            }else if(randnum==1){
                textView.setText("(１００－１３)×(１００＋１３)");//9831
            }else if(randnum==2){
                textView.setText("２×(－５)");//-10
            }else if(randnum==3) {
                textView.setText("(－２)×(－５)");//10
            }else if(randnum==4) {
               textView.setText("(－４)÷２");//-2
            }else if(randnum==5) {
               textView.setText("９÷(－３)");//-3
            }else if(randnum==6) {
            textView.setText("－１０÷(－５)");//2
            }else if(randnum==7) {
                textView.setText("(－１２)÷(－４/３)");//9
            }
        }

        if(Level == 3) {
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

        if(count==10){
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
                    } else if (randnum == 2 && editText.getText().toString().equals("1029")) {
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
                    }else if (editText.getText().toString().equals("")) {
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
                    }else if (editText.getText().toString().equals("")) {
                        Toast.makeText(PlaySoundActivity.this, "数字を入力してください", Toast.LENGTH_LONG).show();
                    }

                }if (Level == 3) {
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
                    }else if (editText.getText().toString().equals("")) {
                        Toast.makeText(PlaySoundActivity.this, "数字を入力してください", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void stopsound(){
        stopService(new Intent(PlaySoundActivity.this, PlaySoundService.class));
        PreferenceUtil pref = new PreferenceUtil(PlaySoundActivity.this);
        pref.delete(com.example.axu1.richarddawkinsalarmclock.MainActivity.ALARM_TIME);
    }

    /*private ShakeGestureManager.GestureListener mListener = new ShakeGestureManager.GestureListener() {
        @Override
        public void onGestureDetected(int gestureType, int gestureCount) {
            // ジェスチャーを認識したらここが呼ばれる
            System.out.println("認識");
            count++;
        }
        public void checkAnswer(View view) {
            // answer?
            Button clickedButton = (Button) view;
            String clickedAnswer = clickedButton.getText().toString();
        }
            @Override
        public void onMessage(String s) {

        }
    };
    */


}
