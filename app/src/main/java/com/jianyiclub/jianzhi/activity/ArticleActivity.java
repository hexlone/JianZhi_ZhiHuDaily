package com.jianyiclub.jianzhi.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.jianyiclub.jianzhi.R;
import com.jianyiclub.jianzhi.entity.DataEntry;
import com.jianyiclub.jianzhi.presenter.MyPresenter;
import com.jianyiclub.jianzhi.utils.AppBarStateChangeListener;
import com.jianyiclub.jianzhi.utils.Config;
import com.jianyiclub.jianzhi.view.ArticleView;



/**
 * Created by wl624 on 2017/11/1.
 */

public class ArticleActivity extends AppCompatActivity {

    private WebView webView01;
    private MyPresenter myPresenter;
    private AppBarLayout appBarLayout01;
    private KenBurnsView kenBurnsView01;
    private CollapsingToolbarLayout collapsingToolbarLayout01;
    private CoordinatorLayout coordinatorLayout;
    private TextView titleOfArticle;
    private Toolbar toolbar01;
    private View frameLayout01;
    private TextView toolbarTitle,imageSource;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Config.isNight){
            setTheme(R.style.AppTheme_Night);
        }else {
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_article);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.alpha(255));
        }



        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinatorLayout01);
        frameLayout01=findViewById(R.id.frameLayout01);
        titleOfArticle=(TextView)findViewById(R.id.titleOfArticle);
        appBarLayout01=(AppBarLayout)findViewById(R.id.appBarLayout01);
        toolbar01=(Toolbar)findViewById(R.id.toolbarOfArticle);
        //toolbar01.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar01);
        toolbar01.setTitleTextColor(Color.WHITE);

        toolbarTitle=(TextView)findViewById(R.id.toolbarTitle01);
        imageSource=(TextView)findViewById(R.id.imageSource);
        toolbar01.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        collapsingToolbarLayout01=(CollapsingToolbarLayout)findViewById(R.id.collapsingToolbarLayout01);
        collapsingToolbarLayout01.setTitleEnabled(false);

        collapsingToolbarLayout01.requestLayout();

        toolbarTitle.setVisibility(View.GONE);
        appBarLayout01.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {

                if (state == State.COLLAPSED) {

                    collapsingToolbarLayout01.setContentScrimColor(Color.WHITE);

                    toolbarTitle.setVisibility(View.GONE);
                    titleOfArticle.setAlpha(1);

                    imageSource.setAlpha(1);
                } else if (state == State.EXPANDED) {

                    //toolbarTitle.setVisibility(View.VISIBLE);
                    titleOfArticle.setAlpha(0);
                    imageSource.setAlpha(0);
                    collapsingToolbarLayout01.setContentScrimColor(ContextCompat
                            .getColor(ArticleActivity.this, android.R.color.transparent));
                }
            }
            public void onOffsetChanged(State state, float offset) {
                System.out.println(offset);

                if(offset==1){
                    toolbarTitle.setVisibility(View.VISIBLE);
                }else {
                    toolbarTitle.setVisibility(View.GONE);
                }

                imageSource.setAlpha(1-offset);
                titleOfArticle.setAlpha(1-offset);
                collapsingToolbarLayout01.setContentScrimColor(
                        (int) new android.animation.ArgbEvaluator().evaluate(offset,
                                ContextCompat.getColor(ArticleActivity.this,
                                        android.R.color.transparent),getResources().getColor(Config.isNight ? R.color.colorPrimary_Night : R.color.colorPrimary)));

            }
        });


        ViewCompat
                .setOnApplyWindowInsetsListener(frameLayout01, new OnApplyWindowInsetsListener() {

                    @Override
                    public WindowInsetsCompat onApplyWindowInsets(View v,
                                                                  WindowInsetsCompat insets) {
                        return insets.consumeSystemWindowInsets();
                    }
                });
        kenBurnsView01=(KenBurnsView)findViewById(R.id.kenBurnsView01);
        webView01=(WebView)findViewById(R.id.webView01);
        webView01.getSettings().setJavaScriptEnabled(true);
        webView01.setScrollbarFadingEnabled(true);
        webView01.getSettings().setBuiltInZoomControls(false);
        webView01.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView01.getSettings().setDomStorageEnabled(true);
        webView01.getSettings().setAppCacheEnabled(true);
        webView01.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
                return true;

            }

        });

        Bundle bundle=this.getIntent().getExtras();

        myPresenter=new MyPresenter(getApplicationContext());

        myPresenter.onCreate();
        myPresenter.getArticle(bundle.getInt("id"));
        myPresenter.attachArticleView(articleView);



    }

    public ArticleView articleView=new ArticleView() {
        @Override
        public void getArticleInformation(DataEntry articleInformation) {


            if(articleInformation.articleImage!=null){
                Glide.with(getApplicationContext()).load(articleInformation.articleImage)
                        .error(Config.isNight?R.color.colorPrimary_Night:R.color.colorPrimary) //在图像加载失败时显示
                        .into(kenBurnsView01);
            }else {
                kenBurnsView01.setBackgroundResource(Config.isNight?R.color.bule:R.color.colorPrimary);
            }

            String str=articleInformation.body;
            
            if(str.indexOf("该文章暂不支持阅读模式")>=0){
                webView01.loadUrl(articleInformation.share_url);
                Snackbar.make(coordinatorLayout,"该文章暂不支持阅读模式",3000).show();

            }else {

                str = str.replace("<div class=\"img-place-holder\">", "");
                String css = "<link rel=\"stylesheet\" href=\"" + articleInformation.css[0] + "\" type=\"text/css\" />";
                String theme = "<body className=\"\" onload=\"onLoaded()\">";
                if (Config.isNight)
                    theme = "<body className=\"\" onload=\"onLoaded()\" class=\"night\">";

                String urlT="<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<meta charset=\"utf-8\" />" +
                        css +
                        "\n</head>\n" +
                        theme +
                        str+
                        "</body>" +
                        "</html>";

                webView01.loadDataWithBaseURL("file:///android_asset/", urlT, "text/html", "utf-8", "http//:daily.zhihu.com/");



                /**
                String stri = "<div class=\"headline\">";
                String stri1 = "</div>";
                String str1 = str.substring(0, str.indexOf(stri) - 1);
                String str2 = str.substring(str.indexOf(stri) + stri.length(), str.indexOf(stri1));
                String str3 = str.substring(str.indexOf(stri1)+ stri1.length());
                String str4 = "<link rel=\"stylesheet\" href=\"" + articleInformation.css[0] + "\" type=\"text/css\" />";
                if (Config.isNight) {
                    String str5 = "<div className=\"\" onload=\"onLoaded()\" class=\"night\">";
                    webView01.loadDataWithBaseURL("file:///android_asset/", (str4 + str5 + str1 + str2 + str3 + stri1), "text/html", "utf-8", "http//:daily.zhihu.com/");
                } else {
                    webView01.loadDataWithBaseURL("file:///android_asset/", (str1 + str2 + str3 + str4), "text/html", "utf-8", "http//:daily.zhihu.com/");

                }
                 **/
            }
            titleOfArticle.setText(articleInformation.articleTitle);
            toolbarTitle.setText(articleInformation.articleTitle);
            imageSource.setText(articleInformation.image_source);

        }
        @Override
        public void onError(String result) {
            Snackbar.make(appBarLayout01,"请求错误！",2000).show();
        }
    };


}
