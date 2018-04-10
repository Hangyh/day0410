package wangyuhang.bwie.com.jd_imitate.gouwuche.bean;

/**
 * Created by dell on 2018/4/9.
 */

public class MessageBean<T> {
    /**
     * msg : 请求成功
     * code : 0
     * data : []
     */

    private String msg;
    private String code;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
