package com.arimbimega.sportlinenews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.arimbimega.sportlinenews.Adapter.SportAdapter;
import com.arimbimega.sportlinenews.Model.Articles;
import com.arimbimega.sportlinenews.Model.SportModel;
import com.arimbimega.sportlinenews.Retrofit.APIService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

//    TextView tvTitleTest, tvPubtest, tvDesctest;

    private RecyclerView mRecyclerView;
    private SportAdapter sportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        tvTitleTest = findViewById(R.id.titleTest);
//        tvPubtest = findViewById(R.id.pubTest);
//        tvDesctest = findViewById(R.id.descTest);

        mRecyclerView =findViewById(R.id.rvSport);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        getSportModel();

    }

    private  void getSportModel(){
        APIService.endPoint().getSportModel()
                .enqueue(new Callback<SportModel>() {
                    @Override
                    public void onResponse(Call<SportModel> call, Response<SportModel> response) {

                        if (response.isSuccessful()){

//                        tvTitleTest.setText(response.body().getArticlesArrayList().get(0).getTitle());
//                        tvPubtest.setText(response.body().getArticlesArrayList().get(0).getSource().getName());
//                        tvDesctest.setText(response.body().getArticlesArrayList().get(0).getDescription());

                            ArrayList<Articles> articlesArrayList = response.body().getArticlesArrayList();
                            sportAdapter = new SportAdapter(articlesArrayList);
                            sportAdapter.notifyDataSetChanged();
                            mRecyclerView.setAdapter(sportAdapter);

                        }
                    }

                    @Override
                    public void onFailure(Call<SportModel> call, Throwable t) {

                    }
                });
    }
}