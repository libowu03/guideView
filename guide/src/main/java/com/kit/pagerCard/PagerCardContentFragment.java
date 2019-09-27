package com.kit.pagerCard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kit.guide.R;

import java.util.List;

public class PagerCardContentFragment<T extends PagerCardBean> extends Fragment implements CardPagerAdapter.ClickPagerCardListener<T> {
    private RecyclerView pagerCardContent;
    private List<T> contentList;
    private CardPagerAdapter.ClickPagerCardListener pagerCardListener;
    private PagerCardAttribute attribute;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pagercard,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null){
            return;
        }
        int colNum = bundle.getInt("col",4);
        pagerCardContent = view.findViewById(R.id.pagerCardContent);
        GridLayoutManager layoutManager = null;
        //设置recyclerview是否可垂直滑动
        if (attribute != null){
            if (attribute.isCanScrollVertically()){
                layoutManager = new GridLayoutManager(getActivity(),colNum);
            }else {
                layoutManager = new GridLayoutManager(getActivity(),colNum){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }

                    @Override
                    public boolean canScrollHorizontally() {
                        return false;
                    }
                };
            }
        }
        CardPagerAdapter pagerContentAdapter = new CardPagerAdapter();
        pagerContentAdapter.setPagerCardAttribute(attribute);
        pagerContentAdapter.setContent(contentList);
        pagerContentAdapter.setCardListener(this);
        pagerCardContent.setLayoutManager(layoutManager);
        pagerCardContent.setAdapter(pagerContentAdapter);
    }

    public void setFragmentList(List<T> contentList){
        this.contentList = contentList;
    }


    /**
     * 设置条目的点击监听器
     * @param pagerCardListener
     */
    public void setPagerCardListener(CardPagerAdapter.ClickPagerCardListener pagerCardListener){
        this.pagerCardListener = pagerCardListener;
    }

    /**
     * pagerCard的点击事件反馈内容
     * @param pagerCardBean
     * @param index
     */
    @Override
    public void onClickPagerCardListener(T pagerCardBean, int index) {
        if (pagerCardListener != null){
            pagerCardListener.onClickPagerCardListener(pagerCardBean,index);
        }
    }

    public void setAttribute(PagerCardAttribute attribute){
        this.attribute = attribute;
    }
}
