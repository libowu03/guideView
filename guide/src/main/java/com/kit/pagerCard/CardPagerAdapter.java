package com.kit.pagerCard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kit.guide.R;

import java.util.List;

public class CardPagerAdapter<T extends PagerCardBean> extends RecyclerView.Adapter{
    private List<T> content;
    private ClickPagerCardListener cardListener;
    private Context context;

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
        cpa.title.setText(pagerCardBean.getName());
        int imgId = context.getResources().getIdentifier(pagerCardBean.getImg(),"drawable",context.getPackageName());
        if (imgId != 0){
            Glide.with(context).load(imgId).into(cpa.img);
        }else if (context.getResources().getIdentifier(pagerCardBean.getImg(),"mipmap",context.getPackageName()) != 0){
            Glide.with(context).load(context.getResources().getIdentifier(pagerCardBean.getImg(),"mipmap",context.getPackageName())).into(cpa.img);
        }else {
            Glide.with(context).load(pagerCardBean.getImg()).into(cpa.img);
        }

        if (pagerCardBean.isShowRedPoint()){
            cpa.redPoint.setVisibility(View.VISIBLE);
        }else {
            cpa.redPoint.setVisibility(View.GONE);
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
        private View redPoint;
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

    public interface ClickPagerCardListener<T>{
        void onClickPagerCardListener(T pagerCardBean, int index);
    }
}
