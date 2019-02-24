package com.jianyiclub.jianzhi.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.jianyiclub.jianzhi.R;
import com.jianyiclub.jianzhi.adapter.DrawerRecyclerAdapter;
import com.jianyiclub.jianzhi.fragment.RecyclerFragment;
import com.jianyiclub.jianzhi.presenter.MyPresenter;
import com.jianyiclub.jianzhi.utils.Config;
import com.jianyiclub.jianzhi.utils.DpToPx;
import com.jianyiclub.jianzhi.view.MyButton;
import com.jianyiclub.jianzhi.view.MyDrawerHeader;
import com.jianyiclub.jianzhi.view.MyPop;
import com.jianyiclub.jianzhi.view.MyView;

/**
 * Created by wl624 on 2017/10/17.
 */

public class MainActivity extends AppCompatActivity {

    private MyPresenter myPresenter=new MyPresenter(this);
    private Bitmap bitmap;
    private RelativeLayout relativeLayout,relativeLayout23;
    private Toolbar toolbar;
    private RecyclerView drawerRecycler;
    private DrawerLayout drawer;
    private MyDrawerHeader myDrawerHeader;
    private MyView myView;
    private Handler handler=new Handler();
    private MyPop popupWindow;
    private int flagTheme=0;
    private DrawerRecyclerAdapter drawerRecyclerAdapter;
    private RelativeLayout relativeLayout1111;
    private RecyclerFragment recyclerFragment;
    private FrameLayout frameLayout;
    private MyButton myButton01;
    private static boolean flag;
    private SharedPreferences sharedPreferences;
    private String[] strings={"首页","日常心理学","用户推荐日报","电影日报","不许无聊","设计日报","大公司日报","财经日报","互联网安全","开始游戏","音乐日报","动漫日报","体育日报"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=this.getIntent().getExtras();
        flagTheme=bundle.getInt("DayOrNight");
        if(flagTheme==10){
            flag=false;
            Config.isNight=false;
            setTheme(R.style.AppTheme);
        }else if(flagTheme==11){
            flag=true;
            Config.isNight=true;
            setTheme(R.style.AppTheme_Night);
        }

        setContentView(R.layout.activity_main_drawerlayout);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.alpha(255));
        }

        frameLayout=(FrameLayout)findViewById(R.id.frameLayoutMain);
        relativeLayout=(RelativeLayout) findViewById(R.id.main);
        relativeLayout23=(RelativeLayout) findViewById(R.id.relativeLayout23);
        myView=(MyView)findViewById(R.id.myView);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        myDrawerHeader=(MyDrawerHeader)findViewById(R.id.drawerHeader);
        drawerRecycler=(RecyclerView)findViewById(R.id.drawerRecycler);
        drawerRecyclerAdapter=new DrawerRecyclerAdapter(getApplicationContext());

        myDrawerHeader.setBG(!Config.isNight);
        toolbar.setTitle("简知");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setElevation(5);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        drawerRecyclerAdapter.setOnItemClickListener(new DrawerRecyclerAdapter.OnItemClickListener(){

            public void onItemClick(View view , int position){
                drawerRecyclerAdapter.show(position);
                setToolTitle(strings[position]);
                RecyclerFragment rr=new RecyclerFragment();
                rr.setContextAndFlag(getApplicationContext(),position,flagTheme);
                getSupportFragmentManager().beginTransaction().replace(R.id.zhihu_daily,rr).commit();
                showAnimation();
                setNew(rr);
                drawer.closeDrawers();
            }
        });

        relativeLayout1111=(RelativeLayout)findViewById(R.id.relativeLayout1111);






        //relativeLayout.setBackgroundColor(Color.WHITE);
        final View view= View.inflate(getApplicationContext(),R.layout.my_pop,null);
        myButton01=(MyButton)view.findViewById(R.id.myButton01);

        popupWindow=new MyPop(view, DpToPx.dpToPx(getApplicationContext(),160),DpToPx.dpToPx(getApplicationContext(),80));
        popupWindow.setFocusable(true);
        //popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        if (popupWindow != null) popupWindow.dismiss();


        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ObjectAnimator animator1=ObjectAnimator.ofFloat(myView,"pro",0,DpToPx.dpToPx(getApplicationContext(),16));
                animator1.setInterpolator(new AccelerateDecelerateInterpolator());
                animator1.setDuration(600);
                animator1.start();
                view.animate().translationY(-DpToPx.dpToPx(getApplicationContext(),4)).setDuration(500).start();
            }
        });




        setSupportActionBar(toolbar);


        initDrawer();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        /**
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        View view=LayoutInflater.from(getApplicationContext()).inflate(R.layout.nav_header,null);
        navigationView.addHeaderView(view);
        int[] ints={0,0};
        navigationView.getLocationInWindow(ints);
        System.out.println(ints[0]+ints[1]);

        //View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.nav_header, drawer, false);

        //getSupportFragmentManager().beginTransaction().replace(R.id.nav_header,new FooterFragment()).commit();
        //.addHeaderView(view);


        /**
        bitmap= BitmapFactory.decodeResource(this.getApplicationContext().getResources(), R.drawable.ic_two);


        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap,
                bitmap.getWidth() / 20,
                bitmap.getHeight() / 20,
                false);
        Bitmap blurBitmap = FastBlurUtil.doBlur(scaledBitmap, 100, true);
        Drawable drawable =new BitmapDrawable(blurBitmap);
        toolbar.setBackgroundDrawable(drawable);
         **/


        ViewCompat
                .setOnApplyWindowInsetsListener(myDrawerHeader, new OnApplyWindowInsetsListener() {

                    @Override
                    public WindowInsetsCompat onApplyWindowInsets(View v,
                                                                  WindowInsetsCompat insets) {
                        return insets.consumeSystemWindowInsets();
                    }
                });

        recyclerFragment=new RecyclerFragment();
        recyclerFragment.setContextAndFlag(getApplicationContext(),0,flagTheme);
        getSupportFragmentManager().beginTransaction().replace(R.id.zhihu_daily,recyclerFragment).commit();

        myDrawerHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        myButton01.setOnUpActionListener(new MyButton.OnUpActionListener() {
            @Override
            public void OnUp(float x, float y,float way) {

                Config.isNight = !Config.isNight;
                showAnimation();
                refreshUI();
                sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);
                //得到SharedPreferences.Editor对象，并保存数据到该对象中
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(Config.isNight){
                    editor.putInt("DON",11);
                }else {
                    editor.putInt("DON",10);
                }
                //保存key-value对到文件中
                editor.commit();
                ObjectAnimator animator=ObjectAnimator.ofFloat(myButton01,"progress",0,way);
                animator.setDuration(400);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.start();


            }
        });
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator=ObjectAnimator.ofFloat(myView,"progress",0,DpToPx.dpToPx(getApplicationContext(),16));
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(600);
                animator.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow.showAtLocation(myView, Gravity.TOP,getWindowManager().getDefaultDisplay().getWidth(),DpToPx.dpToPx(getApplicationContext(),80));
                        view.animate().translationY(DpToPx.dpToPx(getApplicationContext(),4)).setDuration(500).start();
                    }
                },400);


            }
        });



    }
    private void refreshUI() {
        myDrawerHeader.setBG(!Config.isNight);
        frameLayout.setBackgroundResource(Config.isNight ? R.color.recyclerBG_Night : R.color.recyclerBG);
        toolbar.setBackgroundColor(getResources().getColor(Config.isNight ? R.color.colorPrimary_Night : R.color.colorPrimary));
        recyclerFragment.refreshFragment();
        drawerRecyclerAdapter.notifyDataSetChanged();
    }

    public void setToolTitle(String aa){

        if(aa==null){
            aa="首页";
        }

        final String finalAaa = aa;
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    toolbar.setTitle(finalAaa);
                }catch (Exception e){
                    //toolbar.setTitle("简知");
                }

            }
        });
        thread.start();
    }

    public void setMy(){



    }
    public void start(){


    }
    private void initDrawer() {
        

        drawerRecycler.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        drawerRecycler.setLayoutManager(mLayoutManager);
        drawerRecycler.setItemAnimator(new DefaultItemAnimator());

        drawerRecycler.setAdapter(drawerRecyclerAdapter);
        //drawerRecyclerAdapter.setOnDrawerItemClickListener(this);
        
        
    }



    private void setNew(RecyclerFragment re) {
        this.recyclerFragment=re;
    }
    private void setFragment(){}



    @Override
    protected void onDestroy(){
        super.onDestroy();
        myPresenter.onStop();
    }

    private long firstTime=0;
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime=System.currentTimeMillis();
                if(secondTime-firstTime>2000){
                    Snackbar.make(relativeLayout,"再按一次退出程序",Snackbar.LENGTH_SHORT).show();
                    firstTime=secondTime;
                    return true;
                }else{
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }
    private void showAnimation() {
        final View decorView = getWindow().getDecorView();
        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap != null) {
            final View view = new View(this);
            view.setBackgroundDrawable(new BitmapDrawable(getResources(), cacheBitmap));
            ViewGroup.LayoutParams layoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorView).addView(view, layoutParam);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorView).removeView(view);
                }
            });
            objectAnimator.start();
        }
    }
    private Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }



}
