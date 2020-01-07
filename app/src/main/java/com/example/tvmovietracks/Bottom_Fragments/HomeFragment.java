package com.example.tvmovietracks.Bottom_Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tvmovietracks.DataActivity.CelebrityActivity;
import com.example.tvmovietracks.DataActivity.TVActivity;
import com.example.tvmovietracks.R;
import com.example.tvmovietracks.DataActivity.MovieActivity;
import com.example.tvmovietracks.Webview;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    GridLayout mainGridStatus,mainGridMovie,mainGridTV,mainGridCelebrity;
    LinearLayout statusLayout;
    TextView watchStatusM;

    String Status,Type;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        statusLayout = (LinearLayout) view.findViewById(R.id.statusLayout);
        watchStatusM = (TextView) view.findViewById(R.id.watchStatusM);

        mainGridStatus = (GridLayout) view.findViewById(R.id.mainGridCMS);
        mainGridMovie = (GridLayout) view.findViewById(R.id.mainGridCMM);
        mainGridTV = (GridLayout) view.findViewById(R.id.mainGridCMT);
        mainGridCelebrity = (GridLayout) view.findViewById(R.id.mainGridCMC);

        setSingleEvent1(mainGridStatus);
        setSingleEvent2(mainGridMovie);
        setSingleEvent3(mainGridTV);
        setSingleEvent4(mainGridCelebrity);

        Status = "WatchList";

        statusLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.custom_dialog_title, null);
                builder.setCustomTitle(view)
                        .setItems(R.array.watch_StatusT, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //Toast.makeText(getActivity(), getResources().getString(which), Toast.LENGTH_SHORT).show();

                                if (which == 0) {
                                    Status = "WatchList";
                                    watchStatusM.setText(Status);
                                    mainGridStatus.getChildAt(0).setVisibility(View.VISIBLE);
                                    mainGridStatus.getChildAt(1).setVisibility(View.VISIBLE);
                                    Toast.makeText(getActivity(), "WatchList", Toast.LENGTH_SHORT).show();
                                }
                                else if (which == 1) {
                                    Status = "Watching";
                                    watchStatusM.setText(Status);
                                    mainGridStatus.getChildAt(0).setVisibility(View.GONE);
                                    mainGridStatus.getChildAt(1).setVisibility(View.GONE);
                                    Toast.makeText(getActivity(), "Watching", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Status = "Watched";
                                    watchStatusM.setText(Status);
                                    mainGridStatus.getChildAt(0).setVisibility(View.VISIBLE);
                                    mainGridStatus.getChildAt(1).setVisibility(View.VISIBLE);
                                    Toast.makeText(getActivity(), "Watched", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return view;
    }

    private void setSingleEvent1(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final LinearLayout cardView = (LinearLayout) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (finalI==0) {
                        Type = "Animation";
                        Intent intent = new Intent(getActivity(), MovieActivity.class);
                        intent.putExtra("WTypeTitle",Type);
                        intent.putExtra("WStatusSubTitle",Status);
                        startActivity(intent);
                    }
                    if (finalI==1) {
                        Type = "Movie";
                        Intent intent = new Intent(getActivity(), MovieActivity.class);
                        intent.putExtra("WTypeTitle",Type);
                        intent.putExtra("WStatusSubTitle",Status);
                        startActivity(intent);
                    }
                    if (finalI==2) {
                        Type = "TV Show";
                        Intent intent = new Intent(getActivity(), TVActivity.class);
                        intent.putExtra("WTypeTitle",Type);
                        intent.putExtra("WStatusSubTitle",Status);
                        startActivity(intent);
                    }
                    if (finalI==3) {
                        Type = "TV Animated";
                        Intent intent = new Intent(getActivity(), TVActivity.class);
                        intent.putExtra("WTypeTitle",Type);
                        intent.putExtra("WStatusSubTitle",Status);
                        startActivity(intent);
                    }
                }
            });
        }
    }
    private void setSingleEvent2(GridLayout mainGrid) {
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
                        intent.putExtra("webaddress","https://m.imdb.com/coming-soon?ref_=nv_mv_cs");
                        startActivity(intent);
                    }
                    if (finalI==1) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://m.imdb.com/chart/top/?ref_=nv_mv_250");
                        startActivity(intent);
                    }
                    if (finalI==2) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://m.imdb.com/chart/moviemeter/?ref_=nv_mv_mpm");
                        startActivity(intent);
                    }
                    if (finalI==3) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://m.imdb.com/chart/boxoffice/?ref_=nv_ch_cht");
                        startActivity(intent);
                    }
                    if (finalI==4) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://m.imdb.com/news/movie/?ref_=nv_nw_mv");
                        startActivity(intent);
                    }
                }
            });
        }
    }
    private void setSingleEvent3(GridLayout mainGrid) {
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
                        intent.putExtra("webaddress","https://m.imdb.com/chart/toptv/?ref_=nv_tvv_250");
                        startActivity(intent);
                    }
                    if (finalI==1) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://m.imdb.com/chart/tvmeter/?ref_=nv_tvv_mptv");
                        startActivity(intent);
                    }
                    if (finalI==2) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://m.imdb.com/news/tv?ref_=m_nwc_nw_tv_tb");
                        startActivity(intent);
                    }
                }
            });
        }
    }
    private void setSingleEvent4(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            LinearLayout cardView = (LinearLayout) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (finalI==0) {
                        startActivity(new Intent(getActivity(), CelebrityActivity.class));
                    }
                    if (finalI==1) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://m.imdb.com/chart/starmeter/?ref_=nv_cel_m");
                        startActivity(intent);
                    }
                    if (finalI==2) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/name/?birth_monthday=12-28&ref_=nv_cel_brn");
                        startActivity(intent);
                    }
                    if (finalI==3) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://m.imdb.com/news/celebrity/?ref_=nv_cel_nw");
                        startActivity(intent);
                    }
                }
            });
        }
    }

}
