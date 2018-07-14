package com.liuleshuai.common.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 搜索历史Bean
 *
 * @author 67017
 */
@Entity
public class HistoryData {
    private long time;
    private String historyData;

    @Generated(hash = 1976923824)
    public HistoryData(long time, String historyData) {
        this.time = time;
        this.historyData = historyData;
    }

    @Generated(hash = 422767273)
    public HistoryData() {
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getHistoryData() {
        return this.historyData;
    }

    public void setHistoryData(String historyData) {
        this.historyData = historyData;
    }
}
