package com.escrow.smartexamination.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.escrow.smartexamination.Adapter.PaperDBHelper;
import com.escrow.smartexamination.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Submit extends AppCompatActivity {

    private static final String TAG ="" ;
    SQLiteDatabase db;
    Cursor cursor;
    TextView mystring;
    int result=0,NoOfQuestion=0;
    JSONObject jo;//=new JSONObject();
    JSONArray ja = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);


        PaperDBHelper dbHelper=new PaperDBHelper(this);

        String output="";
        db=dbHelper.getReadableDatabase();
        String Projaction[]={"Qid","ANS","StudANS","QuestionNo"};
        //String Projaction[]={"QuestionNo","Question","A","B","C","D","StudANS","Qid","ANS"};
        Log.d(TAG, "onCreate: "+"start submit");
        cursor=db.query("paper",Projaction,null,null,null,null,null);
        cursor.moveToFirst();
            do {
                NoOfQuestion++;
                // Passing values
                String column1 = cursor.getString(0);
                String column2 = cursor.getString(1);
                String column3 = cursor.getString(2);
                String column4 = cursor.getString(3);

                String statesOfAns="false";
                if(column2.equals(column3))
                {
                    statesOfAns="true";
                    result++;

                }
                jo=new JSONObject();
                try {
                    jo.put("Qid",column1);
                    jo.put("statesOfAns",statesOfAns);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /*String column5 = cursor.getString(4);
                String column6 = cursor.getString(5);
                String column7 = cursor.getString(6);
                String column8 = cursor.getString(7);
                String column9 = cursor.getString(8);*/


               // Log.d(TAG, "onCreate: "+column1+column2+column3+column4+column5+column6+column7+"column8+column9");
                // Do something Here with values
                output=output+"\t"+column1+"\n"+column2+"\n"+column3+"\n"+column4+"\t"+statesOfAns+"\n\n"+jo;

                ja.put(jo);
                //output=output+ja;
            } while(cursor.moveToNext());

        output=output+ja+result+NoOfQuestion;
        mystring=(TextView)findViewById(R.id.Mystring);
        mystring.setText(output);

        cursor.close();
        mystring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Result=new Intent(Submit.this,DisplayResult.class);
                Result.putExtra("Result",Integer.toString(result));
                Result.putExtra("NOQ",Integer.toString(NoOfQuestion));

                startActivity(Result);
            }
        });


        //db.close();*/



    }
}
