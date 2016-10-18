package com.example.administrator.mymediaplayer.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.administrator.mymediaplayer.R;

/**
 * Created by Administrator on 2016/10/4.
 */
public class AvatarCircle extends ImageView{
    //view的宽度
    private  int mWidth;
    //圆形的半径
    private int mRadius;
    //位图着色器
    private BitmapShader mBitmapShader;
    //矩阵-用于缩放图片以适应view控件
    private Matrix mMatrix;
    //圆形图像的paint
    private Paint mBitmapPaint;
    //用于绘制圆形图片的外边界
    private Paint mBorderPaint;
    //边界宽度
    private  float mStkroeWidth;
    //边界淹死
    private  int  mStrokeColor;

    public AvatarCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        inti(context,attrs);
    }

    //初始化
    private  void inti(Context context, AttributeSet attrs){
        mMatrix=new Matrix();
        mBitmapPaint= new Paint();
        mBorderPaint=new Paint();
        mBitmapPaint.setAntiAlias(true);//反锯齿
        //获取布局文件自定义控件设置的属性值
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.AvatarCircle);
        mStkroeWidth=typedArray.getDimension(R.styleable.AvatarCircle_StrokeWidth,0);
        mStrokeColor=typedArray.getColor(R.styleable.AvatarCircle_StrokeColor, Color.WHITE);
        typedArray.recycle();//回收属性对象

    }
    @Override
    protected void  onMeasure(int widthMeasureSpec,int heightMeasureSpec){
         super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        mWidth=Math.min(getMeasuredHeight(),getMaxWidth());
        mRadius=mWidth/2;
        setMeasuredDimension(mWidth,mWidth);
        mRadius=(int)(mRadius-2+mStkroeWidth);
    }
    //设置BitmapShader和涂料Paint
    private void setBitmapShader(){
        //获取drawaable对象
        Drawable drawable=getDrawable();
        if (drawable == null) {
            return;
        }
        //将图片转换为Bitmap
        BitmapDrawable bd= (BitmapDrawable) drawable;
        Bitmap bitmap=bd.getBitmap();
        //将bitmap载入着色器，后面两个参数x，y的缩放模式，Clamp为拉升
        mBitmapShader=new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        //初始化图片与view之间的伸缩比例
        float scale=1.0f;
        //将图片的宽高的的小者作为图片的变成，用来和view计算伸缩比例
        int bitmapSize=Math.min(bitmap.getWidth(),bitmap.getHeight());
        //计算缩放比例view的大小和图片大小之间的比例
        scale=mWidth*1.0f/bitmapSize;
        mMatrix.setScale(scale,scale);
        mBitmapShader.setLocalMatrix(mMatrix);
        mBitmapPaint.setShader(mBitmapShader);

    }
//设置边界笔刷paint
    private void setBorderPaint(){
        mBorderPaint.setStyle(Paint.Style.STROKE);//设置笔刷样式：原区域掏空，只画边界
        mBorderPaint.setColor(mStrokeColor);
        mBorderPaint.setStrokeCap(Paint.Cap.ROUND);//设置类型圆形
        mBorderPaint.setStrokeWidth(mStkroeWidth);
        //设置阴影
        this.setLayerType(LAYER_TYPE_SOFTWARE,mBorderPaint);
        mBorderPaint.setShadowLayer(12.0f,3.0f,3.0f,Color.BLACK);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if(getDrawable()==null){
            return;
        }
        //绘制内部圆形图片
        setBitmapShader();
        canvas.drawCircle(mWidth/2,mWidth/2,mRadius,mBitmapPaint);
        //绘制外边界
        setBorderPaint();
        canvas.drawCircle(mWidth/2,mWidth/2,mRadius,mBorderPaint);
    }
}
