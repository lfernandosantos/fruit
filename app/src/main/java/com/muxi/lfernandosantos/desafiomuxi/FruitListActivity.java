package com.muxi.lfernandosantos.desafiomuxi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.muxi.lfernandosantos.desafiomuxi.models.DataFruit;
import com.muxi.lfernandosantos.desafiomuxi.models.Fruit;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FruitListActivity extends AppCompatActivity implements RecyclerViewClickListener{

    private RecyclerView recyclerViewFruits;
    private List<Fruit> fruits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_list);

        findViews();

        recyclerViewFruits.setLayoutManager(getLayoutManager());

    }

    @Override
    protected void onResume() {
        super.onResume();

        loadFruits();
    }

    private void loadFruits() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Aguarde...");
        progressDialog.show();

        IFuitsService json = getRetrofitBuild();

        final Call<DataFruit> requestList = json.listCall();

        requestList.enqueue(new Callback<DataFruit>() {
            @Override
            public void onResponse(Call<DataFruit> call, Response<DataFruit> response) {

                progressDialog.dismiss();
                DataFruit dataFruit = response.body();
                loadRecyclerView(dataFruit);

            }

            @Override
            public void onFailure(Call<DataFruit> call, Throwable t) {

                progressDialog.dismiss();
                showSnack("Verifique sua conexão.");

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

    private void showSnack(String s) {

        if (s == null) {
            s = getString(R.string.erro_default);
        }

        Snackbar.make(recyclerViewFruits, s, Snackbar.LENGTH_SHORT).show();
    }

    private IFuitsService getRetrofitBuild() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IFuitsService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IFuitsService.class);
    }

    private void loadRecyclerView(DataFruit dataFruit) {
        fruits = dataFruit.getFruits();
        FruitListAdapter adapter = new FruitListAdapter(fruits, FruitListActivity.this);
        adapter.setmRecyclerViewClickListener(FruitListActivity.this);
        recyclerViewFruits.setAdapter(adapter);
    }

    @Override
    public void onClick(View view, int position){
        goDetails(position);
    }

    private void goDetails(int position) {
        Intent goDetails = new Intent(this, FruitDetailsActivity.class);
        goDetails.putExtra("fruit", fruits.get(position));
        startActivity(goDetails);
    }
}
