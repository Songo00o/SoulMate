package com.ghost.soulmate;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by mingjie on 2018/3/25.
 * changed by yunzhongxiaoma on 2018/4/1
 */

public class CountTime extends Activity {
    //先为控件起名字  -->  然后将其绑定起来
    private Button stop;
    private TextView time;
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
        time = (TextView) findViewById(R.id.tv_countTime);   // 按Ctrl + time确实可以显示其他地方有相同标识的,但是使用的只有当前layout中定义的属性

        // 放弃当前计划
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 取消线程执行（非一级功能：弹框确认是否停止）
            }
        });
        
    }

    // 获取当前系统时间
    public void getCurrentHourAndMinute() {
        Calendar calendar = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);   // 当前小时
        currentMinute = calendar.get(Calendar.MINUTE);  // 当前分钟
        Log.v("currentTime...    ", String.valueOf(currentHour+":" + currentMinute));
    };

}
