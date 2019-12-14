package com.kit.guide.dialog;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.kit.guide.R;
import com.kit.guide.bean.GuideBean;
import com.kit.guide.callBack.GuideViewClickCallBack;
import com.kit.guide.utils.DialogManager;
import com.kit.guide.view.GuideView;

import java.util.List;

/**
 * 显示新手引导的dialog
 * @author libowu
 * @date 2019/09/27
 */
@SuppressLint("ValidFragment")
public class GuideDialog extends DialogFragment {
    /**
     * 高亮区集合
     */
    private List<GuideBean> guideBeans;
    /**
     * 高亮区布局
     */
    private GuideView guideView;
    /**
     * 高亮区的事件监听器
     */
    private GuideViewClickCallBack clickCallBack;
    /**
     * 高亮区遮罩层颜色
     */
    private int markColor;
    /**
     * 是否启用一屏显示多个高亮区
     */
    private boolean openMore;
    /**
     * 高亮区文字颜色
     */
    private int guideTextColor;
    /**
     * 高亮区文字大小
     */
    private int guideTextSize;
    /**
     * 要显示高亮区activity或fragment的主窗口布局
     */
    private View activityView;
    /**
     * 是否启用精确点击
     */
    private boolean isExactClick;
    /**
     * 矩形圆角
     */
    private int rectCorner;
    private int textMarginTop,textMarginBottom;
    /**
     * 主窗口的view，activity可通过getWindow -> getDecorView 方法获取，fragment可使用onViewCreated中的view
     * @param view
     * @param guideBeans 高亮集合，强制让调用者输入
     */
    @SuppressLint("ValidFragment")
    public GuideDialog(View view,List<GuideBean> guideBeans) {
        this.activityView = view;
        this.guideBeans = guideBeans;
        initData();
    }


     @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = initWindow(inflater,container);
        initGuide(rootView);
        return rootView;
    }

    /**
     * 对高亮布局进行数据设置
     * @param rootView 整个窗口的view，用于判断view是否已经可以获取到控件的位置信息
     */
    private void initGuide(View rootView) {
            guideView = rootView.findViewById(R.id.guide);
            guideView.setGuideViewClickCallBack(new GuideViewClickCallBack() {
                @Override
                public void guideClick(GuideBean guideBean, int guideIndex) {
                    if (clickCallBack != null){
                        clickCallBack.guideClick(guideBean,guideIndex);
                    }
                }

                @Override
                public void guideMoreClick(List<GuideBean> guideBeans) {
                    if (clickCallBack != null){
                        clickCallBack.guideMoreClick(guideBeans);
                    }
                }

                @Override
                public void guideEndCallback() {
                    dismiss();
                    if (clickCallBack != null){
                        clickCallBack.guideEndCallback();
                    }
                }

            });
            if (guideBeans != null){
                guideView.setGuideBeans(guideBeans);
                guideView.setOpenMore(openMore);
                guideView.setActivity(getActivity());
                guideView.setGuideTextColor(guideTextColor);
                guideView.setGuideTextSize(guideTextSize);
                guideView.setMaskColor(markColor);
                guideView.setClickExact(isExactClick);
                guideView.setTextMargin(textMarginTop,textMarginBottom);
                guideView.setActivity(getActivity());
                GuideView.Config.ROUNDED_RECT_VALUE = rectCorner;
                guideView.showGuide(activityView);
            }else {
                dismiss();
                Log.e("guideWarning","reason========>guideBean不应该为空，请记得设置guidebean");
            }
     }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DialogManager.removeCurrentDialog(this);
    }

    /**
     * 初始化窗口
     * @param inflater
     * @param container
     * @return
     */
    private View initWindow(LayoutInflater inflater, ViewGroup container) {
        getDialog().setCanceledOnTouchOutside(true);
        View rootView = inflater.inflate(R.layout.guide_dialog, container, false);
        // 设置宽度为屏宽、靠近屏幕底部。
        final Window window = getDialog().getWindow();
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setBackgroundDrawableResource(R.color.transparent);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.dimAmount = 0.0f;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        return rootView;
     }

    @Override
    public void show(FragmentManager manager, String tag) {
        //如果不存在，则显示，存在不显示，目的是保证dialog不重复显示
        if (!DialogManager.isExistCurrentDialog(this)){
            //如果高亮集合中不存在内容，则不进行显示
            if (guideBeans == null || guideBeans.size() == 0){
                return;
            }
            super.show(manager, tag);
            //移除管理器中的上一个内容
            DialogManager.removePreDialog();
            //将本dialog存放进管理器中进行管理
            DialogManager.putDialog(this);
        }
    }



    /**
     * 设置高亮区的默认信息
     */
    private void initData() {
        markColor = Color.parseColor("#cc000000");
        openMore = false;
        guideTextColor = Color.parseColor("#333333");
        guideTextSize = 20;
        textMarginBottom = 20;
        textMarginTop = 20;
     }

    /**
     * 设置文字距离说明图片的距离
     * @param textMarginTop 顶部边距
     * @param textMarginBottom 底部边距
     */
     public void setTextMargin(int textMarginTop,int textMarginBottom){
        this.textMarginTop = textMarginTop;
        this.textMarginBottom = textMarginBottom;
     }

    /**
     * 设置圆角矩形的圆角值
     */
    public void setRectCorner(int rectCorner){
        this.rectCorner = rectCorner;
    }

    /**
     * 设置高亮区遮罩层的背景颜色
     * @param markColor 遮罩层颜色
     */
    public void setMarkColor(int markColor) {
        this.markColor = markColor;
    }


    /**
     * 设置是否取用一个界面显示多个高亮区，默认关闭，打开后一个屏幕上将会出现多个高亮区，精确点击设置将会失效。
     * @param openMore 启用显示多个高亮区的标记
     */
    public void setOpenMore(boolean openMore) {
        this.openMore = openMore;
    }


    /**
     * 设置要显示字体的颜色
     * @param guideTextColor 字体颜色
     */
    public void setGuideTextColor(int guideTextColor) {
        this.guideTextColor = guideTextColor;
    }

    /**
     * 设置要显示的文字的大小
     * @param guideTextSize
     */
    public void setGuideTextSize(int guideTextSize) {
        this.guideTextSize = guideTextSize;
    }

    /**
     * 设置高亮区集合
     * @param guideBeans 高亮区集合
     */
    public void setGuideBeans(List<GuideBean> guideBeans){
        this.guideBeans = guideBeans;
    }

    /**
     * 设置是否点击高亮区才进行下一步操作。true为点击高亮区才进行下一步操作
     * @param isExactClick 是否启用精确点击
     */
    public void setExactClick(boolean isExactClick){
        this.isExactClick = isExactClick;
    }

    /**
     * 设置高亮布局的事件监听
     * @param guideListener 监听器
     */
    public void setGuideListener(GuideViewClickCallBack guideListener){
        this.clickCallBack = guideListener;
    }


}
