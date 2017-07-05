package com.muxi.lfernandosantos.desafiomuxi;

import android.accessibilityservice.GestureDescription;
import android.content.Context;

/**
 * Created by lf.fernandodossantos on 01/07/17.
 */

public class Converter {


    public String formatPrice(String price){
        return String.format("%.2f",Double.valueOf(price));
    }

    public String getPriceReal(String us) {
        Double preco  = Double.valueOf(us);
        Double dolarPrice = preco * 3.5;
        return String.format("%.2f",dolarPrice);
    }

    //public native double converterToReal(double price);
}
