package com.coolweather.xmbus.Utils;

/**
 * Created by ZongJie on 2016/5/22.
 */
public interface HttpCallBackListener {
    void onFinish(String response);
    void onError(Exception e);
}
