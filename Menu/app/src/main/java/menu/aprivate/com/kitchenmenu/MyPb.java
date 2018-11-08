package menu.aprivate.com.kitchenmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wangkui on 2018/11/8.
 */

public class MyPb extends View {

    private float radius, cx, cy;
    private Paint paint;
    private float sweepAngle;// 旋转角度

    public MyPb(Context context) {
        super(context, null);
    }

    public MyPb(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义的属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyPb);

        // 获取颜色
        int color = a.getColor(R.styleable.MyPb_circle_color, Color.BLACK);// 获取不到给默认值
        radius = a.getDimension(R.styleable.MyPb_circle_radius, 20);
        cx = a.getDimension(R.styleable.MyPb_circle_x, 100);
        cy = a.getDimension(R.styleable.MyPb_circle_y, 100);

        // 需要回收
        a.recycle();

        paint = new Paint();
        paint.setAntiAlias(true);// 抗锯齿
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);// 空心

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (sweepAngle > 360) {
                    return;
                }
                sweepAngle += 1;
                postInvalidate();
            }
        }, 1000, 20);// 每隔20毫秒执行一次

    }

    @Override
    protected void onDraw(Canvas canvas) {
        radius = getWidth()/4;
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        canvas.drawCircle(getWidth()/2, getHeight()/2, getHeight()/4, paint);// 画圆
        paint.setStrokeWidth(10);// 粗细
        // 画运动的轨迹
        paint.setColor(Color.RED);
        // 上下左右与圆重合，左边为圆心的横坐标减去半径，上边为纵坐标减去半径,以此类推
        RectF rectF = new RectF(getWidth()/2-radius, getHeight()/2 - radius, getWidth()/2 + radius, getHeight()/2  + radius);
        // 起始角度，旋转角度，第三个属性为是否填充，画笔
        canvas.drawArc(rectF, -90, sweepAngle, false, paint);

        // 绘制文字
        int progress = (int) (sweepAngle / 360f * 100);
        paint.setTextSize(15);
        paint.setStrokeWidth(0);
        paint.setColor(Color.BLACK);
        canvas.drawText(progress + "%", getWidth()/2 - 10, getHeight()/2, paint);
    }
}