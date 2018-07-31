package com.example.rafaelanastacioalves.moby.vo;


public class Fruit {

    private float price;
    private String name;
    private String image;

    private String convertedPrice;

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

    public String getConvertedPrice() {
        return convertedPrice;
    }

    public void setConvertedPrice(String convertedPrice) {
        this.convertedPrice = convertedPrice;
    }
}
