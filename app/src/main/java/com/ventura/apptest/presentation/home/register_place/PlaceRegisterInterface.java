package com.ventura.apptest.presentation.home.register_place;

import android.net.Uri;

/**
 * Created by Jorge Ventura on 2019-06-06.
 */
public interface PlaceRegisterInterface {

    interface View {
        void onNavigateList();
        void displayUrlPhoto(String urlPhoto);

        //Register Place
        void displayNameError(String error);
        void displayDescriptionError(String error);
        void displayPhotoError(String error);
        void displayRegisterPlaceError(String error);

        //Upload Photo
        void displayUploadPhotoError(String error);

        void displayLoader(boolean loader);
        void setEnabledView(boolean enable);


    }

    interface Presenter {
        void attemptRegisterPhoto(Uri photo);
        void attemptRegisterPlace(String name, String description, String urlPhoto);

        boolean isValidPhoto(Uri photo);
        boolean isValidFormRegisterPlace(String name, String description, String urlPhoto);
        void onDestroy();
    }

    interface Interactor {
        void performRegisterPhoto(Uri photo);
        void performRegisterPlace(String ename, String description, String urlPhoto);
    }

    interface CompleteListener {
        void onSuccessPhoto(String urlPhoto);
        void onErrorPhoto(String message);
        void onSuccessRegisterPlace();
        void onErrorRegisterPlace(String message);
    }
}
