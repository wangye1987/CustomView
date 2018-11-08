package menu.aprivate.com.kitchenmenu;

import android.graphics.Paint;

import java.io.Serializable;

/**
 * Created by wangkui on 2018/11/7.
 */

public class WaterBean implements Serializable {
    float radius;

    float x;

    float y;

    Paint p;

    //默认透明度是255
    int mAlpha;

    public int getmAlpha() {
        return mAlpha;
    }

    public void setmAlpha(int mAlpha) {
        this.mAlpha = mAlpha;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Paint getP() {
        return p;
    }

    public void setP(Paint p) {
        this.p = p;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
