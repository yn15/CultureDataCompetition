package com.example.demo.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Culture {
    private String category;
    private String k_name;
    private String e_name;
    private double lng;
    private double lat;
    private String gu;
    private String dong;
    private String k_describe;
    private String e_describe;
    private String tel;
    private String img_url;
    private String tag;

    public Culture(String category, String k_name, String e_name, double lng, double lat, String gu, String dong, String k_describe, String e_describe, String tel, String img_url, String tag) {
        this.category = category;
        this.k_name = k_name;
        this.e_name = e_name;
        this.lng = lng;
        this.lat = lat;
        this.gu = gu;
        this.dong = dong;
        this.k_describe = k_describe;
        this.e_describe = e_describe;
        this.tel = tel;
        this.img_url = img_url;
        this.tag = tag;
    }
}
