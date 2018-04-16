package wangyuhang.bwie.com.jd_imitate.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.xys.libzxing.zxing.activity.CaptureActivity;

import wangyuhang.bwie.com.jd_imitate.R;

/**
 * Created by dell on 2018/4/9.
 */

public class ShouYe_SouSuo_view extends RelativeLayout {

    final  int REQUEST_CODE = 8888;
    //    public ShouYe_SouSuo_view(Context context, AttributeSet attrs) {
    //        super(context, attrs);
    //    }
    //15:05
    public ShouYe_SouSuo_view(Context context) {
        this(context, null);
}

    public ShouYe_SouSuo_view(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShouYe_SouSuo_view(final Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View view = View.inflate(context, R.layout.sousuo, this);
        ImageView saoyisao = view.findViewById(R.id.saoyisao);
        saoyisao.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"sss",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                context.startActivity(intent);
            }
        });

    }

}