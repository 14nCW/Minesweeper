import javax.swing.*;
import java.awt.*;
import java.lang.Math;

public class Game {
    private JButton[] buttons = null;

    public void game(int WIDTH, int HEIGHT, int DIFF) {

        JFrame GameBoard = new JFrame("POWODZENIA!");
        GameBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameBoard.setSize(WIDTH, HEIGHT);
        GameBoard.setVisible(true);
        JPanel TopPanel = new JPanel();
        buttons = new JButton[(int) (((WIDTH - 50) * (HEIGHT - 120)) / Math.pow(40, 2))];
        GameBoard.setLayout(new GridLayout((WIDTH-50)/40,(HEIGHT-120)/40));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setVisible(true);
            GameBoard.add(buttons[i]);
        }
        System.out.println("chuj nie test");
    }
}

