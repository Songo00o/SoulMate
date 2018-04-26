package com.ghost.soulmate;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.animation.Animator;

public class AnimationTest extends Activity {
    public static final String Action_anim = "com.ghost.soulmate.intent.AnimationTest";

    Button big,small,beat;
    ImageView fox,heart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_anim);

        big = findViewById(R.id.big);
        small = findViewById(R.id.small);
        beat  = findViewById(R.id.beat);

        fox = findViewById(R.id.test_fox);
        heart = findViewById(R.id.heart);
        //加载动画资源
        final Animation anim = AnimationUtils.loadAnimation(this,R.anim.my_anim);
        final Animation anim2 = AnimationUtils.loadAnimation(this,R.anim.my_anim2);
        final Animation anim3 = AnimationUtils.loadAnimation(this,R.anim.my_anim3);

        //设置动画结束后保留结束状态
        anim.setFillAfter(true);
        //anim3.setFillAfter(true);
        big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fox.startAnimation(anim);

            }
        });
        small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fox.startAnimation(anim3);
            }
        });
        beat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heart.startAnimation(anim2);
            }
        });
  /*      anim2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                heart.startAnimation(anim2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });*/
    }
   /* public class HeartBeats extends View{
        public HeartBeats(Context context){
            super(context);
            ObjectAnimator obj = (ObjectAnimator)AnimatorInflater.loadAnimator(AnimationTest.this,R.anim.my_anim);
            obj.setEvaluator(new ArgbEvaluator());      //此类可以自动计算，至于计算什么，不清楚 冏
            obj.setTarget(this);
            obj.start();
        }
    }*/
}
