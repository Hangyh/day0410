package wangyuhang.bwie.com.jd_imitate.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import wangyuhang.bwie.com.jd_imitate.R;

public class WebView_SouSuo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view__sou_suo);

        WebView viewById = (WebView) findViewById(R.id.wv_sousuo);
        WebSettings settings = viewById.getSettings();
        settings.setJavaScriptEnabled(true);
        Intent intent = getIntent();
        String sousuourl = intent.getStringExtra("sousuourl");

        viewById.setWebViewClient(new WebViewClient());
        viewById.loadUrl(sousuourl);
    }
}
