package assets;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class SoundHandler {
    protected ArrayList<Path> songPaths = new ArrayList();
    protected ArrayList<File> songFiles = new ArrayList();
    String path = "Bscending/resources/audio/";

    public static void main(String[] args) {
        SoundHandler soundHandler = new SoundHandler();
        soundHandler.getPaths();
        soundHandler.getFiles();
        Song sampleSong = new Song("Bscending/resources/audio/A. SWIFT - Waiting For The Sunset.wav", "Waiting For The Sunset", 128);

        try {
            // Lade die Audiodatei
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sampleSong.file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            // Spiele die Audiodatei ab
            clip.start();

            // Warte bis die Audiodatei zu Ende gespielt ist
            while (!clip.isRunning()) Thread.sleep(10);
            while (clip.isRunning()) Thread.sleep(10);

            // Schließe die Verbindung zur Audiodatei
            clip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void getPaths() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Path.of(path))) {
            for (Path entry : stream) {
                songPaths.add(entry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Path p : songPaths) {
            System.out.println(p);
        }
    }
    void getFiles() {
        for (Path p : songPaths) {
            songFiles.add(p.toFile());
        }
        for (File f : songFiles) {
            System.out.println(f);
        }
    }
}
