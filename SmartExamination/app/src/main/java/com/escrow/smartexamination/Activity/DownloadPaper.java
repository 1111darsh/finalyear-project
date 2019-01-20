package com.escrow.smartexamination.Activity;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.escrow.smartexamination.Adapter.PaperDBHelper;
import com.escrow.smartexamination.Adapter.Url;
import com.escrow.smartexamination.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DownloadPaper extends AppCompatActivity {

    String Qid ;
    String Quastion ;
    PaperDBHelper dbHelper=new PaperDBHelper(this);
    SQLiteDatabase db;


    String a ;
    String b ;
    String c;
    String d ;
    String ans;
    private String TAG ="";

    String value;
    int TotalQuestion;
    Url u=new Url(); //This is a class in adapter for set ip
    public String url;
    private ProgressDialog pDialog;


    // URL to get contacts JSON
    //private static String url = "https://raw.githubusercontent.com/1111darsh/Escrow/master/Paper.json";

    //public String url= "http://192.168.43.95/SmartExamination/paper.php";
    JSONObject jo = new JSONObject();




    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_paper);
        contactList = new ArrayList<>();
        url=u.getUrl()+"/SmartExamination/paper.php";

        // Log.d(TAG, "onCreate: question "+passedArg);

        new GetContacts().execute();


    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(DownloadPaper.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();



        }

        @Override
        protected Void doInBackground(Void... arg0) {
            /**This is using okHttp **/
            OkHttpClient client = new OkHttpClient();


            try {
                jo.put("Subject","c");
                jo.put("NOQ","5");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "request: "+jo);

            RequestBody body = RequestBody.create(JSON, String.valueOf(jo));

            Request request = new Request.Builder()
                    .url(url).post(body)
                    .build();
            Log.d(TAG, "doInBackground: "+body);
            Log.d(TAG, "request "+request);



            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, "IOException"+e);
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {


                    try {
                        String responseData = response.body().string();
                        Log.d(TAG, "onResponse: "+responseData);

                        JSONObject json = new JSONObject(responseData);
                        JSONArray Paper = json.getJSONArray("Question_Paper");
                        //JSONObject paper1 = Paper.getJSONObject(0);
                        db = dbHelper.getWritableDatabase();
                        db.delete("paper", null, null);
                        for(int i=0;i<Paper.length();i++) {
                            JSONObject paper1 = Paper.getJSONObject(i);
                            Log.d(TAG, "onResponse: " + paper1);

                            Qid = paper1.getString("Qid");
                            Quastion = paper1.getString("Question");

                            a = paper1.getString("a");
                            b = paper1.getString("b");
                            c = paper1.getString("c");
                            d = paper1.getString("d");
                            ans=paper1.getString("ans");
                            Log.d(TAG, "onResponse: " + a + b + c + d + ans );
                            ContentValues values = new ContentValues();
                            String QuestionNo=Integer.toString(i+1);

                            values.put("Qid", Qid);
                            values.put("Question", Quastion);
                            values.put("A", a);
                            values.put("B", b);
                            values.put("C", c);
                            values.put("D", d);
                            values.put("ANS", ans);
                            if(i%2!=1)
                                values.put("StudANS",ans);
                            else
                                values.put("StudANS","nul");
                            values.put("QuestionNo",QuestionNo);
                            //long raw=db.getMaximumSize();


                            long row = db.insert("paper", null, values);
                            Log.d(TAG, "onResponse: raw" + row);

                        }

                    } catch (JSONException e) {
                        Log.d(TAG, "onResponse: "+e);
                    }
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

                Intent next=new Intent(DownloadPaper.this,Paper.class);
                //Log.d(TAG, "raw" + TotalQuestion);
                //next.putExtra("number of question",Integer.toString(TotalQuestion));
                startActivity(next);
            }


        }
    }
}
