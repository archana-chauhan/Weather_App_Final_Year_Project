package com.archana.weathertest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Theme extends AppCompatActivity {

    ImageView ivTheme1;
    String bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        ivTheme1 = (ImageView) findViewById(R.id.ivTheme1);
        ivTheme1.setImageResource(getImageId(Theme.this, bg));
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
