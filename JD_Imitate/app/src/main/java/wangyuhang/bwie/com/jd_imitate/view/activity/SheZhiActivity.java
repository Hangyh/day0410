package wangyuhang.bwie.com.jd_imitate.view.activity;

import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.adddizi.bean.AddBean;
import wangyuhang.bwie.com.jd_imitate.adddizi.presenter.AddPresenter;
import wangyuhang.bwie.com.jd_imitate.adddizi.view.AddView;
import wangyuhang.bwie.com.jd_imitate.base.BaseActivity;
import wangyuhang.bwie.com.jd_imitate.dizi.adapter.MyAdapter;
import wangyuhang.bwie.com.jd_imitate.dizi.bean.DiziBean;
import wangyuhang.bwie.com.jd_imitate.dizi.presenter.ZhanshiDiziPresenter;
import wangyuhang.bwie.com.jd_imitate.dizi.view.ZhanshiDiziView;

public class SheZhiActivity extends BaseActivity<ZhanshiDiziPresenter> implements ZhanshiDiziView {

    ListView listView;
    private MyAdapter myAdapter;
    private List<DiziBean.DataBean> data;

    @Override
    public Context context() {
        return SheZhiActivity.this;
    }

    @Override
    public void zhanshiFail() {

    }

    @Override
    public void zhanshiSuccess(DiziBean diziBean) {
        data = diziBean.getData();
        myAdapter = new MyAdapter(data,SheZhiActivity.this);
        listView.setAdapter(myAdapter);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_she_zhi;
    }

    @Override
    public ZhanshiDiziPresenter getPresenter() {
        return new ZhanshiDiziPresenter(this);
    }

    @Override
    public void initView() {
        listView = (ListView) findViewById(R.id.lv_shouhuo);
        presenter.zhanshiP(12574+"");

    }
    public void add(View view){
        View view1 = View.inflate(SheZhiActivity.this,R.layout.addview,null);
        final EditText editText1 = view1.findViewById(R.id.et_addr);
        final EditText editText2 = view1.findViewById(R.id.et_name2);
        final EditText editText3 = view1.findViewById(R.id.et_mobile);
        new AlertDialog.Builder(SheZhiActivity.this)
                .setTitle("添加收货地址")
                .setView(view1)
                .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s1 = editText1.getText().toString();
                        String s2 = editText2.getText().toString();
                        String s3 = editText3.getText().toString();
                        Log.i("aaaaaaa",s1+s2+s3+"");
                        AddPresenter addPresenter = new AddPresenter(new AddView() {
                            @Override
                            public void adddiziFail() {

                            }

                            @Override
                            public void adddiziSuccess(AddBean addBean) {

                                Toast.makeText(SheZhiActivity.this, ""+addBean.getMsg(), Toast.LENGTH_SHORT).show();
//                                Log.e("------------",addBean.getMsg());
//                                myAdapter.notifyDataSetChanged();
                                presenter.zhanshiP("12574");
                            }

                            @Override
                            public Context context() {
                                return SheZhiActivity.this;
                            }
                        });
                        addPresenter.addP("12574",s1,s3,s2);
                    }
                })
                .create()
                .show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                https://www.zhaoapi.cn/user/setAddr?uid=71&addrid=3&status=1
                Toast.makeText(SheZhiActivity.this, "asdddadsasf", Toast.LENGTH_SHORT).show();
//                Log.i("qqq","dsaasdasdasdasdadsas");
//                int addrid = data.get(position).getAddrid();
//
//                OkHttpClient okHttpClient = new OkHttpClient();
//                Request request = new Request.Builder()
//                        .url("https://www.zhaoapi.cn/user/setAddr?uid=12574&addrid="+addrid)
//                        .build();
//                okHttpClient.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        Log.e("erro",e.getMessage());
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        Log.i("Tag",response.body().string());
//                    }
//                });
            }
        });
    }
}
