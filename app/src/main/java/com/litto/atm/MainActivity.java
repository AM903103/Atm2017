package com.litto.atm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    int[] icons = {R.drawable.func_balance,
            R.drawable.func_history,
            R.drawable.func_news,
            R.drawable.func_finance,
            R.drawable.func_exit};
    private static final int RC_LOGIN = 95;
    private static final String TAG = MainActivity.class.getSimpleName();
    private boolean logon = false;
    private String[] functions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHelper dbHelper = new DbHelper(this,
                "expense.db", null, 1);
        dbHelper.getReadableDatabase()
                .rawQuery("select 1", null);

//        setupListView();
        functions = getResources().getStringArray(R.array.functions);
        GridView grid = findViewById(R.id.grid);
        IconAdapter adapter = new IconAdapter();
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(this,
//                        android.R.layout.simple_list_item_1,
//                        functions);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // if
        if (!logon) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, RC_LOGIN);
        }
    }

    private void setupListView() {
        ListView list = findViewById(R.id.list);
//        String[] drinks = {"珍奶", "綠茶", "烏龍"};
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        getResources().getStringArray(R.array.drinks));
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: " + position);
                String drink =
                        getResources().getStringArray(R.array.drinks)[position];
                Log.d(TAG, "onItemClick: " + drink);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_LOGIN && resultCode == RESULT_OK) {
            String userid = data.getStringExtra("USERID");
            Log.d(TAG, "onActivityResult: " + userid);
            getSharedPreferences("abc", MODE_PRIVATE)
                    .edit()
                    .putString("USERID", userid)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view,
                            int position, long itemId) {
        Log.d(TAG, "onItemClick: " + position);
        switch ((int)itemId){
            case R.drawable.func_balance:
                break;
            case R.drawable.func_history:
                break;
            case R.drawable.func_news:
                break;
            case R.drawable.func_finance:
                startActivity(new Intent(this, FinanceActivity.class));
                break;
            case R.drawable.func_exit:
                finish();
                break;
        }
    }

    class IconAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return functions.length;
        }

        @Override
        public Object getItem(int position) {
            return functions[position];
        }

        @Override
        public long getItemId(int position) {
            return icons[position];
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
//                LayoutInflater layoutInflater =
//                        LayoutInflater.from(parent.getContext());
                View view = getLayoutInflater()
                        .inflate(R.layout.icon_item, null);
                ImageView image = view.findViewById(R.id.item_image);
                TextView tv = view.findViewById(R.id.item_text);
                tv.setText(functions[position]);
                image.setImageResource(icons[position]);
                convertView = view;
            }

            return convertView;
        }
    }
}
