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

public class taskActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference dataRef;
    FirebaseStorage mstoarage;
    FirebaseDatabase mdatabse;
    taskAdapter adapter;
    List<modelTask> modelTaskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        mdatabse = FirebaseDatabase.getInstance();
        dataRef =mdatabse.getInstance().getReference().child("Task");
        dataRef.keepSynced(true);
        mstoarage = FirebaseStorage.getInstance();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerTask);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        modelTaskList =  new ArrayList<modelTask>();
        adapter = new taskAdapter(taskActivity.this,modelTaskList);
        dataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                modelTask model = snapshot.getValue(modelTask.class);
                modelTaskList.add(model);
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
        recyclerView.setAdapter(adapter);

    }
}