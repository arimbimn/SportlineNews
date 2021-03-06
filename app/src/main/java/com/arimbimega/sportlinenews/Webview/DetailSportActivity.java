package com.arimbimega.sportlinenews.Webview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.arimbimega.sportlinenews.Model.Articles;
import com.arimbimega.sportlinenews.Model.Source;
import com.arimbimega.sportlinenews.R;

public class DetailSportActivity extends AppCompatActivity {

    WebView webView;

    ProgressBar progressBar;

    String UrlNews = "https://www.google.com";


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sport);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Articles articles = getIntent().getParcelableExtra("articlesArrayList");
        Source source = getIntent().getParcelableExtra("sourceArrayList");
        this.getSupportActionBar().setTitle(source.getName());
        this.getSupportActionBar().setSubtitle(articles.getTitle());
        UrlNews = articles.getUrl();

        progressBar = findViewById(R.id.progressbar);
        progressBar.setMax(100);

        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultTextEncodingName("utf-8");

        webView.setWebViewClient(new Callback());
        webView.loadUrl(UrlNews);

    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(UrlNews);
            progressBar.setProgress(0);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String urlStart, Bitmap favicon) {
            super.onPageStarted(view, urlStart, favicon);
            UrlNews = urlStart;
            invalidateOptionsMenu();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(view.GONE);
            invalidateOptionsMenu();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}