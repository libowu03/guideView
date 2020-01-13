package com.test.guide;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kit.calendar.bean.DateInfo;
import com.kit.calendar.listener.DateItemClickListener;
import com.kit.calendar.listener.DatePagerChangeListener;
import com.kit.calendar.listener.DateSetListener;
import com.kit.calendar.view.CalendarView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 选择测试功能的活动
 * @author libowu
 * @date 2019/09/27
 */
public class SelectMenuActivity extends AppCompatActivity {
    private TextView sendToGuide;
    private TextView sendToPagerCard;
    private TextView sendToSuperTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu);
        sendToGuide = findViewById(R.id.sendToGuide);
        sendToPagerCard = findViewById(R.id.sendToPagerCard);
        sendToSuperTab = findViewById(R.id.sendToSuperTab);

        sendToGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendToGuide = new Intent(SelectMenuActivity.this,MainActivity.class);
                sendToGuide.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(sendToGuide);
            }
        });

        sendToPagerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendToGuide = new Intent(SelectMenuActivity.this,PagerCardTestActivity.class);
                sendToGuide.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(sendToGuide);
            }
        });

        sendToSuperTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendToGuide = new Intent(SelectMenuActivity.this,MainUiFrameActivity.class);
                sendToGuide.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(sendToGuide);
            }
        });

        CalendarView calendarView = new CalendarView(this);
        calendarView.setPagerChangeListener(new DatePagerChangeListener() {
            @Override
            public void onDatePagerChange(int year, int month, @NotNull List<DateInfo> dateList, int pagerIndex) {

            }
        });
        calendarView.setDateItemSetListener(new DateSetListener() {
            @Override
            public void onDateSetListener(@NotNull View view, @NotNull DateInfo dateItem, @NotNull List<DateInfo> dateList, int index) {

            }
        });
        calendarView.setItemClickListener(new DateItemClickListener() {
            @Override
            public void onDateItemClickListener(@NotNull View currentView, @NotNull DateInfo dateItem, @NotNull List<DateInfo> dateList, int index, @Nullable View oldView) {

            }
        });
        //跳转到某一个日期，这里是跳转到2150年的6月份
        calendarView.jumpToDate(2150,6);
        //跳转到下一个月
        calendarView.nextMonth();
        //跳转到上一个月
        calendarView.preMonth();
        //跳转到下一年
        calendarView.nextYear();
        //跳转到上一年
        calendarView.preYear();
        //获取头部布局
        calendarView.getHeadView();
        //获取尾部布局
        calendarView.getFootView();
        //获取今日的日期信息
        calendarView.getTodayDateInfo();
    }
}
