package com.twg.smartcop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class complaintsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    //FirebaseRecyclerOptions<liveComplaints>options;
//    FirebaseRecyclerAdapter<liveComplaints,MyViewHolder> adapter;
    DatabaseReference dataRef;
    FirebaseStorage mstoarage;
    FirebaseDatabase mdatabse;
    comAdapter adapter;
    List<liveComplaints> modelComplaints;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        mdatabse = FirebaseDatabase.getInstance();
        dataRef =mdatabse.getInstance().getReference().child("Images");
        dataRef.keepSynced(true);
        mstoarage = FirebaseStorage.getInstance();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerComplaints);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        modelComplaints =  new ArrayList<liveComplaints>();
        adapter = new comAdapter(complaintsActivity.this,modelComplaints);

        dataRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                liveComplaints model = snapshot.getValue(liveComplaints.class);
                modelComplaints.add(model);
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

//    private void LoadData() {
//        options = new FirebaseRecyclerOptions.Builder<liveComplaints>()
//                        .setQuery(dataRef, liveComplaints.class)
//                        .build();
//        adapter = new FirebaseRecyclerAdapter<liveComplaints, MyViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull liveComplaints model) {
//                holder.location.setText(model.getLocation());
//                holder.description.setText(model.getImageName());
//                holder.time.setText(model.getTime());
//                String url =null;
//                       url =  model.getImageURL();
//                Picasso.get().load(url).into(holder.imageView);
////                Glide.with(holder).load(model.getImageURL()).into(holder.imageView);
//                Toast.makeText(getApplicationContext(), "I'm a toast!", Toast.LENGTH_LONG).show();
//
//
//
//            }
//
//            @NonNull
//            @Override
//            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View v = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.cardview, parent, false);
//                return new MyViewHolder(v);
//            }
//        };
//        adapter.startListening();
//        recyclerView.setAdapter(adapter);
//    }
}