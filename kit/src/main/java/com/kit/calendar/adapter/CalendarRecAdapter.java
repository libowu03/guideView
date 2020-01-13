package com.kit.calendar.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kit.calendar.bean.CalendarAttribute;
import com.kit.calendar.listener.DateItemClickListener;
import com.kit.calendar.listener.DateSetListener;
import com.kit.calendar.view.CalendarContentView;

import java.util.List;

/**
 * calendar的翻页recyclerview
 */
public class CalendarRecAdapter extends RecyclerView.Adapter<CalendarRecAdapter.Cra> {
    /**
     * 标题，格式为year-month,用于传递给view，已获取对应的日历信息
     */
    private List<String> title;
    /**
     * 点击监听器
     */
    private DateItemClickListener clickListener;
    /**
     * 日期view的属性
     */
    private CalendarAttribute attribute;
    /**
     *
     * @param dateSetListener 每项日期设置完时的监听
     */
    private DateSetListener dateSetListener;

    public CalendarRecAdapter(CalendarAttribute attribute) {
        this.attribute = attribute;
    }

    @NonNull
    @Override
    public Cra onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CalendarContentView contentView = new CalendarContentView(viewGroup.getContext());
        contentView.setAttribute(attribute);
        return new Cra(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull Cra cra, int i) {
        //Log.e("日志","输出："+i);
        ((CalendarContentView) cra.itemView).setClickListener(clickListener);
        ((CalendarContentView) cra.itemView).setDate(title.get(i),dateSetListener);
        LinearLayout.LayoutParams llpResult = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        cra.itemView.setLayoutParams(llpResult);
    }

    @Override
    public int getItemCount() {
        if (title == null) {
            return 0;
        }
        return title.size();
    }

    public void setAttribute(CalendarAttribute attribute) {
        this.attribute = attribute;
        notifyDataSetChanged();
    }

    public void setClickListener(DateItemClickListener clickListener) {
        this.clickListener = clickListener;
        //notifyDataSetChanged();
    }

    public void setDateSetListener(DateSetListener dateSetListener) {
        this.dateSetListener = dateSetListener;
        //notifyDataSetChanged();
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
        notifyDataSetChanged();
    }

    public void addTitleList(List<String> title) {
        this.title.addAll(title);
        notifyDataSetChanged();
    }


    public class Cra extends RecyclerView.ViewHolder {
        public Cra(@NonNull View itemView) {
            super(itemView);
        }
    }
}
