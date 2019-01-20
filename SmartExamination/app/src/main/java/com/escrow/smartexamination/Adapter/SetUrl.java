package com.escrow.smartexamination.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.escrow.smartexamination.Activity.Start;
import com.escrow.smartexamination.R;

public class SetUrl extends AppCompatActivity {

    private static final String TAG = "";
    EditText inputip;
    Button setip;
    Url u = new Url();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_url);
        inputip = findViewById(R.id.inputip);
        setip = findViewById(R.id.buttonSetIp);
        Log.d(TAG, "url: ");
        Toast.makeText(this,u.getUrl(),Toast.LENGTH_SHORT).show();

        setip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url= inputip.getText().toString().trim();
                Log.d(TAG, "url   "+url);
                u.setUrl(url);

                Toast.makeText(SetUrl.this ,u.getUrl(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SetUrl.this,Start.class);
                startActivity(intent);

            }
        });


    }
}
