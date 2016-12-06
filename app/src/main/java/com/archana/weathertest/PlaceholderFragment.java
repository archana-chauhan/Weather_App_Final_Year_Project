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


public class PlaceholderFragment extends Fragment {

    ImageView ivIconHour1, ivIconHour2, ivIconHour3, ivIconHour4, ivIconHour5, ivIconHour6, ivIconHour7;


    private static final String ARG_SECTION_NUMBER = "1";

    public PlaceholderFragment() {
    }


    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_city, container, false);
        final TextView tvPhrase = (TextView) rootView.findViewById(R.id.tvPhrase);
       SearchActivity SearchActivity = new SearchActivity();

        ivIconHour1 = (ImageView) rootView.findViewById(R.id.ivIconHour1);
        ivIconHour2 = (ImageView) rootView.findViewById(R.id.ivIconHour2);
        ivIconHour3 = (ImageView) rootView.findViewById(R.id.ivIconHour3);
        ivIconHour4 = (ImageView) rootView.findViewById(R.id.ivIconHour4);
        ivIconHour5 = (ImageView) rootView.findViewById(R.id.ivIconHour5);
        ivIconHour6 = (ImageView) rootView.findViewById(R.id.ivIconHour6);
        ivIconHour7 = (ImageView) rootView.findViewById(R.id.ivIconHour7);
        TextView tvTempHour1 = (TextView) rootView.findViewById(R.id.tvTempHour1);
        TextView tvTempHour2 = (TextView) rootView.findViewById(R.id.tvTempHour2);
        TextView tvTempHour3 = (TextView) rootView.findViewById(R.id.tvTempHour3);
        TextView tvTempHour4 = (TextView) rootView.findViewById(R.id.tvTempHour4);
        TextView tvTempHour5 = (TextView) rootView.findViewById(R.id.tvTempHour5);
        TextView tvTempHour6 = (TextView) rootView.findViewById(R.id.tvTempHour6);
        TextView tvTempHour7 = (TextView) rootView.findViewById(R.id.tvTempHour7);
        TextView tvHourHour1 = (TextView) rootView.findViewById(R.id.tvHourHour1);
        TextView tvHourHour2 = (TextView) rootView.findViewById(R.id.tvHourHour2);
        TextView tvHourHour3 = (TextView) rootView.findViewById(R.id.tvHourHour3);
        TextView tvHourHour4 = (TextView) rootView.findViewById(R.id.tvHourHour4);
        TextView tvHourHour5 = (TextView) rootView.findViewById(R.id.tvHourHour5);
        TextView tvHourHour6 = (TextView) rootView.findViewById(R.id.tvHourHour6);
        TextView tvHourHour7 = (TextView) rootView.findViewById(R.id.tvHourHour7);
        Intent intent = getActivity().getIntent();
        String city = intent.getStringExtra("cityname");

        final TextView[] TempHourTab = {tvTempHour1, tvTempHour2, tvTempHour3, tvTempHour4, tvTempHour5, tvTempHour6, tvTempHour7};
        final TextView[] HourHourTab = {tvHourHour1, tvHourHour2, tvHourHour3, tvHourHour4, tvHourHour5, tvHourHour6, tvHourHour7};

        final ImageView[] IconHourTab = {ivIconHour1,ivIconHour2,ivIconHour3,ivIconHour4, ivIconHour5, ivIconHour6 , ivIconHour7};


        CurrentAPI.Factory.getInstance().getCurrent(city, "534b11c2ebcc982b1fd81a8fa36f088b").enqueue(new Callback<Current>()  {

            @Override
            public void onResponse(Call<Current> call, Response<Current> response) {
                String iconGet = "";
                iconGet = response.body().getList().get(0).getWeather().get(0).getIcon().toString();
                tvPhrase.setText("\""+ Phrase.Phraseget(iconGet)+"\"");
                int i;
                for (i = 0; i < 7; i++) {
                    System.out.println(i);

                    IconHourTab[i].setImageResource(getImageId(rootView.getContext(), "cf" + (response.body().getList().get(i).getWeather().get(0).getIcon())));
                    HourHourTab[i].setText((CharSequence) response.body().getList().get(i).getDtTxt());
                    TempHourTab[i].setText(""+(int)(response.body().getList().get(i).getMain().getTemp()- 273) + " °C");

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
