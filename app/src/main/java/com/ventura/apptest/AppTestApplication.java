package com.ventura.apptest;

import android.app.Application;
import android.content.Context;

import com.ventura.apptest.data.DaoMaster;
import com.ventura.apptest.data.DaoSession;
import com.ventura.apptest.data.DbOpenHelper;

/**
 * Created by Jorge Ventura on 2019-06-06.
 */
public class AppTestApplication extends Application {

    private static String TAG = "AppTestApplication";

    private static AppTestApplication singleton;
    public static Context context;

    public static synchronized AppTestApplication getInstance(){
        return singleton;
    }


    private DaoSession mDaoSession;

    @Override
    public void onCreate(){
        super.onCreate();
        singleton = this;

        context = AppTestApplication.this;

        mDaoSession = new DaoMaster(new DbOpenHelper(this, "greendao_places.db").getWritableDb()).newSession();


    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }


}
