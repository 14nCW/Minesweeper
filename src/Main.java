import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        JFrame frameMain = new JFrame("Minesweeper");
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setSize(400, 500);

        JButton buttonEasy = new JButton("ESSA");
        frameMain.getContentPane().add(buttonEasy);
        buttonEasy.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMain.dispose();
                game.diff(1);
            }
        });

        JButton buttonMedium = new JButton("MED");
        frameMain.getContentPane().add(buttonEasy);
        buttonMedium.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMain.dispose();
                game.diff(2);
            }
        });

        JButton buttonHard = new JButton("HARD");
        frameMain.getContentPane().add(buttonEasy);
        buttonHard.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMain.dispose();
                game.diff(3);
            }
        });

        JButton buttonTest = new JButton("TEST RUN");
        frameMain.getContentPane().add(buttonEasy);
        buttonTest.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMain.dispose();
                game.diff(4);
            }
        });

        frameMain.setLayout(new GridLayout(1,3));
        frameMain.add(buttonEasy);
        frameMain.add(buttonMedium);
        frameMain.add(buttonHard);
        frameMain.add(buttonTest);
        frameMain.setVisible(true);
    }
}