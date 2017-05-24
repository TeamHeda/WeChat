package com.henu.example.com.wechat;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;

import com.alibaba.fastjson.JSONObject;
import com.henu.example.com.wechat.main.db.TopUser;
import com.henu.example.com.wechat.main.db.TopUserDao;
import com.henu.example.com.wechat.main.utils.CrashHandler;
import com.henu.example.com.wechat.main.utils.LocalDataUtils;
import com.henu.example.com.wechat.main.utils.OkHttpManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by b on 2017/5/15.
 */

public class WeChatApplication extends Application {
    public static Context applicationContext;
    private static WeChatApplication instance;
    // login user name
    public final String PREF_USERNAME = "username";
    public static String currentUserNick = "";
    private JSONObject userJson;
    private Map<String, TopUser> topUsers;
    private String time="";

    private DisplayMetrics displayMetrics = null;
    private List<Activity> activities=new ArrayList<>();
    public static WeChatApplication getApp() {
        if (instance != null && instance instanceof WeChatApplication) {
            return (WeChatApplication) instance;
        } else {
            instance = new WeChatApplication();
            instance.onCreate();
            return (WeChatApplication) instance;
        }
    }

    public static boolean sRunningOnIceCreamSandwich;

    static {
        sRunningOnIceCreamSandwich = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }
    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
        applicationContext = this;
        instance = this;
        OkHttpManager.init(instance);
        LocalDataUtils.init(instance);
        Helper.getInstance().init(applicationContext);
        //red packet code : 初始化红包上下文，开启日志输出开关




        getCrashHandler(applicationContext);
    }
    public static CrashHandler getCrashHandler(Context context) {
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(context);
        return crashHandler;
    }
    public static WeChatApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public  void setUserJson( JSONObject userJson){

        this.userJson=userJson;
        LocalDataUtils.getInstance().setUserJson(userJson);

    }
    public JSONObject getUserJson(){
        if(userJson==null){
            userJson= LocalDataUtils.getInstance().getUserJson();
        }
        return  userJson;
    }
    public String getTime(){
        return time;

    }
    public void setTime(String time){
        this.time=time;
    }
    /**
     * 获取置顶列表
     */
    public Map<String, TopUser> getTopUserList() {
        if(topUsers==null){
            TopUserDao dao = new TopUserDao(instance);
            topUsers=dao.getTopUserList();
        }
        return topUsers;
    }
    /*
    * 设置置顶列表
    * */
    public void saveTopUserList( Map<String, TopUser> topUsers) {
        this.topUsers=topUsers;
        TopUserDao dao = new TopUserDao(instance);
        dao.saveTopUserList(new ArrayList<TopUser>(topUsers.values()));
    }


    public static int getRandomStreamId() {
        Random random = new Random();
        int randint =(int)Math.floor((random.nextDouble()*10000.0 + 10000.0));
        return randint;
    }

    public float getScreenDensity() {
        if (this.displayMetrics == null) {
            setDisplayMetrics(getResources().getDisplayMetrics());
        }
        return this.displayMetrics.density;
    }

    public int getScreenHeight() {
        if (this.displayMetrics == null) {
            setDisplayMetrics(getResources().getDisplayMetrics());
        }
        return this.displayMetrics.heightPixels;
    }

    public int getScreenWidth() {
        if (this.displayMetrics == null) {
            setDisplayMetrics(getResources().getDisplayMetrics());
        }
        return this.displayMetrics.widthPixels;
    }

    public void setDisplayMetrics(DisplayMetrics DisplayMetrics) {
        this.displayMetrics = DisplayMetrics;
    }

    public int dp2px(float f)
    {
        return (int)(0.5F + f * getScreenDensity());
    }

    public int px2dp(float pxValue) {
        return (int) (pxValue / getScreenDensity() + 0.5f);
    }

    //获取应用的data/data/....File目录
    public String getFilesDirPath() {
        return getFilesDir().getAbsolutePath();
    }

    //获取应用的data/data/....Cache目录
    public String getCacheDirPath() {
        return getCacheDir().getAbsolutePath();
    }

    public  void saveActivity(Activity activity){
        if(activity!=null){
            activities.add(activity);
        }

    }

    public  void finishActivities(){
        for(Activity activity:activities){
            if(activity!=null&&!activity.isFinishing()){
                activity.finish();
            }
        }

    }
}
