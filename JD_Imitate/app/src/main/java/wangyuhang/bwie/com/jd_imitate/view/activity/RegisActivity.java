package wangyuhang.bwie.com.jd_imitate.view.activity;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.base.BaseActivity;
import wangyuhang.bwie.com.jd_imitate.bean.RegisBean;
import wangyuhang.bwie.com.jd_imitate.presenter.Registor;
import wangyuhang.bwie.com.jd_imitate.view.RegistorIvew;

/**
 * Created by dell on 2018/3/17.
 */

public class RegisActivity extends BaseActivity<Registor> implements RegistorIvew {

    private EditText et_tel,et_pwd;
    private Button button;
    @Override
    public int getLayout() {
        return R.layout.registor;
    }

    @Override
    public Registor getPresenter() {
        return new Registor(this);
    }


    @Override
    public void initView() {
        et_tel = (EditText) findViewById(R.id.reg_tel);
        et_pwd = (EditText) findViewById(R.id.reg_pwd);
        button = (Button) findViewById(R.id.reg_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = et_tel.getText().toString();
                String s1 = et_pwd.getText().toString();
                presenter.regisP(s,s1);
            }
        });
    }



    @Override
    public Context context() {
        return RegisActivity.this;
    }

    @Override
    public void regisFail2() {

    }

    @Override
    public void regisSuccess2(RegisBean regisBean) {
        Toast.makeText(this, ""+regisBean.getMsg(), Toast.LENGTH_SHORT).show();
        finish();
    }
}
