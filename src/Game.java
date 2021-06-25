import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Math;

public class Game extends JFrame {
    Logic logic = new Logic();
    String flag = Character.toString(logic.flag);

    Icon kafelek = new ImageIcon("src/resource/kafelek.png");
    Icon flaga = new ImageIcon("src/resource/flaga.png");
    Icon kafelekBomba = new ImageIcon("src/resource/kafelekBomba.png");
    Icon bomba = new ImageIcon("src/resource/bomba.png");



    private JButton[][] buttons = null;
    private final static boolean FirstClick = false;
    private JFrame GameBoard = new JFrame("POWODZENIA!");
    private int ROWS;
    private int COLUMNS;
    private int BOMBS;

    public Game() throws IOException {
        return;
    }

    public void diff(int DIFF) {
        switch (DIFF) {
            case 1: {
                ROWS = 14;
                COLUMNS = 10;
                BOMBS = (int) Math.floor(ROWS * COLUMNS * 0.3);
                game();
                break;
            }
            case 2: {
                ROWS = 14;
                COLUMNS = 18;
                BOMBS = (int) Math.floor(ROWS * COLUMNS * 0.3);
                game();
                break;
            }
            case 3: {
                ROWS = 20;
                COLUMNS = 25;
                BOMBS = (int) Math.floor(ROWS * COLUMNS * 0.3);
                game();
                break;
            }
        }
    }

    public void game() {
        JFrame GameBoard = new JFrame("POWODZENIA!");
        JPanel Grid = new JPanel();
        JPanel TopPanel = new JPanel();
        JPanel LeftPanel = new JPanel();
        JPanel RightPanel = new JPanel();

        GameBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameBoard.setSize(COLUMNS * 40 + 60, ROWS * 40 + 50);
        GameBoard.setVisible(true);
        GameBoard.setLayout(new BorderLayout());
        GameBoard.setResizable(false);

        Grid.setPreferredSize(new Dimension(COLUMNS, ROWS));
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

        buttons = new JButton[COLUMNS][ROWS];
        Grid.setLayout(new GridLayout(ROWS, COLUMNS));
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setIcon(kafelek);
                buttons[i][j].setMargin(new Insets(0,0,0,0));
                buttons[i][j].setPreferredSize(new Dimension(40, 40));
                buttons[i][j].setVisible(true);
                buttons[i][j].addMouseListener(new myMouseListener());
                Grid.add(buttons[i][j]);
            }
        }
        generateMinefield(ROWS, COLUMNS, BOMBS);
    }

    public void generateMinefield(int ROWS, int COLUMNS, int BOMBS) {
        if (BOMBS > 0) {
            for (int i = 0; i < BOMBS; i++) {
                double row = Math.floor(Math.random() * ROWS);
                double column = Math.floor(Math.random() * COLUMNS);
                buttons[(int) column][(int) row].setIcon(kafelekBomba);
            }
        }
    }

    /*public void setColour(int ROWS, int COLUMNS) {
        String currentPiece = buttons[ROWS][COLUMNS];
        switch (currentPiece) {
            case "1":
                buttons[ROWS][COLUMNS].setForeground(new Color(1, 0, 254));
                break;
            case "2":
                buttons[ROWS][COLUMNS].setForeground(new Color(1, 127, 1));
                break;
            case "3":
                buttons[ROWS][COLUMNS].setForeground(new Color(254, 0, 0));
                break;
            case "4":
                buttons[ROWS][COLUMNS].setForeground(new Color(0, 0, 127));
                break;
            case "5":
                buttons[ROWS][COLUMNS].setForeground(new Color(129, 1, 2));
                break;
            case "6":
                buttons[ROWS][COLUMNS].setForeground(new Color(0, 128, 129));
                break;
            case "7":
                buttons[ROWS][COLUMNS].setForeground(new Color(0, 0, 0));
                break;
            case "8":
                buttons[ROWS][COLUMNS].setForeground(new Color(128, 128, 128));
                break;
        }
    }*/
    private void setFlag(int col, int row) {
        if (!buttons[col][row].getIcon().equals(flaga)) {
            buttons[col][row].setIcon(flaga);
        } else {
            buttons[col][row].setIcon(kafelek);
        }
    }

    private void isBomb(int col, int row){
        if (!buttons[col][row].getIcon().equals(bomba)) {
            buttons[col][row].setIcon(bomba);
        }
    }

    private boolean isPiece(int row, int col) {
        if (inMargin(row, col) && !buttons[row][col].equals(bomba)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean inMargin(int col, int row){
        if (row >= 0 && row < ROWS && col >= 0 && col < COLUMNS) {
            return true;
        } else {
            return false;
        }
    }

    private int nearbyMines(int col, int row) {
        int minesNearby = 0;
        for (int i=row-1; i<=row+1; i++) {
            for (int j=col-1; j<=col+1; j++) {
                if (isPiece(i, j)) {
                    minesNearby++;
                }
            }
        }
        return minesNearby;
    }

    public class myMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            for (int col = 0; col < COLUMNS; col++) {
                for (int row = 0; row < ROWS; row++) {
                    Icon button = buttons[col][row].getIcon();
                    if (e.getButton() == 3 && e.getSource() == buttons[col][row]) {
                        setFlag(col,row);
                    } else if (e.getButton() == 1 && e.getSource() == buttons[col][row]){
                        if(button.equals(kafelekBomba)){
                            isBomb(col,row);
                        } else {
                            System.out.println(nearbyMines(col, row));
                        }
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}