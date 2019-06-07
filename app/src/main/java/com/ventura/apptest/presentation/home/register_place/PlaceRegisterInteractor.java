package com.ventura.apptest.presentation.home.register_place;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jorge Ventura on 2019-06-06.
 */
public class PlaceRegisterInteractor implements PlaceRegisterInterface.Interactor{

    private PlaceRegisterInterface.CompleteListener listener;
    private FirebaseAuth auth;
    private StorageReference storageReference;
    private FirebaseFirestore dbFirestore;

    public PlaceRegisterInteractor(PlaceRegisterInterface.CompleteListener listener) {
        this.listener = listener;
        auth = FirebaseAuth.getInstance();
        dbFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    @Override
    public void performRegisterPhoto(Uri photo) {

        Calendar calFordDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        String saveCurrentDate = currentDate.format(calFordDate.getTime());

        Calendar calFordTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        String saveCurrentTime = currentTime.format(calFordDate.getTime());

        String postRandomName = saveCurrentDate + saveCurrentTime;

        StorageReference filePath = storageReference.child("Post Images").child(photo.getLastPathSegment() + postRandomName + ".jpg");

        filePath.putFile(photo).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                if(task.isSuccessful()){
                    String downloadUrl = task.getResult().getDownloadUrl().toString();
                    listener.onSuccessPhoto(downloadUrl);
                } else{
                    String message = task.getException().getMessage();
                    listener.onErrorPhoto(message);
                }

            }
        });


    }

    @Override
    public void performRegisterPlace(String ename, String description, String urlPhoto) {

        Map<String, Object> user = new HashMap<>();
        user.put("userId", auth.getCurrentUser().getUid());
        user.put("name", ename);
        user.put("description", description);
        user.put("url", urlPhoto);

        dbFirestore.collection("places_demo")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        listener.onSuccessRegisterPlace();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onErrorRegisterPlace("Error adding document");
                    }
                });

    }


}
