package com.kit.pagerCard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kit.guide.R;
import com.kit.guide.utils.GuideViewUtils;

import java.util.ArrayList;
import java.util.List;

public class PagerCardView<T extends PagerCardBean> extends LinearLayout implements CardPagerAdapter.ClickPagerCardListener<T> {
    protected View view;
    protected LinearLayout indicator;
    protected List<View> indicatorList;
    protected int oldIndicatorIndex;
    private PagerCardListener pagerCardListener;
    private SelfViewPagerView pager2;
    private List<Fragment> fragments;

    private int seIndicatorColor, unSeIndicatorColor,pagerCardTextColor,pagerCardTextSize;


    public PagerCardView(Context context) {
        this(context,null);
    }

    public PagerCardView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public PagerCardView(Context context,AttributeSet attributeSet,int defStyleAttr){
        super(context, attributeSet,0);
        view = LayoutInflater.from(context).inflate(R.layout.view_pagecard,this,true);
        TypedArray attr = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.PagerCardView, defStyleAttr, 0);
        unSeIndicatorColor = attr.getColor(R.styleable.PagerCardView_unSeIndicatorColor, Color.parseColor("#cccccc"));
        seIndicatorColor = attr.getColor(R.styleable.PagerCardView_seIndicatorColor,Color.parseColor("#000000"));
    }

    public void setCardContent(List<T> content, FragmentManager fragmentManager, int rowNum, int colNum){
        setCardContent(content,fragmentManager,rowNum,colNum,null);
    }

    /**
     * 设置每个pagerCard中的内容
     * @param content pagerCard中的内容
     * @param fragmentManager viewpager需要用到的fragment管理器
     * @param rowNum 行数
     * @param colNum 列数
     * @param pagerCardListener 内容的点击监听器
     */
    public void setCardContent(List<T> content, FragmentManager fragmentManager, int rowNum, int colNum, final PagerCardListener pagerCardListener){
        this.pagerCardListener = pagerCardListener;
        if (fragmentManager == null || content == null || content.size() == 0 || rowNum == 0|| colNum == 0){
            return;
        }
        fragments = new ArrayList<>();
        indicator = view.findViewById(R.id.pagerCardIndicator);
        indicator.removeAllViews();
        indicatorList = new ArrayList<>();
        if (content.size() <= rowNum*colNum){
            PagerCardContentFragment fragment = makeFragment(colNum);
            fragment.setFragmentList(content);
            fragments.add(fragment);
            indicatorList.add(makeIndicator());
        }else {
            int length;
            length = content.size()/(rowNum*colNum);
            if (content.size()/(rowNum*colNum*1.0f) > 0){
                length++;
            }
            for (int i=0;i<length;i++){
                indicatorList.add(makeIndicator());
                List<PagerCardBean> result = new ArrayList<>();
                for (int j=i*rowNum*colNum;j<(i+1)*rowNum*colNum;j++){
                    if (j >= content.size()){
                        break;
                    }
                    result.add(content.get(j));
                }
                PagerCardContentFragment fragment = makeFragment(colNum);
                fragment.setFragmentList(result);
                fragments.add(fragment);
            }

            //设置指示器第零个为选中状态
            if (indicatorList.size() != 0){
                oldIndicatorIndex = 0;
                indicatorList.get(0).setBackgroundResource(R.drawable.indicator_bg);
            }

        }
        //fragments.remove(0);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(fragmentManager,fragments);
        pager2 = view.findViewById(R.id.pagerCard);
        pager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                changeIndicator(position);
                if (positionOffset < 0.5f){
                    changeIndicator(position);
                }else {
                    changeIndicator(position+1);
                }
                if (PagerCardView.this.pagerCardListener != null){
                    PagerCardView.this.pagerCardListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                changeIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (PagerCardView.this.pagerCardListener != null){
                    PagerCardView.this.pagerCardListener.onPageScrollStateChanged(state);
                }
            }
        });
        pager2.setRow(rowNum,colNum,content.size());
        pager2.setAdapter(pagerAdapter);
        pager2.setOffscreenPageLimit(3);
        pager2.setPageMargin(GuideViewUtils.dip2px(getContext(),0));


    }

    /**
     * 改变指示器选中状态
     * @param position
     */
    protected void changeIndicator(int position){
        if (PagerCardView.this.pagerCardListener != null){
            PagerCardView.this.pagerCardListener.onPagerSelect(position);
        }
        if (position < 0 || position >= indicatorList.size()){
            return;
        }
       indicatorList.get(position).setBackgroundResource(R.drawable.indicator_bg);
       if (position != oldIndicatorIndex){
           indicatorList.get(oldIndicatorIndex).setBackgroundResource(R.drawable.indicator_unselect_bg);
       }
       oldIndicatorIndex = position;
    }

    /**
     * 设置卡片的指示器
     * @return
     */
    protected View makeIndicator(){
        View v = LayoutInflater.from(getContext()).inflate(R.layout.view_indicator_cardpager,null,false);
        LayoutParams ll = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.height = GuideViewUtils.dip2px(getContext(),5);
        ll.width = GuideViewUtils.dip2px(getContext(),5);
        ll.leftMargin = GuideViewUtils.dip2px(getContext(),3);
        ll.rightMargin = GuideViewUtils.dip2px(getContext(),3);
        v.setLayoutParams(ll);
        indicator.addView(v);
        return v;
    }

    /**
     * 设置fragment
     * @param col 卡片中要显示内容的列数
     * @return
     */
    protected PagerCardContentFragment makeFragment(int col){
        PagerCardContentFragment cardContentFragment = new PagerCardContentFragment();
        cardContentFragment.setPagerCardListener(this);
        Bundle bundle = new Bundle();
        bundle.putInt("col",col);
        cardContentFragment.setArguments(bundle);
        return cardContentFragment;
    }

    @Override
    public void onClickPagerCardListener(T pagerCardBean, int index) {
        if (pagerCardListener != null){
            pagerCardListener.onItemClickListener(pagerCardBean,index,pager2.getCurrentItem());
        }
    }

    public interface PagerCardListener<T extends PagerCardBean>{
        void onItemClickListener(T pagerCardBean, int itemIndex, int currentPagerIndex);
        void onPagerSelect(int currentPagerIndex);
        void onPageScrollStateChanged(int state);
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
    }

    public void updateContent(){

    }
}
