package wangyuhang.bwie.com.jd_imitate.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fynn.fluidlayout.FluidLayout;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.adapter.ListView_Lisi;
import wangyuhang.bwie.com.jd_imitate.adapter.MyApdater3;
import wangyuhang.bwie.com.jd_imitate.base.BaseActivity;
import wangyuhang.bwie.com.jd_imitate.bean.ShowBean;
import wangyuhang.bwie.com.jd_imitate.presenter.ShowPrensenterSou;
import wangyuhang.bwie.com.jd_imitate.sql.SqlBean;
import wangyuhang.bwie.com.jd_imitate.sql.SqlDao;
import wangyuhang.bwie.com.jd_imitate.view.ShowViewSou;

import static wangyuhang.bwie.com.jd_imitate.R.id.rl;

public class Sou extends  BaseActivity {
    String[] arrs={"铝合金门窗","罗汉果茶","容声冰箱","小米音响","魔术头巾","哑铃","吸尘器干湿两用"};
    private FluidLayout fluid;
    private EditText editText;
    private SqlDao sqlDao;
    private ListView listView;
    private TextView textView;
    private ListView_Lisi listView_lisi;

    @Override
    public int getLayout() {
        return R.layout.activity_sou;
    }

    @Override
    public ShowPrensenterSou getPresenter() {
        return null;
    }

    @Override
    public void initView() {
        sqlDao = new SqlDao(Sou.this);
        fluid = (FluidLayout) findViewById(R.id.fluid);
        editText = (EditText) findViewById(R.id.et_sou);
        listView = (ListView) findViewById(R.id.lv_lisi);
        textView = (TextView) findViewById(R.id.tv_clear);
        for (int x = 0; x < arrs.length; x++) {
            Button tv = new Button(this);
            tv.setText(arrs[x]);
            tv.setTextSize(16);
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(3, 3, 3, 3);
            fluid.addView(tv, params);
        }
        final List<SqlBean> sele = sqlDao.sele();
        Log.i("+++++++++++",sele.size()+"");
        listView_lisi = new ListView_Lisi(sele,Sou.this);
        listView.setAdapter(listView_lisi);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDao.delete();
                List<SqlBean> sele1 = sqlDao.sele();
                listView_lisi = new ListView_Lisi(sele1,Sou.this);
                listView.setAdapter(listView_lisi);
                listView_lisi.notifyDataSetChanged();
            }
        });
    }

    public void shousuo(View view) {
        String s = editText.getText().toString();
        Log.i("------------>",s+"asddas");
        sqlDao.add(s);
        List<SqlBean> sele1 = sqlDao.sele();
        listView_lisi = new ListView_Lisi(sele1,Sou.this);
        listView.setAdapter(listView_lisi);
        listView_lisi.notifyDataSetChanged();
        Intent intent = new Intent(Sou.this,SouShuoActivity.class);
        intent.putExtra("et",s);
        startActivity(intent);

    }

    @Override
    public void getData() {
        super.getData();

    }
}
