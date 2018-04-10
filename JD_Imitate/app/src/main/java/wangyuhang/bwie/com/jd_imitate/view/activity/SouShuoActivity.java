package wangyuhang.bwie.com.jd_imitate.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.adapter.MyApdater3;
import wangyuhang.bwie.com.jd_imitate.base.BaseActivity;
import wangyuhang.bwie.com.jd_imitate.bean.ShowBean;
import wangyuhang.bwie.com.jd_imitate.presenter.ShowPrensenterSou;
import wangyuhang.bwie.com.jd_imitate.view.ShowView;
import wangyuhang.bwie.com.jd_imitate.view.ShowViewSou;


public class SouShuoActivity extends BaseActivity<ShowPrensenterSou> implements ShowViewSou {

    private  int  page = 1;
    private EditText sv;
    private RecyclerView rl;
    private Button bt_qie,bt_huan;
    private SwipyRefreshLayout swipyRefreshLayout;
    private boolean falg ;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    @Override
    public Context context() {
        return SouShuoActivity.this;
    }

    @Override
    public void showDataFail() {

    }

    @Override
    public void showDataSuccess(ShowBean showBean) {
        List<ShowBean.DataBean> list = showBean.getData();
        final MyApdater3 myApdater = new MyApdater3(list,SouShuoActivity.this);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rl.setAdapter(myApdater);
            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.activity_sou_shuo;
    }

    @Override
    public ShowPrensenterSou getPresenter() {
        return new ShowPrensenterSou(this);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String et = intent.getStringExtra("et");

        sv = (EditText) findViewById(R.id.et_sousuo);
        rl = (RecyclerView) findViewById(R.id.rl);
        bt_qie = (Button) findViewById(R.id.bt_qie);
        bt_huan = (Button) findViewById(R.id.bt_huan);
        swipyRefreshLayout = (SwipyRefreshLayout) findViewById(R.id.srl);
        linearLayoutManager = new LinearLayoutManager(SouShuoActivity.this);
        gridLayoutManager = new GridLayoutManager(SouShuoActivity.this,2);
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        rl.setLayoutManager(linearLayoutManager);

        SharedPreferences sp = getSharedPreferences("sp", 0);
        final String token = sp.getString("token",null);
        presenter.showP("android",et,page);
        bt_huan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (falg){
                    rl.setLayoutManager(linearLayoutManager);
                    falg=false;
                }else {
                    rl.setLayoutManager(gridLayoutManager);
                    falg = true;
                }
            }
        });

        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                page=1;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.showP("android","笔记本",page);
                        swipyRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }

            @Override
            public void onLoad(int index) {
                page++;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.showP("android","笔记本",page);
                        swipyRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
        bt_qie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = sv.getText().toString();
                presenter.showP("android",s,page);
            }
        });

    }




}
