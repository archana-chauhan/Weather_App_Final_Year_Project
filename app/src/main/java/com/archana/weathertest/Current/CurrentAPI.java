package com.archana.weathertest.Current;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by axel on 04/05/2016.
 */
public interface CurrentAPI {

    String BASE_URL = "http://api.openweathermap.org";
    @GET("/data/2.5/forecast")
    Call<Current> getCurrent(@Query("q") String getText, @Query("appid") String getAppid);

    class Factory {

        private static CurrentAPI service;

        public static CurrentAPI getInstance() {
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
                        .build();

                service = retrofit.create(CurrentAPI.class);

                return service;
            }
            else {
                return service;
            }
        }
    }
}
