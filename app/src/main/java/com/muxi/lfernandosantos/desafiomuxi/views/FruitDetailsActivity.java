package com.muxi.lfernandosantos.desafiomuxi.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.muxi.lfernandosantos.desafiomuxi.Converter;
import com.muxi.lfernandosantos.desafiomuxi.R;
import com.muxi.lfernandosantos.desafiomuxi.models.Fruit;

public class FruitDetailsActivity extends AppCompatActivity {

    private ImageView fruitImage;
    private TextView textViewFruit;
    private TextView textViewPriceUS;
    private TextView textViewPriceReal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Fruit fruit= (Fruit) getIntent().getSerializableExtra("fruit");

        findViews();

        if (fruit != null) {
            pupulaCampos(fruit);
        }

        fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goFullScreenImage(fruit);
            }
        });

    }

    private void pupulaCampos(Fruit fruit) {
        Glide.with(this).load(fruit.image).into(fruitImage);
        setTitle("");

        Converter converter = new Converter();

        textViewFruit.setText(fruit.name.toUpperCase());
        textViewPriceUS.setText("$"+ converter.formatPrice(fruit.price));
        textViewPriceReal.setText("R$" + converter.getPriceReal(fruit.price));
    }

    private void goFullScreenImage(Fruit fruit) {
        Intent goFullScreen = new Intent(FruitDetailsActivity.this, ImageActivity.class);
        goFullScreen.putExtra("fruit", fruit);
        startActivity(goFullScreen);
    }

    private void findViews() {
        fruitImage = (ImageView) findViewById(R.id.fruitImageDetails);
        textViewFruit = (TextView) findViewById(R.id.textNameDetails);
        textViewPriceUS = (TextView) findViewById(R.id.textPriceDolar);
        textViewPriceReal = (TextView) findViewById(R.id.textPriceReal);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
