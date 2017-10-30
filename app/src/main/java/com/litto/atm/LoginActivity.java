package com.litto.atm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
