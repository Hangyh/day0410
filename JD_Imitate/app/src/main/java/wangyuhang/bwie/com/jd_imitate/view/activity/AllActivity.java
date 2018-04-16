package wangyuhang.bwie.com.jd_imitate.view.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import wangyuhang.bwie.com.jd_imitate.R;
import wangyuhang.bwie.com.jd_imitate.view.fragment.Fragment1;
import wangyuhang.bwie.com.jd_imitate.view.fragment.Fragment2;
import wangyuhang.bwie.com.jd_imitate.view.fragment.Fragment3;
import wangyuhang.bwie.com.jd_imitate.view.fragment.Fragment4;
import wangyuhang.bwie.com.jd_imitate.view.fragment.Fragment5;



import static wangyuhang.bwie.com.jd_imitate.R.id.rg;

public class AllActivity extends AppCompatActivity {


    private ViewPager mVp;
    private RadioGroup mRg;
    private static final float DEFAULT_OFFSET = 0.5f;
    private float mInitLazyItemOffset = DEFAULT_OFFSET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        initView();
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //初始化底部标签
        mRg.check(rg);// 默认勾选首页，初始化时候让首页默认勾选

        final List<Fragment> list = new ArrayList<>();
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());
        list.add(new Fragment4());
        list.add(new Fragment5());


        mVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){

                    case R.id.rb1:
                        mVp.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        mVp.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        mVp.setCurrentItem(2);
                        break;
                    case R.id.rb4:
                        mVp.setCurrentItem(3);
                        break;
                    case R.id.rb5:
                        mVp.setCurrentItem(4);
                        break;
                }
            }
        });
        mVp.setOffscreenPageLimit(6);
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mRg = (RadioGroup) findViewById(rg);
    }
}
