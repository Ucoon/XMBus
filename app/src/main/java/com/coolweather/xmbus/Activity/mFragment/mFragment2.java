package com.coolweather.xmbus.Activity.mFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.coolweather.xmbus.R;

/**
 * Created by ZongJie on 2016/6/4.
 */
public class mFragment2 extends Fragment implements LocationSource,AMapLocationListener,
        CompoundButton.OnCheckedChangeListener{
    private MapView mapView;
    private AMap aMap;
    private OnLocationChangedListener mlistener;
    private AMapLocationClient aMapLocationClient;
    private AMapLocationClientOption aMapLocationClientOption;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.mfragment2,container,false);
        mapView= (MapView) rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);//实现地图生命周期管理
        init();
        Log.e("sss", "111");
        return rootView;
    }
    private void init(){
        if(aMap==null){
            Log.e("sss","222");
            aMap=mapView.getMap();
            setUpMap();
            Log.e("sss","ssss");
        }
    }
    private void setUpMap(){
        MyLocationStyle myLocationStyle=new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker));
        myLocationStyle.strokeColor(Color.BLACK);
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));
        myLocationStyle.strokeWidth(0.5f);
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    //定位成功后回调函数
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if(mlistener!=null&&aMapLocation!=null){
            if(aMapLocation!=null&&aMapLocation.getErrorCode()==0){
                mlistener.onLocationChanged(aMapLocation);
            }else {
                String errtext="定位失败"+aMapLocation.getErrorCode()+":"+aMapLocation.getErrorInfo();
                Log.e("AmapErr",errtext);
            }
        }

    }
    //激活定位
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
       mlistener=onLocationChangedListener;
        if(aMapLocationClient!=null){
            aMapLocationClient=new AMapLocationClient(getContext());
            aMapLocationClientOption=new AMapLocationClientOption();
            aMapLocationClient.setLocationListener(this);
            aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            aMapLocationClient.setLocationOption(aMapLocationClientOption);
            aMapLocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mlistener=null;
        if(aMapLocationClient!=null){
            aMapLocationClient.stopLocation();
            aMapLocationClient.onDestroy();
        }
        aMapLocationClient=null;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
