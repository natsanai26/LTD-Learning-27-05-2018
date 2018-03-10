package com.example.windows10.ltd_learning;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by Windows10 on 1/22/2018.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.html5_and_css3_slide,R.drawable.angular_slide,R.drawable.nodejs_2_slide};

    public ViewPagerAdapter(Context context){
        this.context = context;
    }

    public int getCount(){
        return images.length;
    }

    public boolean isViewFromObject(View view,Object object){
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_image_layout,null);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.imageButton);
        imageButton.setImageResource(images[position]);

        ViewPager vp = (ViewPager)container;
        vp.addView(view,0);
        return view;
    }

    public void destroyItem(ViewGroup container,int position, Object object){
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }



}
