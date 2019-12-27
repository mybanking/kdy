package com.bugcoder.sc.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    Button btn_signIn;
    TextView tv_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        btn_signIn = findViewById(R.id.btn_sign_in);
        tv_signUp = findViewById(R.id.tv_sign_up);
        btn_signIn.setOnClickListener(this);
        tv_signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_sign_in) {
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            this.finish();
        } else if (view.getId() == R.id.tv_sign_up) {
            Intent intent = new Intent(getApplicationContext(), SignupScreen.class);
            startActivity(intent);
        }
    }
}
