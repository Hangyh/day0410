package wangyuhang.bwie.com.jd_imitate.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.view.XiangQingView;
import wangyuhang.bwie.com.jd_imitate.adapter.FenLeiYou;
import wangyuhang.bwie.com.jd_imitate.adapter.FenLeiZuo;
import wangyuhang.bwie.com.jd_imitate.adapter.XiangQingAdapter;
import wangyuhang.bwie.com.jd_imitate.bean.GridBean;
import wangyuhang.bwie.com.jd_imitate.bean.ListBeanList;
import wangyuhang.bwie.com.jd_imitate.bean.XiangQingBean;
import wangyuhang.bwie.com.jd_imitate.bean.ZiLeiBean;
import wangyuhang.bwie.com.jd_imitate.presenter.ClassificationPresenter;
import wangyuhang.bwie.com.jd_imitate.presenter.FenLeiZileiPresenter;
import wangyuhang.bwie.com.jd_imitate.presenter.XiangQingPresenter;
import wangyuhang.bwie.com.jd_imitate.view.ClassificationView;
import wangyuhang.bwie.com.jd_imitate.view.FenLeiZilei;

/**
 * Created by dell on 2018/3/14.
 */

public class Fragment2 extends Fragment implements ClassificationView,FenLeiZilei{


    private RecyclerView rl_list_fenlei,rl_grid_fenlei,rl_zilei;
    private FenLeiZuo fenLeiZuo;
    private FenLeiZileiPresenter fenlei;
    private List<GridBean.DataBean> data;
//    private FenLeiYou fenLeiYou;
//    private XiangQingPresenter xiangQingPresenter;
    private List<ZiLeiBean.DataBean> list;
    //    private List<ListBeanList> listBeen;
//    private XiangQingAdapter xiangQingAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
//        listBeen = new ArrayList<>();
        rl_list_fenlei = view.findViewById(R.id.rl_list_fenlei);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rl_list_fenlei.setLayoutManager(linearLayoutManager);

        //分类一
        ClassificationPresenter classificationPresenter = new ClassificationPresenter(this);
        classificationPresenter.ficationView("https://www.zhaoapi.cn/product/getCatagory");
        //分类二

        fenlei = new FenLeiZileiPresenter(new FenLeiZilei() {
            @Override
            public void FenLeiZileiFail() {

            }

            @Override
            public void FenLeiZileiSuccess(String s) {

                Gson gson = new Gson();

                ZiLeiBean ziLeiBean = gson.fromJson(s, ZiLeiBean.class);

                list = ziLeiBean.getData();


            }

            @Override
            public Context context() {
                return getActivity();
            }
        });




        return view;
    }

//分类一
    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void ClassificationFail() {

    }

    @Override
    public void ClassificationViewSuccess(String s) {

        Gson gson = new Gson();

        GridBean gridBean = gson.fromJson(s, GridBean.class);

        data = gridBean.getData();
        Log.i("TAG",""+ data.size());
        fenLeiZuo = new FenLeiZuo(getActivity(), data);
        //分类一点击事件
        fenLeiZuo.setOnItemListenner2(new FenLeiZuo.onItemListenner() {
            @Override
            public void ItemListner(int position) {
//                Toast.makeText(getActivity(), data.get(position).getCid()+"", Toast.LENGTH_SHORT).show();
//                fenlei.zilei(data.get(position).getCid());
            }
        });
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rl_list_fenlei.setAdapter(fenLeiZuo);
            }
        });
    }

    @Override
    public void FenLeiZileiFail() {

    }

    @Override
    public void FenLeiZileiSuccess(String s) {
        Gson gson = new Gson();

        ZiLeiBean ziLeiBean = gson.fromJson(s, ZiLeiBean.class);

        list = ziLeiBean.getData();

    }
}
