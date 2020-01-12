package com.kit.calendar.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import com.kit.utils.L;

import org.json.JSONArray;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.kit.calendar.bean.CalendarConstants.CALENDAR_L_TITLE;

public class CalendarNetUtils {
    //这两个地址是直接使用ip访问的，域名备案比较麻烦，没有备案，希望下载的此项目的小伙伴不要拿这个ip干什么。
    // 如果学生需要拿服务器测试某些内容，可以直接联系我，我可以开一个权限访问服务器，如果有ip滥用的情况，我会直接关掉此台服务器，希望大家可以相互信任。
    public final static String URL_FESTIVAL = "http://114.116.149.238:8080/getFestival";
    public final static String URL_HOLIDAY = "http://114.116.149.238:8080/getHoliday";
    private static ThreadPoolExecutor threadPoolExecutor;


    /**
     * 获取网络节日数据,customUrl为自己的网络地址
     * @param customFestivalUrl 获取节日信息的网络地址
     * @param customHoliday 获取放假信息的网络地址
     */
    public static void getHolidayAndFestival(String customFestivalUrl, String customHoliday, final Application application, boolean isSkipIfHaveCache){
        if (application == null){
            return;
        }
        if (customFestivalUrl == null){
            customFestivalUrl = URL_FESTIVAL;
        }
        if (customHoliday == null){
            customHoliday = URL_HOLIDAY;
        }

        if (threadPoolExecutor == null){
            threadPoolExecutor = new ThreadPoolExecutor(3,5,1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(100));
        }

        final SharedPreferences sp = application.getSharedPreferences("CalendarNetUtils",Application.MODE_PRIVATE);

        if (!sp.getBoolean("hadInitFestival",false) || !isSkipIfHaveCache){
            //获取节日信息
            OkHttpClient okHttpClient = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(customFestivalUrl)
                    .get()//默认就是GET请求，可以不写
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    L.e(CALENDAR_L_TITLE,"节日网络数据访问失败："+e.getLocalizedMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result = response.body().string();
                    //将数据写入本地
                    try{
                        FileOutputStream fileInputStream = application.openFileOutput("festival.json", Context.MODE_PRIVATE);
                        fileInputStream.write(result.getBytes());
                        fileInputStream.close();

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("hadInitFestival",true);
                        editor.apply();
                    }catch (Exception e){
                        L.e(CALENDAR_L_TITLE,"网络节日数据写入失败："+e.getLocalizedMessage());
                    }
                }
            });
        }

        if (!sp.getBoolean("hadInitHoliday",false) || !isSkipIfHaveCache){
            //获取放假信息
            OkHttpClient okHttpClientTwo = new OkHttpClient();
            final Request requestTwo = new Request.Builder()
                    .url(customHoliday)
                    .get()//默认就是GET请求，可以不写
                    .build();
            okHttpClientTwo.newCall(requestTwo).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    L.e(CALENDAR_L_TITLE,"假期网络数据访问失败："+e.getLocalizedMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result = response.body().string();
                    //将数据写入本地
                    try{
                        final JSONArray jsonArray = new JSONArray(result);
                        for (int i=0;i<jsonArray.length();i++){
                            final String name = jsonArray.getJSONObject(i).getString("fileName");
                            Log.e("日志","名字为："+name);
                            final byte[] data = jsonArray.getJSONObject(i).getJSONObject("data").toString().getBytes();
                            Runnable runnable = new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        FileOutputStream fileInputStream = application.openFileOutput(name, Context.MODE_PRIVATE);
                                        fileInputStream.write(data);
                                        fileInputStream.close();
                                    }catch (Exception e){
                                        L.e(CALENDAR_L_TITLE,"假期数据写入失败："+e.getLocalizedMessage());
                                    }
                                }
                            };
                            threadPoolExecutor.execute(runnable);
                        }

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("hadInitHoliday",true);
                        editor.apply();

                    }catch (Exception e){
                        L.e(CALENDAR_L_TITLE,"假期数据写入失败："+e.getLocalizedMessage());
                    }
                }
            });
        }
    }


    /**
     * 使用默认连接配置
     * @param application
     */
    public static void getHolidayAndFestival(Application application){
        getHolidayAndFestival(null,null,application,true);
    }

    /**
     * 使用默认连接配置
     * @param application
     */
    public static void getHolidayAndFestival(Application application,boolean isSkipIfHaveCache){
        getHolidayAndFestival(null,null,application,isSkipIfHaveCache);
    }
}
