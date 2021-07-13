package com.arimbimega.sportlinenews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.arimbimega.sportlinenews.Adapter.SportAdapter;
import com.arimbimega.sportlinenews.Model.Articles;
import com.arimbimega.sportlinenews.Model.SportModel;
import com.arimbimega.sportlinenews.Retrofit.APIService;
import com.arimbimega.sportlinenews.Webview.DetailSportActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private SportAdapter sportAdapter;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.getSupportActionBar().setTitle("Berita Olahraga Terkini");

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        mRecyclerView =findViewById(R.id.rvSport);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSportModel();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(MainActivity.this, "Data sudah paling baru", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);
            }
        });

        getSportModel();

    }

    private void showSelectedItem (Articles articles){
        Intent intent = new Intent(MainActivity.this, DetailSportActivity.class);
        intent.putExtra("articlesArrayList", articles);
        intent.putExtra("sourceArrayList", articles.getSource());
        startActivity(intent);

    }

    private  void getSportModel(){
        APIService.endPoint().getSportModel()
                .enqueue(new Callback<SportModel>() {
                    @Override
                    public void onResponse(Call<SportModel> call, Response<SportModel> response) {

                        if (response.isSuccessful()){

                            ArrayList<Articles> articlesArrayList = response.body().getArticlesArrayList();
                            sportAdapter = new SportAdapter(articlesArrayList);
                            sportAdapter.notifyDataSetChanged();
                            mRecyclerView.setAdapter(sportAdapter);

                            sportAdapter.setOnItemClickCallback(new SportAdapter.OnItemClickCallback() {
                                @Override
                                public void onItemClicked(Articles data) {
                                    showSelectedItem(data);
                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(Call<SportModel> call, Throwable t) {

                        Toast.makeText(MainActivity.this,"Oops, jaringan kamu bermasalah.", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}