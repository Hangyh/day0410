package wangyuhang.bwie.com.jd_imitate.view.fragment;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.youth.banner.Banner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.adapter.Adapter;
import wangyuhang.bwie.com.jd_imitate.adapter.GridAdapter;
import wangyuhang.bwie.com.jd_imitate.adapter.MyAdapter1;
import wangyuhang.bwie.com.jd_imitate.adapter.MyAdapter2;
import wangyuhang.bwie.com.jd_imitate.bean.GridBean;
import wangyuhang.bwie.com.jd_imitate.bean.SuperBean;
import wangyuhang.bwie.com.jd_imitate.presenter.ShowPresenter;
import wangyuhang.bwie.com.jd_imitate.view.ShowView;
import wangyuhang.bwie.com.jd_imitate.view.activity.Sou;
import wangyuhang.bwie.com.jd_imitate.view.activity.SouShuoActivity;
import wangyuhang.bwie.com.jd_imitate.view.activity.TuiJianWebView;

import static android.R.attr.data;


/**
 * Created by dell on 2018/3/12.
 */

public class Fragment1 extends Fragment implements ShowView {

    private TextView tv1,tv_tiao;
    private RecyclerView rl1,rl2;
    private int time;
    private Banner bn;
    private SimpleDateFormat simpleDateFormat;

    private GridView gv;
    private GridAdapter gridAdapter;
    private ImageView imageView,sous;
    private EditText et;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1,container,false);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getActivity().getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        tv1 = view.findViewById(R.id.tv1);
        rl1 = view.findViewById(R.id.rl1);
        rl2 = view.findViewById(R.id.rl2);
        bn = view.findViewById(R.id.iv);
        gv = view.findViewById(R.id.gv);
        tv_tiao = view.findViewById(R.id.tv_tiao);
//        sous  = view.findViewById(R.id.sousuo);
//        et = view.findViewById(R.id.et);
        tv_tiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(),Sou.class));
            }
        });
//        sous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().startActivity(new Intent(getActivity(), SouShuoActivity.class));
//            }
//        });
//扫描二维码
        imageView = view.findViewById(R.id.head_sys);

//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                imageView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//
//                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
//                                != PackageManager.PERMISSION_GRANTED) {
//                            //申请WRITE_EXTERNAL_STORAGE权限
//                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
//                                    1);}//这一块红色的是开启手机里的相机权限，安卓6.0以后的系统需要，否则会报错
//                        Intent intent = new Intent(getActivity(), CaptureActivity.class);//黄色是第三方类库里面的类
//                        startActivity(intent);
//
//                    }
//                });
//            }
//        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rl1.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        rl2.setLayoutManager(gridLayoutManager);

        ShowPresenter showPresenter = new ShowPresenter(this);
        showPresenter.showpresentre("https://www.zhaoapi.cn/ad/getAd");
        showPresenter.showGrid("https://www.zhaoapi.cn/product/getCatagory");

        return view;
    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void Fail() {

    }

    @Override
    public void Success(String s) {

        Gson gson = new Gson();

        SuperBean superBean = gson.fromJson(s, SuperBean.class);
        SuperBean.TuijianBean tuijian = superBean.getTuijian();
        final List<SuperBean.TuijianBean.ListBean> list1 = tuijian.getList();
        final MyAdapter2 myAdapter2 = new MyAdapter2(getActivity(),list1);
        myAdapter2.setOnItemListenner(new MyAdapter2.onItemListenner() {
            @Override
            public void setonItemListenner(int position) {
                String detailUrl = list1.get(position).getDetailUrl();

                Intent intent = new Intent(getActivity(), TuiJianWebView.class);
                intent.putExtra("weburl",detailUrl);
                startActivity(intent);
            }
        });
        final List<SuperBean.DataBean> data = superBean.getData();
        List<String> list2 = new ArrayList<>();
        for (int i = 0;i<data.size();i++){
            list2.add(data.get(i).getIcon());
        }

        bn.setImageLoader(new Adapter());
        bn.setImages(list2);
        bn.setDelayTime(3000);
        SuperBean.MiaoshaBean miaosha = superBean.getMiaosha();
        final List<SuperBean.MiaoshaBean.ListBeanX> list = miaosha.getList();
        final MyAdapter1 myAdapter1 = new MyAdapter1(getActivity(),list);
        time = miaosha.getTime();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                tv1.setText(ms2HMS(time));
                rl1.setAdapter(myAdapter1);
                rl2.setAdapter(myAdapter2);
                bn.start();
                simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                new CountDownTimer(time, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        String format = simpleDateFormat.format(new Date(millisUntilFinished));
                        tv1.setText("京东秒杀:"+format);
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }
        });

    }

    @Override
    public void GridSuccess(String s) {

        Gson gson = new Gson();

        GridBean gridBean = gson.fromJson(s, GridBean.class);

        List<GridBean.DataBean> data = gridBean.getData();
        for (int i=0;i<data.size();i++){
            int cid = data.get(i).getCid();

        }
        gridAdapter = new GridAdapter(data,getActivity());


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gv.setAdapter(gridAdapter);

            }
        });

    }


}
