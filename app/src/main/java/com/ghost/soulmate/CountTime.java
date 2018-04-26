package com.ghost.soulmate;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ghost.soulmate.custom.CircleProgressBar;

import java.util.Calendar;

/**
 * Created by mingjie on 2018/3/25.
 * changed by yunzhongxiaoma on 2018/4/1
 */

public class CountTime extends Activity {
    //先为控件起名字  -->  然后将其绑定起来
    private Button stop;
    private TextView time, plan;
    public static final  int Update = 0x001;
    int i = 0; // 用来传时间   作为中间变量
    public static final String Action_time = "com.ghost.soulmate.intent.CountTime";
    private int hours;
    private int minutes;
    private int progress; // 用来记录占多少百分比

    private int totaltime; //设置总时间用来控制进度条
    private int Angel;     //设置角度
    private Canvas canvas;
    private CircleProgressBar circlepb;
    //设置启动  继承自acitivity
    protected void onCreate(Bundle saveInstanceState) {
        //将所有的控件都获取其相应的id   外加设置控件
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_time);

        stop =  findViewById(R.id.btn_stop);
        time = findViewById(R.id.tv_countTime);   // 按Ctrl + time确实可以显示其他地方有相同标识的,但是使用的只有当前layout中定义的属性
        plan = findViewById(R.id.tv_plan);
        circlepb = findViewById(R.id.pb_circle);
        //接收参数
        Intent intent = getIntent();
        //从Intent当中根据key获得value
        final Bundle date = intent.getExtras();
        hours = date.getInt("hours");
        minutes = date.getInt("minutes");
        totaltime = hours*60 + minutes;         //只需要获取输入的定值即可

            time.setText(String.format("%d小时:%d分",date.getInt("hours"),date.getInt("minutes")));
            plan.setText(date.getString("plan"));


        // 放弃当前计划
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 取消线程执行（非一级功能：弹框确认是否停止）
                Intent intent = new Intent(CountTime.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //创建主线程的Handler,复写其方法
       final Handler myhandler = new Handler(){
           public void handleMessage(Message msg){
               super.handleMessage(msg);
               if(msg.what == Update){
                   time.setText(String.format("%d小时:%d分",msg.arg2,msg.arg1));
              //     Toast.makeText(CountTime.this, ""+(Integer)msg.obj, Toast.LENGTH_SHORT).show();
                   circlepb.setmAngel((Integer) msg.obj);
                   circlepb.setmText(String.format("%d小时:%d分",msg.arg2,msg.arg1));
               }
           }
       };
       //设置子线程
       new Thread(){
           public void run(){
               for(int j = hours; j>=0; j--){
                   for(int i = minutes; i>=0 ; i--){
                       Message msg = new Message();
                       msg.what = Update;
                       msg.arg1 = i;
                       msg.arg2 = j;
                       int current = j*60 + i;
                       Angel = (totaltime - current) * 360 / totaltime;
                       msg.obj = Angel;
                       myhandler.sendMessage(msg);
                       try{
                           Thread.sleep(1000);
                       }catch (InterruptedException e){
                           e.printStackTrace();
                       }

                   }
                   minutes = 59;
               }
//               Toast.makeText(CountTime.this,"小主,您很棒棒哦~~",Toast.LENGTH_SHORT).show();         //添加此行则报错,提示说是handler中包含了一个线程
               startActivity(new Intent(CountTime.this,MainActivity.class));
               finish();
           }
       }.start();
    }
}


