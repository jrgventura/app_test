package com.ventura.apptest.presentation.home.list_place;

import com.ventura.apptest.data.DaoSession;
import com.ventura.apptest.data.Places;
import com.ventura.apptest.data.PlacesDao;

import java.util.List;

/**
 * Created by Jorge Ventura on 2019-06-07.
 */
public class ListPlaceRepository {

    private final DaoSession mDaoSession;

    public ListPlaceRepository(DaoSession daoSession) {
        mDaoSession = daoSession;
    }

    public void addPlaces(Places places, DaoSession daoSession) {

        PlacesDao placesDao = daoSession.getPlacesDao();
        Places places1 = new Places();
        places.setId(null);
        places1.setName(places.getName());
        places1.setDescription(places.getDescription());
        places1.setUrl(places.getUrl());
        places1.setCreatedAt("");

        placesDao.insert(places1);

    }

    public List<Places> getAll(){

        PlacesDao groceryDao = mDaoSession.getPlacesDao();
        return groceryDao.loadAll();

    }


}
