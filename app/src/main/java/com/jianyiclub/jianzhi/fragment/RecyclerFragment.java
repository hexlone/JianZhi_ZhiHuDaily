package com.jianyiclub.jianzhi.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.jianyiclub.jianzhi.R;
import com.jianyiclub.jianzhi.activity.ArticleActivity;
import com.jianyiclub.jianzhi.activity.MainActivity;
import com.jianyiclub.jianzhi.adapter.RecyclerAdapter;
import com.jianyiclub.jianzhi.entity.DataEntry;
import com.jianyiclub.jianzhi.presenter.MyPresenter;
import com.jianyiclub.jianzhi.utils.Config;
import com.jianyiclub.jianzhi.view.NewsView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wl624 on 2017/10/20.
 */

public class RecyclerFragment extends Fragment{
    private List<DataEntry> mData;
    private RecyclerView recyclerView01;
    private LinearLayoutManager mLayoutManager;
    private RecyclerAdapter recyclerAdapter;
    private Context context;
    private FrameLayout fragmRE;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MyPresenter myPresenter;
    private String date;
    private int flag;
    private int id2;
    private int flagTheme;



    private int[] themeId={13,12,3,11,4,5,6,10,2,7,9,8};

    public RecyclerFragment(){

    }
    public void setContextAndFlag(Context context,int flag,int flagTheme){
        this.context=context;
        this.flag=flag;
        this.flagTheme=flagTheme;
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_recyclerview, null);
        fragmRE=(FrameLayout) view.findViewById(R.id.linearLayout11);
        fragmRE.setBackgroundResource(Config.isNight ? R.color.recyclerBG_Night : R.color.recyclerBG);
        myPresenter=new MyPresenter(context);
        swipeRefreshLayout=(SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.RED);
        swipeRefreshLayout.setDistanceToTriggerSync(350);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(flag==0){
                    onRefreshing();
                }else {
                    myPresenter.onCreate();
                    myPresenter.getTheme(themeId[flag-1]);
                    myPresenter.attachView(newsView);
                }
            }
        });
        recyclerView01 = (RecyclerView)view.findViewById(R.id.recyclerView01);
        recyclerView01.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView01.setLayoutManager(mLayoutManager);
        recyclerView01.setItemAnimator(new DefaultItemAnimator());
        recyclerAdapter=new RecyclerAdapter(context,newsView,flagTheme);
        recyclerView01.setAdapter(recyclerAdapter);
        recyclerView01.addOnScrollListener(mOnScrollListener);
        setFooterView(recyclerView01);
        if(flag==0){
            onRefreshing();
        }else {
            myPresenter.onCreate();
            myPresenter.getTheme(themeId[flag-1]);
            myPresenter.attachView(newsView);
        }

        return view;
    }
    public void refreshFragment() {
        fragmRE.setBackgroundResource(Config.isNight ? R.color.recyclerBG_Night : R.color.recyclerBG);

        recyclerAdapter.notifyDataSetChanged();
    }

    protected void onRefreshing() {
        myPresenter.onCreate();
        myPresenter.getLatestNews("latest");
        myPresenter.attachView(newsView);

    }

    protected void loadMore(){

        myPresenter.onCreate();
        myPresenter.getMoreNews(getLatestDate());
        myPresenter.attachView(newsView);

    }

    private NewsView newsView = new NewsView() {
        @Override
        public void onSuccess(List<DataEntry> listBanner,List<DataEntry> listStory){
            setHeaderView(recyclerView01,listBanner);
            if(mData == null) {
                mData = new ArrayList<>();
            }
            setLatestDate(listStory.get(listStory.size()-1).date);
            mData.clear();
            mData.addAll(listStory);

            recyclerAdapter.setmDate(mData);
        }

        @Override
        public void onError(String result) {
            swipeRefreshLayout.setRefreshing(false);
            Snackbar.make(fragmRE,result,3500).show();
        }

        public void onRefreshing(){
            swipeRefreshLayout.setRefreshing(true);
        }

        public void stopRefreshing(){
            Snackbar.make(fragmRE,"刷新完成",1200).show();
            swipeRefreshLayout.setRefreshing(false);
        }

        public void addMoreNews(List<DataEntry> listStory){

            setLatestDate(listStory.get(listStory.size()-1).date);
            mData.addAll(listStory);
            recyclerAdapter.notifyDataSetChanged();
        }

        public void myStart(int id) {
            Intent intent=new Intent(context, ArticleActivity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("id",id);
            intent.putExtras(bundle);
            startActivity(intent);
        }

        @Override
        public void setThemeStory(DataEntry dataEntryOfTheme,List<DataEntry> listTheme) {
            View headerTheme = LayoutInflater.from(context).inflate(R.layout.theme_header, recyclerView01, false);
            TextView textView=(TextView)headerTheme.findViewById(R.id.themeTitle01);
            textView.setText(dataEntryOfTheme.description);
            KenBurnsView kenBurnsView=(KenBurnsView)headerTheme.findViewById(R.id.themeImage01);
            Glide.with(context).load(dataEntryOfTheme.imageTheme)
                    .error(R.mipmap.ic_launcher) //在图像加载失败时显示
                    .into(kenBurnsView);
            recyclerAdapter.setHeaderView(headerTheme);

            if(mData == null) {
                mData = new ArrayList<>();
            }
            setLatestThemeStoryId(listTheme.get(listTheme.size()-1).id);
            mData.clear();
            mData.addAll(listTheme);
            recyclerAdapter.setmDate(mData);
        }

        @Override
        public void setMoreThemeStory(List<DataEntry> listTheme) {
            try{
                setLatestThemeStoryId(listTheme.get(listTheme.size()-1).id);
            }catch (Exception e){
                Snackbar.make(fragmRE,"没有了！！！",2500).show();

            }

            mData.addAll(listTheme);
            recyclerAdapter.notifyDataSetChanged();
        }


    };

    private void setLatestThemeStoryId(int id) {
        this.id2=id;

    }
    private int getLatestThemeStoryId(){
        return id2;
    }

    public RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisibleItem;
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem=mLayoutManager.findLastVisibleItemPosition();
        }
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem + 1 ==recyclerAdapter.getItemCount()) {
                //System.out.println("loading more data"+lastVisibleItem);
                if(flag==0){
                    loadMore();
                }else {
                    myPresenter.onCreate();
                    myPresenter.getMoreTheme(themeId[flag-1],getLatestThemeStoryId());
                    myPresenter.attachView(newsView);
                }

            }
        }
    };
    private void setHeaderView(RecyclerView view1,List<DataEntry> bannerList){
        View header = LayoutInflater.from(context).inflate(R.layout.recyclerview_header, recyclerView01, false);
        BannerFragment bannerFragment=new BannerFragment();
        bannerFragment.setListAndContext(bannerList,context);
        getChildFragmentManager().beginTransaction().replace(R.id.banner11,bannerFragment).commit();
        recyclerAdapter.setHeaderView(header);
    }
    private void setFooterView(RecyclerView view2){
        View footer=LayoutInflater.from(context).inflate(R.layout.fragment_footer,recyclerView01,false);
        //getChildFragmentManager().beginTransaction().replace(R.id.relativeLayout11,new FooterFragment()).commit();
        recyclerAdapter.setFooterView(footer);
    }
    public void setLatestDate(String date1){
        this.date=date1;
    }
    public String getLatestDate(){
        return date;

    }
}
