package com.denisbrandi.stargazers.databinding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by denis on 11/03/17.
 */

public class ImageBinding {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
    }

    @BindingAdapter({"imageUrl", "placeholder"})
    public static void setImageUrl(ImageView imageView, String imageUrl, Drawable placeholder) {
        Glide.with(imageView.getContext()).load(imageUrl).placeholder(placeholder).into(imageView);
    }
}
