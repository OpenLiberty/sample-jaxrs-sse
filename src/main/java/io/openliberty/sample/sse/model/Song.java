package io.openliberty.sample.sse.model;

public class Song {

    private String title;
    private String artist;
    private long lengthMillis;

    public Song(String title, String artist, long lengthMillis) {
        this.title = title;
        this.artist = artist;
        this.lengthMillis = lengthMillis;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public long getLengthMillis() {
        return lengthMillis;
    }

    @Override
    public String toString() {
        return title + "|" + artist + "|" + lengthMillis;
    }
}
