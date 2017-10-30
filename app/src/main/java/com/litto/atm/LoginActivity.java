package com.litto.atm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //
        String userid = getSharedPreferences("abc", MODE_PRIVATE)
                .getString("USERID", null);
        Log.d(TAG, "onCreate: " + userid);
        EditText edUserid = findViewById(R.id.ed_userid);
        edUserid.setText(userid);
    }

    public void login(View view){
        EditText edUserid = findViewById(R.id.ed_userid);
        EditText edPasswd = findViewById(R.id.ed_passwd);
        String userid = edUserid.getText().toString();
        String passwd = edPasswd.getText().toString();
        if ("jack".equals(userid) &&
                "1234".equals(passwd) ){
            getIntent().putExtra("USERID", userid);
            setResult(RESULT_OK, getIntent());
            finish();
        }
    }

    public void quit(View view){

    }
}
