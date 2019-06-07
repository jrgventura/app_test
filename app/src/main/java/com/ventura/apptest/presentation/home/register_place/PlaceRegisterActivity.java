package com.ventura.apptest.presentation.home.register_place;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ventura.apptest.R;

/**
 * Created by Jorge Ventura on 2019-06-06.
 */
public class PlaceRegisterActivity extends AppCompatActivity implements PlaceRegisterInterface.View, View.OnClickListener {


    private EditText edtName;
    private EditText edtDescription;
    private ImageView imgSelectPlace;
    private Button btnSavePlace;

    Toolbar toolbar;
    private TextView txtToolbar;

    private ProgressDialog loadingBar;
    private static final int Gallery_Pick = 1;
    private Uri imageUri;

    private PlaceRegisterInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_place);

        presenter = new PlaceRegisterPresenter(this);

        setupToolbar();

        loadingBar = new ProgressDialog(this);
        loadingBar.setTitle("Cargando");
        loadingBar.setMessage("Please wait, while we are allowing you to login into your Account...");
        loadingBar.setCanceledOnTouchOutside(true);

        edtName = (EditText) findViewById(R.id.edtName);
        edtDescription = (EditText) findViewById(R.id.edtDescription);
        imgSelectPlace = (ImageView) findViewById(R.id.imgSelectPlace);
        btnSavePlace = (Button) findViewById(R.id.btnSavePlace);

        setEvents();
    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtToolbar = (TextView) toolbar.findViewById(R.id.txtToolbar);
        txtToolbar.setText(getString(R.string.toolbar_register));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setEvents(){
        imgSelectPlace.setOnClickListener(this);
        btnSavePlace.setOnClickListener(this);
    }

    /*private void StoringImageToFirebaseStorage() {

        Calendar calFordDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        saveCurrentDate = currentDate.format(calFordDate.getTime());

        Calendar calFordTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrentTime = currentTime.format(calFordDate.getTime());

        postRandomName = saveCurrentDate + saveCurrentTime;

        StorageReference filePath = PostsImagesRefrence.child("Post Images").child(ImageUri.getLastPathSegment() + postRandomName + ".jpg");

        filePath.putFile(ImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task)
            {
                if(task.isSuccessful())
                {
                    downloadUrl = task.getResult().getDownloadUrl().toString();
                    Toast.makeText(PlaceRegisterActivity.this, "image uploaded successfully to Storage...", Toast.LENGTH_SHORT).show();

                    SavingPostInformationToDatabase();

                }
                else
                {
                    loadingBar.dismiss();
                    String message = task.getException().getMessage();
                    Toast.makeText(PlaceRegisterActivity.this, "Error occured: " + message, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }*/

    /*private void SavingPostInformationToDatabase(){

        Map<String, Object> user = new HashMap<>();
        user.put("userId", current_user_id);
        user.put("name", edtName.getText().toString());
        user.put("description", edtDescription.getText().toString());
        user.put("url", downloadUrl);


        dbFirestore.collection("places_demo")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        SendUserToMainActivity();
                        Log.d("Firestore", "DocumentSnapshot added with ID: " + documentReference.getId());
                        loadingBar.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firestore", "Error adding document", e);
                        loadingBar.dismiss();
                    }
                });


    }*/



    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.imgSelectPlace:
                openGallery();
                break;

            case R.id.btnSavePlace:
                setRegisterPlace();
                break;

            default:
                break;

        }
    }

    private void openGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, Gallery_Pick);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallery_Pick && resultCode==RESULT_OK && data!=null)
        {
            imageUri = data.getData();
            imgSelectPlace.setImageURI(imageUri);
        }
    }


    private void setRegisterPlace(){
        if(presenter.isValidPhoto(imageUri)){
            presenter.attemptRegisterPhoto(imageUri);
        }
    }



    @Override
    public void displayUrlPhoto(String urlPhoto) {
        String name = edtName.getText().toString();
        String description = edtDescription.getText().toString();
        if (presenter.isValidFormRegisterPlace(name, description, urlPhoto)){
            presenter.attemptRegisterPlace(name, description, urlPhoto);
        }

    }


    @Override
    public void onNavigateList() {
        finish();
    }

    @Override
    public void displayNameError(String error) {
        edtName.setError(error);
    }

    @Override
    public void displayDescriptionError(String error) {
        edtDescription.setError(error);
    }

    @Override
    public void displayPhotoError(String error) {
        showMessage(error);
    }

    @Override
    public void displayRegisterPlaceError(String error) {
        showMessage(error);
    }

    @Override
    public void displayUploadPhotoError(String error) {
        showMessage(error);
    }

    @Override
    public void displayLoader(boolean loader) {
        if (loader) {
            loadingBar.show();
        } else {
            loadingBar.dismiss();
        }
    }

    @Override
    public void setEnabledView(boolean enable) {
        edtName.setEnabled(enable);
        edtDescription.setEnabled(enable);
        btnSavePlace.setEnabled(enable);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
