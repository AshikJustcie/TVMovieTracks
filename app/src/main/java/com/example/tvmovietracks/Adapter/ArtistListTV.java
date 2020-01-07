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
import com.example.tvmovietracks.ArrayList.ArtistsTV;
import com.example.tvmovietracks.R;

import java.util.List;

public class ArtistListTV extends ArrayAdapter<ArtistsTV> {
    private Activity context;
    private List<ArtistsTV> artistsList;

    public ArtistListTV(Activity context, List<ArtistsTV> artistsList) {
        super(context, R.layout.list_layout_tv,artistsList);
        this.context = context;
        this.artistsList = artistsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout_tv, null, true);

        TextView title = listViewItem.findViewById(R.id.ItemTitle);
        TextView rating = listViewItem.findViewById(R.id.ItemRating);
        TextView collection = listViewItem.findViewById(R.id.ItemCollection);
        TextView TVStarts = listViewItem.findViewById(R.id.ItemTVStarts);
        TextView TVEnds = listViewItem.findViewById(R.id.ItemTVEnds);

        ArtistsTV artists = artistsList.get(position);

        title.setText(String.valueOf(artists.getTitle()));
        rating.setText(String.valueOf(artists.getRating()));
        collection.setText(String.valueOf(artists.getCollection()));
        TVStarts.setText(String.valueOf(artists.getTVStarts()));
        TVEnds.setText(String.valueOf(artists.getTVEnds()));

        return listViewItem;
    }
}
