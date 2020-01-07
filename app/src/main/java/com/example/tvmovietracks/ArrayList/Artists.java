package com.example.tvmovietracks.ArrayList;

public class Artists {

    String id;
    String title;
    String link;
    String status;
    int rating;
    String collection;

    public Artists() {
    }

    public Artists(String id, String title, String link, String status, int rating, String collection) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.status = status;
        this.rating = rating;
        this.collection = collection;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
