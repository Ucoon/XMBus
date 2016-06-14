package com.coolweather.xmbus.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
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
 * Created by ZongJie on 2016/5/23.
 */
public class ShowStation extends AppCompatActivity {
    private ListView list;
    private ArrayAdapter<String> adapter;
    private List<String> datalist=new ArrayList<String>();
    private String address;
    String lineIDRecive;
    String direction;
    String lineNo;
    String departureStation;
    String terminalStation;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.showstations);
        init();
        Intent intent=this.getIntent();
        lineIDRecive=intent.getStringExtra("lineID");
       direction=intent.getStringExtra("direction");
        address = "http://api.diviniti.cn/amoy/bus/current/" + lineIDRecive + "/" + direction;
        queryFromServe(address);


    }

    private void init(){
        list= (ListView) findViewById(R.id.list_item);
        adapter=new ArrayAdapter<String>(ShowStation.this,android.R.layout.simple_list_item_1,datalist);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("lineNo",lineNo);
                intent.putExtra("departureStation",departureStation);
                intent.putExtra("terminalStation",terminalStation);
                intent.putExtra("name",name);
                intent.putExtra("lineID",lineIDRecive);
                intent.putExtra("direction",direction);
                startActivity(intent);
                finish();
            }
        });
    }
    private void queryFromServe(final String address){
        HttpUtil.sendHttpRequestWithHttpURLConnection(address, new HttpCallBackListener() {
            @Override
            public void onFinish(final String response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        StationResponse(ShowStation.this, response);
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(getApplicationContext(),"网络出错...",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void StationResponse(Context context,String response){
        try{
            JSONObject jsonObject=new JSONObject(response);
            boolean status=jsonObject.getBoolean("status");
            JSONObject jsonObject1=jsonObject.getJSONObject("lineDescription");
             lineNo=jsonObject1.getString("lineNo");
            departureStation=jsonObject1.getString("departureStation");
            terminalStation=jsonObject1.getString("terminalStation");
            JSONObject jsonObject2=jsonObject.getJSONObject("schedule");
            String dailyStartTime=jsonObject2.getString("dailyStartTime");
            String dailyEndTime=jsonObject2.getString("dailyEndTime");
            String nextDepartTime=jsonObject2.getString("nextDepartTime");
            int totalStations=jsonObject.getInt("totalStations");
            JSONArray stations=jsonObject.getJSONArray("stations");
            JSONArray jsonArray=stations;
            if(status==true) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject3 = jsonArray.getJSONObject(i);
                    int no = jsonObject3.getInt("no");
                     name = jsonObject3.getString("name");
                    String statu = jsonObject3.getString("status");
                    datalist.add(no + name + statu);
                    adapter.notifyDataSetChanged();
                    list.setSelection(0);
                }
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
       Intent intent=new Intent();
        intent.putExtra("lineID", lineIDRecive);
        setResult(RESULT_OK,intent);
        finish();
    }
}
