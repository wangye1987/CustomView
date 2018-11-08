package menu.aprivate.com.kitchenmenu;

import java.io.Serializable;

/**
 * Created by wangkui on 2018/11/5.
 */

public class ImageBean implements Serializable {
    //图片id
    private int imgaeId;
    //图片名称
    private String imgaeTitle;
    //图片高度
    private int imageHeight;
    //图片宽度
    private int imageWidth;

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImgaeId() {
        return imgaeId;
    }

    public void setImgaeId(int imgaeId) {
        this.imgaeId = imgaeId;
    }

    public String getImgaeTitle() {
        return imgaeTitle;
    }

    public void setImgaeTitle(String imgaeTitle) {
        this.imgaeTitle = imgaeTitle;
    }
}
