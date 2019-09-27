package com.test.guide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * 选择测试功能的活动
 * @author libowu
 * @date 2019/09/27
 */
public class SelectMenuActivity extends AppCompatActivity {
    private TextView sendToGuide;
    private TextView sendToPagerCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu);
        sendToGuide = findViewById(R.id.sendToGuide);
        sendToPagerCard = findViewById(R.id.sendToPagerCard);

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
    }
}
