package com.arimbimega.sportlinenews.Retrofit;

import com.arimbimega.sportlinenews.Model.SportModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIEndPoint {

    @GET("top-headlines?country=id&category=sport&apiKey=240840355b184816ac0f8dd6e46d591d&pageSize=50")
    Call<SportModel> getSportModel();

}
