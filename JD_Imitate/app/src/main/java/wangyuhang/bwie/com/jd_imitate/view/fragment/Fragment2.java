package wangyuhang.bwie.com.jd_imitate.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.base.BaseFragment;
import wangyuhang.bwie.com.jd_imitate.faxian.adapter.MyAdapter;
import wangyuhang.bwie.com.jd_imitate.faxian.bean.FaxianBean;
import wangyuhang.bwie.com.jd_imitate.faxian.presenter.FaxianPrensenter;
import wangyuhang.bwie.com.jd_imitate.faxian.view.FaxianView;
import wangyuhang.bwie.com.jd_imitate.view.activity.Faxian_Webview;

/**
 * Created by dell on 2018/3/14.
 */

public class Fragment2 extends BaseFragment implements FaxianView{


    private RecyclerView rl;
    private List<FaxianBean.ResultBean.DataBean> data;


    @Override
    protected void ininView(View view) {

        rl = view.findViewById(R.id.rl_faxian);
        rl.setLayoutManager(new LinearLayoutManager(getActivity()));
        FaxianPrensenter faxianPrensenter = new FaxianPrensenter(this);
        faxianPrensenter.faxianP();
    }

    @Override
    public int getFargementLayout() {
        return R.layout.fragment2;
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void SucessCallBack(FaxianBean faxianBean) {

        data = faxianBean.getResult().getData();
        MyAdapter myAdapter = new MyAdapter(data,getActivity());
        rl.setAdapter(myAdapter);
        myAdapter.setOnItemListenner(new MyAdapter.setItemListenner() {
            @Override
            public void setClickListenner(int position) {
                String url = data.get(position).getUrl();
                Intent intent = new Intent(getActivity(), Faxian_Webview.class);
                intent.putExtra("faxianurl",url);
                startActivity(intent);
            }
        });

    }
}
