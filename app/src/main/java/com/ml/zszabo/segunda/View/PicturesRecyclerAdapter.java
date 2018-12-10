package com.ml.zszabo.segunda.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ml.zszabo.segunda.Model.Picture;

import java.util.List;

public class PicturesRecyclerAdapter extends RecyclerView.Adapter<PicturesRecyclerAdapter.PictureViewHolder> {

    private List<Picture> pictures;
    private Context context;

    public PicturesRecyclerAdapter(Context context, List<Picture> pictures) {
        this.pictures = pictures;
        this.context = context;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout ll = new LinearLayout(context);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(layoutParams);
        return new PictureViewHolder(ll);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder viewHolder, int i) {
        viewHolder.draweeView.setImageURI(pictures.get(i).getSecureUrl());
    }

    @Override
    public int getItemCount() {
        return pictures == null ? 0 : pictures.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView draweeView;

        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);
            draweeView = new SimpleDraweeView(context);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            draweeView.setLayoutParams(layoutParams);
            LinearLayout linearLayout=(LinearLayout)itemView;
            linearLayout.addView(draweeView);
        }
    }
}
