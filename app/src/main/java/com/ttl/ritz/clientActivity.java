package com.ttl.ritz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class clientActivity extends AppCompatActivity {
    RecyclerView recyclerViewC;
    DatabaseReference dataRef;
    FirebaseStorage mstoarage;
    FirebaseDatabase mdatabse;
    clientAdapter adapter;
    List<modelClient> modelClientList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        mdatabse = FirebaseDatabase.getInstance();
        dataRef =mdatabse.getInstance().getReference().child("Client");
        dataRef.keepSynced(true);
        mstoarage = FirebaseStorage.getInstance();
        recyclerViewC = (RecyclerView)findViewById(R.id.recyclerClient);
        recyclerViewC.setHasFixedSize(true);
        recyclerViewC.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        modelClientList =  new ArrayList<modelClient>();
        adapter = new clientAdapter(clientActivity.this,modelClientList);
        dataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                modelClient model = snapshot.getValue(modelClient.class);
                modelClientList.add(model);
                // Toast.makeText(getApplicationContext(),model.getImageURL(),Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        LoadData();
        recyclerViewC.setAdapter(adapter);
    }

}