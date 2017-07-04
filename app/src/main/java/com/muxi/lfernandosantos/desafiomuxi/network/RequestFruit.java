package com.muxi.lfernandosantos.desafiomuxi.network;

import android.app.Activity;

import com.muxi.lfernandosantos.desafiomuxi.models.DataFruit;
import com.muxi.lfernandosantos.desafiomuxi.models.Fruit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lf.fernandodossantos on 02/07/17.
 */

public class RequestFruit {

    List<Fruit> frutas;
    Activity activity;

    public RequestFruit( Activity activity) {
        this.activity = activity;

        frutas = new ArrayList<>();
    }

    public RequestFruit(){}

    public IFuitsService getRetrofitBuild() {

        OkHttpClient okHttpClient = getRequestHeader();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IFuitsService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IFuitsService.class);
    }

    public List<Fruit> getFruitsRequest(){

        IFuitsService json = getRetrofitBuild();

        final Call<DataFruit> requestList = json.listCall();

        new Thread(){
            @Override
            public void run() {
                super.run();

                try{
                    DataFruit dataFruit = requestList.execute().body();
                    if (dataFruit !=  null) {
                        frutas = dataFruit.getFruits();
                    }

                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();

        return frutas;
    }

    public OkHttpClient getRequestHeader() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        return okHttpClient;
    }
}
