package com.example.tvmovietracks.CelebrityFragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tvmovietracks.Adapter.ArtistListCelebrity;
import com.example.tvmovietracks.ArrayList.ArtistsCelebrity;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class SingerFragment extends Fragment {

    private ListView listView;
    private List<ArtistsCelebrity> artistsList;
    private ArtistListCelebrity adapter;

    private String userID;

    private DatabaseReference databaseReference;

    public SingerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_singer, container, false);

        userID = FirebaseAuth.getInstance().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference("Singer");

        listView = (ListView) view.findViewById(R.id.listViewC);
        artistsList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String Id = artistsList.get(position).getId();
                String Title = artistsList.get(position).getTitle();
                String Link = artistsList.get(position).getLink();

                ShowOptionsDialog(Id,Title,Link);

            }
        });

        return view;
    }
    private void ShowOptionsDialog(final String id,final String title, final String link) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.custom_dialog_title, null);
        TextView titleD = v.findViewById(R.id.CustomDialogTitle);
        titleD.setText(title);
        builder.setCustomTitle(v)
                .setItems(R.array.Listview_Item_Options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0) {
                            Intent intent = new Intent(getActivity(), Webview.class);
                            intent.putExtra("webaddress", link);
                            startActivity(intent);
                        }
                        else if (which == 1) {
                            Intent intent = new Intent(getActivity(), EditMainActivity.class);
                            intent.putExtra("DataID", id);
                            intent.putExtra("DataTITLE", title);
                            intent.putExtra("DataLINK", link);
                            intent.putExtra("DataTYPE", "Singer");
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

        Toast.makeText(getActivity(), "Successfully deleted!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                artistsList.clear();
                for (DataSnapshot artistSnapshot:dataSnapshot.getChildren()) {

                    ArtistsCelebrity artists = artistSnapshot.getValue(ArtistsCelebrity.class);

                    artistsList.add(artists);
                }
                adapter = new ArtistListCelebrity(getActivity(),artistsList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
