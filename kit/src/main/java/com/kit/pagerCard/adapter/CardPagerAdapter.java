package com.kit.pagerCard.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.kit.pagerCard.bean.PagerCardAttribute;
import com.kit.pagerCard.bean.PagerCardBean;

import java.util.List;

/**
 * pagerCard的recyclerview适配器
 *
 * @param <T>
 * @author libowu
 * @date 2019/09/27
 */
public class CardPagerAdapter<T extends PagerCardBean> extends RecyclerView.Adapter {
    private List<T> content;
    private ClickPagerCardListener cardListener;
    private Context context;
    private PagerCardAttribute pagerCardAttribute;

    @NonNull
    @Override
    public Cpa onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        return new Cpa(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardpager_item, viewGroup, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Cpa cpa = (Cpa) viewHolder;
        final T pagerCardBean = content.get(i);
        if (pagerCardAttribute != null) {
            //设置item背景色,如果存在resource，优先设置resource，没有则设置背景色
            if (pagerCardAttribute.getItemBackgrounResource() != null) {
                cpa.itemView.setBackground(pagerCardAttribute.getItemBackgrounResource());
            } else {
                cpa.itemView.setBackgroundColor(pagerCardAttribute.getItemBackgrounColor());
            }

            //设置item的margin
            GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) cpa.itemView.getLayoutParams();
            if (params != null) {
                if (pagerCardAttribute.getItemMargin() != 0) {
                    params.leftMargin = pagerCardAttribute.getItemMargin();
                    params.rightMargin = pagerCardAttribute.getItemMargin();
                    params.topMargin = pagerCardAttribute.getItemMargin();
                    params.bottomMargin = pagerCardAttribute.getItemMargin();
                } else {
                    params.leftMargin = pagerCardAttribute.getItemMarginLeft();
                    params.rightMargin = pagerCardAttribute.getItemMarginRight();
                    params.topMargin = pagerCardAttribute.getItemMarginTop();
                    params.bottomMargin = pagerCardAttribute.getItemMarginBottom();
                }
                if (pagerCardAttribute.getItemPadding() != 0) {
                    cpa.itemView.setPadding(pagerCardAttribute.getItemPadding(), pagerCardAttribute.getItemPadding(), pagerCardAttribute.getItemPadding(), pagerCardAttribute.getItemPadding());
                } else {
                    cpa.itemView.setPadding(pagerCardAttribute.getItemPaddingLeft(), pagerCardAttribute.getItemPaddingTop(), pagerCardAttribute.getItemPaddingRight(), pagerCardAttribute.getItemPaddingBottom());
                }
                cpa.itemView.setLayoutParams(params);
            }

            //设置红点属性
            cpa.redPoint.setTextSize(GuideViewUtils.px2dip(context, pagerCardAttribute.getRedPointTextSize()));
            cpa.redPoint.setTextColor(pagerCardAttribute.getRedPointTextColor());
            GradientDrawable gifDrawableResource = (GradientDrawable)cpa.redPoint.getBackground();
            gifDrawableResource.setColor(pagerCardAttribute.getRedBackGroundColor());
            if (pagerCardBean.getRedPointText() == null || pagerCardBean.getRedPointText().isEmpty()) {
                ConstraintLayout.LayoutParams cl = (ConstraintLayout.LayoutParams) cpa.redPoint.getLayoutParams();
                cl.height = pagerCardAttribute.getRedPointSizeHeight();
                cl.width = pagerCardAttribute.getRedPointSizeWidth();
                if (pagerCardAttribute.getImageHeight() > 0 && pagerCardAttribute.getImageWidth() > 0){
                    cl.leftMargin = pagerCardAttribute.getImageWidth() - pagerCardAttribute.getRedPointSizeWidth()/2;
                }else {
                    cl.leftMargin = GuideViewUtils.dip2px(context, 50) - (pagerCardAttribute.getRedPointTextSize()+GuideViewUtils.dip2px( context,4))/2;
                }
                cpa.redPoint.setLayoutParams(cl);
                if (pagerCardBean.isShowRedPoint()) {
                    cpa.redPoint.setVisibility(View.VISIBLE);
                } else {
                    cpa.redPoint.setVisibility(View.GONE);
                }
            } else {
                cpa.redPoint.setText(pagerCardBean.getRedPointText());
                ConstraintLayout.LayoutParams cl = (ConstraintLayout.LayoutParams) cpa.redPoint.getLayoutParams();
                if (cpa.redPoint.getText().toString().length() == 1){
                    cpa.redPoint.setPadding(0,0,0,0);
                    //如果只有一个文字，则将背景改为圆形
                    cl.height =pagerCardAttribute.getRedPointTextSize()+GuideViewUtils.dip2px( context,4);
                    cl.width =  pagerCardAttribute.getRedPointTextSize()+GuideViewUtils.dip2px( context,4);

                    if (pagerCardAttribute.getImageHeight() > 0 && pagerCardAttribute.getImageWidth() > 0){
                        cl.leftMargin = pagerCardAttribute.getImageWidth() - (pagerCardAttribute.getRedPointTextSize()+GuideViewUtils.dip2px( context,4))/2;
                    }else {
                        cl.leftMargin = GuideViewUtils.dip2px(context, 50) - (pagerCardAttribute.getRedPointTextSize()+GuideViewUtils.dip2px( context,4))/2;
                    }
                }else {
                    cpa.redPoint.measure(0,0);
                    int measureWidht = cpa.redPoint.getMeasuredWidth();
                    if (pagerCardAttribute.getImageHeight() > 0 && pagerCardAttribute.getImageWidth() > 0){
                        cl.leftMargin = pagerCardAttribute.getImageWidth() - measureWidht + GuideViewUtils.dip2px(context, 6);
                    }else {
                        cl.leftMargin = GuideViewUtils.dip2px(context, 50) - measureWidht + GuideViewUtils.dip2px(context, 6);
                    }
                    cl.height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                    cl.width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                }
                cpa.redPoint.setLayoutParams(cl);
            }

            //设置标题属性
            cpa.title.setTextSize(GuideViewUtils.px2dip(context, pagerCardAttribute.getTitleTextSize()));
            cpa.title.setTextColor(pagerCardAttribute.getTitleTextColor());
            if (pagerCardAttribute.getImageHeight() > 0 && pagerCardAttribute.getImageWidth() > 0) {
                //设置图片属性
                ConstraintLayout.LayoutParams llp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                llp.height = pagerCardAttribute.getImageHeight();
                llp.width = pagerCardAttribute.getImageWidth();
                llp.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
                llp.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
                llp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
                llp.topMargin = pagerCardAttribute.getRedPointSizeHeight()/2;
                cpa.img.setLayoutParams(llp);
            } else {
                ConstraintLayout.LayoutParams llp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                llp.height = GuideViewUtils.dip2px(context, 50);
                llp.width = GuideViewUtils.dip2px(context, 50);
                llp.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
                llp.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
                llp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
                llp.topMargin = pagerCardAttribute.getRedPointSizeHeight()/2;
                cpa.img.setLayoutParams(llp);
            }
        }
        if (pagerCardBean.getName() == null) {
            cpa.title.setVisibility(View.GONE);
        } else {
            cpa.title.setText(pagerCardBean.getName());
        }
        int imgId = context.getResources().getIdentifier(pagerCardBean.getImg(), "drawable", context.getPackageName());
        if (imgId == 0){
            imgId = context.getResources().getIdentifier(pagerCardBean.getImg(), "mipmap", context.getPackageName());
        }
        //判断绘制图片的类型，分别可以为圆形，矩形，圆角矩形
        if (pagerCardAttribute.getImgType() == 0) {
            //0为绘制圆形图片
            RequestOptions requestOptions = RequestOptions.circleCropTransform();
            loadImage(imgId, requestOptions, cpa, pagerCardBean);
        } else if (pagerCardAttribute.getImgType() == 1) {
            //绘制圆角矩形图片
            loadImage(imgId, RequestOptions.bitmapTransform(new RoundedCorners(pagerCardAttribute.getImgCorner())), cpa, pagerCardBean);
        } else if (pagerCardAttribute.getImgType() == 2) {
            //加载矩形图片直接加载即可
            loadImage(imgId, null, cpa, pagerCardBean);
        }else {
            //加载矩形图片直接加载即可
            loadImage(imgId, null, cpa, pagerCardBean);
        }

