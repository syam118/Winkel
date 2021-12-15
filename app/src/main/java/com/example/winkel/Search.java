package com.example.winkel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Search extends AppCompatActivity implements AdapterClass.OnNoteListener {

    private DatabaseReference ref;
    ArrayList<Item_class> list;
    ArrayList<Item_class> mylist;
    RecyclerView recyclerView;
    SearchView searchView;
    AdapterClass.OnNoteListener onNoteListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ref = FirebaseDatabase.getInstance().getReference().child("winkel");
        recyclerView = findViewById(R.id.rv);
        searchView = findViewById(R.id.searchView);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (ref != null){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        list = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            list.add(ds.getValue(Item_class.class));
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Search.this, "oops", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (searchView != null){
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    searched(s);
                    return true;
                }
            });
        }
        else{
            recyclerView.removeAllViews();
        }
    }

    private void searched(String str){
        mylist = new ArrayList<>();
        for (Item_class object : list){
            if (object.getItemName().toLowerCase().contains(str.toLowerCase())){
                mylist.add(object);
            }

        }
        AdapterClass adapterClass = new AdapterClass(mylist,this);
        recyclerView.setAdapter(adapterClass);
    }


    @Override
    public void onNoteClick(int postion) {
        mylist.get(postion);

        //ArrayList<Item_class> Into =new ArrayList<>();

        Item_class object = mylist.get(postion);
        //Into.add(object);
        //ArrayList<Item_class> mylis = new ArrayList<>();
        //mylis.add(list.get(postion));
        //String str = mylis.set(0,);
        Intent intent = new Intent(this, DisplayItem.class);
        intent.putExtra("imgname",object.getItemName());
        intent.putExtra("imgavb",object.getItemAvb());
        intent.putExtra("img",object.getItemImg());
        intent.putExtra("root",object.getItemRot());
        startActivity(intent);
    }
}