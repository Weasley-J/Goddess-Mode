package com.android.weasley;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.weasley.util.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv_tips = findViewById(R.id.tv_tips);
        tv_tips.setText("Godess Mode\nPowered Weasley J");
        // 设置TextView控件的文字颜色
        tv_tips.setTextColor(Color.MAGENTA);
        // 设置TextView控件的文字大小
        tv_tips.setTextSize(18);
    }

    private void showScreenInfo() {
        int screenWidth = Utils.getScreenWidth(this);
        int screenHeight = Utils.getScreenHeight(this);
        float screenDensity = Utils.getScreenDensity(this);
        String info = String.format("当前手机的屏幕宽度是：%dpx，高度是：%dpx，像素密度是：%f", screenWidth, screenHeight, screenDensity);
        //tv_screen.setText(info);
    }
}
