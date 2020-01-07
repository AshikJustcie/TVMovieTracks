package com.example.tvmovietracks;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;

public class Webview extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView webView;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ProgressBar progressBar;
    LinearLayout progressLayout;
    String myCurrentUrl,myPreviousUrl,myTitle;
    NestedScrollView nestedScrollView;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        toolbar = findViewById(R.id.toolbarwbm);
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        myPreviousUrl = intent.getStringExtra("webaddress");

        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);


        progressBar = findViewById(R.id.webProgress);
        //imageView = findViewById(R.id.webImage);
       // textView = findViewById(R.id.webTitle);
        progressLayout = findViewById(R.id.progress_layout);
        nestedScrollView = findViewById(R.id.nested_scroll_view);

        webView = findViewById(R.id.webview);

        webView.loadUrl(myPreviousUrl);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);

        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        progressBar.setMax(100);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressLayout.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
                nestedScrollView.scrollTo(0,0);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressLayout.setVisibility(View.GONE);
                super.onPageFinished(view, url);
                myCurrentUrl = url;

            }
        });






        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                collapsingToolbarLayout.setTitleEnabled(false);
                toolbar.setTitle(title);
                myTitle = title;
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                //imageView.setImageBitmap(icon);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_web, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_back:
                onBackPressed();
                break;

            case R.id.menu_forward:
                onForwardPressed();
                break;

            case R.id.menu_refresh:
                webView.reload();
                break;

            case R.id.menu_save:
                Intent intent = new Intent(Webview.this, AddMainActivity.class);
                intent.putExtra("WTitle",myTitle);
                intent.putExtra("WLink", myCurrentUrl);
                startActivity(intent);
                finish();
                break;

            case R.id.menu_share:
                /*ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("https://developers.facebook.com"))
                        .build();*/
                /*Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, myCurrentUrl);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Copied URL");
                startActivity(Intent.createChooser(shareIntent, "Share URL with Friends"));*/
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onForwardPressed() {
        if (webView.canGoForward()) {
            webView.goForward();
        }
        else {
            Toast.makeText(this, "Can't Go Further!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {

        if (webView.canGoBack()) {
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }


}
