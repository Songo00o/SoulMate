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
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by yunzhongxiaoma on 2018/4/1.
 */

public class PlanActivity extends AppCompatActivity {
    EditText title; // 任务标题
    TextView date;  // 预期完成时间
    EditText description;    // 备注
    Button cancel;
    Button start;
    int hours_get,minutes_get;
    String plan_get;
    public static final String Action_plan = "com.ghost.soulmate.intent.addPlan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        title = (EditText) findViewById(R.id.et_title);
        date = (TextView) findViewById(R.id.tv_date);
        description = (EditText)findViewById(R.id.et_description);
        cancel = (Button) findViewById(R.id.btn_cancel);
        start = (Button) findViewById(R.id.btn_start);

        // 返回主界面
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                // 启动intent对应的Activity
                Intent intent = new Intent(PlanActivity.this, MainActivity.class);
                startActivity(intent);
                finish();   // 结束当前Activity
            }
        });

        // 跳转到倒计时视图
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                if (hours_get != 0 || minutes_get != 0 ) {  // 如果用户选过时间，继续
                    plan_get = title.getText().toString();                                            //获取用户输入的计划
                    Intent intent = new Intent(PlanActivity.this,CountTime.class);
                     Bundle bundle = new Bundle();
                     bundle.putInt("hours",hours_get);
                     bundle.putInt("minutes",minutes_get);
                     bundle.putString("plan",plan_get);
                     intent.putExtras(bundle );

//                    i.putExtra("hours",hours_get);   进行简单的传值时,一直传不进去很是无奈
//                    i.putExtra("minutes",minutes_get);
                    startActivity(intent);


                } else {
                    Toast.makeText(PlanActivity.this, "小主,您的时间还没定呢！囧rz=З",Toast.LENGTH_SHORT).show();
                }

            }
        });

        // 显示并监听当前选择时间控件
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog();
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
                    String time = String.format("预期%d小时:%d分钟完成", hours, minutes);
                    date.setText(time);
                    hours_get = hours;
                    minutes_get = minutes;
//                    // 利用Intent进行传值         ---发现此处的值似乎传不出去，必须在startActivity前,好像才行
//                    Intent i = new Intent();
//                    i.putExtra("hours",hours);
//                    i.putExtra("minutes",minutes);

                }
            }, 0, 0, true
        ).show();
    }

}
