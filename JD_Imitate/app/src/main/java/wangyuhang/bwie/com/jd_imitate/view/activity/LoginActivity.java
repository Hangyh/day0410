package wangyuhang.bwie.com.jd_imitate.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.base.BaseActivity;
import wangyuhang.bwie.com.jd_imitate.bean.LoginBean;
import wangyuhang.bwie.com.jd_imitate.presenter.LoginPresenter;
import wangyuhang.bwie.com.jd_imitate.view.LoginView;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    private EditText et_tel,et_pwd;
    private Button bt_login,bt_regis;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    public static final String a = "/^(?=.*[a-zA-Z]+)(?=.*[0-9]+)[a-zA-Z0-9]+$/";
    @Override
    public int getLayout() {
        return R.layout.loginactivity;
    }

    @Override
    public LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    public void initView() {

        et_tel = (EditText) findViewById(R.id.et_tel);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_regis = (Button) findViewById(R.id.bt_regis);
        bt_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, RegisActivity.class));

            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = et_tel.getText().toString();
                String s1 = et_pwd.getText().toString();
                if (s==null&&s.length()==0){
                    et_tel.setError("用户名不能为空");
                }else if (s.length()<11){
                    et_tel.setError("不能小于11位");
                }

                if (TextUtils.isEmpty(s1)){
                    et_pwd.setError("密码不能为空");
                }else if (s1.length()<6) {
                    et_pwd.setError("密码不能小于6位");
                }
                else {
                    presenter.loginP(s,s1);
                }
                if (isPassword(s1)){
                    et_pwd.setError("不能有特殊字符");
                }
            }
        });
    }



    @Override
    public Context context() {
        return LoginActivity.this;
    }

    @Override
    public void loginFail2() {

    }

    @Override
    public void loginSuccess2(LoginBean loginBean) {

        Log.i("sss",loginBean.getMsg());
        Toast.makeText(LoginActivity.this, ""+loginBean.getMsg(), Toast.LENGTH_SHORT).show();

        sharedPreferences = getSharedPreferences("sp",0);
        edit = sharedPreferences.edit();
        edit.putInt("uid",loginBean.getData().getUid());
        edit.putString("token",loginBean.getData().getToken());
        edit.putString("uname",loginBean.getData().getNickname()+"");
        Log.i("TAG",loginBean.getData().getUid()+"");
        Log.i("TAG",loginBean.getData().getToken()+"");
        edit.commit();
        startActivity(new Intent(LoginActivity.this, AllActivity.class));
        Toast.makeText(this, ""+loginBean.getMsg(), Toast.LENGTH_SHORT).show();
//        finish();
    }
    public static boolean isPassword(String password) {
        return Pattern.matches(a, password);
    }

}
