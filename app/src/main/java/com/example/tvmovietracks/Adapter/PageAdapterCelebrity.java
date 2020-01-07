package com.example.tvmovietracks.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tvmovietracks.CelebrityFragments.ActorFragment;
import com.example.tvmovietracks.CelebrityFragments.ActressFragment;
import com.example.tvmovietracks.CelebrityFragments.SingerFragment;

public class PageAdapterCelebrity extends FragmentPagerAdapter {

    private int numOfTabs;

    public PageAdapterCelebrity(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new ActorFragment();
            case 1:
                return new ActressFragment();
            case 2:
                return new SingerFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}