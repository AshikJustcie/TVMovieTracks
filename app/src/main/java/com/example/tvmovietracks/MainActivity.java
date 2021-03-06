package com.example.tvmovietracks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tvmovietracks.Bottom_Fragments.CompanyFragment;
import com.example.tvmovietracks.Bottom_Fragments.GenreFragment;
import com.example.tvmovietracks.Bottom_Fragments.HomeFragment;
import com.example.tvmovietracks.Bottom_Fragments.KeywordFragment;
import com.example.tvmovietracks.DataActivity.MovieActivity;
import com.example.tvmovietracks.DataActivity.TVActivity;
import com.example.tvmovietracks.ExpandableListView.ExpandableListAdapter;
import com.example.tvmovietracks.ExpandableListView.MenuModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    String Type;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;

    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private GenreFragment genreFragment;
    private CompanyFragment companyFragment;
    private KeywordFragment keywordFragment;

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout_main);

        toolbar = findViewById(R.id.toolbarMA);
        setSupportActionBar(toolbar);

        mMainNav = findViewById(R.id.main_nav_bottom);
        mMainFrame = findViewById(R.id.main_frame);

        homeFragment = new HomeFragment();
        genreFragment = new GenreFragment();
        companyFragment = new CompanyFragment();
        keywordFragment = new KeywordFragment();

        setFragment(homeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.menu_genre:
                        setFragment(genreFragment);
                        return true;
                    case R.id.menu_company:
                        setFragment(companyFragment);
                        return true;
                    case R.id.menu_keyword:
                        setFragment(keywordFragment);
                        return true;

                        default:
                            return false;
                }

            }
        });

        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );

        toggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = findViewById(R.id.nav_view);

        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);

        final CircularImageView navImage = headerView.findViewById(R.id.navHeaderImage);
        final TextView navUser = headerView.findViewById(R.id.navHeaderName);
        final TextView navEmail = headerView.findViewById(R.id.navHeaderEmail);


        mAuth = FirebaseAuth.getInstance();
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    //startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                    Glide.with(MainActivity.this)
                            .load(R.drawable.ic_account_circle_black_24dp)
                            .into(navImage);

                    navUser.setText(R.string.nav_header_title);
                    navEmail.setText(R.string.nav_header_subtitle);
                }
                else {
                    String name = mAuth.getCurrentUser().getDisplayName();
                    String email = mAuth.getCurrentUser().getEmail();
                    Uri image = mAuth.getCurrentUser().getPhotoUrl();

                    Glide.with(MainActivity.this)
                            .load(image)
                            .placeholder(R.drawable.loading)
                            .into(navImage);

                    navUser.setText(name);
                    navEmail.setText(email);
                }
            }
        };



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_signUp:
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                }
                else {

                }

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void prepareMenuData() {

        //Animation
        MenuModel menuModel = new MenuModel("Animation", true, true); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("WatchList", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("Watched", false, false);
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }


        //Movie
        menuModel = new MenuModel("Movie", true, true); //Menu of Java Tutorials
        headerList.add(menuModel);

        childModelsList = new ArrayList<>();
        childModel = new MenuModel("WatchList", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("Watched", false, false);
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {
            Log.d("API123","here");
            childList.put(menuModel, childModelsList);
        }

        //TV SHow
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("TV Show", true, true); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("Watching", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("WatchList", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("Watched", false, false);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        //Menu TV SHOW ANIMATED
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("TV Animated", true, true); //Menu of Python Tutorials
        headerList.add(menuModel);
        childModel = new MenuModel("Watching", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("WatchList", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("Watched", false, false);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }

        menuModel = new MenuModel("Logout", true, false); //Menu of Python Tutorials
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
    }

    private void populateExpandableList() {
        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View clickedView, int groupPosition, long id) {

                ImageView groupIndicator = (ImageView) clickedView.findViewById(R.id.help_group_indicator);

                if (parent.isGroupExpanded(groupPosition)) {
                    parent.collapseGroup(groupPosition);
                    groupIndicator.setImageResource(R.drawable.ic_expand_more_black_24dp);
                } else {
                    parent.expandGroup(groupPosition);
                    groupIndicator.setImageResource(R.drawable.ic_expand_less_black_24dp);
                }

                if (groupPosition == 4){
                    SignOut();
                    onBackPressed();
                }



                return true;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);

                    MenuModel model2 =  headerList.get(groupPosition) ;

                    String childName = model.menuName;
                    String ss = model2.menuName;

                    String toastMessage = "\nGroupPostion" + groupPosition + "\nGroupName:" + ss;
                    if (groupPosition == 0) {
                        Type = "Animation";
                        if (childName.equals("WatchList")) {
                            Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                            intent.putExtra("WTypeTitle",Type);
                            intent.putExtra("WStatusSubTitle","WatchList");
                            startActivity(intent);
                            onBackPressed();
                        }
                        if (childName.equals("Watched")) {
                            Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                            intent.putExtra("WTypeTitle",Type);
                            intent.putExtra("WStatusSubTitle","Watched");
                            startActivity(intent);
                            onBackPressed();
                        }
                    }
                    else if (groupPosition == 1) {
                        Type = "Movie";
                        if (childName.equals("WatchList")) {
                            Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                            intent.putExtra("WTypeTitle",Type);
                            intent.putExtra("WStatusSubTitle","WatchList");
                            startActivity(intent);
                            onBackPressed();
                        }
                        if (childName.equals("Watched")) {
                            Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                            intent.putExtra("WTypeTitle",Type);
                            intent.putExtra("WStatusSubTitle","Watched");
                            startActivity(intent);
                            onBackPressed();
                        }
                    }
                    else  if (groupPosition == 2) {
                        Type = "TV Show";
                        if (childName.equals("Watching")) {
                            Intent intent = new Intent(MainActivity.this, TVActivity.class);
                            intent.putExtra("WTypeTitle",Type);
                            intent.putExtra("WStatusSubTitle","Watching");
                            startActivity(intent);
                            onBackPressed();
                        }
                        if (childName.equals("WatchList")) {
                            Intent intent = new Intent(MainActivity.this, TVActivity.class);
                            intent.putExtra("WTypeTitle",Type);
                            intent.putExtra("WStatusSubTitle","WatchList");
                            startActivity(intent);
                            onBackPressed();
                        }
                        if (childName.equals("Watched")) {
                            Intent intent = new Intent(MainActivity.this, TVActivity.class);
                            intent.putExtra("WTypeTitle",Type);
                            intent.putExtra("WStatusSubTitle","Watched");
                            startActivity(intent);
                            onBackPressed();
                        }
                    }
                    else if (groupPosition == 3) {
                        Type = "TV Animated";
                        if (childName.equals("Watching")) {
                            Intent intent = new Intent(MainActivity.this, TVActivity.class);
                            intent.putExtra("WTypeTitle",Type);
                            intent.putExtra("WStatusSubTitle","Watching");
                            startActivity(intent);
                            onBackPressed();
                        }
                        if (childName.equals("WatchList")) {
                            Intent intent = new Intent(MainActivity.this, TVActivity.class);
                            intent.putExtra("WTypeTitle",Type);
                            intent.putExtra("WStatusSubTitle","WatchList");
                            startActivity(intent);
                            onBackPressed();
                        }
                        if (childName.equals("Watched")) {
                            Intent intent = new Intent(MainActivity.this, TVActivity.class);
                            intent.putExtra("WTypeTitle",Type);
                            intent.putExtra("WStatusSubTitle","Watched");
                            startActivity(intent);
                            onBackPressed();
                        }
                    }
                }

                return false;
            }
        });
    }
    public void SignOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);

        builder.setMessage("Do you really want to sign out?");

        // Add the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                mAuth.signOut();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        // Set other dialog properties
        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }
}