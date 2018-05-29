
package com.example.lenovo.moni_ljq_529.app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.lenovo.moni_ljq_529.DaoMaster;
import com.example.lenovo.moni_ljq_529.DaoSession;


public class App extends Application {

    private DaoSession daoSession;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDao();
    }

    public static App getInstance() {
        return instance;
    }

    private void initDao() {
        //创建数据库
        SQLiteDatabase db = new DaoMaster.DevOpenHelper(this, "data.db").getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        }


    public DaoSession getDao() {
        return daoSession;
    }
}