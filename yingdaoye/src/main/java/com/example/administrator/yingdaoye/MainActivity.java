package com.example.administrator.yingdaoye;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ViewPager mViewPager;
    private Button mBtnGo;
    private ViewPaperAdapter vpAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniViews();
    }
    public void iniViews(){
        mViewPager= (ViewPager) findViewById(R.id.vp_guide);
        mBtnGo= (Button) findViewById(R.id.btn_go);
//实例化各个界面布局
        View view1=View.inflate(this,R.layout.guide_view,null);
        View view2=View.inflate(this,R.layout.guide_view,null);
        View view3=View.inflate(this,R.layout.guide_view,null);

        ((ImageView) view1.findViewById(R.id.tv_pic)).setImageResource(R.drawable.android_guide_step_1);
        ((ImageView) view2.findViewById(R.id.tv_pic)).setImageResource(R.drawable.android_guide_step_2);
        ((ImageView) view3.findViewById(R.id.tv_pic)).setImageResource(R.drawable.android_guide_step_3);

        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MusicMain.class);
                startActivity(intent);
                finish();
            }
        });

        view1.findViewById(R.id.tv_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);//setCurrentItem方法了，它的意思是跳转到ViewPager的指定页面
            }
        });

        view2.findViewById(R.id.tv_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);//setCurrentItem方法了，它的意思是跳转到ViewPager的指定页面
            }
        });
        view2.findViewById(R.id.tv_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(2);//setCurrentItem方法了，它的意思是跳转到ViewPager的指定页面
            }
        });

        ((TextView)view1.findViewById(R.id.tv_title)).setText("精彩首页");
        ((TextView)view1.findViewById(R.id.tv_title)).setTextColor(Color.parseColor("#FF5000"));
        ((TextView)view2.findViewById(R.id.tv_title)).setText("发现定位");
        ((TextView)view2.findViewById(R.id.tv_title)).setTextColor(Color.parseColor("#49ca65"));
        ((TextView)view3.findViewById(R.id.tv_title)).setText("欢乐互动");
        ((TextView)view3.findViewById(R.id.tv_title)).setTextColor(Color.parseColor("#16c5c6"));

        ((TextView)view1.findViewById(R.id.tv_desc)).setText("优惠第一时间为你奉上\n");
        ((TextView)view2.findViewById(R.id.tv_desc)).setText("给你最好的\n做你最想要的");
        ((TextView)view3.findViewById(R.id.tv_desc)).setText("欢迎互动\n精彩由你");

        mViewPager = (ViewPager) findViewById(R.id.vp_guide);

        ArrayList<View> views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        vpAdapter = new ViewPaperAdapter(views);

        mViewPager.setOffscreenPageLimit(views.size());//预告加载的页面数量
        mViewPager.setPageMargin(-dip2px(135));//设置页与页之间的间距
        mViewPager.setAdapter(vpAdapter);
//当view3时button可见
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position + 1 == mViewPager.getAdapter().getCount()) {
                    if (mBtnGo.getVisibility() != View.VISIBLE) {
                        mBtnGo.setVisibility(View.VISIBLE);
                        mBtnGo.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));
                    }
                } else {
                    if (mBtnGo.getVisibility() != View.GONE) {
                        mBtnGo.setVisibility(View.GONE);
                        mBtnGo.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));
                    }
                }
            }
        });
//实现个性化的ViewPager切换动画，主要是改变标题和文字和图片的大小
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            private static final float MIN_SCALE = 0.85f;
//            private static final float MIN_ALPHA = 0.5f;

            private static final float MIN_TXT_SCALE = 0.0f;
            private static final float MIN_TXT_ALPHA = 0.0f;

            @Override
            public void transformPage(View view, float position) {
                View mGuideImage = view.findViewById(R.id.tv_pic);
                View mTitle = view.findViewById(R.id.tv_title);
                View mDesc = view.findViewById(R.id.tv_desc);

                int viewWidth = mDesc.getWidth();

                if (position < -1) {
                    mTitle.setAlpha(0);
                    mDesc.setAlpha(0);
                } else if (position <= 1) {
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                    float scaleTxtFactor = Math.max(MIN_TXT_SCALE, 1 - Math.abs(position));

                   float horzMargin = viewWidth * (1 - (1 - Math.abs(position))) / 2;
                    if (position < 0) {
                        mTitle.setTranslationX(horzMargin);
                        mDesc.setTranslationX(horzMargin);
                   } else {
                        mTitle.setTranslationX(-horzMargin);
                        mDesc.setTranslationX(-horzMargin);
                    }

                    mGuideImage.setScaleX(scaleFactor);
                    mGuideImage.setScaleY(scaleFactor);

                    mTitle.setScaleX(scaleTxtFactor);
                    mTitle.setScaleY(scaleTxtFactor);
                    mTitle.setAlpha(MIN_TXT_ALPHA + (scaleTxtFactor - MIN_TXT_SCALE) / (1 - MIN_TXT_SCALE) * (1 - MIN_TXT_ALPHA));

                    mDesc.setAlpha(mTitle.getAlpha());
                    mDesc.setScaleX(scaleTxtFactor);
                    mDesc.setScaleY(scaleTxtFactor);
                } else {
                    mTitle.setAlpha(0);
                    mDesc.setAlpha(0);
                }
            }
        });

    }
    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
