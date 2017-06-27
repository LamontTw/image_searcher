package com.tw2416.gogolook;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.tw2416.gogolook.IImage.IImageData;


/**
 * Created by Lamont on 2017/6/26.
 */

public class ImageHolder extends RecyclerView.ViewHolder{

    public static ImageHolder generateImageHolder(@NonNull ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_holder, parent, false);
        return new ImageHolder(itemView);
    }

    public final ImageView cardImageView;

    private String mImgUrl = null;

    public ImageHolder(View itemView) {
        super(itemView);
        cardImageView = (ImageView) itemView.findViewById(R.id.card_image);
    }

    public void bindView(IImageData image) {
        if (image == null) {
            return;
        }

        mImgUrl = image.getUrl();
        cardImageView.setImageResource(R.drawable.loading);

        Glide.with(cardImageView.getContext())
               .load(new GlideUrl(mImgUrl))
               .into(cardImageView);
    }
}
