package com.archana.weathertest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.archana.weathertest.Current.Current;
import com.archana.weathertest.Current.CurrentAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;
    ImageView ivBgTemp;
    String iconnumber;
    int inttemp;
    TextView tvTemp, tvCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        ivBgTemp = (ImageView) findViewById(R.id.ivBgTemp);
        assert ivBgTemp != null;
        ivBgTemp.setImageResource(R.drawable.cfbg10d);
        tvTemp = (TextView) findViewById(R.id.tvTemp);
        tvCity = (TextView) findViewById(R.id.tvCity);
        Intent intent = getIntent();
        String city = intent.getStringExtra("cityname");
        tvCity.setText(city);

        CurrentAPI.Factory.getInstance().getCurrent(city, "534b11c2ebcc982b1fd81a8fa36f088b").enqueue(new Callback<Current>()  {

            @Override
            public void onResponse(Call<Current> call, Response<Current> response) {

                tvCity.setText( response.body().getCity().getName());
                Log.d("city", String.valueOf(response.body().getCity().getName()));
                iconnumber = "cfbg"+(response.body().getList().get(0).getWeather().get(0).getIcon());
                inttemp = (int) (response.body().getList().get(0).getMain().getTemp() - 273);
                tvTemp.setText("" + inttemp + "°C");
                System.out.println(iconnumber);

                ivBgTemp.setImageResource(getImageId(CityActivity.this, iconnumber));
            }

            @Override
            public void onFailure(Call<Current> call, Throwable t) {
                Log.e("Fail", t.getMessage());
                tvTemp.setText("" + 0 + "°C");
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);




    }


    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_city, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        if (id== R.id.icSearch){
            Intent intent = new Intent(CityActivity.this, SearchActivity.class);
            CityActivity.this.startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return PlaceholderFragmentDetails.newInstance();
                case 1:
                    return PlaceholderFragment.newInstance(position + 1);
                case 2:
                    return PlaceholderFragmentSemaine.newInstance();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Details";
                case 1:
                    return "Today";
                case 2:
                    return "This Week";

            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
