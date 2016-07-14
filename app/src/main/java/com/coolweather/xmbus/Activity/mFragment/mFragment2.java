package com.coolweather.xmbus.Activity.mFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.coolweather.xmbus.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZongJie on 2016/6/4.
 */
public class mFragment2 extends Fragment{
    private AMapLocationClient aMapLocationClient=null;
    private AMapLocationListener aMapLocationListener;
    private AMapLocationClientOption aMapLocationClientOption=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mfragment2,container,false);
        /*aMapLocationClient=new AMapLocationClient(getContext());
        aMapLocationClientOption=new AMapLocationClientOption();
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        aMapLocationClientOption.setNeedAddress(true);
        aMapLocationClientOption.setOnceLocation(false);
        aMapLocationClientOption.setWifiActiveScan(true);
        aMapLocationClientOption.setMockEnable(false);
        aMapLocationClientOption.setInterval(2000);
        aMapLocationClient.setLocationOption(aMapLocationClientOption);
        aMapLocationClient.startLocation();
        aMapLocationListener=new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if(aMapLocation!=null){
                    if(aMapLocation.getErrorCode()==0){
                        aMapLocation.getLocationType();
                        aMapLocation.getLatitude();
                        aMapLocation.getLongitude();
                        aMapLocation.getAccuracy();
                        SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd HH:m:s");
                        Date date=new Date(aMapLocation.getTime());
                        ft.format(date);
                        aMapLocation.getCountry();
                        aMapLocation.getProvince();
                        aMapLocation.getCity();
                        aMapLocation.getDistrict();
                        aMapLocation.getStreet();
                        aMapLocation.getStreetNum();
                        aMapLocation.getCityCode();
                        aMapLocation.getAdCode();
                        aMapLocation.getAoiName();
                    }else{
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }

            }
        };
        aMapLocationClient.setLocationListener(aMapLocationListener);*/

        return view;
    }


}

