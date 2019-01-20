package com.escrow.smartexamination.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PaperDBHelper extends SQLiteOpenHelper {



    public PaperDBHelper(Context context) {
        super(context, "Paper.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("Create table paper (QuestionNo TEXT,Question TEXT,A TEXT,B TEXT, C TEXT, D TEXT , StudANS TEXT, Qid TEXT, ANS TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists paper");
        onCreate(sqLiteDatabase);

    }


}
