package com.kit.calendar.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kit.calendar.view.CalendarContentView;
import com.kit.guide.R;

import java.util.List;

public class CalendarRecAdapter extends RecyclerView.Adapter<CalendarRecAdapter.Cra> {
    private List<String> title;

    @NonNull
    @Override
    public Cra onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Cra(new CalendarContentView(viewGroup.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull Cra cra, int i) {
        ViewGroup.LayoutParams llp = cra.itemView.getLayoutParams();
        LinearLayout.LayoutParams llpResult = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        cra.itemView.setLayoutParams(llpResult);
    }

    public void setTitle(List<String> title){
        this.title = title;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class Cra extends RecyclerView.ViewHolder{
        public Cra(@NonNull View itemView) {
            super(itemView);
        }
    }
}
