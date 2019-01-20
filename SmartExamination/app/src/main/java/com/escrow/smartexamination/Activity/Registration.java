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
import android.widget.Spinner;
import android.widget.Toast;

import com.escrow.smartexamination.Adapter.Url;
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

import static android.content.ContentValues.TAG;

public class Registration extends AppCompatActivity {
    EditText firstname,lastname,email,password,repassword,contectno;
    Spinner degree,passedyear;
    Button signupBtn;
    TextInputLayout inputLayoutfirstname,inputLayoutlastname,inputLayoutemail,inputLayoutpassword,inputLayoutrepassword,inputLayoutcontectno;
    private ProgressDialog pDialog;
    private String responseData;
    Url u=new Url();  //This is a class in adapter for set ip
    public String url;//= "http://192.168.43.95/SmartExamination/Registration.php";
    JSONObject jo = new JSONObject();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        url=u.getUrl()+"/SmartExamination/Registration.php";
        Log.d(TAG, "onCreate: "+url);
        inputLayoutfirstname=findViewById(R.id.inputLayoutFirstName);
        inputLayoutlastname=findViewById(R.id.inputLayoutLastName);
        inputLayoutemail=findViewById(R.id.inputLayoutEmail);
        inputLayoutpassword=findViewById(R.id.inputLayoutPassword);
        inputLayoutrepassword=findViewById(R.id.inputLayoutRePassword);
        inputLayoutcontectno=findViewById(R.id.inputLayoutContectNo);





        firstname=findViewById(R.id.FirstNameField);
        lastname=findViewById(R.id.LastNameField);
        email=findViewById(R.id.emailField);
        password=findViewById(R.id.passwordField);
        repassword=findViewById(R.id.RepasswordField);
        contectno=findViewById(R.id.ContectNoField);
        degree=findViewById(R.id.Course);
        passedyear=findViewById(R.id.PassedYear);
        signupBtn = (Button) findViewById(R.id.signUpBtn);
        initializeListeners();

    }

    private void initializeListeners() {

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    signUp();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void signUp() throws JSONException {

        boolean isValid = true;

        String fn=firstname.getText().toString();
        String ln=lastname.getText().toString();
        String em=email.getText().toString();
        String ps=password.getText().toString();
        String cn=contectno.getText().toString();
        String dg=degree.getSelectedItem().toString();
        String py=passedyear.getSelectedItem().toString();
        Log.d(TAG, "signUp: dg "+dg);




        if (fn.isEmpty()) {
            inputLayoutfirstname.setError("Your First Name is mandatory");
            isValid = false;
        } else {
            inputLayoutfirstname.setErrorEnabled(false);
        }
        if (ln.isEmpty()) {
            inputLayoutlastname.setError("Your Last Name is mandatory");
            isValid = false;
        } else {
            inputLayoutlastname.setErrorEnabled(false);
        }

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

            jo.put("S_ID", fn);
            jo.put("Name", fn);
            jo.put("Lastname",ln);
            jo.put("Email",em);
            jo.put("Password",ps);
            jo.put("contect_no",cn);
            jo.put("degree",dg);
            jo.put("graduate_year",py+"-01-01");


            Log.d(TAG, "signUp: "+jo);

            new Singingup().execute();

            //Toast.makeText(Registration.this,"all ok", Toast.LENGTH_SHORT).show();
        }
    }
    private class Singingup extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Registration.this);
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
                    //Toast.makeText(Registration.this,"all ok", Toast.LENGTH_SHORT).show();


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
                Toast.makeText(Registration.this,responseData, Toast.LENGTH_SHORT).show();
                Intent Login = new Intent(Registration.this,Login.class);
                startActivity(Login);

            }


        }



    }
}
