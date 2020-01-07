package com.example.tvmovietracks.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tvmovietracks.ArrayList.Artists;
import com.example.tvmovietracks.R;

import java.util.List;

public class ArtistList extends ArrayAdapter<Artists> {
    private Activity context;
    private List<Artists> artistsList;

    TextView title,rating,collection;

    public ArtistList(Activity context, List<Artists> artistsList) {
        super(context, R.layout.list_layout_movie,artistsList);
        this.context = context;
        this.artistsList = artistsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout_movie, null, true);

        title = listViewItem.findViewById(R.id.ItemTitle);
        rating = listViewItem.findViewById(R.id.ItemRating);
        collection = listViewItem.findViewById(R.id.ItemCollection);

        Artists artists = artistsList.get(position);

        title.setText(String.valueOf(artists.getTitle()));
        rating.setText(String.valueOf(artists.getRating()));
        collection.setText(String.valueOf(artists.getCollection()));

        return listViewItem;
    }
}
