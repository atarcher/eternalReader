package com.gbh.eternalreader.database;

import com.gbh.eternalreader.database.entity.Book;
import com.gbh.eternalreader.database.greendao.GreenDaoHelper;

import java.util.List;

/**
 * Created by gbh
 * Date 2021/1/16
 * Description 数据库操作
 */
public class DBHelper {
    private static DBHelper instance;
    private static Object object = new Object();
    public static DBHelper getInstance() {
        if (instance == null) {
            synchronized (object) {
                if (instance == null) {
                    instance = new DBHelper();
                }
            }
        }
        return instance;
    }

    public List<Book> getAllBook() {
        return GreenDaoHelper.getInstance().getDaoSession().getBookDao().loadAll();
    }

    public void updateBooksEntity(Book book) {
        GreenDaoHelper.getInstance().getDaoSession().getBookDao().update(book);
    }



}
