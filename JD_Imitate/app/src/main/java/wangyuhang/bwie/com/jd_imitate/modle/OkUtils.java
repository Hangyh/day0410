package wangyuhang.bwie.com.jd_imitate.modle;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by dell on 2018/3/12.
 */

public class OkUtils {

    public static volatile OkUtils okUtils;

    public OkHttpClient okHttpClient;

    public OkUtils() {
        okHttpClient = new OkHttpClient();
    }
    public static OkUtils getInstens(){

        if (null==okUtils){
            synchronized (OkUtils.class){
                if (null==okUtils){
                    okUtils = new OkUtils();
                }
            }
        }
        return okUtils;
    }

    public void doGet(String url, final ResponseCallBack respo){

        Request requset = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(requset);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (null!=respo){
                    respo.ResponseFail();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null!=respo){
                    respo.ResponseSucceess(response.body().string());
                }
            }
        });

    }

    public interface ResponseCallBack{

        void ResponseFail();

        void ResponseSucceess(String s);

    }
}
