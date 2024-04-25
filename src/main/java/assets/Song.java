package assets;

import java.io.File;
import java.nio.file.Path;

public class Song {
    public String url;
    public Path path;
    public File file;
    public String title;
    public String artist;
    public String album;
    public String genre;
    public int year;
    public int duration;
    public double bpm;
    public String key;

    public Song(String url, String title, String artist, String album, String genre, int year, int duration, double bpm, String key) {
        this.url = url;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.year = year;
        this.duration = duration;
        this.bpm = bpm;
        this.key = key;
    }

    public Song(String url, String title, double bpm){
        this.url = url;
        this.title = title;
        this.bpm = bpm;

        this.path = Path.of(url);
        try {
            this.file = new File(url);
        } catch (Exception e) {
            e.printStackTrace();
    }
}
}
