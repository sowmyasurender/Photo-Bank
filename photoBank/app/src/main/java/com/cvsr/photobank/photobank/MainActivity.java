package com.cvsr.photobank.photobank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClickButtonListener();
        OnClickButtonListener1();
    }

    public void OnClickButtonListener() {
        TextView textView3=(TextView)findViewById(R.id.link_signup);
        textView3.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(".SignupActivity");
                        startActivity(intent);
                    }
                }
        );

    }
    public void OnClickButtonListener1() {
        Button btlogin=(Button) findViewById(R.id.btn_login);
        btlogin.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(".AlbumActivity");
                        startActivity(intent);
                    }
                }
        );

    }
}
