package com.example.mitchrv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mitchrv.R;

import java.util.ArrayList;

import timber.log.Timber;

public class ThreadsRecyclerViewAdapter extends RecyclerView.Adapter<ThreadsRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mImageNames;
    private ArrayList<String> mImages;
    private Context mContext;

    private OnViewListener mOnViewListener;

    public ThreadsRecyclerViewAdapter(Context mContext, ArrayList<String> mImageNames, ArrayList<String> mImages, OnViewListener mOnViewListener) {
        this.mContext = mContext;
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mOnViewListener = mOnViewListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Timber.d("onCreateViewHolder: called.");
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
            return new ViewHolder(view, mOnViewListener);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Timber.d("onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.imageName.setText(mImageNames.get(position));

    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView imageName;
        RelativeLayout parentLayout;

        OnViewListener onViewListener;

        public ViewHolder(@NonNull View itemView, OnViewListener onViewListener) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            this.onViewListener = onViewListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onViewListener.onViewClick(getAdapterPosition());
        }
    }

    //better way to implement onClick:

    public interface OnViewListener {
        void onViewClick(int position);
    }
}
