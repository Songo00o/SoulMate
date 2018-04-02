package com.ghost.soulmate;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by yunzhongxiaoma on 2018/4/1.
 */

public class PlanActivity extends AppCompatActivity {
    EditText title; // 任务标题
    TextView date;  // 预期完成时间
    EditText remind;    // 备注
    Button cancel;
    Button start;
    Bundle bundle;
    public static final String Action_plan = "com.ghost.soulmate.intent.addPlan";
    public boolean pickTime_ok = false; // 选择时间标志位,初始false没选择

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        title = (EditText) findViewById(R.id.et_title);
        date = (TextView) findViewById(R.id.tv_date);
        remind = (EditText) findViewById(R.id.et_remind);
        cancel = (Button) findViewById(R.id.btn_cancel);
        start = (Button) findViewById(R.id.btn_start);

        // 返回主界面
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                Intent intent = new Intent(MainActivity.Action_main);
                startActivity(intent);  // 启动intent对应的Activity
                finish();   // 结束当前Activity
            }
        });

        // 跳转到倒计时视图
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                if (pickTime_ok) {  // 如果用户选过时间，继续
                    Intent intent = new Intent();
                    intent.setAction(CountTime.Action_time);
                    bundle = new Bundle();
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        // 显示并监听当前选择时间控件
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog();
                pickTime_ok = true; // 更改选择时间标志位
            }
        });

    }


    public void timePickerDialog() {
        // 用户选择预期完成时间
        new TimePickerDialog(this,
            // 监听用户选择的时间
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                    bundle.putInt("planHours", hours);
                    bundle.putInt("planMinutes", minutes);
                    String time = String.format("预期%d小时:%d分钟完成", hours, minutes);
                    date.setText(time);
                }
            }, 0, 0, true
        ).show();
    }

}
