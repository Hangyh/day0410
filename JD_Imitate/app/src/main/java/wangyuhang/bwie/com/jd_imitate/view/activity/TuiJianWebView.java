package wangyuhang.bwie.com.jd_imitate.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import wangyuhang.bwie.com.jd_imitate.R;

public class TuiJianWebView extends AppCompatActivity {

    private WebView mTuijianWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tui_jian_web_view);
        initView();

        Intent intent = getIntent();
        String weburl = intent.getStringExtra("weburl");

        mTuijianWv.setWebViewClient(new WebViewClient());
        mTuijianWv.loadUrl(weburl);

    }

    private void initView() {
        mTuijianWv = (WebView) findViewById(R.id.tuijian_wv);
    }
}
