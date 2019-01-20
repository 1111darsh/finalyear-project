package com.escrow.smartexamination.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.escrow.smartexamination.Adapter.PaperDBHelper;
import com.escrow.smartexamination.Adapter.Utils;
import com.escrow.smartexamination.Fragement.BlankFragment;
import com.escrow.smartexamination.R;

public class Paper extends AppCompatActivity {

    private static final String TAG ="Paper" ;
    /**This is for the display question on the view**/
    PaperDBHelper dbHelper=new PaperDBHelper(this);
    SQLiteDatabase db;
    String userAnswer;
    TextView tQid;
    TextView tQuastion;
    RadioButton ra;
    RadioButton rb;
    RadioButton rc;
    RadioButton rd;
    Button buttonNext,buttonPrevious;
    String value;
    Cursor cursor;
    int va;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper);
        Log.d(TAG, "onCreate: "+"hello world");

        /*This is for the getting answer from radio button using Radio group*/
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioAnswer);
        Log.d(TAG, "onCreate: "+radioGroup.getCheckedRadioButtonId());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                userAnswer =((RadioButton)findViewById(group.getCheckedRadioButtonId()))
                        .getText().toString();
                Log.d(TAG, "onCreate: "+group.getCheckedRadioButtonId()+ "Check id "+checkedId+userAnswer);
                // Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
            }
        });


        /** This is for get Intent Extra value form the another (Main activity/Question Display activity) a**/
        Intent mIntent = getIntent();
        value = mIntent.getStringExtra("Question");
        String row =mIntent.getStringExtra("row");


        if(value==null)
        {
            va=0;
        }
        else {

            va = Integer.parseInt(value);
        }
        Log.d(TAG, "value: question " + value + row);
        //va=va-1;
        Log.d(TAG, "onCreate: va "+va);


        /*if(Utils.RecycleViewClick) {
            value = mIntent.getStringExtra("Question");
            va = Integer.parseInt(value);
            va=va-1;
            Log.d(TAG, "value: question " + value);
            Utils.RecycleViewClick=false;

        }*/

        db=dbHelper.getWritableDatabase();// for updating a answer in database

        /**Next button**/

        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues cv = new ContentValues();
                cv.put("StudANS", userAnswer);
                Log.d(TAG, "answer cv cv "+userAnswer+cv);
                Log.d(TAG, "Qid+va  Qid="+Integer.toString(va+1));//va is a index of a question Qid +1 than va
                db.update("paper",cv,"Qid="+Integer.toString(va+1),null);

                Intent intent = new Intent(Paper.this,Paper.class);
                va=va+1;
                intent.putExtra("Question", Integer.toString(va));
                Log.d(TAG, "onClick: intent "+intent);
                startActivity(intent);

            }
        });


        /** Previous Button **/
        buttonPrevious=(Button)findViewById(R.id.buttonPrevious);
        if(va==0){
            buttonPrevious.setVisibility(View.INVISIBLE);
        }
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues cv = new ContentValues();
                cv.put("ANS", userAnswer);
                Log.d(TAG, "answer cv cv "+userAnswer+cv);
                Log.d(TAG, "Qid+va  Qid="+Integer.toString(va+1));//va is a index of a question Qid +1 than va
                db.update("paper",cv,"Qid="+Integer.toString(va+1),null);

                Log.d(TAG, "answer "+userAnswer);
                Intent intent = new Intent(Paper.this,Paper.class);
                va=va-1;
                intent.putExtra("Question", Integer.toString(va));
                Log.d(TAG, "onClick: intent "+intent);
                startActivity(intent);

            }
        });


        /**This is for the display question on the view**/
        db=dbHelper.getReadableDatabase();
        //String Projaction[]={"QuestionNo","Question","A","B","C","D","StudANS","Qid","ANS"};
        String Projaction[]={"QuestionNo","Question","A","B","C","D","ANS"};
        cursor=db.query("paper",Projaction,null,null,null,null,null);

        cursor.moveToPosition(va);

        Log.d(TAG, "Qid  " + cursor.getString(0) + "  Question  " + cursor.getString(1) + "  A  " + cursor.getString(2) + "  b  " + cursor.getString(3) + "\n");

        tQid = (TextView) findViewById(R.id.Qid);
        tQuastion = (TextView) findViewById(R.id.Quastion);
        ra = (RadioButton) findViewById(R.id.a);
        rb = (RadioButton) findViewById(R.id.b);
        rc = (RadioButton) findViewById(R.id.c);
        rd = (RadioButton) findViewById(R.id.d);

        tQid.setText(cursor.getString(0));
        tQuastion.setText(cursor.getString(1));
        ra.setText(cursor.getString(2));
        rb.setText(cursor.getString(3));
        rc.setText(cursor.getString(4));
        rd.setText(cursor.getString(5));

        Log.d(TAG, "database stored answer "+cursor.getString(6)+ra.getText());
        //ra.setChecked(true);
        String s = cursor.getString(6);
        String a= ra.getText().toString();
        String b= rb.getText().toString();
        String c= rc.getText().toString();
        String d= rd.getText().toString();

        if(s.equals(a)) {
            ra.setChecked(true);
        }
        else if(s.equals(b)){
            rb.setChecked(true);
        }
        else if(s.equals(c)){
            rc.setChecked(true);
        }
        else if(s.equals(d)){
            rd.setChecked(true);
        }





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Que:
                Intent intent = new Intent(Paper.this,QuestionList.class);
                startActivity(intent);
                return true;
            case R.id.Submit:
                Intent intentl = new Intent(Paper.this,Submit.class);
                startActivity(intentl);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
