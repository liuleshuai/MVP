package com.liuleshuai.common.tools;

import com.liuleshuai.common.base.BaseApplication;
import com.liuleshuai.common.dao.DaoSession;
import com.liuleshuai.common.dao.HistoryData;
import com.liuleshuai.common.dao.HistoryDataDao;

import java.util.List;

/**
 * 数据库操作工具类
 *
 * @author 67017
 */
public class HistoryDataUtil {

    private static HistoryDataDao getDao() {
        DaoSession session = BaseApplication.getDaoSession();
        HistoryDataDao dao = session.getHistoryDataDao();
        return dao;
    }

    public static List<HistoryData> queryAllData() {
        return getDao().loadAll();
    }

    public static List<HistoryData> queryData(long time) {
        return getDao().queryBuilder().where(HistoryDataDao.Properties.Time.eq(time)).list();
    }

    public static void insertData(HistoryData user) {
        getDao().insert(user);
    }

    public static void removeData(HistoryData user) {
        getDao().delete(user);
    }

    public static void updateData(HistoryData user) {
        getDao().update(user);
    }
}
