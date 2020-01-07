package com.example.tvmovietracks.Bottom_Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.example.tvmovietracks.MainActivity;
import com.example.tvmovietracks.R;
import com.example.tvmovietracks.Webview;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyFragment extends Fragment {

    GridLayout mainGrid;

    public CompanyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company, container, false);

        mainGrid = (GridLayout) view.findViewById(R.id.mainGridfc);

        setSingleEvent(mainGrid);

        return view;
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            LinearLayout cardView = (LinearLayout) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (finalI==0) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/title/?companies=fox&adult=include");
                        startActivity(intent);
                    }
                    if (finalI==1) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/title/?companies=dreamworks&adult=include");
                        startActivity(intent);
                    }
                    if (finalI==2) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/title/?companies=mgm&adult=include");
                        startActivity(intent);
                    }
                    if (finalI==3) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/title/?companies=paramount&adult=include");
                        startActivity(intent);
                    }
                    if (finalI==4) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/title/?companies=sony&adult=include");
                        startActivity(intent);
                    }
                    if (finalI==5) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/title/?companies=universal&adult=include");
                        startActivity(intent);
                    }
                    if (finalI==6) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/title/?companies=disney&adult=include");
                        startActivity(intent);
                    }
                    if (finalI==7) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/title/?companies=warner&adult=include");
                        startActivity(intent);
                    }


                }
            });
        }
    }

}
