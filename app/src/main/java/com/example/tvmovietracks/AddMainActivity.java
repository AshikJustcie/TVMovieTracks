package com.example.tvmovietracks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tvmovietracks.ArrayList.Artists;
import com.example.tvmovietracks.ArrayList.ArtistsCelebrity;
import com.example.tvmovietracks.ArrayList.ArtistsSinger;
import com.example.tvmovietracks.ArrayList.ArtistsTV;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddMainActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText Title,Type,Status,Collection, TVStarts, TVEnds;
    SeekBar ratingBar;
    Button SaveBtn;
    TextView ratingText;
    ImageView typeBtn,stsBtn,collBtn,TVStartDate,TVEndDate;
    LinearLayout seekBarLayout, StatusLay, CollLay, TVStartsLay,TVEndsLay;

    DatabaseReference databaseReference;
    String userID;

    String PTitle, PLink;

    private RadioGroup radioGroup;
    private RadioButton sound, vibration;
    String celebrityGender;

    private DatePickerDialog.OnDateSetListener mDateSetListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_main);

        userID = FirebaseAuth.getInstance().getUid();

        //TextInputEditText
        Title = findViewById(R.id.titleY);
        Type = findViewById(R.id.type);
        Status = findViewById(R.id.status);
        Collection = findViewById(R.id.collection);
        TVStarts = findViewById(R.id.tvstarts);
        TVEnds = findViewById(R.id.tvends);

        //LinearLayout
        seekBarLayout = findViewById(R.id.seekBarLayout);
        StatusLay = findViewById(R.id.statusLay);
        CollLay = findViewById(R.id.collLay);
        TVStartsLay = findViewById(R.id.tvStartsLayout);
        TVEndsLay = findViewById(R.id.tvEndsLayout);

        seekBarLayout.setVisibility(View.GONE);

        ratingBar = findViewById(R.id.ratingBar);

        ratingText = findViewById(R.id.ratingText);


        //ImageView As Button
        TVStartDate = findViewById(R.id.datepickerStart);
        TVEndDate = findViewById(R.id.datepickerEnd);
        typeBtn = findViewById(R.id.typeBtn);
        stsBtn = findViewById(R.id.statusBtn);
        collBtn = findViewById(R.id.collectBtn);

        //Button
        SaveBtn = findViewById(R.id.addBtn);


        ratingBar.setMax(5);

        Type.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        Status.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        Collection.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        //Button setOnClickListner
        typeBtn.setOnClickListener(this);
        stsBtn.setOnClickListener(this);
        collBtn.setOnClickListener(this);
        SaveBtn.setOnClickListener(this);
        TVStartDate.setOnClickListener(this);
        TVEndDate.setOnClickListener(this);


        Intent intent = getIntent();
        PTitle = intent.getStringExtra("WTitle");
        PLink = intent.getStringExtra("WLink");

        int titleLength = PTitle.length();

        String substr=PTitle.substring(0,titleLength-7);

        Title.setText(substr);


        ratingBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 0) {

                }
                else if (progress == 1) {
                    ratingText.setText("WASTE OF TIME");
                    ratingText.setTextColor(getResources().getColor(R.color.RED));
                    ratingText.setGravity(Gravity.LEFT);
                }
                else if (progress == 2) {
                    ratingText.setText("NOT GOOD");
                    ratingText.setTextColor(getResources().getColor(R.color.Orange));
                    ratingText.setGravity(Gravity.LEFT);
                }
                else if (progress == 3) {
                    ratingText.setText("SO SO");
                    ratingText.setTextColor(getResources().getColor(R.color.Pink));
                    ratingText.setGravity(Gravity.CENTER);
                }
                else if (progress == 4) {
                    ratingText.setText("GOOD");
                    ratingText.setTextColor(getResources().getColor(R.color.Violet));
                    ratingText.setGravity(Gravity.CENTER);
                }
                else {
                    ratingText.setText("FAVOURITE");
                    ratingText.setTextColor(getResources().getColor(R.color.Blue));
                    ratingText.setGravity(Gravity.RIGHT);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);

        radioGroup.setVisibility(View.GONE);
        celebrityGender = "Actress";

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.actor) {
                    celebrityGender = "Actor";
                    Toast.makeText(getApplicationContext(), "Actor", Toast.LENGTH_SHORT).show();
                } else {
                    celebrityGender = "Actress";
                    Toast.makeText(getApplicationContext(), "Actress", Toast.LENGTH_SHORT).show();
                }
            }

        });

        TVStartsLay.setVisibility(View.GONE);
        TVEndsLay.setVisibility(View.GONE);

        if (PTitle.matches(".*\\d+.*")) {
            if (PTitle.contains("TV")) {
                //TV
                Type.setText("TV Show");
                databaseReference = FirebaseDatabase.getInstance().getReference("TV Show");
            }
            else {
                //Movie
                Type.setText("Movie");
                databaseReference = FirebaseDatabase.getInstance().getReference("Movie");
            }
        }
        else {
            //Celebrity
            Type.setText("Celebrity");
            seekBarLayout.setVisibility(View.GONE);
            StatusLay.setVisibility(View.GONE);
            CollLay.setVisibility(View.GONE);
            radioGroup.setVisibility(View.VISIBLE);

            databaseReference = FirebaseDatabase.getInstance().getReference("Celebrity");
        }

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.typeBtn:
                // do your code
                TypeC();
                break;

            case R.id.statusBtn:
                // do your code
                WatchStatus();
                break;

            case R.id.collectBtn:
                // do your code
                Collections();
                break;

            case R.id.addBtn:
                // do your code
                addArtists();
                break;

            case R.id.datepickerStart:
                GetStartDate();
                break;

            case R.id.datepickerEnd:
                GetEndDate();
                break;

            default:
                break;
        }

    }

    private void TypeC() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog_title, null);
        TextView titleD = view.findViewById(R.id.CustomDialogTitle);
        titleD.setText("Data Type");
        builder.setCustomTitle(view)
                .setItems(R.array.TypeCat, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0) {
                            Type.setText("Animation");
                            StatusLay.setVisibility(View.VISIBLE);
                            CollLay.setVisibility(View.VISIBLE);
                            radioGroup.setVisibility(View.GONE);
                            TVStartsLay.setVisibility(View.GONE);
                            TVEndsLay.setVisibility(View.GONE);
                            databaseReference = FirebaseDatabase.getInstance().getReference("Animation");
                        }
                        else if (which == 1) {
                            Type.setText("Movie");
                            StatusLay.setVisibility(View.VISIBLE);
                            CollLay.setVisibility(View.VISIBLE);
                            radioGroup.setVisibility(View.GONE);
                            TVStartsLay.setVisibility(View.GONE);
                            TVEndsLay.setVisibility(View.GONE);
                            databaseReference = FirebaseDatabase.getInstance().getReference("Movie");
                        }
                        else if (which == 2) {
                            Type.setText("TV Show");
                            StatusLay.setVisibility(View.VISIBLE);
                            CollLay.setVisibility(View.VISIBLE);
                            radioGroup.setVisibility(View.GONE);
                            TVStartsLay.setVisibility(View.VISIBLE);
                            TVEndsLay.setVisibility(View.VISIBLE);
                            databaseReference = FirebaseDatabase.getInstance().getReference("TV Show");
                        }
                        else if (which == 3){
                            Type.setText("TV Animated");
                            StatusLay.setVisibility(View.VISIBLE);
                            CollLay.setVisibility(View.VISIBLE);
                            radioGroup.setVisibility(View.GONE);
                            TVStartsLay.setVisibility(View.VISIBLE);
                            TVEndsLay.setVisibility(View.VISIBLE);
                            databaseReference = FirebaseDatabase.getInstance().getReference("TV Animated");
                        }
                        else if (which == 4){
                            Type.setText("Celebrity");
                            StatusLay.setVisibility(View.GONE);
                            CollLay.setVisibility(View.GONE);
                            radioGroup.setVisibility(View.VISIBLE);
                            TVStartsLay.setVisibility(View.GONE);
                            TVEndsLay.setVisibility(View.GONE);
                            databaseReference = FirebaseDatabase.getInstance().getReference("Celebrity");
                        }
                        else if (which == 5){
                            Type.setText("Singer");
                            StatusLay.setVisibility(View.GONE);
                            CollLay.setVisibility(View.GONE);
                            radioGroup.setVisibility(View.GONE);
                            TVStartsLay.setVisibility(View.GONE);
                            TVEndsLay.setVisibility(View.GONE);
                            databaseReference = FirebaseDatabase.getInstance().getReference("Singer");
                        }
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void WatchStatus() {
        if (Type.getText().toString().equals("Animation") || Type.getText().toString().equals("Movie")) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.custom_dialog_title, null);
            TextView titleD = view.findViewById(R.id.CustomDialogTitle);
            titleD.setText("Watch Status");
            builder.setCustomTitle(view)
                    .setItems(R.array.watch_StatusM, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (which == 0) {
                                Status.setText("WatchList");
                                seekBarLayout.setVisibility(View.GONE);
                            }
                            else {
                                Status.setText("Watched");
                                seekBarLayout.setVisibility(View.VISIBLE);
                            }
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
        else if (Type.getText().toString().equals("TV Show") || Type.getText().toString().equals("TV Animated")){

            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.custom_dialog_title, null);
            builder.setTitle("Watch Status")
                    .setCustomTitle(view)
                    .setItems(R.array.watch_StatusT, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (which == 0) {
                                Status.setText("WatchList");
                                seekBarLayout.setVisibility(View.GONE);

                                TVStartsLay.setVisibility(View.GONE);
                                TVEndsLay.setVisibility(View.GONE);
                            }
                            else if (which == 1) {
                                Status.setText("Watching");
                                seekBarLayout.setVisibility(View.VISIBLE);

                                TVStartsLay.setVisibility(View.VISIBLE);
                                TVEndsLay.setVisibility(View.VISIBLE);
                            }
                            else {
                                Status.setText("Watched");
                                seekBarLayout.setVisibility(View.VISIBLE);

                                TVStartsLay.setVisibility(View.GONE);
                                TVEndsLay.setVisibility(View.GONE);
                            }
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
        else {

        }

    }

    private void Collections() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog_title, null);
        TextView titleD = view.findViewById(R.id.CustomDialogTitle);
        titleD.setText("Collection Status");

        builder.setCustomTitle(view)
                .setItems(R.array.Collection, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (which == 0) {
                            Collection.setText("In Collection");
                        }
                        else if (which == 1) {
                            Collection.setText("Not Collected");
                        }
                        else {
                            Collection.setText("Must Collect");
                        }
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void GetStartDate() {
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int months = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        DatePickerDialog dialog = new DatePickerDialog(AddMainActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListner = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        String mon=MONTHS[month];
                        String dates = mon + " " + dayOfMonth + "," + year;

                        TVStarts.setText(dates);

                    }
                },year,months,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
    private void GetEndDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        DatePickerDialog dialog = new DatePickerDialog(AddMainActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListner = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        String mon=MONTHS[month];

                        String dates = mon + " " + dayOfMonth + "," + year;
                        TVEnds.setText(dates);

                    }
                },year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }

    private void addArtists() {
        String title = Title.getText().toString();
        String link = PLink;
        String status = Status.getText().toString();
        int rating = ratingBar.getProgress();
        String collection = Collection.getText().toString();
        String CelebrityGen = celebrityGender;

        String tvStarts = TVStarts.getText().toString();
        String tvEnds = TVEnds.getText().toString();

        String type = Type.getText().toString();


        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(link)) {

            String id = databaseReference.push().getKey();

            if (type.equals("Celebrity")) {
                ArtistsCelebrity celebrity = new ArtistsCelebrity(id,title,link,CelebrityGen);
                databaseReference.child(userID).child(id).setValue(celebrity);
                Toast.makeText(getApplicationContext(), "Successfully Added to celebrity:)",Toast.LENGTH_SHORT).show();
            }
            else if (type.equals("Singer")) {
                ArtistsSinger singer = new ArtistsSinger(id,title,link);
                databaseReference.child(userID).child(id).setValue(singer);
                Toast.makeText(getApplicationContext(), "Successfully Added to Singer:)",Toast.LENGTH_SHORT).show();
            }
            else if (type.equals("Animation") || type.equals("Movie")){
                Artists artists = new Artists(id,title,link,status,rating,collection);
                databaseReference.child(userID).child(id).setValue(artists);
                Toast.makeText(getApplicationContext(), "Successfully Added:)",Toast.LENGTH_SHORT).show();
            }
            else {
                ArtistsTV artistsTV = new ArtistsTV(id,title,link,status,rating,collection,tvStarts,tvEnds);
                databaseReference.child(userID).child(id).setValue(artistsTV);
                Toast.makeText(getApplicationContext(), "Successfully Added To TV:)",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if (TextUtils.isEmpty(title)) {
                Title.setError("Must need a title :(");
            }
            if (TextUtils.isEmpty(link)) {
                Toast.makeText(getApplicationContext(), "Must need a link :(",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
