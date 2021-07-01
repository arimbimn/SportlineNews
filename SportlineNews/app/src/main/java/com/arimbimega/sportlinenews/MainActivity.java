package com.arimbimega.sportlinenews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.arimbimega.sportlinenews.Model.SportModel;
import com.arimbimega.sportlinenews.Retrofit.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tvTitleTest, tvPubtest, tvDesctest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitleTest = findViewById(R.id.titleTest);
        tvPubtest = findViewById(R.id.pubTest);
        tvDesctest = findViewById(R.id.descTest);

        getSportModel();

    }

    private  void getSportModel(){
        APIService.endPoint().getSportModel()
                .enqueue(new Callback<SportModel>() {
                    @Override
                    public void onResponse(Call<SportModel> call, Response<SportModel> response) {
                        tvTitleTest.setText(response.body().getArticlesArrayList().get(0).getTitle());
                        tvPubtest.setText(response.body().getArticlesArrayList().get(0).getSource().getName());
                        tvDesctest.setText(response.body().getArticlesArrayList().get(0).getDescription());
                    }

                    @Override
                    public void onFailure(Call<SportModel> call, Throwable t) {

                    }
                });
    }
}