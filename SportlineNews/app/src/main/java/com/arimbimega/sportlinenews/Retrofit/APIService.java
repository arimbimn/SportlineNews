package com.arimbimega.sportlinenews.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {

    private static String BASE_URL = "https://newsapi.org/v2/";

    private static Retrofit retrofit;

    public static APIEndPoint endPoint(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(APIEndPoint.class);
    }

}
