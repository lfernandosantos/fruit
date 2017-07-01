package com.muxi.lfernandosantos.desafiomuxi.models;

import java.io.Serializable;

/**
 * Created by lf.fernandodossantos on 28/06/17.
 */

public class Fruit implements Serializable{
    public String name;
    public String image;
    public String price;

    public Fruit(){}

    public Fruit(String name, String image, String price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
