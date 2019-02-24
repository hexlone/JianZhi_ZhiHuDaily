package com.jianyiclub.jianzhi.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jianyiclub.jianzhi.R;
import com.jianyiclub.jianzhi.activity.ArticleActivity;
import com.jianyiclub.jianzhi.entity.DataEntry;
import com.jianyiclub.jianzhi.entity.MyNews;
import com.jianyiclub.jianzhi.fragment.RecyclerFragment;
import com.jianyiclub.jianzhi.utils.Config;
import com.jianyiclub.jianzhi.utils.MyStartActivity;
import com.jianyiclub.jianzhi.view.NewsView;

import java.util.List;

/**
 * Created by wl624 on 2017/10/20.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的

    private Context context;
    private View mHeaderView,mFooterView;
    private List<DataEntry> mData;
    private NewsView newsView;
    private int flagTheme;
    private OnItemClickListener mOnItemClickListener = null;

    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    public RecyclerAdapter(Context context,NewsView newsView,int flagTheme){
        this.context=context;
        this.newsView=newsView;

    }
    public void setmDate(List<DataEntry> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }


    public View getHeaderView() {
        return mHeaderView;
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    public View getFooterView(){
        return mFooterView;

    }
    public void setFooterView(View footerView){
        mFooterView = footerView;
        notifyItemChanged(getItemCount()-1);
    }



    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null){
            return TYPE_NORMAL;
        }
        if (position == 0){
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        if (position == getItemCount()-1){
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;

    }

    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if(mHeaderView != null && viewType == TYPE_HEADER) {
            return new ViewHolder(mHeaderView);
        }
        if(mFooterView != null && viewType == TYPE_FOOTER) {
            return new ViewHolder(mFooterView);
        }


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        //view.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(view);
        return vh;

    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, final int position) {

        if(getItemViewType(position) == TYPE_NORMAL){
            System.out.println(position-1);
            //System.out.println(position+"  "+mData.get(position-1).imageStory[0]);
            if(mData.get(position-1).imageStory[0]==""){

                Glide.with(context).load(R.mipmap.ic_zhihu).into(holder.imageView);
            }else {
                Glide.with(context).load(mData.get(position-1).imageStory[0])
                        .error(R.mipmap.ic_launcher) //在图像加载失败时显示
                        .into(holder.imageView);
            }


            holder.textView.setText(mData.get(position-1).title);
            holder.textView.setTextColor(
                    Config.isNight ? context.getResources().getColor(R.color.textCard_Night) : context.getResources().getColor(R.color.textCard));
            holder.cardView.setBackgroundColor(Config.isNight ? context.getResources().getColor(R.color.cardBG_Night) : context.getResources().getColor(R.color.cardBG));
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    newsView.myStart(mData.get(position-1).id);




                }

            });

        }else if(getItemViewType(position) == TYPE_HEADER) {
            return;
        }else {
            return;
        }



    }




    @Override
    public int getItemCount() {
        if(mData==null){
            return 0;
        }else if(mHeaderView == null && mFooterView == null){
            return mData.size();
        }else if(mHeaderView == null && mFooterView != null){
            return mData.size() + 1;
        }else if (mHeaderView != null && mFooterView == null){
            return mData.size() + 1;
        }else {
            return mData.size() + 2;
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView){
                return;
            }
            if (itemView == mFooterView){
                return;
            }


            cardView=(CardView)itemView.findViewById(R.id.recycler_card);
            imageView=(ImageView)itemView.findViewById(R.id.card_imageView01);
            textView=(TextView)itemView.findViewById(R.id.card_textView01);
        }
    }
}
