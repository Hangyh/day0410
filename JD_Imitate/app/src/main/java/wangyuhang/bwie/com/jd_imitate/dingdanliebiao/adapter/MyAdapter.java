package wangyuhang.bwie.com.jd_imitate.dingdanliebiao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.dingdanliebiao.bean.DingDanBean;

/**
 * Created by dell on 2018/4/13.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    List<DingDanBean.DataBean> list;
    Context context;

    public MyAdapter(List<DingDanBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.dingdanitem,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String createtime = list.get(position).getCreatetime();
        double price = list.get(position).getPrice();
        String title = list.get(position).getTitle();

        holder.textView1.setText(title);
        holder.textView2.setText("￥:"+price);
        holder.textView3.setText(createtime);
        holder.simpleDraweeView.setImageResource(R.mipmap.ic_launcher);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView simpleDraweeView;
        TextView textView1,textView2,textView3;
        public MyViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.dingdan_sdv);
            textView1 = itemView.findViewById(R.id.dingdan_title);
            textView2 = itemView.findViewById(R.id.dingdan_price);
            textView3 = itemView.findViewById(R.id.dingdan_data);
        }
    }
}
