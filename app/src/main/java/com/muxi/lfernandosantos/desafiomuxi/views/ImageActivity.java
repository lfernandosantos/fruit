package com.muxi.lfernandosantos.desafiomuxi.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.muxi.lfernandosantos.desafiomuxi.R;
import com.muxi.lfernandosantos.desafiomuxi.models.Fruit;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Fruit fruit = (Fruit) getIntent().getSerializableExtra("fruit");
        ImageView imageView = (ImageView) findViewById(R.id.imageFull);

        Glide.with(this).load(fruit.image).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
