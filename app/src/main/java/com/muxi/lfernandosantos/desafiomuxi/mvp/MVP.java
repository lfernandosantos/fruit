package com.muxi.lfernandosantos.desafiomuxi.mvp;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.muxi.lfernandosantos.desafiomuxi.models.DataFruit;
import com.muxi.lfernandosantos.desafiomuxi.models.Fruit;
import com.muxi.lfernandosantos.desafiomuxi.network.IFuitsService;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by lf.fernandodossantos on 02/07/17.
 */

public interface MVP {

    interface ViewFruit{
        public LinearLayoutManager getLayoutManager();
        public void showSnack(String s, View view);
        public void showProgressDialog();
        public void dismissProgressDialog();
        public void setFruitAdapter(List<Fruit> fruitList, RecyclerView recyclerView);

    }

    interface PresenterFruit{
        public Context getContext();
        public void goDetails(Fruit fruit);
    }
}
