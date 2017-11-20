package com.litto.atm;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HistoryActivity extends AppCompatActivity {

    private String TAG = HistoryActivity.class.getSimpleName();
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        list = findViewById(R.id.list);
//        new HistoryTask().execute("http://atm201605.appspot.com/h");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://atm201605.appspot.com/h").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                Log.d(TAG, "onResponse: " + json);
                try {
                    List<Map<String , Object>> data = new ArrayList<>();
                    JSONArray array = new JSONArray(json);
                    for (int i=0 ; i<array.length(); i++){
                        Map<String, Object> map = new HashMap<>();
                        JSONObject obj = array.getJSONObject(i);
                        String account = obj.getString("account");
                        String date = obj.getString("date");
                        int amount = obj.getInt("amount");
                        int type = obj.getInt("type");
                        map.put("account", account);
                        map.put("date", date);
                        map.put("amount", amount);
                        map.put("type", type);
                        data.add(map);
                        Log.d(TAG, "obj: " + account + "/"
                                + date + "/" + amount + "/" + type);
                    }
                    String[] from = {"date", "amount"};
                    int[] to = {android.R.id.text1, android.R.id.text2};
                    final SimpleAdapter adapter = new SimpleAdapter(HistoryActivity.this,
                            data, android.R.layout.simple_list_item_2, from, to);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            list.setAdapter(adapter);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new AlertDialog.Builder(HistoryActivity.this)
                                .setMessage(json)
                                .show();
                    }
                });
            }
        });

    }
    class HistoryTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuffer sb = new StringBuffer();
            try {
                URL url = new URL(strings[0]);
                InputStream is = url.openStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                String data = in.readLine();
                while(data != null){
                    sb.append(data);
                    data = in.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: "+s);
        }
    }
}
