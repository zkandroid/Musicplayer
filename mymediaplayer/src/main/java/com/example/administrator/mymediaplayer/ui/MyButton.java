package com.example.administrator.mymediaplayer.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.mymediaplayer.R;

/**
 * Created by Administrator on 2016/10/7.
 */
public class MyButton extends LinearLayout {
    private ImageView iv;
    private TextView tv;

    public MyButton(Context context) {
        this(context, null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 导入布局
        LayoutInflater.from(context).inflate(R.layout.mybutton, this, true);
        iv = (ImageView) findViewById(R.id.iv);
        tv = (TextView) findViewById(R.id.tv);
    }
    public void setImageResource(int resId) {
        iv.setImageResource(resId);
    }
    public void setTextViewText(String text) {
        tv.setText(text);
    }
}
