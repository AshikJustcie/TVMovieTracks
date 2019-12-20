package com.example.tvmovietracks.ExpandableListView;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tvmovietracks.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<MenuModel> lisDataHeader;
    private HashMap<MenuModel, List<MenuModel>> listDataChild;

    public ExpandableListAdapter(Context context, List<MenuModel> lisDataHeader, HashMap<MenuModel, List<MenuModel>> listChildData) {
        this.context = context;
        this.lisDataHeader = lisDataHeader;
        this.listDataChild = listChildData;
    }

    @Override
    public int getGroupCount() {
        return this.lisDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (this.listDataChild.get(this.lisDataHeader.get(groupPosition)) == null)
        return 0;
        else
            return this.listDataChild.get(this.lisDataHeader.get(groupPosition)).size();
    }

    @Override
    public MenuModel getGroup(int groupPosition) {
        return this.lisDataHeader.get(groupPosition);
    }

    @Override
    public MenuModel getChild(int groupPosition, int childPosition) {
        return this.listDataChild.get(this.lisDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerTitle = getGroup(groupPosition).menuName;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group_header,null);
        }

        TextView lbListHeader = convertView.findViewById(R.id.lblListHeader);
        lbListHeader.setTypeface(null, Typeface.BOLD);
        lbListHeader.setText(headerTitle);

        ImageView groupIndicator = (ImageView) convertView.findViewById(R.id.help_group_indicator);

        if (getChildrenCount(groupPosition) == 0) {
            groupIndicator.setVisibility(View.INVISIBLE);
        }
        else {
            groupIndicator.setVisibility(View.VISIBLE );
        }

        ImageView navMenuIcon = (ImageView) convertView.findViewById(R.id.nav_menu_icon);

        if (groupPosition == 0) {
            navMenuIcon.setImageResource(R.drawable.ic_movie_filter_black_24dp);
        }
        else if (groupPosition == 1) {
            navMenuIcon.setImageResource(R.drawable.ic_movie_creation_black_24dp);
        }
        else if (groupPosition == 2) {
            navMenuIcon.setImageResource(R.drawable.ic_tv_ondemand_video_black_24dp);
        }
        else if (groupPosition == 3) {
            navMenuIcon.setImageResource(R.drawable.ic_live_tv_black_24dp);
        }
        else if (groupPosition == 4) {
            navMenuIcon.setImageResource(R.drawable.ic_power_settings_new_black_24dp);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = getChild(groupPosition, childPosition).menuName;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group_child, null);
        }

        TextView txtListChild = convertView.findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
