package com.dept.filesite.entity;

import java.util.List;

/**
 * @className: Planning
 * @description: 策划实体类
 * @author: 202111
 * @create: 2021-05-14 14:40
 */


public class Planning {

    private int id;
    private String name;
    private String brand;
    private String series_name;
    private String product_code;
    private String product_model;
    private String energy_level;
    private String category;
    private String development_platform;
    private String product_specifications;
    private String product_position;
    private String function;
    private String proto;
    private String distribution_channel;
    private String color;
    private String material;
    private String change_point;
    private List<Image> images;
    private int imageNumber;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber =imageNumber;
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Planning{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageNumber = '" + imageNumber + '\'' +
                ", brand = '" + brand + '\'' +
                ", series_name = '" + series_name +
                ", image_list = '" + images +
                '}';
    }
}
