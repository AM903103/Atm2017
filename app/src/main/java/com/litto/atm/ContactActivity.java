package com.litto.atm;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static android.Manifest.permission.READ_CONTACTS;

public class ContactActivity extends AppCompatActivity {

    private static final int REQUEST_CONTACT = 500;
    private static final String TAG = ContactActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        if (ActivityCompat.checkSelfPermission(this, READ_CONTACTS)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,
                    new String[]{READ_CONTACTS}, REQUEST_CONTACT);
        }else {
            readContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CONTACT &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED){
            readContacts();
        }
    }

    private void readContacts() {
        Log.d(TAG, "readContacts: ");
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
    }
}
