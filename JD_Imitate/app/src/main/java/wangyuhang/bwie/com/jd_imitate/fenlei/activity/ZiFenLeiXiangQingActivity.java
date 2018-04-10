package wangyuhang.bwie.com.jd_imitate.fenlei.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.fenlei.adapter.IAdapter;
import wangyuhang.bwie.com.jd_imitate.fenlei.bean.ZiFenLeiInfo;
import wangyuhang.bwie.com.jd_imitate.fenlei.presenter.IPresenter;
import wangyuhang.bwie.com.jd_imitate.fenlei.view.IView;
import wangyuhang.bwie.com.jd_imitate.view.activity.WebViewActivity;


public class ZiFenLeiXiangQingActivity extends BaseActivity<IPresenter> implements IView {


    private RecyclerView mRlv;

    @Override
    protected void getData() {
        Bundle extras = getIntent().getExtras();
        String string = extras.getString("id");
        presenter.getIPresenter(string);
    }

    @Override
    protected void initView() {
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected IPresenter getPresenter() {
        presenter = new IPresenter(this);
        return presenter;
    }

    @Override
    protected int getID() {
        return R.layout.activity_zi_fen_lei_xiang_qing;
    }

    @Override
    public void OnSuccess(ZiFenLeiInfo ziFenLeiInfo) {

        List<ZiFenLeiInfo.DataBean> data = ziFenLeiInfo.getData();
        IAdapter iAdapter=new IAdapter(ZiFenLeiXiangQingActivity.this,data);
        mRlv.setAdapter(iAdapter);
        iAdapter.getCidListener(new IAdapter.setUrl() {
            @Override
            public void OnSuccess(String url) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("url",url);
                intent.putExtras(bundle);
                intent.setClass(ZiFenLeiXiangQingActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });
    }


}
