package menu.aprivate.com.kitchenmenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.logging.LogRecord;

/**
 * Created by wangkui on 2018/11/7.
 * 水波纹试图
 */

public class WaterView extends View {

    private Paint mPaint;

    public WaterView(Context context) {
        super(context);
    }

    public WaterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        list = new ArrayList();
    }



    public WaterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    ArrayList<WaterBean> list ;
    float x;
    float y;
    float radius = 5;
    boolean isTrue;

    int ids[] = new int[]{R.color.colorPrimary,R.color.colorPrimaryDark,R.color.colorAccent,R.color.colorRound1,R.color.colorRound2,R.color.colorRound3};
    //获取手指点击位置
    @SuppressLint("ResourceAsColor")
    private void initData(float x, float y) {

            WaterBean mWaterBean = new WaterBean();
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(1);
            mWaterBean.setP(mPaint);
            mWaterBean.setX(x);
            mWaterBean.setY(y);
            mWaterBean.setmAlpha(255);
            list.add(mWaterBean);
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            flush();
            invalidate();
            if(isTrue)
                mHandler.sendEmptyMessage(0);
        }
    };

    //刷新界面
    private void flush(){
        for(int i=0;i<list.size();i++){
            WaterBean mater  = list.get(i);
            mater.setRadius( mater.getRadius()+5);

            Paint mPaint = mater.getP();
            mPaint.setStrokeWidth(2);
            mPaint.setColor(ids[(int) (Math.random()*5)]);
            int mAlpha = mater.getmAlpha();
            if(mAlpha ==0){
                list.remove(i);
                continue;
            }
            mAlpha -=  5;
            if(mAlpha <5)
                mAlpha = 0;
            mPaint.setAlpha(mAlpha);
            list.get(i).setmAlpha(mAlpha);
            list.get(i).setP(mPaint);
            list.get(i).setRadius(mater.getRadius()+5);
        }
        if(list.size() ==0)
            isTrue = false;
    }
    public void AddPoint( float x, float y){
            // 初始化的时候
        if(list.size() == 0) {
            initData(x, y);
            isTrue = true;
            mHandler.sendEmptyMessage(0);
        }else{
                initData(x, y);
           }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            if(isTrue) {
                for(int i=0;i<list.size();i++){
                    canvas.drawCircle(list.get(i).getX(), list.get(i).getY(), list.get(i).getRadius(),   list.get(i).getP());
                }
                invalidate();
            }
    }
}
