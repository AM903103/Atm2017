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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

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
        ListView list = findViewById(R.id.list);
        Cursor cursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        String[] from = {ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME};
        int[] to = {android.R.id.text1, android.R.id.text2};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                cursor, from , to);
        list.setAdapter(adapter);
    }
}
