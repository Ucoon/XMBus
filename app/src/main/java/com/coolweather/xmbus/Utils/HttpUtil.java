package com.coolweather.xmbus.Utils;

import com.coolweather.xmbus.Utils.HttpCallBackListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ZongJie on 2016/5/22.
 */
public class HttpUtil {
    public static void sendHttpRequestWithHttpURLConnection(final String address,
                                                            final HttpCallBackListener listener){
    //开启线程发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try{
                    URL url=new URL(address);
                    connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in=connection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
                    StringBuilder response=new StringBuilder();
                    String line;
                    while((line=bufferedReader.readLine())!=null){
                        response.append(line);
                    }
                    if(listener!=null){
                        listener.onFinish(response.toString());
                    }
                }catch(Exception e){
                    listener.onError(e);
                }
                finally{
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


}
