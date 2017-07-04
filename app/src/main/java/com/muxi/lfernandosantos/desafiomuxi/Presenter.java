package com.muxi.lfernandosantos.desafiomuxi;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.muxi.lfernandosantos.desafiomuxi.adapter.FruitListAdapter;
import com.muxi.lfernandosantos.desafiomuxi.models.DataFruit;
import com.muxi.lfernandosantos.desafiomuxi.models.Fruit;
import com.muxi.lfernandosantos.desafiomuxi.network.IFuitsService;
import com.muxi.lfernandosantos.desafiomuxi.network.RequestFruit;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lf.fernandodossantos on 02/07/17.
 */

public class Presenter implements MVP.PresenterFruit {

    public Context context;
    private ImageLoader imageLoader;

    public Presenter(Context context){
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public String loadFruits() {

        final int[] status = {0};
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Aguarde...");
        progressDialog.show();

        IFuitsService json = getRetrofitBuild();

        final Call<DataFruit> requestList = json.listCall();

        RequestFruit requestFruit = new RequestFruit();
        try {
            DataFruit data = requestList.execute().body();

            List<Fruit> f = data.getFruits();
            Log.i("Foi", f.get(0).getName());
            return "foi";
        } catch (IOException e) {
            e.printStackTrace();
            return "nao";
        }

    }

    @Override
    public IFuitsService getRetrofitBuild() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IFuitsService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IFuitsService.class);
    }

    private FruitListAdapter loadRecyclerView(DataFruit dataFruit) {

        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                //.showImageForEmptyUri(getResources().getDrawable(R.drawable.blank_spinner))
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

        imageLoader =  ImageLoader.getInstance();
        imageLoader.init(configuration);

        FruitListAdapter adapter = new FruitListAdapter(dataFruit.getFruits(), context, imageLoader);
        adapter.setmRecyclerViewClickListener((RecyclerViewClickListener) context);
        return adapter;
    }
}
