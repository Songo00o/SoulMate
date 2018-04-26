package com.ghost.soulmate.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import com.ghost.soulmate.R;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class CircleProgressBar extends View {
    private final static String TAG = CircleProgressBar.class.getSimpleName();  //获取当前控制台输出日志
    //定义自定义属性中的默认值


    private int progressBarColor;    //进度条颜色
    private int radius;              //圆形进度条半径
    private int roundWidth;          //线的宽度

    private String mText;           //中间的文字
    private int textColor;          //字的颜色
    private float textSize;         //字体大小

    private int incircleColor;   //内圆的颜色
    private int outcircleColor;  //外圈圆的颜色

    private int mAngle;         //弧度
    private int mProgress = 0;  //进度
    private static int MAX_PROGRESS = 100;

    //必要组件及属性
    private Context mContext;     //init方法中使用到了
    private Paint mPaint;

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }




    private void init(AttributeSet attributeSet){
       mPaint = new Paint();
       TypedArray typedArray = mContext.obtainStyledAttributes(attributeSet,R.styleable.CircleProgressBar);
       if(typedArray != null){
           radius   = typedArray.getDimensionPixelSize(R.styleable.CircleProgressBar_progress_radius,50);
           roundWidth = typedArray.getDimensionPixelOffset(R.styleable.CircleProgressBar_progress_radius_width,3);
           progressBarColor = typedArray.getColor(R.styleable.CircleProgressBar_progress_color,Color.CYAN);

           textColor = typedArray.getColor(R.styleable.CircleProgressBar_progress_text_color,Color.BLACK);
           incircleColor = typedArray.getColor(R.styleable.CircleProgressBar_progress_incircle_color,Color.GRAY);
           outcircleColor = typedArray.getColor(R.styleable.CircleProgressBar_progress_outcircle_color,Color.CYAN);

           typedArray.recycle();
       }

    }
    //设置偏转角度
    public  int setmAngel(int mAngle){
        this.mAngle = mAngle;
        Log.e("angle", "----------------mAngle" + mAngle);
        invalidate();

        return  mAngle;
    }
    public int getmAngle(){
        return  this.mAngle;
    }
    public  void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int center = getWidth() / 2;  //圆心
        mPaint.setColor(progressBarColor);
        radius  = (center - roundWidth/2);                                        //设置半径,为了显示出内外圆之分
        mPaint.setStrokeWidth(roundWidth);  //线的宽度
        mPaint.setStyle(Paint.Style.STROKE); //空心圆 ,描边
        mPaint.setAntiAlias(true);       //消除锯齿效果
        mPaint.getStrokeCap();
        canvas.drawCircle(center,center,radius,mPaint);


        //画内圆
        int inradius = radius - roundWidth*2;
        mPaint.setColor(incircleColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(center,center,inradius,mPaint);

        //画进度    一个弧线
        radius = radius - roundWidth;
        mPaint.setColor(outcircleColor);
        RectF rectF = new RectF(center - radius,center - radius,center + radius,center + radius); //圆弧范围的外界矩形
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,-90,mAngle,false,mPaint);
        Log.e("draw", "----------------mAngle" + mAngle);

        canvas.save();          //平移画布前 保存之前画的

        //画进度终点的小球  --旋转画布的方式实现
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        canvas.translate(center,center);        //将画布坐标原点移动至圆心
        canvas.rotate(mAngle-90);              //  旋转和进度相同的角度
        canvas.translate(radius,0);
        canvas.drawCircle(0,0,roundWidth,mPaint);
        canvas.restore();   //画完后回复画布坐标
//
        //画文字  将坐标平移至圆心
        canvas.translate(center,center);
        mPaint.setStrokeWidth(0);
        mPaint.setColor(textColor);
          //设置默认显示的字符 以防空指针异常
        if(mText == null){
            mText = ""+mProgress;
        }
          //动态设置文字为圆半径 ,计算字体大小
        float textLength = mText.length();
        textSize = radius * 2 / textLength;
        mPaint.setTextSize(textSize);
          //将文字画到中间
        float textWidth =  mPaint.measureText(mText);
        canvas.drawText(mText,-textWidth / 2, textSize / 2 , mPaint);   //此处圆心的设置不是太理解
    }

    //提供给外部类一个可以修改进度的方法
//    public int getmProgress(){
//            return mProgress;
//    }
//    public void setmProgress(int p){
//        if (p > MAX_PROGRESS){
//                mProgress = MAX_PROGRESS;
//                mAngle = 360;
//         }else {
//                mProgress = p;
//                mAngle = 360 * p /MAX_PROGRESS ; //360 * p /MAX_PROGRESS;
//        }
//    }
//
    public String getmText(){
            return mText;
    }
    public final void setmText(String mText){
            this.mText = mText;
            invalidate();
    }


    //设置动画          --目前不是很懂里面的属性值
    public void setAnimProgress(int p) {
        if (p > MAX_PROGRESS) {
            mProgress = MAX_PROGRESS;
        } else {
            mProgress = p;
        }
        //设置属性动画
        ValueAnimator valueAnimator = new ValueAnimator().ofInt(0, p);
        //动画从快到慢
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(3000);
        //监听值的变化
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentV = (Integer) animation.getAnimatedValue();
                Log.e("fwc", "current" + currentV);
                mAngle = 360 * currentV / MAX_PROGRESS;
                mText = currentV + "%";
                invalidate();
            }
        });
        valueAnimator.start();
    }
}

