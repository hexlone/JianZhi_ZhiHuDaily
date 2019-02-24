package com.jianyiclub.jianzhi.presenter;

import android.content.Context;
import android.content.Intent;

import com.jianyiclub.jianzhi.entity.Article;
import com.jianyiclub.jianzhi.entity.DataEntry;
import com.jianyiclub.jianzhi.entity.MyNews;
import com.jianyiclub.jianzhi.entity.Theme;
import com.jianyiclub.jianzhi.manager.DataManager;
import com.jianyiclub.jianzhi.view.ArticleView;
import com.jianyiclub.jianzhi.view.NewsView;
import com.jianyiclub.jianzhi.view.View;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by wl624 on 2017/10/17.
 */

public class MyPresenter implements Presenter {
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private NewsView newView;
    private ArticleView articleView;
    private MyNews myNews;
    private Article article;
    private Theme theme;


    public MyPresenter (Context mContext){
        this.mContext = mContext;
    }
    @Override
    public void onCreate() {
        manager = new DataManager(mContext);
        mCompositeSubscription = new CompositeSubscription();

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }

    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        newView=(NewsView)view;
        //

    }

    @Override
    public void attachArticleView(View view) {
        articleView=(ArticleView)view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }
    public void getLatestNews(String when){
        mCompositeSubscription.add(manager.getLatestNews(when)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyNews>() {
                    @Override
                    public void onCompleted() {
                        if (myNews != null){
                            List<DataEntry> listBanner=new ArrayList<>();

                            List<MyNews.TopStoriesBean> topStories=new ArrayList<>();
                            DataEntry dataEntry=null;

                            topStories=myNews.getTop_stories();


                            for(int i=0;i<topStories.size();i++){
                                dataEntry=new DataEntry();
                                dataEntry.image= topStories.get(i).getImage();

                                dataEntry.id = topStories.get(i).getId();
                                dataEntry.title = topStories.get(i).getTitle();

                                listBanner.add(dataEntry);

                            }
                            List<DataEntry> listStory=new ArrayList<DataEntry>();
                            List<MyNews.StoriesBean> storiesBeen=new ArrayList<>();
                            DataEntry dataEntry1=null;
                            storiesBeen=myNews.getStories();
                            String date2=myNews.getDate();
                            for(int i=0;i<storiesBeen.size();i++){
                                dataEntry1=new DataEntry();
                                dataEntry1.imageStory= storiesBeen.get(i).getImages();
                                dataEntry1.id = storiesBeen.get(i).getId();
                                dataEntry1.title = storiesBeen.get(i).getTitle();
                                dataEntry1.date=date2;
                                listStory.add(dataEntry1);

                            }

                            newView.stopRefreshing();
                            newView.onSuccess(listBanner,listStory);




                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        newView.onError("请求失败！！!");

                    }

                    @Override
                    public void onNext(MyNews myNews1) {
                        myNews = myNews1;
                    }
                })
        );
    }
    public void getMoreNews(String date) {
        mCompositeSubscription.add(manager.getMoreNews(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyNews>() {
                    @Override
                    public void onCompleted() {
                        if (myNews != null) {

                            List<DataEntry> listStory = new ArrayList<DataEntry>();
                            List<MyNews.StoriesBean> storiesBeen = new ArrayList<>();
                            DataEntry dataEntry1 = null;
                            storiesBeen = myNews.getStories();
                            String date1=myNews.getDate();
                            for (int i = 0; i < storiesBeen.size(); i++) {
                                dataEntry1 = new DataEntry();
                                dataEntry1.imageStory = storiesBeen.get(i).getImages();
                                dataEntry1.id = storiesBeen.get(i).getId();
                                dataEntry1.title = storiesBeen.get(i).getTitle();
                                dataEntry1.date = date1;
                                listStory.add(dataEntry1);

                            }


                            newView.addMoreNews(listStory);


                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        newView.onError("请求失败！！");

                    }

                    @Override
                    public void onNext(MyNews myNews1) {
                        myNews = myNews1;
                    }
                })
        );
    }
    public void getArticle(int id) {
        mCompositeSubscription.add(manager.getArticle(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Article>() {
                    @Override
                    public void onCompleted() {
                        if (article != null) {

                            
                            DataEntry articleInformation = new DataEntry();
                            //System.out.println(article.getTitle());
                            articleInformation.body=article.getBody();
                            articleInformation.articleImage=article.getImage();
                            articleInformation.image_source=article.getImage_source();
                            articleInformation.articleTitle=article.getTitle();
                            articleInformation.share_url=article.getShare_url();
                            articleInformation.css=article.getCss();
                            articleView.getArticleInformation(articleInformation);


                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        articleView.onError("请求失败！！");

                    }

                    @Override
                    public void onNext(Article article1) {
                        article =article1;
                    }
                })
        );
    }
    public void getTheme(int id){
        mCompositeSubscription.add(manager.getTheme(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Theme>() {
                    @Override
                    public void onCompleted() {
                        if (theme != null) {
                            List<DataEntry> listTheme=new ArrayList<>();
                            List<Theme.StoriesBean> storiesBeen=new ArrayList<>();
                            storiesBeen=theme.getStories();
                            DataEntry dataEntry3=null;
                            for (int i=0;i<storiesBeen.size();i++){
                                dataEntry3=new DataEntry();
                                dataEntry3.id=storiesBeen.get(i).getId();
                                if(storiesBeen.get(i).getImages()==null){
                                    String[] strings={""};
                                    dataEntry3.imageStory=strings;
                                }else {
                                    dataEntry3.imageStory=storiesBeen.get(i).getImages();
                                }
                                dataEntry3.title=storiesBeen.get(i).getTitle();
                                listTheme.add(dataEntry3);
                            }
                            DataEntry dataEntryOfTheme=new DataEntry();
                            dataEntryOfTheme.background=theme.getBackground();
                            dataEntryOfTheme.description=theme.getDescription();
                            dataEntryOfTheme.imageTheme=theme.getImage();
                            newView.stopRefreshing();
                            newView.setThemeStory(dataEntryOfTheme,listTheme);
                        }else {
                            newView.onError("请求失败！！");

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        newView.onError("请求失败！！");

                    }

                    @Override
                    public void onNext(Theme theme1) {
                        theme =theme1;
                    }
                })
        );

    }
    public void getMoreTheme(int id,int id1){
        mCompositeSubscription.add(manager.getMoreTheme(id,id1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Theme>() {
                    @Override
                    public void onCompleted() {
                        if (theme != null) {
                            List<DataEntry> listTheme=new ArrayList<>();
                            List<Theme.StoriesBean> storiesBeen=new ArrayList<>();
                            storiesBeen=theme.getStories();
                            DataEntry dataEntry3=null;
                            for (int i=0;i<storiesBeen.size();i++){
                                dataEntry3=new DataEntry();
                                dataEntry3.id=storiesBeen.get(i).getId();
                                if(storiesBeen.get(i).getImages()==null){
                                    String[] strings={""};
                                    dataEntry3.imageStory=strings;
                                }else {
                                    dataEntry3.imageStory=storiesBeen.get(i).getImages();
                                }
                                dataEntry3.title=storiesBeen.get(i).getTitle();
                                listTheme.add(dataEntry3);
                            }

                            newView.setMoreThemeStory(listTheme);
                        }else {
                            newView.onError("请求失败！！");

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        newView.onError("请求失败！！");

                    }

                    @Override
                    public void onNext(Theme theme1) {
                        theme =theme1;
                    }
                })
        );

    }
}
