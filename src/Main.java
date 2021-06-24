import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
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
                game.game(1);
            }
        });

        JButton buttonMedium = new JButton("MED");
        frameMain.getContentPane().add(buttonEasy);
        buttonMedium.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMain.dispose();
                game.game(2);
            }
        });

        JButton buttonHard = new JButton("HARD");
        frameMain.getContentPane().add(buttonEasy);
        buttonHard.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMain.dispose();
                game.game(3);
            }
        });

        frameMain.setLayout(new GridLayout(1,3));
        frameMain.add(buttonEasy);
        frameMain.add(buttonMedium);
        frameMain.add(buttonHard);
        frameMain.setVisible(true);
    }
}