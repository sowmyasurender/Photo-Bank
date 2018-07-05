package com.cvsr.photobank.photobank;


import android.app.ProgressDialog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sowmya on 05-07-2018.
 */

public class SignupActivity extends AppCompatActivity {

    private EditText editTextEmail,editTextPassword,editTextCnfPassword,etfn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextEmail= (EditText)findViewById(R.id.input_email) ;
        editTextPassword= (EditText)findViewById(R.id.input_password);
        editTextCnfPassword=(EditText)findViewById(R.id.input_password);
        OnClickButtonListener();

    }


    public void OnClickButtonListener() {
        Button bt_signup=(Button) findViewById(R.id.btsignup);
        bt_signup.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        final String email = editTextEmail.getText().toString();
                        final String pass = editTextPassword.getText().toString();

                        if (!isValidEmail(email)) {
                            editTextEmail.setError(getText(R.string.invalid_username));
                        }
                        if (!isValidPassword(pass)) {
                            editTextPassword.setError(getText(R.string.empty_password));
                        }
                        if (isValidEmail(email) && isValidPassword(pass)){
                            Intent intent = new Intent(".AlbumActivity");
                            startActivity(intent);
                        }
                    }
                });
        }



    private void SignUp() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String cnfPassword = editTextCnfPassword.getText().toString().trim();
        String fname = etfn.getText().toString().trim();



        if (!isValidEmail(email)) {
            editTextEmail.setError(getText(R.string.invalid_username));
        } else if (!isValidPassword(password)) {
            editTextPassword.setError(getText(R.string.invalid_password));
        } else if (!cnfPassword.equals(password)) {
            editTextCnfPassword.setError(getText(R.string.password_mismatch));
        } else {
            startActivity(new Intent(SignupActivity.this, MainActivity.class));

        }

    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }


}







