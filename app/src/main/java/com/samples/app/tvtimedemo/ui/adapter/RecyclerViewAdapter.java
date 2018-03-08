package com.samples.app.tvtimedemo.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.samples.app.tvtimedemo.R;
import com.samples.app.tvtimedemo.util.ImageLoader;
import com.samples.app.tvtimedemo.vo.TVShow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by $Abed Elaziz Shehadeh on 21, January, 2018
 * elaziz.shehadeh@gmail.com
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<TVShow> data = new ArrayList<>();
    private ImageLoader imageLoader;

    private OnItemClicked onItemClickedListener;

    public RecyclerViewAdapter(List<TVShow> data, Context context, OnItemClicked onItemClickedListener) {
        this.data = data;
        this.onItemClickedListener = onItemClickedListener;
        imageLoader = new ImageLoader(context, R.drawable.placeholder);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_layout,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(data.get(position));
        holder.itemView.setOnClickListener(view -> onItemClickedListener.onItemClickedListener(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        ImageView imageView;

        @BindView(R.id.titleTextView)
        TextView titleTextView;

        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }

        public void bind(TVShow tvShow) {
            imageLoader.loadImage(tvShow.getBackdropPath(), imageView, new RequestListener<String, Bitmap>() {

                @Override
                public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    progressBar.setVisibility(View.GONE);
                    return false;
                }
            });
            titleTextView.setText(tvShow.getOriginalName());
        }
    }

    public interface OnItemClicked{

        void onItemClickedListener(int position);
    }
}
