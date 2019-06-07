package com.ventura.apptest.presentation.home.entities;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.List;

/**
 * Created by Jorge Ventura on 2019-06-06.
 */
@IgnoreExtraProperties
public class Place {

    private String name;
    private String description;
    private String userId;
    private String url;
    private List<String> arrayPhotos;

    public Place() {
    }


    public Place(String name, String description, String userId, String url, List<String> arrayPhotos) {
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.url = url;
        this.arrayPhotos = arrayPhotos;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getListImages() {
        return arrayPhotos;
    }

    public void setListImages(List<String> arrayPhotos) {
        this.arrayPhotos = arrayPhotos;
    }
}
