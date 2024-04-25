import assets.SoundHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;

public class GameFrame extends JPanel {
    SoundHandler soundHandler;
    JFrame parent;
    JLabel bpmLabel;
    JPanel gamePanel;
    int bpm;
    double currentBpm;
    double timeBetweenBeats;
    Time[] timestamps = new Time[10]; // Array to store last 10 timestamps
    int currentIndex = 0;

    public GameFrame(JFrame parent) {
        soundHandler = new SoundHandler();
        bpm = 120;
        setLayout(new BorderLayout()); // Set the layout to BorderLayout
        this.parent = parent;
        setGUI();
    }

    void setGUI() {
        setSize(800, 600);
        setVisible(true);

        //GUI

        gamePanel = new JPanel();
        JPanel bpmPanel = new JPanel();
        setPreferredSize(new Dimension(800, 600));
        bpmLabel = new JLabel("BPM: ?");

        int padding = 20;
        bpmPanel.add(bpmLabel);
        bpmPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(padding, padding, padding, padding),
                BorderFactory.createEtchedBorder()));
        gamePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(padding, padding, padding, padding),
                BorderFactory.createEtchedBorder()));

        add(bpmPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Mouse clicked");

                updateBPM();
                backgroundAnimation();
            }
        });
        parent.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                System.out.println("Key pressed");
                updateBPM();
            }
        });
    }

    private void backgroundAnimation() {
        // Create a new thread for background animation
        Thread backgroundThread = new Thread(() -> {
            try {
                // Play sound at current BPM
                gamePanel.setBackground(Color.gray);
                // Sleep for 1 second
                Thread.sleep(100);
                gamePanel.setBackground(Color.WHITE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        backgroundThread.start();
    }

    void updateBPM() {
        Time currentTime = new Time(System.currentTimeMillis());

        if (timestamps[currentIndex] != null) {
            // Calculate time difference between current and previous timestamp
            long timeDifference = currentTime.getTime() - timestamps[currentIndex].getTime();
            timeBetweenBeats = (double) timeDifference / 10; // Calculate average time between beats
        }

        // Store current timestamp in array
        timestamps[currentIndex] = currentTime;
        currentIndex = (currentIndex + 1) % 10; // Move to next slot in circular manner

        // Calculate current BPM
        if (timeBetweenBeats != 0) {
            currentBpm = 60000 / timeBetweenBeats;
        } else {
            currentBpm = 0; // If timeBetweenBeats is 0, set BPM to 0 to avoid division by zero
        }

        // Update BPM label
        if (currentBpm == 0){
            bpmLabel.setText("BPM: ?");
        }else{


        String s = String.format("%.1f", currentBpm);
        bpmLabel.setText("BPM: " + s);
    }
    }
}
