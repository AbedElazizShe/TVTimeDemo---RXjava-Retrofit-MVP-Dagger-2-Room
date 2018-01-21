package com.samples.app.tvtimedemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.samples.app.tvtimedemo.R;
import com.samples.app.tvtimedemo.mvp.model.Result;
import com.samples.app.tvtimedemo.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.samples.app.tvtimedemo.util.Constants.IMAGE_BASE_URL;

/**
 * Created by $Abed Elaziz Shehadeh on 21, January, 2018
 * elaziz.shehadeh@gmail.com
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Result> data = new ArrayList<>();
    private ImageLoader imageLoader;

    public RecyclerViewAdapter(List<Result> data, Context context) {
        this.data = data;
        imageLoader = new ImageLoader(context);

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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Result result) {
            String imageURL = IMAGE_BASE_URL + result.getBackdropPath();
            Log.wtf("path", result.getBackdropPath());
            imageLoader.loadImage(imageURL, imageView);
            titleTextView.setText(result.getOriginalName());
        }
    }
}
