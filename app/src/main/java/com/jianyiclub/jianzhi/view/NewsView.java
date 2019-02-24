package com.jianyiclub.jianzhi.view;

import android.view.*;

import com.jianyiclub.jianzhi.entity.DataEntry;
import com.jianyiclub.jianzhi.entity.MyNews;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by wl624 on 2017/10/17.
 */

public interface NewsView extends View {
    void onSuccess(List<DataEntry> listBanner,List<DataEntry> listStory);
    void onError(String result);
    void onRefreshing();
    void stopRefreshing();
    void addMoreNews(List<DataEntry> listStory);
    void myStart(int id);
    void setThemeStory(DataEntry dataEntryOfTheme,List<DataEntry> listTheme);
    void setMoreThemeStory(List<DataEntry> listTheme);
}
