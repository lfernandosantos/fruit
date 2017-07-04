package com.muxi.lfernandosantos.desafiomuxi;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.muxi.lfernandosantos.desafiomuxi.models.DataFruit;
import com.muxi.lfernandosantos.desafiomuxi.models.Fruit;
import com.muxi.lfernandosantos.desafiomuxi.network.IFuitsService;

/**
 * Created by lf.fernandodossantos on 02/07/17.
 */

public interface MVP {

    interface ViewFruit{
        public LinearLayoutManager getLayoutManager();
        public void pupulaCampos(Fruit fruit);
        public void findViews();
        public void showSnack(String s);
        public void loadRecyclerView(DataFruit dataFruit);
        public void goDetails(int position);
        public void goFullScreenImage(Fruit fruit);

    }

    interface PresenterFruit{
        public Context getContext();
        public String loadFruits();
        public IFuitsService getRetrofitBuild();
    }
}
