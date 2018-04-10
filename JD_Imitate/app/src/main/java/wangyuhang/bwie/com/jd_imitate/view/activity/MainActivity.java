package wangyuhang.bwie.com.jd_imitate.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.base.BaseActivity;
import wangyuhang.bwie.com.jd_imitate.base.BasePresenter;

import static android.R.id.edit;
import static wangyuhang.bwie.com.jd_imitate.R.id.i;

public class MainActivity extends BaseActivity {

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv_three.setText("倒计时："+msg.what);
            if (msg.what==0){
                startActivity(new Intent(MainActivity.this, AllActivity.class));

            }
        }
    };
    int i = 3;
    private TextView tv_three;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void initView() {

        tv_three = (TextView) findViewById(R.id.tv_Three);

        new Thread() {

            @Override
            public void run() {
                super.run();

                final Timer timer = new Timer();

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {

                        handler.sendEmptyMessage(i);
                        i--;
                        if (i < 0) {
                            timer.cancel();
                        }
                    }
                }, 0, 1000);

            }
        }.start();

    }
}
