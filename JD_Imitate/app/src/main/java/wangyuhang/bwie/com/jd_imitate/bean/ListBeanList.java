package wangyuhang.bwie.com.jd_imitate.bean;

/**
 * Created by dell on 2018/3/14.
 */

public class ListBeanList {

    private String detailUrl;
    private String images;
    private String subhead;

    public ListBeanList(String detailUrl, String images, String subhead) {
        this.detailUrl = detailUrl;
        this.images = images;
        this.subhead = subhead;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getSubhead() {
        return subhead;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }
}
