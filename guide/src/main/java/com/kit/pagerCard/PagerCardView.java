package com.kit.pagerCard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.load.resource.gif.GifDrawableResource;
import com.kit.guide.R;
import com.kit.guide.utils.GuideViewUtils;
import com.kit.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * pagerCard的容器，容器里有一个viewpager和若干个textview组成，textview用于指示器的显示
 * @author libowu
 * @date 2019/09/27
 * @param <T>
 */
public class PagerCardView<T extends PagerCardBean> extends LinearLayout implements CardPagerAdapter.ClickPagerCardListener<T> {
    protected View view;
    protected LinearLayout indicator;
    protected List<View> indicatorList;
    protected int oldIndicatorIndex;
    private PagerCardListener pagerCardListener;
    private SelfViewPagerView pager2;
    private List<Fragment> fragments;
    private PagerCardAttribute attribute;
    private int indicatorWidth,indicatorHeight;
    private int seIndicatorColor, unSeIndicatorColor,pagerCardTextColor,pagerCardTextSize;
    private boolean needIndicator;
    private int colm,row;


    public PagerCardView(Context context) {
        this(context,null);
    }

    public PagerCardView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public PagerCardView(Context context,AttributeSet attributeSet,int defStyleAttr){
        super(context, attributeSet,0);
        view = LayoutInflater.from(context).inflate(R.layout.view_pagecard,this,true);
        initAttr(context,attributeSet,defStyleAttr);
    }

    /**
     * 获取xml属性
     * @param context
     * @param attributeSet
     * @param defStyleAttr
     */
    @SuppressLint("ResourceAsColor")
    private void initAttr(Context context, AttributeSet attributeSet, int defStyleAttr) {
        TypedArray attr = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.PagerCardView, defStyleAttr, 0);
        //获取未选中指示器颜色
        unSeIndicatorColor = attr.getColor(R.styleable.PagerCardView_unSeIndicatorColor, Color.parseColor("#cccccc"));
        //获取选中指示器颜色
        seIndicatorColor = attr.getColor(R.styleable.PagerCardView_seIndicatorColor,Color.parseColor("#000000"));
        //指示器高度
        indicatorHeight = (int) attr.getDimension(R.styleable.PagerCardView_indicatorHeight,GuideViewUtils.dip2px(getContext(),5));
        //指示器宽度
        indicatorWidth = (int) attr.getDimension(R.styleable.PagerCardView_indicatorWidth,GuideViewUtils.dip2px(getContext(),5));
        //pagerCard标题颜色
        int pagerCardTextColor = attr.getColor(R.styleable.PagerCardView_pagerCardTextColor,Color.BLACK);
        //pagerCard标题大小
        int pagerCardTextSize = (int) attr.getDimension(R.styleable.PagerCardView_pagerCardTextSize,12);
        //pagerCard图片宽度
        int imgWidht = (int) attr.getDimension(R.styleable.PagerCardView_pagerCardImgWidth,-1);
        //pagerCard图片高度
        int imgHeight = (int) attr.getDimension(R.styleable.PagerCardView_pagerCardImgHeight,-1);
        //pagerCard空点宽度
        int redPointWidht = (int) attr.getDimension(R.styleable.PagerCardView_pagerCardRedPointWidth,GuideViewUtils.dip2px(getContext(),6));
        //pagerCard红点高度
        int redPointHeight = (int) attr.getDimension(R.styleable.PagerCardView_pagerCardRedPointHeight,GuideViewUtils.dip2px(getContext(),6));
        //pagerCard红点颜色背景
        int redPointBackground = attr.getColor(R.styleable.PagerCardView_pagerCardRedPointTextColor,Color.RED);
        //红点字体颜色
        int redPointTextSize = (int) attr.getDimension(R.styleable.PagerCardView_pagerCardRedPointTextSize,GuideViewUtils.dip2px(getContext(),10));
        //获取图片类型
        int imgType = attr.getInt(R.styleable.PagerCardView_pagerCardImgType,0);
        //获取圆角矩形的圆角弧度
        int imgCorner = (int) attr.getDimension(R.styleable.PagerCardView_imgCorner,GuideViewUtils.dip2px(getContext(),6));
        //是否需要显示指示器
        needIndicator = attr.getBoolean(R.styleable.PagerCardView_needIndicator,true);
        //是否启用上下滑动
        boolean canScrollVertically = attr.getBoolean(R.styleable.PagerCardView_canScrollVertically,false);
        //获取分割线宽度
        int itemDecorationWeight = (int) attr.getDimension(R.styleable.PagerCardView_itemDecorationWeight,-1);
        //获取分割线颜色
        int itemDecorationColor = attr.getColor(R.styleable.PagerCardView_itemDecorationColor,Color.parseColor("#EBEBEB"));
        //获取左边的margin
        int itemMarginLeft = (int) attr.getDimension(R.styleable.PagerCardView_itemMarginLeft,0);
        //获取右边的margin
        int itemMarginRight = (int) attr.getDimension(R.styleable.PagerCardView_itemMarginRight,0);
        //获取顶部的margin
        int itemMarginTop = (int) attr.getDimension(R.styleable.PagerCardView_itemMarginTop,0);
        //获取底部的margin
        int itemMarginBottom = (int) attr.getDimension(R.styleable.PagerCardView_itemMarginBottom,0);
        //获取margin
        int itemMargin = (int) attr.getDimension(R.styleable.PagerCardView_itemMargin,0);
        //获取左边的padding
        int itemPaddingLeft = (int) attr.getDimension(R.styleable.PagerCardView_itemPaddingLeft,0);
        //获取右边的padding
        int itemPaddingRight = (int) attr.getDimension(R.styleable.PagerCardView_itemPaddingRight,0);
        //获取顶部的padding
        int itemPaddingTop = (int) attr.getDimension(R.styleable.PagerCardView_itemPaddingTop,0);
        //获取底部的padding
        int itemPaddingBottom = (int) attr.getDimension(R.styleable.PagerCardView_itemPaddingBottom,0);
        //获取padding
        int itemPadding = (int) attr.getDimension(R.styleable.PagerCardView_itemPadding,0);
        //获取每个宫格的背景resource资源
        Drawable itemBackgroundResource = attr.getDrawable(R.styleable.PagerCardView_itemBg);
        //获取每个宫格背景色
        int itemBackgroundColor = Color.WHITE;
        if (itemBackgroundResource == null){
            itemBackgroundColor = attr.getColor(R.styleable.PagerCardView_itemBg,Color.TRANSPARENT);
        }

