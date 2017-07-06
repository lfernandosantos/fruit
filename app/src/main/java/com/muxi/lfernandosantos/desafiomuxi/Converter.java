package com.muxi.lfernandosantos.desafiomuxi;

import android.accessibilityservice.GestureDescription;
import android.content.Context;

/**
 * Created by lf.fernandodossantos on 01/07/17.
 */

public class Converter {

    static {
        System.loadLibrary("converterJNI");
    }

    public String formatPrice(String price){
        return String.format("%.2f",Double.valueOf(price));
    }

    public String getPriceReal(String us) {

        double priceUSD  =  Double.parseDouble(us);

        double priceReal = converterToReal(priceUSD);

        return String.format("%.2f",priceReal);
    }

    public native double converterToReal(double price);
}
