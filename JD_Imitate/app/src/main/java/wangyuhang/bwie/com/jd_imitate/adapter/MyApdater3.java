package wangyuhang.bwie.com.jd_imitate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.bean.ShowBean;


/**
 * Created by dell on 2018/3/17.
 */

public class MyApdater3 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int TYPE_PULL_IMAGE = 0;
    public static final int TYPE_RIGHT_IMAGE = 1;
    public static final int TYPE_THREE_IMAGE = 2;
    List<ShowBean.DataBean> list;
    Context context;

    public MyApdater3(List<ShowBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType==TYPE_PULL_IMAGE){
            view = LayoutInflater.from(context).inflate(R.layout.ritem,parent,false);

            return new MyViewHoler1(view);
        }else if (viewType==TYPE_RIGHT_IMAGE){
            view = LayoutInflater.from(context).inflate(R.layout.ritem2,parent,false);

            return new MyViewHoler2(view);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.ritem3,parent,false);

            return new MyViewHoler3(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyViewHoler1){
            MyViewHoler1 holder1  = (MyViewHoler1) holder;
            holder1.textView1.setText(list.get(position).getTitle());
            holder1.textView2.setText("￥:"+list.get(position).getPrice());
            String images = list.get(position).getImages();

            String[] split = images.split("\\|");

            for (int i  = 0 ;i<split.length;i++){
                Log.i("TAG",split[i]);
                Picasso.with(context).load(split[i]).into(holder1.imageView);
            }
        }else if (holder instanceof MyViewHoler2){

            MyViewHoler2 holer2 = (MyViewHoler2) holder;
            holer2.textView1.setText(list.get(position).getTitle());
            holer2.textView2.setText("￥:"+list.get(position).getPrice());
            String images = list.get(position).getImages();

            String[] split = images.split("\\|");

            for (int i  = 0 ;i<split.length;i++){
//                Log.i("TAG",split[i]);
                Picasso.with(context).load(split[0]).into(holer2.imageView);
                Picasso.with(context).load(split[1]).into(holer2.imageView2);

            }
        }else if(holder instanceof MyViewHoler3){
            MyViewHoler3 holer3 = (MyViewHoler3) holder;
            holer3.textView.setText(list.get(position).getTitle());
            holer3.textView2.setText("￥:"+list.get(position).getPrice());
            String images = list.get(position).getImages();

            String[] split = images.split("\\|");

            for (int i  = 0 ;i<split.length;i++){
//                Log.i("TAG",split[i]);
                Picasso.with(context).load(split[0]).into(holer3.imageView);
                Picasso.with(context).load(split[1]).into(holer3.imageView2);
                Picasso.with(context).load(split[2]).into(holer3.imageView3);

            }
        }

    }

    @Override
    public int getItemViewType(int position) {

        ShowBean.DataBean dataBean = list.get(position);
        int itemtype = dataBean.getItemtype();

        if (itemtype==0){
           return TYPE_PULL_IMAGE;
        }else if (itemtype==1){
            return TYPE_RIGHT_IMAGE;
        }else {
            return TYPE_THREE_IMAGE;
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHoler1 extends RecyclerView.ViewHolder{

        TextView textView1,textView2;
        ImageView imageView;

        public MyViewHoler1(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.rl_tv);
            textView2 = itemView.findViewById(R.id.rl_price1);
            imageView = itemView.findViewById(R.id.rl_iv);
        }
    }

    public class MyViewHoler2 extends RecyclerView.ViewHolder{

        TextView textView1,textView2;
        ImageView imageView,imageView2;

        public MyViewHoler2(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.rl_tv2);
            textView2 = itemView.findViewById(R.id.rl_price2);
            imageView = itemView.findViewById(R.id.rl_iv2);
            imageView2 = itemView.findViewById(R.id.rl_iv22);
        }
    }

    public class MyViewHoler3 extends RecyclerView.ViewHolder{

        TextView textView,textView2;
        ImageView imageView,imageView2,imageView3;

        public MyViewHoler3(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.rl_tv3);
            textView2 = itemView.findViewById(R.id.rl_price3);
            imageView = itemView.findViewById(R.id.rl_iv3);
            imageView2 = itemView.findViewById(R.id.rl_iv33);
            imageView3 = itemView.findViewById(R.id.rl_iv333);
        }
    }
}