        attribute = new PagerCardAttribute(imgHeight,imgWidht,redPointTextSize,redPointBackground,redPointWidht,
                redPointHeight,pagerCardTextSize,pagerCardTextColor,unSeIndicatorColor,seIndicatorColor,
                10,10,imgType,imgCorner,needIndicator,canScrollVertically,itemDecorationColor,itemDecorationWeight,
                itemMarginLeft,itemMarginRight,itemMarginTop,itemMarginBottom,itemMargin,itemPadding,itemPaddingLeft,itemPaddingTop,itemPaddingRight,itemPaddingBottom,itemBackgroundColor,itemBackgroundResource);
        attr.recycle();
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
        this.row = rowNum;
        this.colm = colNum;
        this.pagerCardListener = pagerCardListener;
        if (fragmentManager == null || content == null || content.size() == 0 || rowNum == 0|| colNum == 0){
            LogUtils.i("kitMessage","参数错误");
            return;
        }
        fragments = new ArrayList<>();
        indicator = view.findViewById(R.id.pagerCardIndicator);
        indicator.removeAllViews();
        indicatorList = new ArrayList<>();
        if (content.size() <= rowNum*colNum || rowNum == -1){
            PagerCardContentFragment fragment = makeFragment(colNum);
            fragment.setFragmentList(content);
            fragments.add(fragment);
            if (needIndicator){
                indicatorList.add(makeIndicator());
            }
        }else {
            int length;
            length = content.size()/(rowNum*colNum);
            if (content.size()/(rowNum*colNum*1.0f) > 0){
                length++;
            }
            for (int i=0;i<length;i++){
                if (needIndicator){
                    indicatorList.add(makeIndicator());
                }
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
                GradientDrawable gifDrawableResource = (GradientDrawable)indicatorList.get(0).getBackground();
                gifDrawableResource.setColor(seIndicatorColor);
            }

        }
        /*if (fragments.size() > 1){
            fragments.add(0,fragments.get(fragments.size()-1));
            fragments.add(fragments.size()-1,fragments.get(0));
        }*/
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
                /*if (fragments.size() > 1){
                    if (position == fragments.size()-1){

                    }
                }*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (PagerCardView.this.pagerCardListener != null){
                    PagerCardView.this.pagerCardListener.onPageScrollStateChanged(state);
                }
            }
        });
        if (rowNum == -1){
            pager2.setRow(rowNum,colNum,content.size(),true,attribute);
        }else {
            pager2.setRow(rowNum,colNum,content.size(),false,attribute);
        }
        pager2.setAdapter(pagerAdapter);
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
        GradientDrawable gifDrawableResource = (GradientDrawable)v.getBackground();
        gifDrawableResource.setColor(unSeIndicatorColor);
        LayoutParams ll = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.height = indicatorHeight;
        ll.width = indicatorWidth;
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
        cardContentFragment.setAttribute(attribute);
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

    /**
     * pagerCard的动作监听
     * @param <T>
     */
    public interface PagerCardListener<T extends PagerCardBean>{
        void onItemClickListener(T pagerCardBean, int itemIndex, int currentPagerIndex);
        void onPagerSelect(int currentPagerIndex);
        void onPageScrollStateChanged(int state);
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
    }

