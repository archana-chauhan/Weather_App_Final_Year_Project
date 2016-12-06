package com.archana.weathertest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.archana.weathertest.Current.Current;
import com.archana.weathertest.Current.CurrentAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceholderFragmentDetails extends Fragment {

    double pressure;
    int humidity;
    double speed;
    int tempMin;

    public PlaceholderFragmentDetails() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragmentDetails newInstance() {
        PlaceholderFragmentDetails fragment = new PlaceholderFragmentDetails();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_city_details, container, false);
        final TextView pressure = (TextView) rootView.findViewById(R.id.pressure);
        final TextView humidity = (TextView) rootView.findViewById(R.id.humidity);
        final TextView speed = (TextView) rootView.findViewById(R.id.speed);
        final TextView tempMin = (TextView) rootView.findViewById(R.id.tempMin);
        Intent intent = getActivity().getIntent();
        String city = intent.getStringExtra("cityname");


        SearchActivity SearchActivity = new SearchActivity();


        CurrentAPI.Factory.getInstance().getCurrent(city, "534b11c2ebcc982b1fd81a8fa36f088b").enqueue(new Callback<Current>()  {

            @Override
            public void onResponse(Call<Current> call, Response<Current> response) {

             pressure.setText("Pressure : " + (int) response.body().getList().get(0).getMain().getPressure() + " mbar");
             humidity.setText("Humidity : " + response.body().getList().get(0).getMain().getHumidity() + " %");
             speed.setText("Wind Speed : " + (int) response.body().getList().get(0).getWind().getSpeed() + " km/h");
             tempMin.setText("Temperature : " + ((int) response.body().getList().get(0).getMain().getTempMin()- 273) + " °C");
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
