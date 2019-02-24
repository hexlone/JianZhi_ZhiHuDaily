package com.jianyiclub.jianzhi.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jianyiclub.jianzhi.R;
import com.jianyiclub.jianzhi.activity.ArticleActivity;
import com.jianyiclub.jianzhi.activity.MainActivity;
import com.jianyiclub.jianzhi.entity.DataEntry;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wl624 on 2017/10/18.
 */

public class BannerFragment extends Fragment {
    private MZBannerView mMZBanner;
    private List<DataEntry> bannerList =new ArrayList<>();
    private Context context;


    /**
    public BannerFragment newInstance(){
        this.bannerList=bannerList;
        return new BannerFragment();
    }
     **/
    public BannerFragment(){
    }
    public void setListAndContext(List<DataEntry> bannerList,Context context){
        this.bannerList=bannerList;
        this.context=context;
    }

    private void initView(View view) {

        mMZBanner = (MZBannerView) view.findViewById(R.id.banner01);

        mMZBanner.setDelayedTime(2000);
        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onPageClick(View view, int position) {
                Intent intent1=new Intent(context, ArticleActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("id",bannerList.get(position).id);
                intent1.putExtras(bundle);
                startActivity(intent1);


            }
        });
        mMZBanner.setDelayedTime(2000);
        mMZBanner.addPageChangeLisnter(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mMZBanner.setDelayedTime(3200);
        mMZBanner.setIndicatorVisible(false);

        mMZBanner.setPages(bannerList, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });



    }



    public static class BannerViewHolder implements MZViewHolder<DataEntry> {
        private ImageView mImageView;
        private TextView mTextView;
        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.bannerImageView);
            mTextView = (TextView) view.findViewById(R.id.bannerTextView);
            return view;
        }

        @Override
        public void onBind(Context context, int position, DataEntry data) {
            // 数据绑定

            if(data==null){

            }else {

                //System.out.println(position+"我查查");
                Glide.with(context).load(data.image)
                        .error(R.mipmap.ic_launcher) //在图像加载失败时显示
                        .into(mImageView);

                mTextView.setText(data.title);

            }

        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhihu_daily,null);
        initView(view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();
    }
}
