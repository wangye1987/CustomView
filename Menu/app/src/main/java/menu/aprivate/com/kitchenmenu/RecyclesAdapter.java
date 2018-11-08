package menu.aprivate.com.kitchenmenu;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wangkui on 2018/11/5.
 */

public class RecyclesAdapter extends RecyclerView.Adapter<RecyclesAdapter.ViewHolder> {

    ArrayList<ImageBean> mImagelist;
    Context mContext;

    private ViewHolder mViewHolder;
    private View mView;

    RecyclesAdapter(Context mContext, ArrayList mImagelist){
               this.mContext = mContext;
               this.mImagelist = mImagelist;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.kitchen_item,null);
        mViewHolder = new ViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageBean bean = mImagelist.get(position);
        holder.image_id.setImageResource(bean.getImgaeId());
        holder.image_head.setImageResource(bean.getImgaeId());
        holder.image_title.setText(bean.getImgaeTitle());
        float screenWidth = ((Activity) holder.image_id.getContext()).getWindowManager().getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams params = holder.image_id.getLayoutParams();
        params.width = (int) (screenWidth/2-20);
        params.height = bean.getImageHeight();
        holder.image_id.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return mImagelist.size();
    }

     static class ViewHolder extends RecyclerView.ViewHolder{
         private  ImageView image_id;
         private  CricleImageView image_head;
         private  TextView image_title;
        public ViewHolder(View itemView) {
            super(itemView);
            image_id = itemView.findViewById(R.id.image_id);
            image_head = itemView.findViewById(R.id.image_head);
            image_title = itemView.findViewById(R.id.image_title);
        }

    }
}
