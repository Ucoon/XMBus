package com.coolweather.xmbus.Activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.coolweather.xmbus.Activity.mFragment.mFragment0;
import com.coolweather.xmbus.Activity.mFragment.mFragment1;
import com.coolweather.xmbus.Activity.mFragment.mFragment2;
import com.coolweather.xmbus.Activity.mFragment.mFragment3;
import com.coolweather.xmbus.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }
    private void init(){
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);
        String[] titles={"关注","搜索","周边","更多"};
        List<Fragment> fragments=new ArrayList<>();

        for(int i=0;i<4;i++){

            switch (i){
                case 0:
                    tabLayout.addTab(tabLayout.newTab().setText(titles[0]));
                    mFragment0 mfragment0=new mFragment0();
                    fragments.add(mfragment0);


                    break;
                case 1:
                    tabLayout.addTab(tabLayout.newTab().setText(titles[1]));
                    mFragment1 mfragment1=new mFragment1();
                    fragments.add(mfragment1);
                    break;
                case 2:
                    tabLayout.addTab(tabLayout.newTab().setText(titles[2]));
                    mFragment2 mfragment2=new mFragment2();
                    fragments.add(mfragment2);
                    break;
                case 3:
                    tabLayout.addTab(tabLayout.newTab().setText(titles[3]));
                    mFragment3 mfragment3=new mFragment3();
                    fragments.add(mfragment3);
                    break;
                default:break;
            }
        }
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewPager);
        mViewPagerAdapter mViewPagerAdapter=new mViewPagerAdapter(getSupportFragmentManager(),titles,fragments);
        viewPager.setAdapter(mViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }


}
