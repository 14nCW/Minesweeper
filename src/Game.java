import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.Math;

public class Game {
    private JButton[] buttons = null;

    public void game(int DIFF) {
        int ROWS= 0 , COLUMNS = 0, BOMBS = 0;
        switch (DIFF){
            case 1:
                ROWS = 14;
                COLUMNS = 10;
                BOMBS = (int) Math.floor(ROWS*COLUMNS*0.3);
                break;
            case 2:
                ROWS = 14;
                COLUMNS = 18;
                BOMBS = (int) Math.floor(ROWS*COLUMNS*0.3);
                break;
            case 3:
                ROWS = 20;
                COLUMNS = 25;
                BOMBS = (int) Math.floor(ROWS*COLUMNS*0.3);
                break;
        }

        JFrame GameBoard = new JFrame("POWODZENIA!");
        JPanel Grid = new JPanel();
        JPanel TopPanel = new JPanel();
        JPanel LeftPanel = new JPanel();
        JPanel RightPanel = new JPanel();

        GameBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameBoard.setSize(COLUMNS*40+60, ROWS*40+50);
        GameBoard.setVisible(true);
        GameBoard.setLayout(new BorderLayout());

        Grid.setPreferredSize(new Dimension(COLUMNS,ROWS));
        Grid.setVisible(true);
        Grid.setBackground(Color.cyan);

        TopPanel.setPreferredSize(new Dimension(COLUMNS, 60));
        TopPanel.setVisible(true);
        TopPanel.setBackground(Color.GREEN);

        RightPanel.setPreferredSize(new Dimension(25, ROWS));
        RightPanel.setVisible(true);
        RightPanel.setBackground(Color.GRAY);

        LeftPanel.setPreferredSize(new Dimension(25, ROWS));
        LeftPanel.setBackground(Color.GRAY);
        LeftPanel.setVisible(true);

        GameBoard.add(TopPanel, BorderLayout.NORTH);
        GameBoard.add(Grid, BorderLayout.CENTER);
        GameBoard.add(LeftPanel, BorderLayout.WEST);
        GameBoard.add(RightPanel, BorderLayout.EAST);

        buttons = new JButton[(int) ((COLUMNS) * ROWS)];
        Grid.setLayout(new GridLayout(ROWS,COLUMNS));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setText(""+i);
            buttons[i].setPreferredSize(new Dimension(40,40));
            buttons[i].setVisible(true);
            System.out.println("test");
//            buttons[i].addActionListener(new ActionListener() );
            Grid.add(buttons[i]);
        }
    }
}

