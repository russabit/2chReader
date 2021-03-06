package com.example.mitchrv.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mitchrv.R;

import java.util.ArrayList;

import timber.log.Timber;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String> mComments;
    private ArrayList<String> mImages;
    private Context mContext;

    private ThreadsRecyclerViewAdapter.OnViewListener mOnViewListener;

    public PostsRecyclerViewAdapter(Context mContext,
                                    ArrayList<String> mComments,
                                    ArrayList<String> mImages,
                                    ThreadsRecyclerViewAdapter.OnViewListener mOnViewListener) {
        this.mContext = mContext;
        this.mComments = mComments;
        this.mImages = mImages;
        this.mOnViewListener = mOnViewListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mImages.get(position)!=null) return R.layout.layout_listitem_msg_img;
        else return R.layout.layout_listitem_msg;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final RecyclerView.ViewHolder viewHolder;
        View view;

        Timber.d("onCreateViewHolder: called.");

        if (viewType == R.layout.layout_listitem_msg_img) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_listitem_msg_img, parent, false);
            viewHolder = new ImageViewHolder(view, mOnViewListener);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_listitem_msg, parent, false);
            viewHolder = new ViewHolder(view, mOnViewListener);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder,
                                 final int position) {
        Timber.d("onBindViewHolder: called.");

        if (viewHolder instanceof ImageViewHolder) {
        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(((ImageViewHolder) viewHolder).image);

        ((ImageViewHolder) viewHolder).comment.setText(Html.fromHtml(mComments.get(position)));
        }
        else if (viewHolder instanceof ViewHolder) {
            ((ViewHolder) viewHolder).comment.setText(Html.fromHtml(mComments.get(position)));
        }

    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView comment;
        RelativeLayout parentLayout;

        ThreadsRecyclerViewAdapter.OnViewListener onViewListener;

        public ViewHolder(@NonNull View itemView,
                          ThreadsRecyclerViewAdapter.OnViewListener onViewListener) {
            super(itemView);
            comment = itemView.findViewById(R.id.comment_msg);
            parentLayout = itemView.findViewById(R.id.msg_layout);
            this.onViewListener = onViewListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onViewListener.onViewClick(getAdapterPosition());
        }
    }

    //Another ViewHolder for OP post and posts with image
    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView comment;
        ConstraintLayout parentLayout;

        ThreadsRecyclerViewAdapter.OnViewListener onViewListener;

        ImageViewHolder(@NonNull View itemView, ThreadsRecyclerViewAdapter.OnViewListener onViewListener) {
            super(itemView);
            image = itemView.findViewById(R.id.image_msg);
            comment = itemView.findViewById(R.id.comment_msg_img);
            parentLayout = itemView.findViewById(R.id.msg_img_layout);
            this.onViewListener = onViewListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onViewListener.onViewClick(getAdapterPosition());
        }
    }

    //better way to implement onClick:

    public interface OnMessageListener {
        void onViewClick(int position);
    }
}
