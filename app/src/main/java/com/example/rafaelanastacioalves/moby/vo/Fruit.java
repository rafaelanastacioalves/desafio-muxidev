package com.example.rafaelanastacioalves.moby.vo;


import java.io.Serializable;

public class Fruit implements Serializable {

    private float price;
    private String name;
    private String image;
    private float convertedPrice;

    public Fruit(){
        super();
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public float getConvertedPrice() {
        return convertedPrice;
    }

    public void setConvertedPrice(float convertedPrice) {
        this.convertedPrice = convertedPrice;
    }
}
