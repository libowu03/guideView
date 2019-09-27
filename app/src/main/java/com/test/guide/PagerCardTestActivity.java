package com.test.guide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kit.pagerCard.PagerCardBean;
import com.kit.pagerCard.PagerCardView;

import java.util.ArrayList;
import java.util.List;

public class PagerCardTestActivity extends AppCompatActivity implements PagerCardView.PagerCardListener {
    private PagerCardView pagerCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_card_test);
        pagerCardView = findViewById(R.id.cardview);
        List<PagerCardBean> bean = new ArrayList<>();
        bean.add(new PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569418221253&di=1770a74bb77874862ef83b5d0dd9deea&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201511%2F14%2F20151114095852_2KEvG.jpeg").setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setRedPointText("3"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setShowRedPoint(true));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setRedPointText("4"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setRedPointText("3"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setShowRedPoint(true));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setRedPointText("4"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setRedPointText("3"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setShowRedPoint(true));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setRedPointText("4"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setRedPointText("3"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setShowRedPoint(true));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setRedPointText("4"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setRedPointText("4"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setRedPointText("3"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setShowRedPoint(true));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试").setRedPointText("4"));
        bean.add(new PagerCardBean(""+R.mipmap.default_pet).setName("测试"));
        pagerCardView.setCardContent(bean,getSupportFragmentManager(),2,4,this);
        //pagerCardView.setCurrentPager(1);
    }

    @Override
    public void onItemClickListener(PagerCardBean pagerCardBean, int itemIndex, int currentPagerIndex) {

    }

    @Override
    public void onPagerSelect(int currentPagerIndex) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
}
