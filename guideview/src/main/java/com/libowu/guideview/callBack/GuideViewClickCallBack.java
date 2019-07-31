package com.libowu.guideview.callBack;

import com.libowu.guideview.bean.GuideBean;

import java.util.List;

public interface GuideViewClickCallBack {
    /**
     * 引导点击事件
     * @param guideBean 引导的基本信息
     * @param guideIndex 当前引导的位置
     */
    void guideClick(GuideBean guideBean, int guideIndex);

    /**
     * 一屏多个控件说明的点击事件
     * @param guideBeans
     */
    void guideMoreClick(List<GuideBean> guideBeans);

    void guideEndCallback();
}
