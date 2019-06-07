package com.ventura.apptest.presentation.home.list_place;

import com.ventura.apptest.data.DaoSession;
import com.ventura.apptest.data.Places;

import java.util.List;

/**
 * Created by Jorge Ventura on 2019-06-07.
 */
public class PlaceListPresenter implements PlaceListInterface.Presenter, PlaceListInterface.CompleteListener {

    private PlaceListInterface.View view;
    private PlaceListInterface.Interactor interactor;
    private DaoSession daoSession;

    public PlaceListPresenter(PlaceListInterface.View view, DaoSession daoSession) {
        this.view = view;
        this.daoSession = daoSession;
        this.interactor = new PlacesListInteractor(this, daoSession);
    }

    @Override
    public void attemptListPlacesFirestore() {
        if(view != null) {
            view.displayLoader(true);
        }
        interactor.performListPlaces();
    }

    @Override
    public void attemptListPlacesDao() {
        if(view != null) {
            view.displayLoader(true);
        }
        interactor.performListPlaces();
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onSuccess() {
        if(view != null) {
            view.displayLoader(false);
            view.displaySuccessFirestore();
        }
    }

    @Override
    public void onSuccessPlaces(List<Places> list) {
        if(view != null) {
            view.displayLoader(false);
            view.displaySuccessDao(list);
        }
    }

    @Override
    public void onError() {
        if(view != null) {
            view.displayLoader(false);
            view.displayListError("Error list firestore");
        }
    }
}
