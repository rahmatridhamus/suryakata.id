package com.idev.rahmatridham.imm.model;

/**
 * Created by rahmatridham on 8/17/2016.
 */
public class JualanModel {
    String id, img, name,desc,price;

    public JualanModel(String id, String img, String name, String desc, String price) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
