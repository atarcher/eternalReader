package com.gbh.eternalreader.database.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gbh.eternalreader.database.greendao.gen.DaoMaster;
import com.gbh.eternalreader.database.greendao.gen.DaoSession;


public class GreenDaoHelper {

    public static final String DB_NAME = "eternalReader.db";

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private SQLiteDatabase db;

    private GreenDaoHelper() {
    }

    private static class InstanceHolder {
        private static final GreenDaoHelper INSTANCE = new GreenDaoHelper();
    }

    public static GreenDaoHelper getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void setupGreenDao(Context context) {
        //初始化GreenDao提供的DevOpenHelper，用于获取GreenDao为我们创建SQLiteOpenHelper类
        //DevOpenHelper在数据库升级时，会删除所有的表不会自动的备份数据。所以在数据库升级时必须要自己去实现数据库的安全升级
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context, DB_NAME, null);
        db = helper.getWritableDatabase();

        //DaoMaster的作用是以一定的模式持有数据库对象（SQLiteDatabase）并管理一些DAO类（而不是对象）。简单点DaoMaster就像一个快递的外包装
        mDaoMaster = new DaoMaster(db);

        //DaoSession的作用提供一些通用的持久化方法，比如对实体进行插入，加载，更新，刷新和删除等等
        mDaoSession = mDaoMaster.newSession();
    }

    /**
     * 向外提供DaoMaster，如：需要手动删除表时会用到
     */
    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    /**
     * 向外提供DaoSession，获取相应DAO类进行增删改查
     */
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb(){
        return db;
    }
}
