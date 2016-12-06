package com.archana.weathertest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.archana.weathertest.Current.Current;
import com.archana.weathertest.Current.CurrentAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlaceholderFragmentSemaine extends Fragment {

    public PlaceholderFragmentSemaine() {
    }
    ImageView ivIconHour1, ivIconHour2, ivIconHour3, ivIconHour4, ivIconHour5, ivIconHour6;
    String iconnumber;
    TextView tvPhrase;

    public static PlaceholderFragmentSemaine newInstance() {
        PlaceholderFragmentSemaine fragment = new PlaceholderFragmentSemaine();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_city_semaine, container, false);
        final TextView tvPhrase = (TextView) rootView.findViewById(R.id.tvPhrase);
        ivIconHour1 = (ImageView) rootView.findViewById(R.id.ivIconHour1);
        ivIconHour2 = (ImageView) rootView.findViewById(R.id.ivIconHour2);
        ivIconHour3 = (ImageView) rootView.findViewById(R.id.ivIconHour3);
        ivIconHour4 = (ImageView) rootView.findViewById(R.id.ivIconHour4);
        ivIconHour5 = (ImageView) rootView.findViewById(R.id.ivIconHour5);
        ivIconHour6 = (ImageView) rootView.findViewById(R.id.ivIconHour6);
        TextView tvTempHour1 = (TextView) rootView.findViewById(R.id.tvTempHour1);
        TextView tvTempHour2 = (TextView) rootView.findViewById(R.id.tvTempHour2);
        TextView tvTempHour3 = (TextView) rootView.findViewById(R.id.tvTempHour3);
        TextView tvTempHour4 = (TextView) rootView.findViewById(R.id.tvTempHour4);
        TextView tvTempHour5 = (TextView) rootView.findViewById(R.id.tvTempHour5);
        TextView tvTempHour6 = (TextView) rootView.findViewById(R.id.tvTempHour6);
        TextView tvHourHour1 = (TextView) rootView.findViewById(R.id.tvHourHour1);
        TextView tvHourHour2 = (TextView) rootView.findViewById(R.id.tvHourHour2);
        TextView tvHourHour3 = (TextView) rootView.findViewById(R.id.tvHourHour3);
        TextView tvHourHour4 = (TextView) rootView.findViewById(R.id.tvHourHour4);
        TextView tvHourHour5 = (TextView) rootView.findViewById(R.id.tvHourHour5);
        TextView tvHourHour6 = (TextView) rootView.findViewById(R.id.tvHourHour6);
        Intent intent = getActivity().getIntent();
        String city = intent.getStringExtra("cityname");


        final TextView[] TempHourTab = {tvTempHour1, tvTempHour2, tvTempHour3, tvTempHour4, tvTempHour5, tvTempHour6};
        final TextView[] HourHourTab = {tvHourHour1, tvHourHour2, tvHourHour3, tvHourHour4, tvHourHour5, tvHourHour6 };

        final ImageView[] IconHourTab = {ivIconHour1,ivIconHour2,ivIconHour3,ivIconHour4, ivIconHour5, ivIconHour6};


        ivIconHour2.setImageResource(R.drawable.cf10d);
        SearchActivity SearchActivity = new SearchActivity();


        CurrentAPI.Factory.getInstance().getCurrent(city, "534b11c2ebcc982b1fd81a8fa36f088b").enqueue(new Callback<Current>()  {

            @Override
            public void onResponse(Call<Current> call, Response<Current> response) {

                int i;
                for (i = 0; i < 5; i++) {

                    IconHourTab[i].setImageResource(getImageId(rootView.getContext(), "cf" + (response.body().getList().get(i * 8).getWeather().get(0).getIcon())));
                    HourHourTab[i].setText(response.body().getList().get(i * 8).getDtTxt().split(" ")[0]);
                    TempHourTab[i].setText("" + (int)(response.body().getList().get(i).getMain().getTemp() - 273) + " °C");

                }
            }

            @Override
            public void onFailure(Call<Current> call, Throwable t) {
                Log.e("Fail", t.getMessage());

            }
        });
        return rootView;
    }
    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }
}
