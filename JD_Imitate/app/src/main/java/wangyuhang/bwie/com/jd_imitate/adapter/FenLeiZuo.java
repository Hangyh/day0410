package wangyuhang.bwie.com.jd_imitate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.bean.GridBean;

/**
 * Created by dell on 2018/3/14.
 */

public class FenLeiZuo extends RecyclerView.Adapter<FenLeiZuo.MyViewHolde>{

    Context context;
    List<GridBean.DataBean> list;

    public FenLeiZuo(Context context, List<GridBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.itemfenlei,parent,false);
        final MyViewHolde myViewHolde = new MyViewHolde(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int layoutPosition = myViewHolde.getLayoutPosition();
                onItemListenner2.ItemListner(layoutPosition);
            }
        });

        return myViewHolde;
    }

    @Override
    public void onBindViewHolder(MyViewHolde holder, int position) {
            holder.textView.setText(list.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolde extends RecyclerView.ViewHolder{

        TextView textView;

        public MyViewHolde(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.feilei_zuo);
        }
    }

    public interface onItemListenner{
        void ItemListner(int position);
    }
    public onItemListenner onItemListenner2;

    public void setOnItemListenner2(onItemListenner onItemListenner){
        onItemListenner2 = onItemListenner;
    }
}
