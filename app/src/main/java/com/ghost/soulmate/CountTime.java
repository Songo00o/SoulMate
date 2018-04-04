package com.ghost.soulmate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by mingjie on 2018/3/25.
 * changed by yunzhongxiaoma on 2018/4/1
 */

public class CountTime extends Activity {
    //先为控件起名字  -->  然后将其绑定起来
    private Button stop;
    private TextView time;
    private ProgressBar progress;
    int i = 0; // 用来传时间   作为中间变量
    public static final String Action_time = "com.ghost.soulmate.intent.CountTime";
    public int currentHour;
    public int currentMinute;

    //设置启动  继承自acitivity
    protected void onCreate(Bundle saveInstanceState) {
        //将所有的控件都获取其相应的id   外加设置控件
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_time);

        stop = (Button) findViewById(R.id.btn_stop);
        progress = (ProgressBar) findViewById(R.id.pb_progress);
        time = (TextView) findViewById(R.id.tv_countTime);   // 按Ctrl + time确实可以显示其他地方有相同标识的,但是使用的只有当前layout中定义的属性

        progress.setProgress(100);



        //接收参数
        Intent intent = getIntent();
        //从Intent当中根据key获得value
        Bundle date = intent.getExtras();
        if(intent != null){

            time.setText(String.format("%d小时:%d分钟",date.getInt("hours"),date.getInt("minutes")));
            //测试代码
//            String s = i.getStringExtra("hours");
//            Toast.makeText(CountTime.this,s,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(CountTime.this,"啥也没",Toast.LENGTH_SHORT).show();
        }

        // 放弃当前计划
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 取消线程执行（非一级功能：弹框确认是否停止）
                Intent intent = new Intent(CountTime.this,MainActivity.class);
                startActivity(intent);
            }
        });
        
    }
}
