package com.example.tvmovietracks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class THEActivity extends AppCompatActivity {

    ListView listView;
    List<Artists> artistsList;
    ArtistList adapter;

    Toolbar toolbar;
    Spinner SPBtnSts;
    ArrayAdapter<String> spinnerArrayAdapter;

    String TitleToolbar,SubTitleToolbar,userID;

    DatabaseReference databaseReference;
    int posSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the);

        userID = FirebaseAuth.getInstance().getUid();

        toolbar = findViewById(R.id.toolbarD);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        TitleToolbar = intent.getStringExtra("WTypeTitle");
        SubTitleToolbar = intent.getStringExtra("WStatusSubTitle");

        toolbar.setTitle(TitleToolbar);
        toolbar.setSubtitle(SubTitleToolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference("Movie");

        if (TitleToolbar.equals("Animation")) {
            databaseReference = FirebaseDatabase.getInstance().getReference("Animation");
        }
        else if (TitleToolbar.equals("Movie")) {
            databaseReference = FirebaseDatabase.getInstance().getReference("Movie");
        }
        else if (TitleToolbar.equals("TV Show")) {
            databaseReference = FirebaseDatabase.getInstance().getReference("TV Show");
        }
        else if (TitleToolbar.equals("TV Animated")){
            databaseReference = FirebaseDatabase.getInstance().getReference("TV Animated");
        }



        SPBtnSts = findViewById(R.id.ImgBtnStatus);

        if (TitleToolbar.equals("Animation") || TitleToolbar.equals("Movie")) {
            String WStatusM[] = {"WatchList","Watched"};

            if (SubTitleToolbar.equals("WatchList")) { posSP = 0; }
            else { posSP = 1; }

            spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,WStatusM) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    // this part is needed for hiding the original view
                    View view = super.getView(position, convertView, parent);
                    view.setVisibility(View.GONE);

                    return view;
                }
            };
        }
        else if (TitleToolbar.equals("TV Show") || TitleToolbar.equals("TV Animated")) {
            String WStatusT[] = {"WatchList","Watching","Watched"};

            if (SubTitleToolbar.equals("WatchList")) { posSP = 0; }
            else if (SubTitleToolbar.equals("Watching")) { posSP = 1; }
            else { posSP = 2; }

            spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,WStatusT) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    // this part is needed for hiding the original view
                    View view = super.getView(position, convertView, parent);
                    view.setVisibility(View.GONE);

                    return view;
                }
            };
        }
        //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SPBtnSts.setAdapter(spinnerArrayAdapter);

        SPBtnSts.setSelection(posSP);

        listView = (ListView) findViewById(R.id.listView);
        artistsList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                artistsList.clear();
                for (DataSnapshot artistSnapshot:dataSnapshot.getChildren()) {

                    Artists artists = artistSnapshot.getValue(Artists.class);

                    if (artists.getStatus().equals(SubTitleToolbar)) {
                        artistsList.add(artists);
                    }
                }
                adapter = new ArtistList(THEActivity.this,artistsList);
                listView.setAdapter(adapter);

                SPBtnSts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String status = SPBtnSts.getSelectedItem().toString();
                        toolbar.setSubtitle(status);

                        artistsList.clear();
                        for (DataSnapshot artistSnapshot:dataSnapshot.getChildren()) {

                            Artists artists = artistSnapshot.getValue(Artists.class);

                            if (artists.getStatus().equals(status)) {
                                artistsList.add(artists);
                            }
                        }
                        adapter = new ArtistList(THEActivity.this,artistsList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_dactivity, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_app_bar_search:
                final SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        searchView.clearFocus();
                        if(artistsList.contains(query)){
                            adapter.getFilter().filter(query);
                        }else{
                            Toast.makeText(THEActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                        }
                        return false;

                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if(artistsList.contains(newText)){
                            adapter.getFilter().filter(newText);
                        }
                        return false;
                    }
                });
                break;

            case R.id.menu_save:

                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
