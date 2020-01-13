package com.test.guide;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kit.guide.bean.GuideBean;
import com.kit.guide.callBack.GuideViewClickCallBack;
import com.kit.pagerCard.bean.PagerCardBean;
import com.kit.pagerCard.view.PagerCardView;

import java.util.ArrayList;
import java.util.List;


/**
 * @author libowu
 * @date 2019/07/30
 * 测试guideview
 */
public class MainActivity extends AppCompatActivity implements GuideViewClickCallBack, View.OnClickListener, PagerCardView.PagerCardListener<PagerCardBean> {
    private TabLayout tab;
    private ViewPager viewpager;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        tab = findViewById(R.id.tab);
        viewpager = findViewById(R.id.viewpager);
        fragments = new ArrayList<>();
        fragments.add(new CustomFragment());
        fragments.add(new CustomFragment());
        fragments.add(new CustomFragment());
        fragments.add(new CustomFragment());
        fragments.add(new CustomFragment());
        viewpager.setAdapter(new FslpVpAdapter(getSupportFragmentManager(),new String[]{"测试","测试","测试","测试","测试"},fragments));
        tab.addTab(tab.newTab().setText("测试"));
        tab.addTab(tab.newTab().setText("测试"));
        tab.addTab(tab.newTab().setText("测试"));
        tab.addTab(tab.newTab().setText("测试"));
        tab.addTab(tab.newTab().setText("测试"));
        viewpager.setOffscreenPageLimit(5);
        viewpager.setCurrentItem(4);
        tab.setupWithViewPager(viewpager);

       /* List<GuideBean> guideBeans = new ArrayList<>();
        guideBeans.add(new GuideBean(R.mipmap.guide,this,tab));
        GuideDialog guideDialog = new GuideDialog(getWindow().getDecorView(),guideBeans);
        guideDialog.show(getSupportFragmentManager(),getClass().getName());*/
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (i1 == 0){
                    ((CustomFragment)fragments.get(i)).showDialog();
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }

        });
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void guideClick(GuideBean guideBean, int index) {
        //如果想要在点击引导后执行什么动作，可以在这里执行
    }

    @Override
    public void guideMoreClick(List<GuideBean> guideBeans) {

    }

    @Override
    public void guideEndCallback() {
        //如果想要在引导播放结束后做什么操作，可以在此进行
    }


    @Override
    public void onClick(View view) {

    }


    @Override
    public void onItemClickListener(PagerCardBean pagerCardBean, int itemIndex, int currentPagerIndex) {
        Toast.makeText(this,"info为：",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPagerSelect(int currentPagerIndex) {
       //Toast.makeText(this,"当前页数为："+currentPagerIndex,Toast.LENGTH_LONG).show();
        Log.e("日志","当前页数为："+currentPagerIndex);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.e("日志","状态："+state);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.e("日志","滚动状态："+position+","+positionOffset+"，"+positionOffsetPixels);
    }

}
