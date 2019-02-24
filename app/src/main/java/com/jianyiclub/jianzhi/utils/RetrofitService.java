package com.jianyiclub.jianzhi.utils;

import com.jianyiclub.jianzhi.entity.Article;
import com.jianyiclub.jianzhi.entity.MyNews;
import com.jianyiclub.jianzhi.entity.Theme;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


/**
 * Created by wl624 on 2017/10/17.
 */

public interface RetrofitService {
    @GET("news/{when}")
    Observable<MyNews> getLatestNews(@Path("when") String when);

    @GET("news/before/{date}")
    Observable<MyNews> getMoreNews(@Path("date") String date);

    @GET("news/{id}")
    Observable<Article> getArticle(@Path("id") int id);

    @GET("theme/{id}")
    Observable<Theme> getTheme(@Path("id") int id);

    @GET("theme/{id}/before/{id1}")
    Observable<Theme> getMoreTheme(@Path("id") int id,@Path("id1") int id1);

}
