package com.jianyiclub.jianzhi.view;



import com.jianyiclub.jianzhi.entity.DataEntry;

/**
 * Created by wl624 on 2017/11/1.
 */

public interface ArticleView extends View {
    public void getArticleInformation(DataEntry articleInformation);
    public void onError(String result);
}
