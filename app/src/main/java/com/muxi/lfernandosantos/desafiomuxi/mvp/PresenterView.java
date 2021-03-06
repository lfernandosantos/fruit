package com.muxi.lfernandosantos.desafiomuxi.mvp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.muxi.lfernandosantos.desafiomuxi.ViewActions;
import com.muxi.lfernandosantos.desafiomuxi.models.DataFruit;
import com.muxi.lfernandosantos.desafiomuxi.models.Fruit;
import com.muxi.lfernandosantos.desafiomuxi.mvp.MVP;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by lf.fernandodossantos on 03/07/17.
 */

public class PresenterView implements MVP.ViewFruit{

    private Context context;
    private ProgressDialog progressDialog;

    public PresenterView(Context context){
        this.context = context;
    }

    @Override
    public LinearLayoutManager getLayoutManager() {
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        return llm;
    }


    @Override
    public void showSnack(String s, View view) {
       ViewActions viewActions = new ViewActions();
        viewActions.showSnack(s, view, context);
    }
    

    @Override
    public void showProgressDialog() {

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Aguarde...");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }



    @Override
    public void setFruitAdapter(List<Fruit> fruitList, RecyclerView recyclerView) {
        ViewActions viewActions = new ViewActions();
        viewActions.setAdapter(fruitList, recyclerView, context);
    }
}
