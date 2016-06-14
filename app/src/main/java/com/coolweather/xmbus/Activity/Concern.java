package com.coolweather.xmbus.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.coolweather.xmbus.R;
import com.coolweather.xmbus.Utils.FontManager;

import java.util.ArrayList;

/**
 * Created by ZongJie on 2016/6/5.
 */
public class Concern extends AppCompatActivity {
    private EditText edt_input;
    private TextView search_tv;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Typeface iconFont= FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.add), iconFont);
        setContentView(R.layout.add_concern);
        init();

    }
    private void init(){
        edt_input= (EditText) findViewById(R.id.edt_input);
        search_tv= (TextView) findViewById(R.id.tv1);
        search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), myFragment.class);
                String lineNo = edt_input.getText().toString();
                intent.putExtra("lineNO", lineNo);
                startActivity(intent);
            }
        });
    }
}
