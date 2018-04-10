package wangyuhang.bwie.com.jd_imitate.view.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.base.BaseFragment;
import wangyuhang.bwie.com.jd_imitate.view.activity.LoginActivity;

/**
 * Created by dell on 2018/3/14.
 */

public class Fragment5 extends Fragment{

    private TextView tv_login_regis;
//    private LinearLayout ll;
//    private FrameLayout fl;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment5,container,false);



        tv_login_regis = view.findViewById(R.id.tv_login_regis);
//        ll = view.findViewById(R.id.ll);
//        fl = view.findViewById(R.id.ll_replec);
        tv_login_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                startActivityForResult();
                startActivity(new Intent(getActivity(),LoginActivity.class));

            }
        });


        return view;

    }
}

