package com.libowu.guideview.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.libowu.guideview.R;
import com.libowu.guideview.bean.GuideBean;
import com.libowu.guideview.callBack.GuideViewClickCallBack;
import com.libowu.guideview.view.GuideView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author libowu
 * @date 2019/07/30
 * 测试guideview
 */
public class MainActivity extends AppCompatActivity implements GuideViewClickCallBack {
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
        guide.setGuideViewClickCallBack(this);
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
        //设置是否精确点击，即只有点击到对应的控件区域，才执行点击
        GuideView.Config.CLICK_EXACT = true;
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

    @Override
    public void guideClick(GuideBean guideBean,int index) {
        //如果想要在点击引导后执行什么动作，可以在这里执行
    }

    @Override
    public void guideMoreClick(List<GuideBean> guideBeans) {

    }

    @Override
    public void guideEndCallback() {
        //如果想要在引导播放结束后做什么操作，可以在此进行
    }
}
