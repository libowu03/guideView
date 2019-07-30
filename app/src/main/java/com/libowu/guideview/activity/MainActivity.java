package com.libowu.guideview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.libowu.guideview.R;
import com.libowu.guideview.bean.GuideBean;
import com.libowu.guideview.view.GuideView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView,textViewTwo;
    private GuideView guide;
    private List<GuideBean> guides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        configGuideView();
        initListen();

    }

    private void initListen() {
        textViewTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //想要显示引导时，调用此方法即可显示
                guide.showGuide();
            }
        });
    }

    /**
     * 设置guideview的基本配置
     */
    private void configGuideView() {
        //设置遮罩层颜色
        GuideView.Config.COLOR = Color.parseColor("#cc000000");
        //是否一屏显示多个控件说明
        GuideView.Config.OPENMORE = false;
    }

    private void initView() {
        textView = findViewById(R.id.test);
        textViewTwo = findViewById(R.id.test_two);
        guide = findViewById(R.id.guide);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //将要说明的控件添加到集合中，让后给guideview设置数据即可
        guides = new ArrayList<>();
        guides.add(new GuideBean(R.mipmap.guide,this,textView));
        guides.add(new GuideBean(R.mipmap.xuankong_year,this,textViewTwo));
        guide.setGuideBeans(guides);
    }
}
