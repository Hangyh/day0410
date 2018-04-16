package wangyuhang.bwie.com.jd_imitate.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import wangyuhang.bwie.com.jd_imitate.R;

public class WebView_SouSuo extends AppCompatActivity {

    private WebView mWeb;
    private String source = "android";
    private String addcarurl = "https://www.zhaoapi.cn/product/addCart";
    private String addGood = "https://www.zhaoapi.cn/product/getOrders";
    private String price;
    private String pid;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view__sou_suo);

        mWeb = (WebView) findViewById(R.id.wv_sousuo);
        settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        Intent intent = getIntent();
        final String sousuourl = intent.getStringExtra("sousuourl");
        price = intent.getStringExtra("price");
        pid = intent.getStringExtra("pid");
        mWeb.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(sousuourl);
                return true;
            }
            //当页面加载完毕时  给H5的加入购物车按钮添加点击事件
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                setWebImageClick();
                setaddGoods();
            }

        });
        mWeb.addJavascriptInterface(new JsCallJavaObj() {
            @JavascriptInterface
            @Override
            public void showBigImg() {
                Toast.makeText(WebView_SouSuo.this, "你点击了加入购物车", Toast.LENGTH_SHORT).show();
                //添加购物车
                addCar("12574", pid +"");
            }
        },"jsCallJavaObj");
        mWeb.addJavascriptInterface(new JsCallJavaObj2(){
            @JavascriptInterface
            @Override
            public void setaddGoods() {
                Toast.makeText(WebView_SouSuo.this, "sadada", Toast.LENGTH_SHORT).show();
                addGoods();

            }
        },"jsCallJavaObj2");
        mWeb.loadUrl(sousuourl);
    }
    private interface JsCallJavaObj{
        void showBigImg();

    }
    private interface JsCallJavaObj2{
        void setaddGoods();
    }
    private  void setaddGoods() {
        String jsCode2="javascript:(function(){" +
                "var btn=document.getElementById(\"directorderBtm\");" +
                "btn.onclick=function(){" +
                "window.jsCallJavaObj2.setaddGoods();" +
                "}})()";
        mWeb.loadUrl(jsCode2);
    }
    //    add_cart_spec     addCartBtm   service-ok-btn directorderBtm addCartBtm
    private  void setWebImageClick() {
        String jsCode = "javascript:(function(){" +
                "var btn=document.getElementById(\"addCartBtm\");" +
                "btn.onclick=function(){" +
                "window.jsCallJavaObj.showBigImg();" +
                "}})()";
        mWeb.loadUrl(jsCode);
    }
    public void addCar(String uid,String pid){
        OkHttpClient build = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(addcarurl+"?uid=12574&pid="+pid+"&source=android").build();
        build.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }
    private void addGoods() {
//        https://www.zhaoapi.cn/product/getOrders?source=android&uid=12574&price=0.01
        Toast.makeText(this, "dasdasd", Toast.LENGTH_SHORT).show();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(addGood+"?source=android&uid=12574&"+"price="+price).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("erro",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.i("addd",response.body().string());
                startActivity(new Intent(WebView_SouSuo.this,DingDanLieBiao.class));
            }
        });
    }
}
