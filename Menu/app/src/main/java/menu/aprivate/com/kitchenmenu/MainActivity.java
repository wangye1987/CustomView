package menu.aprivate.com.kitchenmenu;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by wangkui on 2018/11/5.
 */

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private Context mContex;
    private StaggeredGridLayoutManager gridLayoutManager;
    private int images[]  ;
    private ArrayList mImageList ;
    private ImageBean bean;
    private int imageIndex;
    private WaterView mWaterViewer;
    private float rawX;
    private float rawY;
    private float width;
    private float height;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setInitData();
    }

    /**
     * 初始化界面元素
     */
    private void initView() {
        mContex = MainActivity.this;
        mImageList = new ArrayList();
        recyclerView =  findViewById(R.id.recyclerView);
        mWaterViewer = findViewById(R.id.waterView);
        gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        RecyclesAdapter mRecyclesAdapter = new RecyclesAdapter(mContex,mImageList);
        gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.addItemDecoration(new SpaceItemDecoration(10));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mRecyclesAdapter);
        Point mPoint = new Point();
        getWindowManager().getDefaultDisplay().getSize(mPoint);
        width = mPoint.x;
        height = mPoint.y;
        mWaterViewer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        rawX = event.getRawX();
                        rawY = event.getRawY();

                        mWaterViewer.AddPoint(rawX, rawY);
                          break;
                }
                return true;
            }
        });
    }




        /**
     * 初始化数据
     * */
    private void setInitData(){

        Field[] fields = R.drawable.class.getDeclaredFields();
        for(int i = fields.length-1;i>-1;i--){
               if(fields[i].getName().startsWith("image_")){
                   try {
                       imageIndex = fields[i].getInt(R.drawable.class);
                       Drawable mDrawable =  getResources().getDrawable(imageIndex);
                       BitmapDrawable bd = (BitmapDrawable) mDrawable;
                       bean = new ImageBean();
                       bean.setImgaeId(imageIndex);
                       bean.setImgaeTitle("图片image_"+i);
                       bean.setImageWidth( bd.getBitmap().getWidth());
                       bean.setImageHeight( bd.getBitmap().getHeight());
                       mImageList.add(bean);
                   } catch (IllegalAccessException e) {
                       e.printStackTrace();
                   }
               }
        }
        for(int i=0;i<10;i++) {
            mImageList.addAll(mImageList);
        }
    }




}
