package wangyuhang.bwie.com.jd_imitate.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.base.BaseFragment;
import wangyuhang.bwie.com.jd_imitate.gouwuche.adapter.MyAdapter;
import wangyuhang.bwie.com.jd_imitate.gouwuche.bean.DatasBean;
import wangyuhang.bwie.com.jd_imitate.gouwuche.bean.MessageBean;
import wangyuhang.bwie.com.jd_imitate.gouwuche.bean.SomeId;
import wangyuhang.bwie.com.jd_imitate.gouwuche.event.MessageEvent;
import wangyuhang.bwie.com.jd_imitate.gouwuche.event.PriceAndCountEvent;
import wangyuhang.bwie.com.jd_imitate.gouwuche.presenter.DelPresenter;
import wangyuhang.bwie.com.jd_imitate.gouwuche.presenter.NewsPresenter;
import wangyuhang.bwie.com.jd_imitate.gouwuche.view.Iview;
import wangyuhang.bwie.com.jd_imitate.gwc.adapter.MyAdapter_Gwc;
import wangyuhang.bwie.com.jd_imitate.gwc.bean.GwcBean;
import wangyuhang.bwie.com.jd_imitate.gwc.presenter.GwcPresenter;
import wangyuhang.bwie.com.jd_imitate.gwc.view.GwcView;


/**
 * Created by dell on 2018/3/14.
 */

public class Fragment4 extends BaseFragment implements Iview {

    private String uid = "12574";
    private NewsPresenter presenter;
    private CheckBox mCheckbox2;
    private ExpandableListView mElv;

    /**
     * 0
     */
    private TextView mTvPrice;
    /**
     * 结算(0)
     */
    private TextView mTvNum;
    private MyAdapter adapter;
    private List<DatasBean> groupList;
    private List<List<DatasBean.ListBean>> childList;
    private String pid;
    private DelPresenter delPresenter;


    @Override
    protected void ininView(View view) {
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mCheckbox2 = (CheckBox) view.findViewById(R.id.checkbox2);
        mTvPrice = (TextView) view.findViewById(R.id.tv_price);
        mTvNum = (TextView) view.findViewById(R.id.tv_num);


        presenter = new NewsPresenter();
        presenter.attachView(this);
        delPresenter = new DelPresenter();
        delPresenter.attachView(this);
//        initView();


        mCheckbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changeAllListCbState(mCheckbox2.isChecked());
            }
        });
    }

    @Override
    public int getFargementLayout() {
//        EventBus.getDefault().register(this);
        return R.layout.fragment4;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }



    @Override
    public void onSuccess(Object o) {
        groupList = new ArrayList<>();
        childList = new ArrayList<>();
        if(o!=null){
            List<DatasBean> list = (List<DatasBean> )o;
            if(list!=null){
                groupList.addAll(list);
                for (int i = 0; i < list.size(); i++) {
                    List<DatasBean.ListBean> datas = list.get(i).getList();
                    childList.add(datas);
                }
                adapter = new MyAdapter(getActivity(), groupList, childList);
                mElv.setAdapter(adapter);

                adapter.notifyDataSetChanged();
                mCheckbox2.setChecked(true);

                adapter.changeAllListCbState(true);
                mElv.setGroupIndicator(null);
                for (int i=0;i<groupList.size();i++){
                    mElv.expandGroup(i);
                }

            }
        }
    }

    @Override
    public void onFailed(Exception e) {

    }

    @Override
    public void delSuccess(MessageBean listMessageBean) {
        Toast.makeText(getActivity(),listMessageBean.getMsg(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getData(uid,pid);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        mCheckbox2.setChecked(event.isChecked());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PriceAndCountEvent event) {
        mTvNum.setText("结算(" + event.getCount() + ")");
        mTvPrice.setText("￥"+event.getPrice() );
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SomeId event) {
        pid = event.getPid();
        Log.e("zxz","我得到了pid:"+pid);
        delPresenter.getData(uid,pid);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (presenter != null) {
            presenter.detachView();
        }
    }

    //    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//        if (presenter != null) {
//            presenter.detachView();
//        }
//    }
}
