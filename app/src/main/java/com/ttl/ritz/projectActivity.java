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

public class projectActivity extends AppCompatActivity {
    RecyclerView recyclerViewP;
    DatabaseReference dataRef;
    FirebaseStorage mstoarage;
    FirebaseDatabase mdatabse;
    projectAdapter adapter;
    List<modelProject> modelProjectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        mdatabse = FirebaseDatabase.getInstance();
        dataRef =mdatabse.getInstance().getReference().child("Project");
        dataRef.keepSynced(true);
        mstoarage = FirebaseStorage.getInstance();
        recyclerViewP = (RecyclerView)findViewById(R.id.recyclerProject);
        recyclerViewP.setHasFixedSize(true);
        recyclerViewP.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        modelProjectList =  new ArrayList<modelProject>();
        adapter = new projectAdapter(projectActivity.this,modelProjectList);
        dataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                modelProject model = snapshot.getValue(modelProject.class);
                modelProjectList.add(model);
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
        recyclerViewP.setAdapter(adapter);
    }
}