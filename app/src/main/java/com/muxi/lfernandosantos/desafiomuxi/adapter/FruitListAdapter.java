package com.muxi.lfernandosantos.desafiomuxi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.muxi.lfernandosantos.desafiomuxi.Converter;
import com.muxi.lfernandosantos.desafiomuxi.R;
import com.muxi.lfernandosantos.desafiomuxi.RecyclerViewClickListener;
import com.muxi.lfernandosantos.desafiomuxi.models.Fruit;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

/**
 * Created by lf.fernandodossantos on 28/06/17.
 */

public class FruitListAdapter extends RecyclerView.Adapter<FruitListAdapter.MyViewHolder> {

    private List<Fruit> fruits;
    private LayoutInflater inflater;
    private Context context;
    private RecyclerViewClickListener mRecyclerViewClickListener;
    private ImageLoader imageLoader;

    public FruitListAdapter(List<Fruit> fruits, Context context, ImageLoader imageLoader){
        this.fruits = fruits;
        this.context = context;
        this.imageLoader = imageLoader;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_recycleview_adapter, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        imageLoader.displayImage(fruits.get(position).image, holder.image, null, null);

        //Glide.with(context).load(fruits.get(position).image).into(holder.image);
        holder.name.setText(fruits.get(position).name.toUpperCase());

        Converter converter = new Converter();
        String priceUS = converter.formatPrice(fruits.get(position).price);
        holder.price.setText("$"+ priceUS);

    }



    @Override
    public int getItemCount() {
        return fruits.size();
    }



    public void setmRecyclerViewClickListener(RecyclerViewClickListener mRecyclerViewClickListener) {
        this.mRecyclerViewClickListener = mRecyclerViewClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView image;
        public TextView name;
        public TextView price;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.fruitImage);
            name = (TextView) itemView.findViewById(R.id.txViewName);
            price = (TextView) itemView.findViewById(R.id.txViewPrice);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if (mRecyclerViewClickListener != null){
                mRecyclerViewClickListener.onClick(v, getPosition());
            }
        }
    }
}
