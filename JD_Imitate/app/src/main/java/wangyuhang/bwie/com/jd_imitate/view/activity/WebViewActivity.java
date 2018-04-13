package wangyuhang.bwie.com.jd_imitate.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import static wangyuhang.bwie.com.jd_imitate.R.id.web;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWeb;
    private String source = "android";
    private String addcarurl = "https://www.zhaoapi.cn/product/addCart";
    private String addGoods = "https://www.zhaoapi.cn/product/getOrders";
    private int pid;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
        Bundle extras = getIntent().getExtras();
        final String string = extras.getString("url");
        price = extras.getString("price");
        pid = extras.getInt("pid");
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
//        mWeb.addJavascriptInterface(new Android, "test");
        mWeb.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(string);
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
                Toast.makeText(WebViewActivity.this, "你点击了加入购物车", Toast.LENGTH_SHORT).show();
                //添加购物车
                addCar("12574",pid+"");
            }
        },"jsCallJavaObj");
        mWeb.addJavascriptInterface(new JsCallJavaObj2(){
            @JavascriptInterface
            @Override
            public void setaddGoods() {
//                Toast.makeText(WebViewActivity.this, "sadada", Toast.LENGTH_SHORT).show();
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(addGoods+"?uid=12574&"+"price="+price).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("erro",e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

//                        Log.i("addd",response.body().string());
                        startActivity(new Intent(WebViewActivity.this,DingDanLieBiao.class));
                    }
                });

            }
        },"jsCallJavaObj2");
//        mWeb.addJavascriptInterface(new JsCallJavaObj2() {
//
//            }
//        });

        mWeb.loadUrl(string);
//        mWeb.loadUrl(string);
//        mWeb.evaluateJavascript();
    }
    /**
     * Js調用Java接口
     */
    private interface JsCallJavaObj{
        void showBigImg();

    }
    private interface JsCallJavaObj2{
        void setaddGoods();
    }
    private  void setaddGoods() {
        String jsCode="javascript:(function(){" +
                "var btn=document.getElementById(\"directorderBtm\");" +
                "btn.onclick=function(){" +
                "window.jsCallJavaObj2.setaddGoods();" +
                "}})()";
        mWeb.loadUrl(jsCode);
    }
//    add_cart_spec     addCartBtm   service-ok-btn directorderBtm
    private  void setWebImageClick() {
        String jsCode="javascript:(function(){" +
                "var btn=document.getElementById(\"add_cart_spec\");" +
                "btn.onclick=function(){" +
                "window.jsCallJavaObj.showBigImg();" +
                "}})()";
        mWeb.loadUrl(jsCode);
    }

    public void addCar(String uid,String pid){
        OkHttpClient build = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(addcarurl+"?uid="+uid+"&pid="+pid+"&source=android").build();
        build.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }
    private void initView() {
        mWeb = (WebView) findViewById(web);
    }
}
