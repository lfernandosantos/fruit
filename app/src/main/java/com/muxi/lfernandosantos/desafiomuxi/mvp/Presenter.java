package com.muxi.lfernandosantos.desafiomuxi.mvp;

import android.content.Context;
import android.content.Intent;

import com.muxi.lfernandosantos.desafiomuxi.models.Fruit;
import com.muxi.lfernandosantos.desafiomuxi.mvp.MVP;
import com.muxi.lfernandosantos.desafiomuxi.views.FruitDetailsActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

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
    public void goDetails(Fruit fruit) {
        Intent goDetails = new Intent(context, FruitDetailsActivity.class);
        goDetails.putExtra("fruit", fruit);
        context.startActivity(goDetails);
    }


}
