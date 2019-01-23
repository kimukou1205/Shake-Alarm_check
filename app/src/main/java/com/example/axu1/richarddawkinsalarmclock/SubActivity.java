package com.example.axu1.richarddawkinsalarmclock;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sub);

        Button returnButton = (Button) findViewById(R.id.return_button);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        // 低ボタンが押された時
        Button rowButton = (Button) findViewById(R.id.row_button);
        rowButton.setOnClickListener(new View.OnClickListener() { // returnButtonになっていた
            @Override
            public void onClick(View v) {
                SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = data.edit();
                editor.putInt("LevelSave", 1); // 1:低、2:中、3:高の３段階があるとする。
                editor.apply();
                Toast.makeText(SubActivity.this, "簡単がクリックされました！", Toast.LENGTH_LONG).show();
            }
        });

        // 中ボタンが押された時
        Button normalButton = (Button) findViewById(R.id.normal_button);
        normalButton.setOnClickListener(new View.OnClickListener() { // returnButtonになっていた
            @Override
            public void onClick(View v) {
                SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = data.edit();
                editor.putInt("LevelSave", 2); // 1:低、2:中、3:高の３段階があるとする。
                editor.apply();
                Toast.makeText(SubActivity.this, "普通がクリックされました！", Toast.LENGTH_LONG).show();
            }
        });

        // 高ボタンが押された時
        Button highButton = (Button) findViewById(R.id.high_button);
        highButton.setOnClickListener(new View.OnClickListener() { // returnButtonになっていた
            @Override
            public void onClick(View v) {
                SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = data.edit();
                editor.putInt("LevelSave", 3); // 1:低、2:中、3:高の３段階があるとする。
                editor.apply();
                Toast.makeText(SubActivity.this, "難しいがクリックされました！", Toast.LENGTH_LONG).show();
            }
        });

    }
}