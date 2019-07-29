package com.libowu.guideview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView,textViewTwo;
    private GuideView guide;
    private List<GuideBean> guides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guides = new ArrayList<>();
        textView = findViewById(R.id.test);
        textViewTwo = findViewById(R.id.test_two);
        guide = findViewById(R.id.guide);

        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guide.setRec(guides.get(1));
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        guides.add(new GuideBean(R.mipmap.guide,this,textView,getSupportActionBar()));
        guides.add(new GuideBean(R.mipmap.xuankong_year,this,textViewTwo,getSupportActionBar()));
        guide.setRec(new GuideBean(R.mipmap.guide,this,textView,getSupportActionBar()));
    }
}
