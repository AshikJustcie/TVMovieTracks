package com.example.tvmovietracks.Bottom_Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.tvmovietracks.R;
import com.example.tvmovietracks.Webview;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeywordFragment extends Fragment {

    GridLayout mainGrid;

    public KeywordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_keyword, container, false);

        mainGrid = (GridLayout) view.findViewById(R.id.mainGridfk);

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
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=action-hero&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_1");
                        startActivity(intent);
                    }
                    if (finalI==1) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=ambiguous-ending&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_3");
                        startActivity(intent);
                    }
                    if (finalI==2) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=anime&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_5");
                        startActivity(intent);
                    }
                    if (finalI==3) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=b-movie&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_8");
                        startActivity(intent);
                    }
                    if (finalI==4) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=based-on-book&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_10");
                        startActivity(intent);
                    }
                    if (finalI==5) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=based-on-comic%2Cbased-on-comic-book&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_ref_key&sort=moviemeter,asc&mode=detail&page=1");
                        startActivity(intent);
                    }
                    if (finalI==6) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=bollywood&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_22");
                        startActivity(intent);
                    }
                    if (finalI==7) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=character-name-in-title&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_29");
                        startActivity(intent);
                    }
                    if (finalI==8) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=criminal-mastermind&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_36");
                        startActivity(intent);
                    }
                    if (finalI==9) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=cyberpunk&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_39");
                        startActivity(intent);
                    }
                    if (finalI==10) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=dialogue-driven&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_42");
                        startActivity(intent);
                    }
                    if (finalI==11) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=fairy-tale&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_54");
                        startActivity(intent);
                    }
                    if (finalI==12) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=high-school&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_70");
                        startActivity(intent);
                    }
                    if (finalI==13) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=kung-fu&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_77");
                        startActivity(intent);
                    }
                    if (finalI==14) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=multiple-perspectives&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_86");
                        startActivity(intent);
                    }
                    if (finalI==15) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=ninja&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_93");
                        startActivity(intent);
                    }
                    if (finalI==16) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=one-man-army&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_101");
                        startActivity(intent);
                    }
                    if (finalI==17) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=parenthood&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_104");
                        startActivity(intent);
                    }
                    if (finalI==18) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=parody&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_105");
                        startActivity(intent);
                    }
                    if (finalI==19) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=plot-twist&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_106");
                        startActivity(intent);
                    }
                    if (finalI==20) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=robot&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_118");
                        startActivity(intent);
                    }
                    if (finalI==21) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=southern-gothic&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_127");
                        startActivity(intent);
                    }
                    if (finalI==22) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=superhero&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_133");
                        startActivity(intent);
                    }
                    if (finalI==23) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=dc-comics&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_ref_key&sort=moviemeter,asc&mode=detail&page=1");
                        startActivity(intent);
                    }
                    if (finalI==24) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=marvel-comics&ref_=fn_al_kw_1");
                        startActivity(intent);
                    }
                    if (finalI==25) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=supernatural&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_134");
                        startActivity(intent);
                    }
                    if (finalI==26) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=time-travel&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_139");
                        startActivity(intent);
                    }
                    if (finalI==27) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=vampire&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_142");
                        startActivity(intent);
                    }
                    if (finalI==28) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=virtual-reality&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_143");
                        startActivity(intent);
                    }
                    if (finalI==29) {
                        Intent intent = new Intent(getActivity(), Webview.class);
                        intent.putExtra("webaddress","https://www.imdb.com/search/keyword/?keywords=zombie&pf_rd_m=A2FGELUUNOQJNL&pf_rd_p=7846868c-8414-4178-8f43-9ad6b2ef0baf&pf_rd_r=RFGKNG8FV8Q22S9SFGR7&pf_rd_s=center-1&pf_rd_t=15051&pf_rd_i=moka&ref_=kw_148");
                        startActivity(intent);
                    }

                }
            });
        }
    }

}
