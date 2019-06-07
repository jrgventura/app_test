package com.ventura.apptest.presentation.home.list_place;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.ventura.apptest.data.DaoSession;
import com.ventura.apptest.data.Places;
import com.ventura.apptest.presentation.home.entities.Place;

import java.util.List;

/**
 * Created by Jorge Ventura on 2019-06-07.
 */
public class PlacesListInteractor implements PlaceListInterface.Interactor {

    private PlaceListInterface.CompleteListener listener;
    private FirebaseFirestore dbFirestore;
    private ListPlaceRepository listPlaceRepository;
    private DaoSession daoSessione;

    public PlacesListInteractor(PlaceListInterface.CompleteListener listener, DaoSession daoSession) {
        this.listener = listener;
        dbFirestore = FirebaseFirestore.getInstance();
        daoSessione = daoSession;
        listPlaceRepository = new ListPlaceRepository(daoSessione);
    }

    @Override
    public void performListPlaces() {

        CollectionReference docRef = dbFirestore.collection("places_demo");
        docRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Place> place = queryDocumentSnapshots.toObjects(Place.class);

                for (int i = 0; i < place.size() ; i++){

                    Places places = new Places();
                    places.setName(place.get(i).getName());
                    places.setDescription(place.get(i).getDescription());
                    places.setUrl(place.get(i).getUrl());

                    listPlaceRepository.addPlaces(places, daoSessione);

                }

                listener.onSuccess();

            }
        });

    }

    @Override
    public void performListDB() {
        List<Places> list = listPlaceRepository.getAll();
        listener.onSuccessPlaces(list);
    }
}
