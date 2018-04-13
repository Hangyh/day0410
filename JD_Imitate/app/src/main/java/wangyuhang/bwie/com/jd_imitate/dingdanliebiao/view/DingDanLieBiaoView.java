package wangyuhang.bwie.com.jd_imitate.dingdanliebiao.view;

import wangyuhang.bwie.com.jd_imitate.base.BaseView;
import wangyuhang.bwie.com.jd_imitate.dingdanliebiao.bean.DingDanBean;

/**
 * Created by dell on 2018/4/13.
 */

public interface DingDanLieBiaoView extends BaseView{


    void dingdanliebiaoFail();

    void dingdanliebiaoSuccess(DingDanBean dingDanBean);
}
