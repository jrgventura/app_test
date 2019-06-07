package com.ventura.apptest.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Jorge Ventura on 2019-06-07.
 */
@Entity(nameInDb = "place")
public class Places {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "description")
    private String description;

    @Property(nameInDb = "url")
    private String url;

    @Property(nameInDb = "created_at")
    private String createdAt;

    @Generated(hash = 1148963044)
    public Places(Long id, String name, String description, String url,
            String createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.createdAt = createdAt;
    }

    @Generated(hash = 1930620307)
    public Places() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
