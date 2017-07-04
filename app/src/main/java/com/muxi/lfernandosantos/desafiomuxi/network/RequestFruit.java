package com.muxi.lfernandosantos.desafiomuxi.network;

import com.muxi.lfernandosantos.desafiomuxi.models.DataFruit;
import com.muxi.lfernandosantos.desafiomuxi.models.Fruit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lf.fernandodossantos on 02/07/17.
 */

public class RequestFruit implements Callback<DataFruit> {

    List<Fruit> frutas;
    String string;

    @Override
    public void onResponse(Call<DataFruit> call, Response<DataFruit> response) {

        DataFruit dataFruit = response.body();
        frutas = dataFruit.getFruits();
        string = "Foi";


    }

    public synchronized List<Fruit> getFruits(){
        return frutas;
    }

    public synchronized String getString(){

        return string;
    }

    @Override
    public void onFailure(Call<DataFruit> call, Throwable t) {

    }
}
