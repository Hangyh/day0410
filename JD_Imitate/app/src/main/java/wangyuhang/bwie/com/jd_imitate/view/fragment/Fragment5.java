package wangyuhang.bwie.com.jd_imitate.view.fragment;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.base.BaseFragment;
import wangyuhang.bwie.com.jd_imitate.bean.UserBean;
import wangyuhang.bwie.com.jd_imitate.bean.XiuGaiBean;
import wangyuhang.bwie.com.jd_imitate.presenter.UpdatePresenter;
import wangyuhang.bwie.com.jd_imitate.presenter.UsetPrensenter;
import wangyuhang.bwie.com.jd_imitate.view.UpdateView;
import wangyuhang.bwie.com.jd_imitate.view.UserView;
import wangyuhang.bwie.com.jd_imitate.view.activity.LoginActivity;
import wangyuhang.bwie.com.jd_imitate.view.activity.SheZhiActivity;
import wangyuhang.bwie.com.jd_imitate.view.activity.UserActivity;

import static wangyuhang.bwie.com.jd_imitate.R.mipmap.s;

/**
 * Created by dell on 2018/3/14.
 */

public class Fragment5 extends Fragment {

    private TextView tv_login_regis,textView5;
    private UsetPrensenter usetPrensenter;
    private SharedPreferences sp;
    private int uid;
    private String token;
    //    private LinearLayout ll;
//    private FrameLayout fl;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment5,container,false);

        ImageView imageView = view.findViewById(R.id.iiv);
        textView5 = view.findViewById(R.id.textView5);
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), SheZhiActivity.class));
            }
        });
        sp = getActivity().getSharedPreferences("sp", 0);
        uid = sp.getInt("uid", 0);
        token = sp.getString("token", null);
        tv_login_regis = view.findViewById(R.id.tv_login_regis);
//        ll = view.findViewById(R.id.ll);
//        fl = view.findViewById(R.id.ll_replec);
        usetPrensenter = new UsetPrensenter(new UserView() {
            @Override
            public void userFailData() {

            }

            @Override
            public void userSuccessData(UserBean userBean) {
                Object nickname = userBean.getData().getNickname();
                tv_login_regis.setText(nickname+"");
            }

            @Override
            public Context context() {
                return getActivity();
            }
        });
        usetPrensenter.userP(uid);
        tv_login_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                startActivityForResult();
                startActivity(new Intent(getActivity(),LoginActivity.class));

            }
        });
        tv_login_regis.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
    //                Toast.makeText(getActivity(), "adadda", Toast.LENGTH_SHORT).show();
                final View view1 = View.inflate(getActivity(),R.layout.alert,null);
                 new AlertDialog.Builder(getActivity())
                    .setView(view1)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        EditText viewById = view1.findViewById(R.id.et_name);

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final String s = viewById.getText().toString();

                            UpdatePresenter updatePresenter = new UpdatePresenter(new UpdateView() {
                                @Override
                                public void xiugaiFail() {

                                }

                                @Override
                                public void xiugaiSuccess(XiuGaiBean xiuGaiBean) {

                                    if (xiuGaiBean.getCode().equals("0")){
                                        tv_login_regis.setText(s);
                                    }else{
                                        Toast.makeText(getActivity(), ""+xiuGaiBean.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public Context context() {
                                    return getActivity();
                                }
                            });
                            updatePresenter.updateP(uid +"",s, token);

                        }
                    })
                         .create()
                    .show();

//                final String uname2 = sp.getString("uname", null);
////                final View view2 = View.inflate(getActivity(),R.layout.alert,null);
//                tv_login_regis.setText(uname2);
                return true;
            }
            });
//
//                new AlertDialog.Builder(getActivity())
//                        .setTitle("修改昵称")
//                        .setView(view2)
//                .setPositiveButton("确定",new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        EditText viewById = view2.findViewById(R.id.et_name);
//
//                     final  String s2 = viewById.getText().toString();
//            UpdatePresenter presenter = new UpdatePresenter(new UpdateView() {
//                @Override
//                public void xiugaiFail() {
//
//                }
//
//                @Override
//                public void xiugaiSuccess(XiuGaiBean xiuGaiBean) {
//                    Toast.makeText(getActivity(), ""+xiuGaiBean.getMsg(), Toast.LENGTH_SHORT).show();
//                    Log.i("-----------",xiuGaiBean.getMsg());
//                    UsetPrensenter pre = new UsetPrensenter(new UserView() {
//                        @Override
//                        public void userFailData() {
//
//                        }
//
//                        @Override
//                        public void userSuccessData(UserBean userBean) {
//
//                        }
//
//                        @Override
//                        public Context context() {
//                            return getActivity();
//                        }
//                    });
//                    pre.userP(uid);
//                }
//
//                @Override
//                public Context context() {
//                    return getActivity();
//                }
//            });
//                presenter.updateP(uid+"",token,s2);
//                    }
//
//                })
//                .create()
//                .show();
//                return true;




//        SharedPreferences sp = getActivity().getSharedPreferences("sp", 0);
//        String uname = sp.getString("uname", null);
//        tv_login_regis.setText(uname);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserActivity.class));
            }
        });
        return view;

    }
}

