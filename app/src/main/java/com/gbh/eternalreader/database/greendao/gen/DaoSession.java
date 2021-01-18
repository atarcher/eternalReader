package com.gbh.eternalreader.database.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.gbh.eternalreader.database.entity.Book;
import com.gbh.eternalreader.database.entity.Chapter;
import com.gbh.eternalreader.database.entity.SearchHistory;

import com.gbh.eternalreader.database.greendao.gen.BookDao;
import com.gbh.eternalreader.database.greendao.gen.ChapterDao;
import com.gbh.eternalreader.database.greendao.gen.SearchHistoryDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig bookDaoConfig;
    private final DaoConfig chapterDaoConfig;
    private final DaoConfig searchHistoryDaoConfig;

    private final BookDao bookDao;
    private final ChapterDao chapterDao;
    private final SearchHistoryDao searchHistoryDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        bookDaoConfig = daoConfigMap.get(BookDao.class).clone();
        bookDaoConfig.initIdentityScope(type);

        chapterDaoConfig = daoConfigMap.get(ChapterDao.class).clone();
        chapterDaoConfig.initIdentityScope(type);

        searchHistoryDaoConfig = daoConfigMap.get(SearchHistoryDao.class).clone();
        searchHistoryDaoConfig.initIdentityScope(type);

        bookDao = new BookDao(bookDaoConfig, this);
        chapterDao = new ChapterDao(chapterDaoConfig, this);
        searchHistoryDao = new SearchHistoryDao(searchHistoryDaoConfig, this);

        registerDao(Book.class, bookDao);
        registerDao(Chapter.class, chapterDao);
        registerDao(SearchHistory.class, searchHistoryDao);
    }
    
    public void clear() {
        bookDaoConfig.clearIdentityScope();
        chapterDaoConfig.clearIdentityScope();
        searchHistoryDaoConfig.clearIdentityScope();
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public ChapterDao getChapterDao() {
        return chapterDao;
    }

    public SearchHistoryDao getSearchHistoryDao() {
        return searchHistoryDao;
    }

}
