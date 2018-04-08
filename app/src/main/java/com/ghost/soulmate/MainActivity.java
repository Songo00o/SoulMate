package com.ghost.soulmate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.ghost.soulmate.R;

public class MainActivity extends Activity{
    int[] jokes = {R.string.joke1, R.string.joke2, R.string.joke3, R.string.joke4, R.string.joke5};
    public static final String Action_main = "android.intent.action.MAIN";
    ImageButton feed;
    Button btn_test;
    pl.droidsonroids.gif.GifImageView   gif_cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        feed = (ImageButton)findViewById(R.id.ibtn_Feed);
        btn_test = findViewById(R.id.btn_test);

        gif_cat = findViewById(R.id.gif_cat);

        //为按钮的单击事件绑定事件监听器       --此方法为匿名内部类
        gif_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个Toast提示信息
                int randomNum = (int)(Math.random()*5);
                Toast.makeText(MainActivity.this, jokes[randomNum], Toast.LENGTH_SHORT).show();
            }
        });

        // 转到计划模块
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlanActivity.Action_plan);
                // 启动intent对应的Activity
                startActivity(intent);
            }
        });

        //测试模块
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CountTest.Action_Test));
            }
        });
    }

}
