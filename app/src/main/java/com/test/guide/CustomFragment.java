package com.test.guide;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kit.pagerCard.PagerCardBean;
import com.kit.pagerCard.PagerCardView;
import com.test.guide.R;
import com.kit.guide.bean.GuideBean;
import com.kit.guide.callBack.GuideViewClickCallBack;
import com.kit.guide.dialog.GuideDialog;
import com.kit.guide.utils.GuideViewUtils;
import com.kit.guide.view.GuideView;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试新手引导的活动
 * @author libowu
 * @date 2019/09/27
 */
public class CustomFragment extends Fragment {
    private TextView test,test_two,test_three;
    private LinearLayout test_go;
    private List<GuideBean> guides;
    private String age;
    private GuideDialog guideDialog;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PagerCardView pagerCardView = view.findViewById(R.id.cardview);
        pagerCardView.addContent(new PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569591923995&di=23164aeb16b55d649bad5509cb7e3048&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D7d2272098782b9013dadc333438ca97e%2F10dfa9ec8a136327d00e9b1d9c8fa0ec08fac739.jpg").setName("dqq"))
                .addContent(new PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569647607838&di=daadb04b3c41ab14c6a4eb5ebdb4f5ce&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D432aef2ac35c1038247ecec28211931c%2Fd4628535e5dde7113a95acc6a2efce1b9d1661bf.jpg").setName("dqq"))
                .addContent(new PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569591923995&di=23164aeb16b55d649bad5509cb7e3048&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D7d2272098782b9013dadc333438ca97e%2F10dfa9ec8a136327d00e9b1d9c8fa0ec08fac739.jpg").setName("dqq"))
                .addContent(new PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569418221253&di=1770a74bb77874862ef83b5d0dd9deea&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201511%2F14%2F20151114095852_2KEvG.jpeg").setName("dqq").setShowRedPoint(true))
                .addContent(new PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569591923995&di=23164aeb16b55d649bad5509cb7e3048&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D7d2272098782b9013dadc333438ca97e%2F10dfa9ec8a136327d00e9b1d9c8fa0ec08fac739.jpg").setName("dqq"))
                .addContent(new PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569647607838&di=daadb04b3c41ab14c6a4eb5ebdb4f5ce&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D432aef2ac35c1038247ecec28211931c%2Fd4628535e5dde7113a95acc6a2efce1b9d1661bf.jpg").setName("dqq"))
                .addContent(new PagerCardBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569647607838&di=daadb04b3c41ab14c6a4eb5ebdb4f5ce&imgtype=0&src=http%3A%2F%2Fimgsa.baidu.com%2Fexp%2Fw%3D500%2Fsign%3D432aef2ac35c1038247ecec28211931c%2Fd4628535e5dde7113a95acc6a2efce1b9d1661bf.jpg").setName("dqq"))
                .build(getChildFragmentManager(),2,3);

    /*    Log.e("日志","3");
        test = getActivity().findViewById(R.id.test);
        test_two = getActivity().findViewById(R.id.test_two);
        test_three = getActivity().findViewById(R.id.test_three);
        test_go = getActivity().findViewById(R.id.test_go);

        if (true){
            return;
        }
        //将要说明的控件添加到集合中
        guides = new ArrayList<>();
        //GuideBean的构造方法比较多，后面会提到各个构造方法的作用
        guides.add(new GuideBean(R.mipmap.guide,getActivity(),test_go).setShape(GuideView.Config.CIRCLE));
        guides.add(new GuideBean(R.mipmap.guide,getActivity(),test_two).setShape(GuideView.Config.CIRCLE));
        //设置高亮区弹窗,传入的view为activity或fragment的主界面的view，activity可以通过getWindow().getDecorView()的方式获取，fragment可以在onViewCreated中获取到，用于判断主view是否可以获取到控件的位置参数了。
        guideDialog = new GuideDialog(getActivity().getWindow().getDecorView());
        //设置高亮集合
        guideDialog.setGuideBeans(guides);
        //设置是否启用精确点击（true时只有点击高亮区时才执行下一步操作，默认为false）
        guideDialog.setExactClick(false);
        //是否启用一个屏幕内显示多个高亮区（true时一个屏幕内将显示出高亮集合中的所有内容，默认为false）
        guideDialog.setOpenMore(true);
        //设置高亮区点击事件的监听器
        guideDialog.setGuideListener(new GuideViewClickCallBack() {
            @Override
            public void guideClick(GuideBean guideBean, int guideIndex) {

            }

            @Override
            public void guideMoreClick(List<GuideBean> guideBeans) {

            }

            @Override
            public void guideEndCallback() {

            }
        });
        //如果要显示字体，这个可以设置显示字体的颜色（目前只针对直接传入rect的有效，通过view获取的rect无效）
        guideDialog.setGuideTextColor(Color.RED);
        //设置字体大小（目前只针对直接传入rect的有效，通过view获取的rect无效）
        guideDialog.setGuideTextSize(20);
        //设置高亮区遮罩层的样色,默认值为#cc000000
        guideDialog.setMarkColor(Color.parseColor("#DD000000"));
        //执行显示高亮控件
        guideDialog.show(getFragmentManager(),getClass().getName());*/
    }

    public void showDialog(){
       /* //将要说明的控件添加到集合中
        guides = new ArrayList<>();
        //GuideBean的构造方法比较多，后面会提到各个构造方法的作用
        guides.add(new GuideBean(R.mipmap.guide,getActivity(),test_two).setShape(GuideView.Config.ROUNDED_RECT).setPadding(GuideViewUtils.dip2px(getContext(),10)));
        guides.add( new GuideBean(R.mipmap.guide,getActivity(),test_go).setShape(GuideView.Config.OVAL).setPadding(GuideViewUtils.dip2px(getContext(),20)));
        guides.add(new GuideBean(R.mipmap.guide,getActivity(),new Rect(0,0,getResources().getDisplayMetrics().widthPixels,200)));
        //设置高亮区弹窗,传入的view为activity或fragment的主界面的view，activity可以通过getWindow().getDecorView()的方式获取，fragment可以在onViewCreated中获取到，用于判断主view是否可以获取到控件的位置参数了。
        guideDialog = new GuideDialog(view);
        //设置高亮集合
        guideDialog.setGuideBeans(guides);
        //设置是否启用精确点击（true时只有点击高亮区时才执行下一步操作，默认为false）
        guideDialog.setExactClick(false);
        //是否启用一个屏幕内显示多个高亮区（true时一个屏幕内将显示出高亮集合中的所有内容，默认为false）
        guideDialog.setOpenMore(false);
        //设置高亮区点击事件的监听器
        guideDialog.setGuideListener(new GuideViewClickCallBack() {
            @Override
            public void guideClick(GuideBean guideBean, int guideIndex) {

            }

            @Override
            public void guideMoreClick(List<GuideBean> guideBeans) {

            }

            @Override
            public void guideEndCallback() {

            }

        });
        //如果要显示字体，这个可以设置显示字体的颜色（目前只针对直接传入rect的有效，通过view获取的rect无效）
        guideDialog.setGuideTextColor(Color.RED);
        //设置字体大小（目前只针对直接传入rect的有效，通过view获取的rect无效）
        guideDialog.setGuideTextSize(20);
        //设置高亮区遮罩层的样色,默认值为#cc000000
        guideDialog.setMarkColor(Color.parseColor("#DD000000"));
        //设置圆角
        guideDialog.setRectCorner(GuideViewUtils.dip2px(getContext(),20));
        //执行显示高亮控件
        guideDialog.show(getFragmentManager(),getClass().getName());*/
    }
}
