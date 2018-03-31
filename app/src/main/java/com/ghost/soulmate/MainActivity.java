package com.ghost.soulmate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ghost.soulmate.R;

public class MainActivity extends Activity{
    int[] jokes = {R.string.joke1, R.string.joke2, R.string.joke3, R.string.joke4, R.string.joke5};

    ImageButton cat;
    Button timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cat = (ImageButton)findViewById(R.id.img);
        timer = (Button)findViewById(R.id.btn_start);
        //设置点击跳转
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CountTime.Action1));

            }
        });

        //为按钮的单击事件绑定事件监听器       --此方法为匿名内部类
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建一个Toast提示信息
                int randomNum = (int)(Math.random()*5);
                Toast.makeText(MainActivity.this, jokes[randomNum], Toast.LENGTH_SHORT).show();
            }
        });
    }

}
