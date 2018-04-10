package wangyuhang.bwie.com.jd_imitate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.bean.ZiLeiBean;


import static android.R.attr.name;

/**
 * Created by dell on 2018/4/2.
 */

public class MyAdaptera_rl extends RecyclerView.Adapter<MyAdaptera_rl.MyVIewHodler> {

    List<ZiLeiBean.DataBean.ListBean> list;
    Context context;
    private View inflate;
    public MyAdaptera_rl(List<ZiLeiBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyVIewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.erjiliebaiitem3, parent, false);
        MyVIewHodler myVIewHodler=new MyVIewHodler(inflate);
        return myVIewHodler;
    }

    @Override
    public void onBindViewHolder(MyVIewHodler holder, int position) {
        ZiLeiBean.DataBean.ListBean listBean = list.get(position);
        String name = listBean.getName();
        String icon = listBean.getIcon();
        Glide.with(context).load(icon).into(holder.imageView);
        holder.textView.setText(name);
        final int pscid = listBean.getPscid();

        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setCid!=null){
//                    MyAdaptera_rl.setCid.OnSuccess(pscid);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyVIewHodler extends  RecyclerView.ViewHolder{

        public final ImageView imageView;
        public final TextView textView;

        public MyVIewHodler(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imgg);
            textView = (TextView) itemView.findViewById(R.id.textt);
        }
    }

    public interface setCid{

        void OnSuccess(int pscid);
    }
    setCid setCid;
    public void getCidListener(setCid setCid){
        this.setCid=setCid;
    }
}
