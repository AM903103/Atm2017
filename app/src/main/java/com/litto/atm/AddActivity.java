package com.litto.atm;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private EditText edDate;
    private EditText edInfo;
    private EditText edAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        edDate = findViewById(R.id.add_date);
        edInfo = findViewById(R.id.add_info);
        edAmount = findViewById(R.id.add_amount);
    }

    public void add(View view){
        String date = edDate.getText().toString();
        String info = edInfo.getText().toString();
        int amount = Integer.parseInt(edAmount.getText().toString());
        DbHelper dbHelper = new DbHelper(this);
        ContentValues values = new ContentValues();
        values.put("cdate", date);
        values.put("info", info);
        values.put("amount", amount);
        long id = dbHelper.getWritableDatabase()
                .insert("exp", null, values);
        if (id != -1){
            Toast.makeText(this,
                    "新增成功", Toast.LENGTH_LONG).show();
        }
    }
}
