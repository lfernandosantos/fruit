package com.muxi.lfernandosantos.desafiomuxi.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.muxi.lfernandosantos.desafiomuxi.mvp.Presenter;
import com.muxi.lfernandosantos.desafiomuxi.mvp.PresenterView;
import com.muxi.lfernandosantos.desafiomuxi.R;
import com.muxi.lfernandosantos.desafiomuxi.models.DataFruit;
import com.muxi.lfernandosantos.desafiomuxi.models.Fruit;
import com.muxi.lfernandosantos.desafiomuxi.network.IFuitsService;
import com.muxi.lfernandosantos.desafiomuxi.network.RequestFruit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FruitListActivity extends AppCompatActivity implements RecyclerViewClickListener {

    private RecyclerView recyclerViewFruits;
    private List<Fruit> fruits;
    private PresenterView presenterView;
    private Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_list);

        findViews();

        recyclerViewFruits.setLayoutManager(getLayoutManager());
        presenterView = new PresenterView(this);
        presenter = new Presenter(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (presenter.getStatusConexion()) {
            loadFruits();
        }
        else {
            presenterView.showSnack("Verifique sua conexão...", recyclerViewFruits);
        }
    }

    private void loadFruits() {

        presenterView.showProgressDialog();

        fruits = new ArrayList<>();

        RequestFruit requestFruit = new RequestFruit();

        IFuitsService request = requestFruit.getRetrofitBuild();

        Call<DataFruit> requestList = request.listCall();

        requestList.enqueue(new Callback<DataFruit>() {
            @Override
            public void onResponse(Call<DataFruit> call, Response<DataFruit> response) {
                presenterView.dismissProgressDialog();
                DataFruit dataFruit = response.body();
                loadRecyclerView(dataFruit);
            }

            @Override
            public void onFailure(Call<DataFruit> call, Throwable t) {

                presenterView.dismissProgressDialog();
                presenterView.showSnack("Verifique sua conexão.", recyclerViewFruits);
            }
        });

    }

    @NonNull
    private LinearLayoutManager getLayoutManager() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        return llm;
    }

    private void findViews() {
        recyclerViewFruits = (RecyclerView) findViewById(R.id.recyclerViewFruit);
    }


    private void loadRecyclerView(DataFruit dataFruit) {
        fruits = dataFruit.getFruits();
        presenterView.setFruitAdapter(fruits, recyclerViewFruits);
    }

    @Override
    public void onClick(View view, int position){
        presenter.goDetails(fruits.get(position));
    }

}
