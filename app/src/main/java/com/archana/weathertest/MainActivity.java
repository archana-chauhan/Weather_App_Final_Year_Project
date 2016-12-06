package com.archana.weathertest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.archana.weathertest.Current.Current;
import com.archana.weathertest.Current.CurrentAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textView, tvTempMain;
    Button imageButton;
    String iconnumber;
    ImageView ivIconMain;
    int inttemp;
    FrameLayout frameL;
    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        imageButton = (Button) findViewById(R.id.imageButton);
        ivIconMain = (ImageView) findViewById(R.id.ivIconMain);
        tvTempMain = (TextView) findViewById(R.id.tvTempMain);
        frameL = (FrameLayout) findViewById(R.id.frameL);
        imageButton.setOnClickListener(this);
        Intent intent = getIntent();
        city = intent.getStringExtra("cityname");
        textView.setText(city);
        frameL.setOnClickListener(this);
        if (city == null) {
            city = "Delhi-NCR";
        }
        System.out.println(city);

        CurrentAPI.Factory.getInstance().getCurrent(city, "534b11c2ebcc982b1fd81a8fa36f088b").enqueue(new Callback<Current>() {

            @Override
            public void onResponse(Call<Current> call, Response<Current> response) {

                textView.setText( response.body().getCity().getName());
                iconnumber = "cf"+(response.body().getList().get(0).getWeather().get(0).getIcon());
                inttemp = (int) (response.body().getList().get(0).getMain().getTemp() - 273);
                tvTempMain.setText("" + inttemp + "°C");
                System.out.println(iconnumber);
                System.out.println("-----------------" + response.body().getList().get(0).getMain().getTemp());

                ivIconMain.setImageResource(getImageId(MainActivity.this, iconnumber));
            }

            @Override
            public void onFailure(Call<Current> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
                tvTempMain.setText("" + 0 + "°C");

            }
        });

}
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    @Override
    public void onClick(View v) {
        String getText = city;
        switch(v.getId()){
            case R.id.imageButton:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                MainActivity.this.startActivity(intent);
                break;
            case R.id.frameL:
                Intent intent2 = new Intent(MainActivity.this, CityActivity.class);
                intent2.putExtra("cityname", getText);
                MainActivity.this.startActivity(intent2);
                break;
        }
    }
}

