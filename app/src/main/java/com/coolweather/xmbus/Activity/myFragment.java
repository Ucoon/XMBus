package com.coolweather.xmbus.Activity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.coolweather.xmbus.R;
import com.coolweather.xmbus.Utils.HttpCallBackListener;
import com.coolweather.xmbus.Utils.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZongJie on 2016/5/22.
 */
public class myFragment extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<String>();
    String address;
    private List<String> lineID=new ArrayList<String>();
    private List<String> direction=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        initial();
        Intent intent = this.getIntent();
       String NO = intent.getStringExtra("lineNO");
        address = "http://api.diviniti.cn/amoy/bus/line/"+ NO;
        String url= Uri.encode(address,"-![.:/,%?&=]");
        queryFromServer(url, NO);

    }

    private void initial() {
        listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(myFragment.this,ShowStation.class);
                intent.putExtra("lineID", lineID.get(position));
                intent.putExtra("direction", direction.get(position));
                startActivityForResult(intent, 1);
            }
        });
    }

    private void queryFromServer(final String address, final String NO) {
        HttpUtil.sendHttpRequestWithHttpURLConnection(address, new HttpCallBackListener() {
            @Override
            public void onFinish(final String response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        handleBusResponse(myFragment.this, response);

                    }
                });
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(getApplicationContext(),"网络炸啦",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void handleBusResponse(Context context, String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            boolean status = jsonObject.getBoolean("status");
            Log.e("status",status+"");
            int total = jsonObject.getInt("total");
            JSONArray results = jsonObject.getJSONArray("results");
            JSONArray jsonArray = results;
            if (status == true) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String lineid = jsonObject1.getString("lineID");
                    lineID.add(lineid);
                    String direction1 = jsonObject1.getString("direction");
                    direction.add(direction1);
                    JSONObject detail = jsonObject1.getJSONObject("detail");
                    String name = detail.getString("name");
                    String startStation = detail.getString("startStation");
                    String endStation = detail.getString("endStation");
                    dataList.add(name + startStation + "开往" + endStation+direction1);
                    adapter.notifyDataSetChanged();
                    listView.setSelection(0);
                }
            }else if(status==false){
                Toast.makeText(getApplicationContext(),"网络炸啦",Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String lineID1=data.getStringExtra("lineID");
                    Log.e("lineID1",lineID1);
                }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(myFragment.this, MainActivity.class));
    }
}
