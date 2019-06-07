package com.ventura.apptest.presentation.home.register_place;

import android.net.Uri;

/**
 * Created by Jorge Ventura on 2019-06-06.
 */
public class PlaceRegisterPresenter implements PlaceRegisterInterface.Presenter, PlaceRegisterInterface.CompleteListener{

    private PlaceRegisterInterface.View view;
    private PlaceRegisterInterface.Interactor interactor;

    public PlaceRegisterPresenter(PlaceRegisterInterface.View view) {
        this.view = view;
        this.interactor = new PlaceRegisterInteractor(this);
    }

    @Override
    public void attemptRegisterPhoto(Uri photo) {
        if(view != null) {
            view.setEnabledView(false);
            view.displayLoader(true);
        }
        interactor.performRegisterPhoto(photo);
    }

    @Override
    public void attemptRegisterPlace(String name, String description, String urlPhoto) {
        if(view != null) {
            view.setEnabledView(false);
            view.displayLoader(true);
        }
        interactor.performRegisterPlace(name, description, urlPhoto);
    }

    @Override
    public boolean isValidPhoto(Uri photo) {
        boolean isValid = true;
        if(photo == null) {
            isValid = false;
            view.displayUploadPhotoError("Photo null");
        }
        return isValid;
    }


    @Override
    public boolean isValidFormRegisterPlace(String name, String description, String urlPhoto) {
        boolean isValid = true;
        if(name.equals("")) {
            isValid = false;
            view.displayNameError("Name empty");
        } else if (description.equals("")){
            isValid = false;
            view.displayDescriptionError("Description empty");
        } else if (urlPhoto.equals("")){
            isValid = false;
            view.displayPhotoError("Url photo empty");
        }
        return isValid;
    }

    @Override
    public void onDestroy() {
        view = null;
    }


    @Override
    public void onSuccessPhoto(String urlPhoto) {
        if(view != null) {
            view.setEnabledView(true);
            view.displayLoader(false);
            view.displayUrlPhoto(urlPhoto);
        }
    }

    @Override
    public void onErrorPhoto(String message) {
        if(view != null) {
            view.setEnabledView(true);
            view.displayLoader(false);
            view.displayUploadPhotoError(message);
        }
    }

    @Override
    public void onSuccessRegisterPlace() {
        if(view != null) {
            view.setEnabledView(true);
            view.displayLoader(false);
            view.onNavigateList();
        }
    }

    @Override
    public void onErrorRegisterPlace(String message) {
        if(view != null) {
            view.setEnabledView(true);
            view.displayLoader(false);
            view.displayRegisterPlaceError(message);
        }
    }
}
