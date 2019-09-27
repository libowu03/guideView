package com.kit.pagerCard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.kit.guide.R;
import com.kit.guide.utils.GuideViewUtils;

import java.util.List;

public class CardPagerAdapter<T extends PagerCardBean> extends RecyclerView.Adapter{
    private List<T> content;
    private ClickPagerCardListener cardListener;
    private Context context;
    private PagerCardAttribute pagerCardAttribute;

    @NonNull
    @Override
    public Cpa onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        return new Cpa(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_pager,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Cpa cpa = (Cpa) viewHolder;
        final T pagerCardBean = content.get(i);
        if (pagerCardAttribute != null){
            //设置红点属性
            cpa.redPoint.setTextSize(GuideViewUtils.px2dip(context,pagerCardAttribute.getRedPointTextSize()));
            cpa.redPoint.setTextColor(pagerCardAttribute.getRedPointTextColor());
            if (pagerCardBean.getRedPointText() == null || pagerCardBean.getRedPointText().isEmpty()){
                ConstraintLayout.LayoutParams cl = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                cl.height = pagerCardAttribute.getRedPointSizeHeight();
                cl.width = pagerCardAttribute.getRedPointSizeWidth();
                cl.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
                cl.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
                cpa.redPoint.setLayoutParams(cl);
            }else {
                ConstraintLayout.LayoutParams cl = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                cl.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                cl.width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                cl.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
                cl.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
                cpa.redPoint.setLayoutParams(cl);
            }

            //设置标题属性
            cpa.title.setTextSize(GuideViewUtils.px2dip(context,pagerCardAttribute.getTitleTextSize()));
            cpa.title.setTextColor(pagerCardAttribute.getTitleTextColor());
            if (pagerCardAttribute.getImageHeight() > 0 && pagerCardAttribute.getImageWidth() > 0){
                //设置图片属性
                ConstraintLayout.LayoutParams llp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                llp.height = pagerCardAttribute.getImageHeight();
                llp.width = pagerCardAttribute.getImageWidth();
                llp.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
                llp.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
                cpa.img.setLayoutParams(llp);
            }else {
                ConstraintLayout.LayoutParams llp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                llp.height = GuideViewUtils.dip2px(context,40);
                llp.width = GuideViewUtils.dip2px(context,40);
                llp.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
                llp.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
                cpa.img.setLayoutParams(llp);
            }
        }
        if (pagerCardBean.getName() == null){
            cpa.title.setVisibility(View.GONE);
        }else {
            cpa.title.setText(pagerCardBean.getName());
        }
        int imgId = context.getResources().getIdentifier(pagerCardBean.getImg(),"drawable",context.getPackageName());
        //判断绘制图片的类型，分别可以为圆形，矩形，圆角矩形
        if (pagerCardAttribute.getImgType() == 0){
            //0为绘制圆形图片
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
           loadImage(imgId,requestOptions,cpa,pagerCardBean);
        }else if (pagerCardAttribute.getImgType() == 1){
            //绘制圆角矩形图片
            loadImage(imgId,RequestOptions.bitmapTransform(new RoundedCorners(pagerCardAttribute.getImgCorner())),cpa,pagerCardBean);
        }else if (pagerCardAttribute.getImgType() == 2){
            //加载矩形图片直接加载即可
            loadImage(imgId,null,cpa,pagerCardBean);
        }

        if (pagerCardBean.getRedPointText() == null || pagerCardBean.getRedPointText().isEmpty()){
            if (pagerCardBean.isShowRedPoint()){
                cpa.redPoint.setVisibility(View.VISIBLE);
            }else {
                cpa.redPoint.setVisibility(View.GONE);
            }
        }else {
            cpa.redPoint.setBackgroundResource(0);
            cpa.redPoint.setText(pagerCardBean.getRedPointText());
        }

        cpa.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardListener != null){
                    cardListener.onClickPagerCardListener(pagerCardBean,i);
                }
            }
        });
    }

    /**
     * 加载图片
     * @param imgId 图片id
     * @param requestOptions
     * @param cpa
     * @param pagerCardBean
     */
    public void loadImage(int imgId,RequestOptions requestOptions,Cpa cpa,T pagerCardBean){
        if (requestOptions != null){
            if (imgId != 0){
                Glide.with(context).load(imgId).apply(requestOptions).into(cpa.img);
            }else if (context.getResources().getIdentifier(pagerCardBean.getImg(),"mipmap",context.getPackageName()) != 0){
                Glide.with(context).load(context.getResources().getIdentifier(pagerCardBean.getImg(),"mipmap",context.getPackageName())).apply(requestOptions).into(cpa.img);
            }else {
                Glide.with(context).load(pagerCardBean.getImg()).apply(requestOptions).into(cpa.img);
            }
        }else {
            if (imgId != 0){
                Glide.with(context).load(imgId).into(cpa.img);
            }else if (context.getResources().getIdentifier(pagerCardBean.getImg(),"mipmap",context.getPackageName()) != 0){
                Glide.with(context).load(context.getResources().getIdentifier(pagerCardBean.getImg(),"mipmap",context.getPackageName())).into(cpa.img);
            }else {
                Glide.with(context).load(pagerCardBean.getImg()).into(cpa.img);
            }
        }

    }

    @Override
    public int getItemCount() {
        if (content == null){
            return 0;
        }
        return content.size();
    }

    public class Cpa extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title;
        private TextView redPoint;
        public Cpa(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.homeFuncName);
            img = itemView.findViewById(R.id.homeFunImg);
            redPoint = itemView.findViewById(R.id.redPoint);
        }
    }

    /**
     * 设置pagercard的点击监听器
     * @param cardListener
     */
    public void setCardListener(ClickPagerCardListener cardListener){
        this.cardListener = cardListener;
    }

    public void setContent(List<T> content){
        this.content = content;
        notifyDataSetChanged();
    }

    public void setPagerCardAttribute(PagerCardAttribute pagerCardAttribute){
        this.pagerCardAttribute = pagerCardAttribute;
    }

    public interface ClickPagerCardListener<T>{
        void onClickPagerCardListener(T pagerCardBean, int index);
    }
}
