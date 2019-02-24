package com.jianyiclub.jianzhi.manager;

import android.content.Context;

import com.jianyiclub.jianzhi.entity.Article;
import com.jianyiclub.jianzhi.entity.MyNews;
import com.jianyiclub.jianzhi.entity.Theme;
import com.jianyiclub.jianzhi.utils.RetrofitHelper;
import com.jianyiclub.jianzhi.utils.RetrofitService;

import rx.Observable;


/**
 * Created by win764-1 on 2016/12/12.
 */

public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }
    public Observable<MyNews> getLatestNews(String when){
        return  mRetrofitService.getLatestNews(when);
    }
    public Observable<MyNews> getMoreNews(String date){
        return  mRetrofitService.getMoreNews(date);
    }
    public Observable<Article> getArticle(int id){
        return  mRetrofitService.getArticle(id);
    }
    public Observable<Theme> getTheme(int id){
        return  mRetrofitService.getTheme(id);
    }
    public Observable<Theme> getMoreTheme(int id,int id1){
        return  mRetrofitService.getMoreTheme(id,id1);
    }

}
