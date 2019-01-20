package com.escrow.smartexamination.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.escrow.smartexamination.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.escrow.smartexamination.Adapter.Url;

import static android.content.ContentValues.TAG;

public class Login extends AppCompatActivity {
    EditText email,password;
    Button loginBtn;
    TextInputLayout inputLayoutemail,inputLayoutpassword;
    TextView SignUp,guest;
    Url u=new Url(); //This is a class in adapter for set ip
    public String url;//"http://192.168.43.95/SmartExamination/login.php";
    JSONObject jo = new JSONObject();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public String responseData;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        url=u.getUrl()+"/SmartExamination/login.php";
        Log.d(TAG, "onCreate: "+url);


        inputLayoutemail=findViewById(R.id.inputLayoutEmail);
        inputLayoutpassword=findViewById(R.id.inputLayoutPassword);

        email=findViewById(R.id.emailField);
        password=findViewById(R.id.passwordField);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        SignUp= findViewById(R.id.signUpBtn);
        guest=findViewById(R.id.Guest);


        initializeListeners();
    }

    private void initializeListeners() {



        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,FaceId.class));
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Registration.class));
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    login();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void login() throws JSONException {

        boolean isValid = true;


        String em=email.getText().toString();
        String ps=password.getText().toString();


        if (em.isEmpty()) {
            inputLayoutemail.setError("Email is mandatory");
            isValid = false;
        } else {
            inputLayoutemail.setErrorEnabled(false);
        }

        if (ps.trim().length() < 8 ) {
            inputLayoutpassword.setError("Minimum 8 characters required");
            isValid = false;
        } else {
            inputLayoutpassword.setErrorEnabled(false);
        }

        if (isValid) {


            jo.put("Email",em);
            jo.put("Password",ps);

            Log.d(TAG, "signUp: "+jo);

            new Loginingup().execute();

            Toast.makeText(Login.this,"all ok", Toast.LENGTH_SHORT).show();
        }
    }
    private class Loginingup extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();



        }

        @Override
        protected Void doInBackground(Void... arg0) {
            /**This is using okHttp **/
            OkHttpClient client = new OkHttpClient();
            Log.d(TAG, "request: "+jo);

            RequestBody body = RequestBody.create(JSON, String.valueOf(jo));

            Request request = new Request.Builder()
                    .url(url).post(body)
                    .build();

            Log.d(TAG, "body"+body);
            Log.d(TAG, "request "+request);
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, "IOException"+e);
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {



                    responseData = response.body().string();
                    Log.d(TAG, "onResponse: "+responseData);



                }
            });


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog


            if (pDialog.isShowing()) {
                pDialog.dismiss();


                Log.d(TAG, "onResponsepe: "+responseData);
                String s = responseData.trim().toString();
                Toast.makeText(Login.this,""+responseData, Toast.LENGTH_SHORT).show();
                if(s.equals("succesful"))
                {

                    Intent paper = new Intent(Login.this,Paper.class);
                    startActivity(paper);

                }


            }


        }

    }

}