        cpa.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardListener != null) {
                    cardListener.onClickPagerCardListener(pagerCardBean, i);
                }
            }
        });
    }

    public List<T> getPagerItemList(){
        return content;
    }

    public T getPagerItem(int index){
        if (index >= content.size()){
            return null;
        }
        return content.get(index);
    }

    public boolean updateItemPagerCard(int index,T item){
        if (content == null || index >= content.size()){
            return false;
        }
        content.set(index,item);
        notifyItemChanged(index);
        return true;
    }

    /**
     * 加载图片
     *
     * @param imgId          图片id
     * @param requestOptions
     * @param cpa
     * @param pagerCardBean
     */
    public void loadImage(int imgId, RequestOptions requestOptions, Cpa cpa, T pagerCardBean) {
        if (requestOptions != null) {
            if (imgId != 0) {
                Glide.with(context).load(imgId).apply(requestOptions).into(cpa.img);
            } else if (context.getResources().getIdentifier(pagerCardBean.getImg(), "mipmap", context.getPackageName()) != 0) {
                Glide.with(context).load(context.getResources().getIdentifier(pagerCardBean.getImg(), "mipmap", context.getPackageName())).apply(requestOptions).into(cpa.img);
            } else {
                Glide.with(context).load(pagerCardBean.getImg()).apply(requestOptions).into(cpa.img);
            }
        } else {
            if (imgId != 0) {
                Glide.with(context).load(imgId).into(cpa.img);
            } else if (context.getResources().getIdentifier(pagerCardBean.getImg(), "mipmap", context.getPackageName()) != 0) {
                Glide.with(context).load(context.getResources().getIdentifier(pagerCardBean.getImg(), "mipmap", context.getPackageName())).into(cpa.img);
            } else {
                Glide.with(context).load(pagerCardBean.getImg()).into(cpa.img);
            }
        }

    }

    @Override
    public int getItemCount() {
        if (content == null) {
            return 0;
        }
        return content.size();
    }

    /**
     * 设置pagercard的点击监听器
     *
     * @param cardListener
     */
    public void setCardListener(ClickPagerCardListener cardListener) {
        this.cardListener = cardListener;
    }

    public void setContent(List<T> content) {
        //Log.e("日志","内容长度为："+content.size());
        this.content = content;
        notifyDataSetChanged();
    }

    public void setPagerCardAttribute(PagerCardAttribute pagerCardAttribute) {
        this.pagerCardAttribute = pagerCardAttribute;
    }

    public interface ClickPagerCardListener<T> {
        void onClickPagerCardListener(T pagerCardBean, int index);
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
}
