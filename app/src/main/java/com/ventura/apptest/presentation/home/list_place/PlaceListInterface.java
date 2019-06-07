package com.ventura.apptest.presentation.home.list_place;

import com.ventura.apptest.data.Places;

import java.util.List;

/**
 * Created by Jorge Ventura on 2019-06-07.
 */
public interface PlaceListInterface {

    interface View {
        void onNavigateRegisterPlace();
        void displayListError(String error);
        void displayLoader(boolean loader);
        void displaySuccessFirestore();
        void displaySuccessDao(List<Places> places);
    }

    interface Presenter {
        void attemptListPlacesFirestore();
        void attemptListPlacesDao();
        void onDestroy();
    }

    interface Interactor {
        void performListPlaces();
        void performListDB();
    }

    interface CompleteListener {
        void onSuccess();
        void onSuccessPlaces(List<Places> list);
        void onError();
    }

}
