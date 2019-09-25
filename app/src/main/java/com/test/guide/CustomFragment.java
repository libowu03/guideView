package com.test.guide;

import android.graphics.Color;
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

import com.test.guide.R;
import com.kit.guide.bean.GuideBean;
import com.kit.guide.callBack.GuideViewClickCallBack;
import com.kit.guide.dialog.GuideDialog;
import com.kit.guide.utils.GuideViewUtils;
import com.kit.guide.view.GuideView;

import java.util.ArrayList;
import java.util.List;

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
        Log.e("日志","3");
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
        //设置高亮区的padding（只针对简单几何图形的高亮区有效，如果高亮区为某个view的图形，此设置无效）
        guideDialog.setHeightLightPadding(20);
        //设置高亮区遮罩层的样色,默认值为#cc000000
        guideDialog.setMarkColor(Color.parseColor("#DD000000"));
        //执行显示高亮控件
        guideDialog.show(getFragmentManager(),getClass().getName());
    }

    public void showDialog(){
        //将要说明的控件添加到集合中
        guides = new ArrayList<>();
        //GuideBean的构造方法比较多，后面会提到各个构造方法的作用
        GuideBean b = new GuideBean(R.mipmap.guide,getActivity(),test_two);
        GuideBean a = new GuideBean(R.mipmap.guide,getActivity(),test_go);
        GuideBean c = new GuideBean(R.mipmap.guide,getActivity(),test_three);
        guides.add(b);
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
        //设置高亮区的padding（只针对简单几何图形的高亮区有效，如果高亮区为某个view的图形，此设置无效）
        guideDialog.setHeightLightPadding(20);
        //设置高亮区遮罩层的样色,默认值为#cc000000
        guideDialog.setMarkColor(Color.parseColor("#DD000000"));
        //执行显示高亮控件
        guideDialog.show(getFragmentManager(),getClass().getName());
        //guideDialog.setRectCorner(30);
    }
}
