package com.litto.atm;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class FinanceActivity extends AppCompatActivity {

    private static final String TAG = FinanceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        ListView list = findViewById(R.id.list);
        DbHelper dbHelper = new DbHelper(this);
        Cursor cursor = dbHelper.getReadableDatabase()
                .query("exp", null, null, null,
                        null, null, null);
        String[] from = {"cdate", "info", "amount"};
        int[] to = {R.id.row_date, R.id.row_info, R.id.row_amount};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
//                android.R.layout.simple_list_item_2,
                R.layout.row,
                cursor,
                from, to,
//                new String[]{"cdate", "info"},
//                new int[]{android.R.id.text1, android.R.id.text2},
                0
        );
        list.setAdapter(adapter);

        /*while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String cdate = cursor.getString(cursor.getColumnIndex("cdate"));
            String info = cursor.getString(cursor.getColumnIndex("info"));
            int amount = cursor.getInt(cursor.getColumnIndex("amount"));
            Log.d(TAG, "onCreate: " + id+"/"+cdate+"/"+info+"/"+amount);
        }*/


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinanceActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

}
