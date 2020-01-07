package com.example.tvmovietracks.DataActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import com.example.tvmovietracks.Adapter.ArtistList;
import com.example.tvmovietracks.Adapter.ArtistListTV;
import com.example.tvmovietracks.ArrayList.Artists;
import com.example.tvmovietracks.ArrayList.ArtistsTV;
import com.example.tvmovietracks.EditMainActivity;
import com.example.tvmovietracks.R;
import com.example.tvmovietracks.Webview;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TVActivity extends AppCompatActivity {

    ListView listView;
    List<ArtistsTV> artistsList;
    ArtistListTV adapter;

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

        //databaseReference = FirebaseDatabase.getInstance().getReference("Movie");

        if (TitleToolbar.equals("TV Show")) {
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String Id = artistsList.get(position).getId();
                String Title = artistsList.get(position).getTitle();
                String Link = artistsList.get(position).getLink();
                String Status = artistsList.get(position).getStatus();
                int rating = artistsList.get(position).getRating();
                String Collection = artistsList.get(position).getCollection();
                String Starts = artistsList.get(position).getTVStarts();
                String Ends = artistsList.get(position).getTVEnds();

                ShowOptionsDialog(Id,Title,Link,Status,rating,Collection,Starts,Ends);


            }
        });

    }

    private void ShowOptionsDialog(final String id,final String title, final String link,final String status,final int rating,final String collection,final String starts, final String ends) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.custom_dialog_title, null);
        TextView titleD = v.findViewById(R.id.CustomDialogTitle);
        titleD.setText(title);
        builder.setCustomTitle(v)
                .setItems(R.array.Listview_Item_Options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0) {
                            Intent intent = new Intent(TVActivity.this, Webview.class);
                            intent.putExtra("webaddress", link);
                            startActivity(intent);
                        }
                        else if (which == 1) {
                            Intent intent = new Intent(TVActivity.this, EditMainActivity.class);
                            intent.putExtra("DataID", id);
                            intent.putExtra("DataTITLE", title);
                            intent.putExtra("DataLINK", link);
                            intent.putExtra("DataSTATUS", status);
                            intent.putExtra("DataRATING", rating);
                            intent.putExtra("DataCOLLECTION", collection);
                            intent.putExtra("DataTYPE", TitleToolbar);
                            intent.putExtra("DataSTARTS", starts);
                            intent.putExtra("DataENDS", ends);
                            startActivity(intent);
                        }
                        else if (which == 2) {
                            DeleteDialog(id);
                        }
                        else {

                        }
                    }
                });

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void DeleteDialog(String id) {
        DatabaseReference dataRef = databaseReference.child(userID).child(id);

        dataRef.removeValue();

        Toast.makeText(this, "Successfully deleted!",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        getAllData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_dactivity, menu);

        MenuItem item = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (!TextUtils.isEmpty(query.trim())) {
                    searchData(query);
                }
                else {
                    getAllData();
                }
                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (!TextUtils.isEmpty(newText.trim())) {
                    searchData(newText);
                }
                else {
                    getAllData();
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void getAllData() {
        databaseReference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                artistsList.clear();
                for (DataSnapshot artistSnapshot:dataSnapshot.getChildren()) {

                    ArtistsTV artists = artistSnapshot.getValue(ArtistsTV.class);

                    if (artists.getStatus().equals(SubTitleToolbar)) {
                        artistsList.add(artists);
                    }
                }
                adapter = new ArtistListTV(TVActivity.this,artistsList);
                listView.setAdapter(adapter);

                SPBtnSts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String status = SPBtnSts.getSelectedItem().toString();
                        toolbar.setSubtitle(status);

                        artistsList.clear();
                        for (DataSnapshot artistSnapshot:dataSnapshot.getChildren()) {

                            ArtistsTV artists = artistSnapshot.getValue(ArtistsTV.class);

                            if (artists.getStatus().equals(status)) {
                                artistsList.add(artists);
                            }
                        }
                        adapter = new ArtistListTV(TVActivity.this,artistsList);
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

    private void searchData(final String query) {
        databaseReference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                artistsList.clear();
                for (DataSnapshot artistSnapshot:dataSnapshot.getChildren()) {

                    ArtistsTV artists = artistSnapshot.getValue(ArtistsTV.class);

                    if (artists.getStatus().equals(SubTitleToolbar)) {
                        if (artists.getTitle().toLowerCase().contains(query.toLowerCase())) {
                            artistsList.add(artists);
                        }
                    }
                }
                adapter = new ArtistListTV(TVActivity.this,artistsList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
