package com.coolweather.xmbus.Activity.mFragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.coolweather.xmbus.Activity.myFragment;
import com.coolweather.xmbus.R;
import com.coolweather.xmbus.Utils.FontManager;

/**
 * Created by ZongJie on 2016/6/4.
 */
public class mFragment1 extends Fragment {
    private EditText editText;
    private Button btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.mfragment1,container,false);
        Typeface iconFont= FontManager.getTypeface(getContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(rootView.findViewById(R.id.con),iconFont);
        editText= (EditText)rootView.findViewById(R.id.edt);
        btn= (Button)rootView.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),myFragment.class);
                String lineNo=editText.getText().toString();
                intent.putExtra("lineNO",lineNo);
                startActivity(intent);
            }
        });
        return rootView;
    }


}
