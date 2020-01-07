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
import com.example.tvmovietracks.ArrayList.ArtistsCelebrity;
import com.example.tvmovietracks.R;

import java.util.List;

public class ArtistListCelebrity extends ArrayAdapter<ArtistsCelebrity> {
    private Activity context;
    private List<ArtistsCelebrity> artistsList;

    public ArtistListCelebrity(Activity context, List<ArtistsCelebrity> artistsList) {
        super(context, R.layout.list_layout_celebrity,artistsList);
        this.context = context;
        this.artistsList = artistsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        convertView = inflater.inflate(R.layout.list_layout_celebrity, null, true);

        TextView title = convertView.findViewById(R.id.ItemTitle);

        ArtistsCelebrity artists = artistsList.get(position);

        title.setText(String.valueOf(artists.getTitle()));

        return convertView;
    }
}
