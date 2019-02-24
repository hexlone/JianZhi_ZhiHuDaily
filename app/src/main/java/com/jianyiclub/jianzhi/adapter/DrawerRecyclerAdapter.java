package com.jianyiclub.jianzhi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jianyiclub.jianzhi.R;
import com.jianyiclub.jianzhi.activity.MainActivity;
import com.jianyiclub.jianzhi.fragment.RecyclerFragment;
import com.jianyiclub.jianzhi.utils.Config;
import com.jianyiclub.jianzhi.utils.OnDrawerItemClickListener;
import com.jianyiclub.jianzhi.view.ThemeLocation;

/**
 * Created by wl624 on 2017/11/18.
 */

public class DrawerRecyclerAdapter extends RecyclerView.Adapter<DrawerRecyclerAdapter.ViewHolder> implements View.OnClickListener{

    private int flag=-1;
    private ViewHolder vh1;
    private Context context;
    private OnItemClickListener mOnItemClickListener = null;
    private DrawerRecyclerAdapter.ViewHolder holder;
    private String[] strings={"首页","日常心理学","用户推荐日报","电影日报","不许无聊","设计日报","大公司日报","财经日报","互联网安全","开始游戏","音乐日报","动漫日报","体育日报"};

    public DrawerRecyclerAdapter(Context context){
        this.context=context;
    }
    @Override
    public DrawerRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawerrecycler_item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        setViewHoler(vh1);
        return vh;
    }
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    private void setViewHoler(ViewHolder vh1){
        this.vh1=vh1;
    }
    private void setBG(){
        vh1.relativeLayout3.setBackgroundResource(R.color.colorPrimary_Night);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    /**
     public void setOnDrawerItemClickListener(OnDrawerItemClickListener oDrawerItemClickListener) {
     onDrawerItemClickListener = oDrawerItemClickListener;
     }
     **/
    @Override
    public void onBindViewHolder(DrawerRecyclerAdapter.ViewHolder holder,final int position) {

        holder.itemView.setTag(position);
        holder.image1.setVisibility(View.GONE);
        if(flag==-1){
            if(position==0){
                holder.image1.setVisibility(View.VISIBLE);
            }
        }
        if(position==flag){
            holder.image1.setVisibility(View.VISIBLE);
        }
        setHolder(holder);
        holder.textView.setTextColor(
                Config.isNight ? context.getResources().getColor(R.color.textCard_Night) : context.getResources().getColor(R.color.textCard));
        holder.textView.setText(strings[position]);
        /**
        holder.relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(position);
                drawer.closeDrawers();

                Thread t=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerFragment rrr=new RecyclerFragment(getApplicationContext(),position,flagTheme);
                        getSupportFragmentManager().beginTransaction().replace(R.id.zhihu_daily,rrr).commit();

                    }
                });
                t.start();




            }
        });
         **/
    }
    @Override
    public int getItemCount() {
        return strings.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView textView;
        private RelativeLayout relativeLayout3;
        private ThemeLocation image1;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.drawerItemTitle);

            image1=(ThemeLocation) itemView.findViewById(R.id.image1);
            relativeLayout3=(RelativeLayout)itemView.findViewById(R.id.drawerItemBg) ;
            //relativeLayout3.setBackgroundColor(Color.WHITE);
        }


    }
    public void show(int postion){
        flag=postion;
        //MainActivity mainActivity=new MainActivity();
        //mainActivity.setToolTitle(strings[postion]);
        notifyDataSetChanged();
    }
    public void setHolder(DrawerRecyclerAdapter.ViewHolder holder){
        this.holder=holder;
    }

}

