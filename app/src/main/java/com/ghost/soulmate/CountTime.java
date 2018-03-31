package com.ghost.soulmate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ghost.soulmate.R;

/**
 * Created by mingjie on 2018/3/25.
 */

public class CountTime extends Activity implements View.OnClickListener
{   //先为控件起名字  -->  然后将其绑定起来
    private EditText input;
    private Button getTime,start,stop;
    private TextView time;
    int i = 0  ; //用来传时间   作为中间变量
    public static final String Action_time = "com.ghost.soulmate.intent.CountTime";
    //将所有的控件都获取其相应的id   外加设置控件
    private void initView(){
        input = (EditText) findViewById(R.id.et_input);
        getTime = (Button)findViewById(R.id.btn_getTime);
        start = (Button)findViewById(R.id.btn_start);
        stop = (Button) findViewById(R.id.btn_stop);
        time = (TextView) findViewById(R.id.tv_showtime);   // 按Ctrl + time确实可以显示其他地方有相同标识的,但是使用的只有当前layout中定义的属性
        getTime.setOnClickListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) { //给按钮添加监听时间
        switch (view.getId()){
            case R.id.btn_getTime:
                time.setText(input.getText().toString()); //从输入框中获取相应的时间
                i =  Integer.parseInt(input.getText().toString());        // 把获取到String 类型数值转化为int
                break;
        }

    }
    //设置启动  继承自acitivity
    protected  void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_time);
    }
}
