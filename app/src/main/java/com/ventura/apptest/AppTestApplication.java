package com.ventura.apptest;

import android.app.Application;
import android.content.Context;

import com.ventura.apptest.data.DaoMaster;
import com.ventura.apptest.data.DaoSession;
import com.ventura.apptest.data.DbOpenHelper;
import com.ventura.apptest.data.User;

/**
 * Created by Jorge Ventura on 2019-06-06.
 */
public class AppTestApplication extends Application {

    private static String TAG = "GoQuickApplication";

    //public static ThreadExecutor threadExecutor;
    //public static MainThreadExecutor mainThreadExecutor;
    //public static AccountEntity account;

    private static AppTestApplication singleton;
    public static Context context;

    public static final boolean DEBUG = true;

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

        // USER CREATION FOR DEMO PURPOSE
        if(mDaoSession.getUserDao().loadAll().size() == 0){
            mDaoSession.getUserDao().insert(new User(1L, "Janishar Ali","", ""));
        }

        //threadExecutor = new ThreadExecutor();
        //mainThreadExecutor = new MainThreadExecutorImp();

        //PREF_TAXIAPP = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);

        //Fabric.with(this, new Crashlytics());

        //getAndroidID();

    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }


}