    /**
     * 更新某一页中的内容
     * @param pagerNum 要更新内容的所在页码
     */
    public void updatePagerContent(int pagerNum,List<T> updateContent){

    }

    /**
     * 设置当前的pager
     * @param pagerNum 页码
     */
    public void setCurrentPager(int pagerNum){
        setCurrentPager(pagerNum,false);
    }

    /**
     * 更新pagerNum页面的数据
     * @param pagerNum
     */
    public boolean updatePagerCardList(int pagerNum,List<T> contentList){
        if (fragments == null){
            LogUtils.i("kitMessage","请在更新页面数据前设置数据，即调用setCardContent方法进行页面数据装载，目前pagerCard中不存在页面");
            return false;
        }else {
            if (pagerNum >= fragments.size()){
                LogUtils.i("kitMessage","传入的页码数大于pagerCard中的最大页码数，将会出现“越界”的情况，请检查传入参数是否正确");
                return false;
            }
            if (contentList.size() > row*colm){
                LogUtils.i("kitMessage","更新集合长度不允许超过当前页面被定义的长度，即在调用setCardContent方法时，传入的rowNum和colNum决定了页面可显示的最大内容，更新的内容长度不允许超过rowNum*colNum");
                return false;
            }
            ((PagerCardContentFragment)fragments.get(pagerNum)).updatePagerCardList(contentList);
            return true;
        }
    }

    /**
     * 获取某页中的所有内容
     * @param index
     * @return
     */
    public List<T> getPagerList(int index){
        if (fragments == null){
            LogUtils.i("kitMessage","请在获取页面数据前设置数据，即调用setCardContent方法进行页面数据装载，目前pagerCard中不存在页面");
            return null;
        }else {
            if (index >= fragments.size()){
                LogUtils.e("kitError","传入的页码数大于pagerCard中的最大页码数，将会出现“越界”的情况，请检查传入参数是否正确");
            }
            //复制一份数据出来，如果直接返回源数据，调用者就可以直接给源数据内容进行修改了，而不需要经过updatePagerCardList方法，这是不允许的，如果允许updatePagerCardList中的判断也就没有意义了。
            List<T> pagerList = new ArrayList<>();
            List<T> source = ((PagerCardContentFragment)fragments.get(index)).getPagerContentList();
            for (int i=0; i< source.size(); i++){
                pagerList.add(source.get(i));
            }
            return pagerList;
        }
    }

    /**
     * 获取有多少页内容
     * @return
     */
    public int getPagerSize(){
        if (fragments != null){
            return fragments.size();
        }else {
            return 0;
        }
    }

    /**
     * 设置当前的pager
     * @param pagerNum 页码
     * @param smoothScroll 是否启用滑动动画
     */
    public void setCurrentPager(int pagerNum,boolean smoothScroll){
        if (pager2 != null){
            pager2.setCurrentItem(pagerNum,smoothScroll);
        }else {
            LogUtils.e("KitError","PagerCard：viewpager can not be null");
        }
    }
}
