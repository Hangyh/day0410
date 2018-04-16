package wangyuhang.bwie.com.jd_imitate.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.base.BaseActivity;
import wangyuhang.bwie.com.jd_imitate.dingdanliebiao.adapter.MyAdapter;
import wangyuhang.bwie.com.jd_imitate.dingdanliebiao.bean.DingDanBean;
import wangyuhang.bwie.com.jd_imitate.dingdanliebiao.presenter.DingDanLieBiaoPresenter;
import wangyuhang.bwie.com.jd_imitate.dingdanliebiao.view.DingDanLieBiaoView;

public class DingDanLieBiao extends BaseActivity<DingDanLieBiaoPresenter> implements DingDanLieBiaoView {

    private EditText mEtAddr3;
    private EditText mEtMobile3;
    private EditText mEtName3;
    private RecyclerView mRclDingdan;
    /**
     * 提交订单
     */
    private Button mBtDingdan;


    @Override
    public Context context() {
        return null;
    }

    @Override
    public void dingdanliebiaoFail() {

    }

    @Override
    public void dingdanliebiaoSuccess(DingDanBean dingDanBean) {

        List<DingDanBean.DataBean> data = dingDanBean.getData();
//        data.get(0).get
        MyAdapter myAdapter = new MyAdapter(data,DingDanLieBiao.this);
        mRclDingdan.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRclDingdan.setAdapter(myAdapter);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_ding_dan_lie_biao;
    }

    @Override
    public DingDanLieBiaoPresenter getPresenter() {
        return new DingDanLieBiaoPresenter(this);
    }

    @Override
    public void initView() {
        presenter.dingdanP("12574");
        mEtAddr3 = (EditText) findViewById(R.id.et_addr3);
        mEtMobile3 = (EditText) findViewById(R.id.et_mobile3);
        mEtName3 = (EditText) findViewById(R.id.et_name3);
        mRclDingdan = (RecyclerView) findViewById(R.id.rcl_dingdan);
        mBtDingdan = (Button) findViewById(R.id.bt_dingdan);
        mRclDingdan.setLayoutManager(new LinearLayoutManager(DingDanLieBiao.this));
    }
}
