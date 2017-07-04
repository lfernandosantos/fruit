package com.muxi.lfernandosantos.desafiomuxi.network;

import com.muxi.lfernandosantos.desafiomuxi.models.DataFruit;
import com.muxi.lfernandosantos.desafiomuxi.models.Fruit;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lf.fernandodossantos on 29/06/17.
 */

public interface IFuitsService  {

    String BASE_URL = "https://raw.githubusercontent.com/muxidev/desafio-android/master/";

    @GET("fruits.json")
    Call<DataFruit> listCall();
}
