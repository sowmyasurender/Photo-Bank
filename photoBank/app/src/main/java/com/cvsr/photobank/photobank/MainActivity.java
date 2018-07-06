package com.cvsr.photobank.photobank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    private EditText emailText,passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailText= (EditText)findViewById(R.id.input_email) ;
        passwordText= (EditText)findViewById(R.id.input_password);

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

                        final String email = emailText.getText().toString();
                        final String pass = passwordText.getText().toString();

                        if (!isValidEmail(email)) {
                            emailText.setError(getText(R.string.invalid_username));
                        }

                        if (!isValidPassword(pass)) {
                            passwordText.setError(getText(R.string.empty_password));
                        }

                        if (isValidEmail(email) && isValidPassword(pass))
                        {
                            Intent intent = new Intent(".ImageList");
                            startActivity(intent);
                        }


                    }
                }
        );

    }



    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 0) {
            return true;
        }
        return false;
    }
}
