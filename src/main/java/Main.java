import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("game");
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.add(new GameFrame(frame));

    }
}
