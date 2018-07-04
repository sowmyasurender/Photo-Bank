package com.cvsr.photobank.photobank;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sowmya on 05-07-2018.
 */

public class SignupActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        OnClickButtonListener1();
    }
    public void OnClickButtonListener1() {
        Button btsignup=(Button) findViewById(R.id.btsignup);
        btsignup.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(".AlbumActivity");
                        startActivity(intent);
                    }
                }
        );

    }
}




