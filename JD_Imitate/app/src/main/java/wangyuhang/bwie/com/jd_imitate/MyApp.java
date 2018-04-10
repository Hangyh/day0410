package wangyuhang.bwie.com.jd_imitate;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by dell on 2018/3/14.
 */

public class MyApp extends Application{


    public static MyApp myApp;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        Fresco.initialize(this);

    }

    public static MyApp getMyApp(){

        return myApp;
    }
}
