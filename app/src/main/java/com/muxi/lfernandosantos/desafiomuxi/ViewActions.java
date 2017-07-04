package com.muxi.lfernandosantos.desafiomuxi;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.muxi.lfernandosantos.desafiomuxi.adapter.FruitListAdapter;
import com.muxi.lfernandosantos.desafiomuxi.models.Fruit;
import com.muxi.lfernandosantos.desafiomuxi.views.RecyclerViewClickListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by lf.fernandodossantos on 03/07/17.
 */

public  class ViewActions {


    public ImageLoader getImageLoader(Context context) {

        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.blankspinner)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(displayImageOptions)
                .memoryCacheSize(50 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .threadPoolSize(5)
                .build();

        ImageLoader imageLoader =  ImageLoader.getInstance();
        imageLoader.init(configuration);

        return imageLoader;
    }

    public void setAdapter( List<Fruit> fruitList, RecyclerView recyclerView, Context context){
        FruitListAdapter adapter = new FruitListAdapter(fruitList,context, getImageLoader(context));
        adapter.setmRecyclerViewClickListener((RecyclerViewClickListener) context);
        recyclerView.setAdapter(adapter);
    }

    public void showSnack(String s, View view, Context context) {

        if (s == null) {
            s = context.getString(R.string.erro_default);
        }

        Snackbar.make(view, s, Snackbar.LENGTH_SHORT).show();
    }
}
