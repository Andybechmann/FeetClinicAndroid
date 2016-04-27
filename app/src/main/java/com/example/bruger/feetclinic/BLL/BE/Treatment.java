package com.example.bruger.feetclinic.BLL.BE;

/**
 * Created by Bruger on 18-04-2016.
 */
public class Treatment {

    private String name;
    private String id;
    private String description;
    private String imageUrl;
    private int price;
    private int duration;
    private String type;

    public Treatment(String name)
    {
    setName(name);
    }

    public Treatment(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Treatment(String name, String description, String imageUrl, int price, int duration, String type) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.duration = duration;
        this.type = type;
    }

    public Treatment() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
