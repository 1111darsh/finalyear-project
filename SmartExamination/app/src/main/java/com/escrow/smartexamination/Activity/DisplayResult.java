package com.escrow.smartexamination.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.escrow.smartexamination.R;

public class DisplayResult extends AppCompatActivity {
    private static final String TAG = "DisplayResult";
    TextView result,NOQ;
    Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        Intent intent=getIntent();
        String Score="h",NoFoQue="h";
        Score = intent.getStringExtra("Result");
        NoFoQue = intent.getStringExtra("NOQ");


        result=(TextView)findViewById(R.id.result);
        NOQ=(TextView)findViewById(R.id.NOQ);

        result.setText(Score);
        NOQ.setText(NoFoQue);

        Logout=(Button)findViewById(R.id.LogoutButton);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Start=new Intent(DisplayResult.this,Start.class);
                startActivity(Start);
            }
        });
    }
}
