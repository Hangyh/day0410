package wangyuhang.bwie.com.jd_imitate.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.base.BaseFragment;
import wangyuhang.bwie.com.jd_imitate.fenlei.adapter.ErJiLieBiaoAdapter;
import wangyuhang.bwie.com.jd_imitate.fenlei.adapter.FenAdapter;
import wangyuhang.bwie.com.jd_imitate.fenlei.adapter.MyAdaptera;
import wangyuhang.bwie.com.jd_imitate.fenlei.bean.ErJiLieBiao;
import wangyuhang.bwie.com.jd_imitate.fenlei.bean.FenLei;
import wangyuhang.bwie.com.jd_imitate.fenlei.presenter.FenPresenter;
import wangyuhang.bwie.com.jd_imitate.fenlei.presenter.FenPresenter2;
import wangyuhang.bwie.com.jd_imitate.fenlei.view.FenView;
import wangyuhang.bwie.com.jd_imitate.fenlei.view.FenView2;

/**
 * Created by dell on 2018/3/14.
 */

public class Fragment3 extends Fragment implements FenView,FenView2 {

    private View view;
    private RecyclerView mRlv;
    private ExpandableListView mElist;
    String url="http://120.27.23.105/product/getCatagory";
    String url2="http://120.27.23.105/product/getProductCatagory?cid=";
    private FenPresenter2 fenPresenter2;
    private List<ErJiLieBiao.DataBean.ListBean> list1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        initView(view);
        mRlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        FenPresenter fenPresenter=new FenPresenter(this);
        fenPresenter.getFenPresenter(url);
        fenPresenter2 = new FenPresenter2(this);
        fenPresenter2.getFenPresenter(url2+1);
        return view;
    }
    private void initView(View view) {
        mRlv = (RecyclerView) view.findViewById(R.id.rlv);
        mElist = (ExpandableListView) view.findViewById(R.id.elist);
    }

    @Override
    public void OnSuccess(FenLei fenLei) {
        final List<FenLei.DataBean> data = fenLei.getData();
        final FenAdapter fenAdapter=new FenAdapter(getActivity(),data);
        mRlv.setAdapter(fenAdapter);
//        mRlv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        fenAdapter.setBianse(new FenAdapter.Bianse() {
            @Override
            public void bianse(int position) {
                int cid = data.get(position).getCid();
                fenPresenter2.getFenPresenter(url2+cid);
                fenAdapter.setThisPosition(position);
                fenAdapter.notifyDataSetChanged();
            }
        });
//        fenAdapter.getDanJi(new FenAdapter.DanJi() {
//            @Override
//            public void OnSuccess(int cid) {
//                fenPresenter2.getFenPresenter(url2+cid);
////                fenAdapter.setThisPosition(cid);
////                fenAdapter.notifyDataSetChanged();
//            }
//        });
    }
    @Override
    public void OnSuccess(ErJiLieBiao erJiLieBiao) {
        List<ErJiLieBiao.DataBean> data = erJiLieBiao.getData();
        mElist.setAdapter(new ErJiLieBiaoAdapter(getActivity(),data));
        int count = mElist.getCount();
        for (int i=0; i<count; i++) {
            mElist.expandGroup(i);
        };
        for (int i = 0; i <data.size() ; i++) {
            ErJiLieBiao.DataBean dataBean = data.get(i);
            list1 = dataBean.getList();

        }
        MyAdaptera myAdapter=new MyAdaptera(getActivity(),list1);


    }


}
