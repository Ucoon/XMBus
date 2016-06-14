package com.coolweather.xmbus.Activity.mFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.coolweather.xmbus.Activity.Concern;
import com.coolweather.xmbus.Activity.ShowStation;
import com.coolweather.xmbus.Activity.myFragment;
import com.coolweather.xmbus.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZongJie on 2016/6/4.
 */
public class mFragment0 extends Fragment {
    @Bind(R.id.text_add)
    TextView textAdd;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> datalist=new ArrayList<>();
    String lineNo="";
    String departureStation="";
    String terminalStation="";
    String lineIDReceive;
    String direction;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mfragment0, container, false);
        ButterKnife.bind(this, rootView);

        getResource();
        adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,datalist);
        listView= (ListView)rootView.findViewById(R.id.listView_concern);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getContext(), ShowStation.class);
                intent.putExtra("lineID", lineIDReceive);
                intent.putExtra("direction", direction);
                startActivity(intent);
            }
        });
        if(savedInstanceState!=null){
            String lineNo=savedInstanceState.getString("lineNo");
            String departureStation=savedInstanceState.getString("departureStation");
            String terminalStation=savedInstanceState.getString("terminalStation");
            datalist.add(lineNo + " " + departureStation + "→" + terminalStation);
        }
        return rootView;
    }


    private void getResource(){

        Intent intent=getActivity().getIntent();
        lineNo=intent.getStringExtra("lineNo");
        departureStation=intent.getStringExtra("departureStation");
        terminalStation = intent.getStringExtra("terminalStation");
        lineIDReceive=intent.getStringExtra("lineID");
        direction = intent.getStringExtra("direction");
        datalist.add(lineNo + " " + departureStation + "→" + terminalStation);
        Log.e("ss",datalist+"");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("lineNo", lineNo);
        outState.putString("depatrureStation", departureStation);
        outState.putString("lineIDReceive", lineIDReceive);
        outState.putString("direction",direction);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.text_add)
    public void onClick() {
        startActivity(new Intent(getContext(),Concern.class));
    }
}

