package com.example.bruger.feetclinic.BLL.BE;


import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by Bruger on 18-04-2016.
 */
public class Treatment implements IEntity{

    protected String name;
    protected String _id;
    protected String description;
    protected String imageUrl;
    protected int price;
    protected int duration;
    protected String type;

    public Treatment() {
    }

    public Treatment(Treatment treatment) {
        this.name = treatment.getName();
        this._id = treatment.get_Id();
        this.description = treatment.getDescription();
        this.imageUrl = treatment.getImageUrl();
        this.price = treatment.getPrice();
        this.duration = treatment.getDuration();
        this.type = treatment.getType();
    }
    public Treatment(String name)
    {
        this.name = name;
    }

    public Treatment(String name, int price) {
        this(name);
        this.price = price;
    }

    public Treatment(String name, String description, String imageUrl, int price, int duration, String type) {
        this(name,price);
        this.description = description;
        this.imageUrl = imageUrl;
        this.duration = duration;
        this.type = type;
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

    public String get_Id() {
        return _id;
    }

    public void set_Id(String id) {
        this._id = id;
    }
}
