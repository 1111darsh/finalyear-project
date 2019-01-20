package com.escrow.smartexamination.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.escrow.smartexamination.Adapter.RecyclerViewAdapter;
import com.escrow.smartexamination.R;

import java.util.ArrayList;

public class QuestionList extends AppCompatActivity {

    private static final String TAG = "QuestionList";
    //vars


    private ArrayList<String> mqnum = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        Log.d(TAG, "onCreate: started.");
        intiqusBitmaps();
    }

    private void intiqusBitmaps() {
        Log.d(TAG, "intiqusBitmaps: preparing bitmaps.");

        for (int i = 1; i <= 5; i++) {
            mqnum.add("Question " + i);
        }
        initRecyclerView();
    }

    private  void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView=findViewById(R.id.RecyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,mqnum);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



}
