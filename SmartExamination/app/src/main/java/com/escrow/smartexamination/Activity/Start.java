package com.escrow.smartexamination.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.escrow.smartexamination.R;

public class Start extends AppCompatActivity implements View.OnClickListener {

    Button signup,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        signup =(Button)findViewById(R.id.buttonsignup);
        login = (Button)findViewById(R.id.buttonlogin);

        signup.setOnClickListener(this);
        login.setOnClickListener(this);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.buttonsignup:
                 Intent intent = new Intent(Start.this,Registration.class);
                 startActivity(intent);
                 break;
            case R.id.buttonlogin:
                 Intent sign = new Intent(Start.this,Login.class);
                 startActivity(sign);


        }
    }

}
