package com.example.inpeace.slideshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.inpeace.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class slideshowAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    private int[] images = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.e};


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (RelativeLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_slideshow,container,false);

        ImageView img =  view.findViewById(R.id.imageSlide);
        img.setImageResource(images[position]);

        container.addView(view);
        return view;
    }

    public slideshowAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
