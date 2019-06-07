package com.ventura.apptest.presentation.home.list_place;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.ventura.apptest.AppTestApplication;
import com.ventura.apptest.R;
import com.ventura.apptest.data.DaoSession;
import com.ventura.apptest.data.User;
import com.ventura.apptest.presentation.home.entities.Place;
import com.ventura.apptest.presentation.home.register_place.PlaceRegisterActivity;

import java.util.List;

public class PlaceHomeActivity extends AppCompatActivity {

    private RecyclerView recyclerPlaces;

    private FirebaseAuth mAuth;
    private FirebaseFirestore dbFirestore;
    private FirestoreRecyclerAdapter adapter;

    LinearLayoutManager linearLayoutManager;


    private String currentUserID;

    DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();
        dbFirestore = FirebaseFirestore.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();

        daoSession = ((AppTestApplication)getApplication()).getDaoSession();


        recyclerPlaces = (RecyclerView) findViewById(R.id.recyclerPlaces);
        recyclerPlaces.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerPlaces.setLayoutManager(linearLayoutManager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registerIntent = new Intent(PlaceHomeActivity.this, PlaceRegisterActivity.class);
                startActivity(registerIntent);

            }
        });

        User user = ((AppTestApplication)getApplication()).getDaoSession().getUserDao().load(1L);
        if(user != null){
            Log.v("GRENDAO_DEMO", user.getName());
        }

        setData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void setData(){

        Query query = dbFirestore.collection("places_demo");

        CollectionReference docRef = dbFirestore.collection("places_demo");
        docRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Place> place = queryDocumentSnapshots.toObjects(Place.class);
                Log.v("LISTA_LISTA", place.size()+"");
                

            }
        });


        FirestoreRecyclerOptions<Place> response = new FirestoreRecyclerOptions.Builder<Place>()
                .setQuery(query, Place.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Place, PlacesHolder>(response) {
            @Override
            public void onBindViewHolder(PlacesHolder holder, int position, Place model) {
                holder.textName.setText(model.getName());
                holder.textDescription.setText(model.getDescription());
                Glide.with(getApplicationContext())
                        .load(model.getUrl())
                        .into(holder.post_image);
            }
            @Override
            public PlacesHolder onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext()).inflate(R.layout.list_item, group, false);
                return new PlacesHolder(view);
            }
            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());
            }
        };

        adapter.notifyDataSetChanged();
        recyclerPlaces.setAdapter(adapter);

    }

    public class PlacesHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textDescription;
        ImageView post_image;

        public PlacesHolder(View itemView) {
            super(itemView);
            this.textName = (TextView) itemView.findViewById(R.id.textName);
            this.textDescription = (TextView) itemView.findViewById(R.id.textDescription);
            this.post_image = (ImageView) itemView.findViewById(R.id.post_image);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
