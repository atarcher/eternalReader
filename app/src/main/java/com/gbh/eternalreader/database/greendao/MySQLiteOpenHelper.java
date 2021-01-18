package com.gbh.eternalreader.database.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gbh.eternalreader.database.entity.Chapter;
import com.gbh.eternalreader.database.greendao.gen.BookDao;
import com.gbh.eternalreader.database.greendao.gen.ChapterDao;
import com.gbh.eternalreader.database.greendao.gen.DaoMaster;
import com.gbh.eternalreader.database.greendao.gen.SearchHistoryDao;
import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, BookDao.class, ChapterDao.class, SearchHistoryDao.class);

/*        MigrationHelper.migrate(db, CrowdDao.class, GRelationDao.class, PlanDao.class, ProjectDao.class, ProjectDao.class,
                ReservationDao.class, RRelationDao.class, SchoolDao.class, ScoreDao.class, StudentDao.class, TRelationDao.class);*/


    }
}
