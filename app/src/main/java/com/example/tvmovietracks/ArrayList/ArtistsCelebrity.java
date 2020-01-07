package com.example.tvmovietracks.ArrayList;

public class ArtistsCelebrity {

    String id;
    String title;
    String link;
    String celebrityGender;

    public ArtistsCelebrity() {
    }

    public ArtistsCelebrity(String id, String title, String link, String celebrityGender) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.celebrityGender = celebrityGender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCelebrityGender() {
        return celebrityGender;
    }

    public void setCelebrityGender(String celebrityGender) {
        this.celebrityGender = celebrityGender;
    }
}
