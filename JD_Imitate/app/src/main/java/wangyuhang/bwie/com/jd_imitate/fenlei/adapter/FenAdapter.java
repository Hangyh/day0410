package wangyuhang.bwie.com.jd_imitate.fenlei.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.fenlei.bean.FenLei;

import static wangyuhang.bwie.com.jd_imitate.R.id.i;


/**
 * Created by lenovo on 2018/3/9.
 */

public class FenAdapter extends RecyclerView.Adapter<FenAdapter.FenViewHodler> {
    Context context;
    List<FenLei.DataBean> data;
//    private FenLei fenLei;
    private View inflate;
    private int thisPosition;
    //再定义一个int类型的返回值方法
    public int getthisPosition() {
        return thisPosition;
    }
    public void setThisPosition(int thisPosition) {
        this.thisPosition = thisPosition;
    }
//    private List<Boolean> isClicks;
    public FenAdapter(Context context, List<FenLei.DataBean> data) {
        this.context = context;
        this.data = data;
//        isClicks = new ArrayList<>();
//        for(int i = 0;i<data.size();i++){
//            isClicks.add(false);
//        }
    }

    @Override
    public FenViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.fenleiitem1, parent, false);
        FenViewHodler fenViewHodler=new FenViewHodler(inflate);

        return fenViewHodler;
    }
//    public void setfenLei(List<FenLei.DataBean> datas){
//            data = datas;
//    }
    @Override
    public void onBindViewHolder(FenViewHodler holder, int position) {
        FenLei.DataBean dataBean = data.get(position);
        String name = dataBean.getName();
        final int cid = dataBean.getCid();
        holder.textView.setText(name);
//        fenLei  = data.get(position);
//        if(){
//
//            holder.textView.setTextColor(Color.parseColor("#f00f00"));
//        }else{
//            holder.textView.setTextColor(Color.parseColor("#000000"));
//        }
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(danJi!=null){
                danJi.OnSuccess(cid);

            }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FenViewHodler extends RecyclerView.ViewHolder{


        public final TextView textView;

        public FenViewHodler(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.fltext);
        }
    }
    public interface DanJi{

        void OnSuccess(int cid);

    }
    DanJi danJi;
    public void getDanJi(DanJi danJi){
        this.danJi=danJi;
    }

}
