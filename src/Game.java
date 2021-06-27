import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Math;
import java.util.Timer;

public class Game extends JFrame {
    Icon kafelek = new ImageIcon("src/resource/kafelek.png");
    Icon flaga = new ImageIcon("src/resource/flaga.png");
    Icon kafelekBomba = new ImageIcon("src/resource/kafelekBomba.png");
    Icon bomba = new ImageIcon("src/resource/bomba.png");
    Icon jeden = new ImageIcon("src/resource/jeden.png");
    Icon dwa = new ImageIcon("src/resource/dwa.png");
    Icon trzy = new ImageIcon("src/resource/trzy.png");
    Icon cztery = new ImageIcon("src/resource/cztery.png");
    Icon piec = new ImageIcon("src/resource/piec.png");
    Icon szesc = new ImageIcon("src/resource/szesc.png");
    Icon siedem = new ImageIcon("src/resource/siedem.png");
    Icon osiem = new ImageIcon("src/resource/osiem.png");
    Icon zero = new ImageIcon("src/resource/pustyKafelek.png");
    Icon you = new ImageIcon("src/resource/you.png");
    Icon have = new ImageIcon("src/resource/have.png");
    Icon won = new ImageIcon("src/resource/won.png");
    Icon smile = new ImageIcon("src/resource/smile.png");

    private JButton[][] buttons = null;
    private final JFrame GameBoard = new JFrame("Powodzenia!");
    private int ROWS;
    private int COLUMNS;
    private int BOMBS;
    private boolean GameStat = true;
    private Timer timer = new Timer();

    public void diff(int DIFF) {
        switch (DIFF) {
            case 1:
                ROWS = 14;
                COLUMNS = 10;
                BOMBS = (int) Math.floor(ROWS * COLUMNS * 0.3);
                game();
                break;
            case 2:
                ROWS = 14;
                COLUMNS = 18;
                BOMBS = (int) Math.floor(ROWS * COLUMNS * 0.3);
                game();
                break;
            case 3:
                ROWS = 19;
                COLUMNS = 25;
                BOMBS = (int) Math.floor(ROWS * COLUMNS * 0.3);
                game();
                break;
            case 4:
                ROWS = 6;
                COLUMNS = 6;
                BOMBS = 8;
                game();
                break;
        }
    }

    public void game() {
        JPanel Grid = new JPanel();
        JPanel TopPanel = new JPanel();
        JPanel LeftPanel = new JPanel();
        JPanel RightPanel = new JPanel();

        GameBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameBoard.setSize(ROWS * 40 + 50, COLUMNS * 40 + 60);
        GameBoard.setVisible(true);
        GameBoard.setLayout(new BorderLayout());
        GameBoard.setResizable(false);

        JButton reset = new JButton("reset");
        reset.setPreferredSize(new Dimension(60, 60));
        TopPanel.add(reset);
        reset.addActionListener(new myActionListener());
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
        Grid.setLayout(new GridLayout(COLUMNS, ROWS));
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
            for (int i = 0; i < BOMBS; i++) {
                double row = Math.floor(Math.random() * ROWS);
                double column = Math.floor(Math.random() * COLUMNS);
                if (buttons[(int) column][(int) row].getIcon().equals(kafelekBomba)){
                    i--;
                } else {
                    buttons[(int) column][(int) row].setIcon(kafelekBomba);
                }
            }
    }

    public void setIcon(int col, int row) {
        int minesNearby = nearbyMines(col, row);
        System.out.println(minesNearby);
        switch (minesNearby) {
            case 0:
                buttons[col][row].setIcon(zero);
                break;
            case 1:
                buttons[col][row].setIcon(jeden);
                break;
            case 2:
                buttons[col][row].setIcon(dwa);
                break;
            case 3:
                buttons[col][row].setIcon(trzy);
                break;
            case 4:
                buttons[col][row].setIcon(cztery);
                break;
            case 5:
                buttons[col][row].setIcon(piec);
                break;
            case 6:
                buttons[col][row].setIcon(szesc);
                break;
            case 7:
                buttons[col][row].setIcon(siedem);
                break;
            case 8:
                buttons[col][row].setIcon(osiem);
                break;
        }
    }

    private void setFlag(int col, int row) {
        if (!buttons[col][row].getIcon().equals(kafelek) && !buttons[col][row].getIcon().equals(kafelekBomba) && !buttons[col][row].getIcon().equals(flaga)) {
            return;
        }
        else if (!buttons[col][row].getIcon().equals(flaga)) {
            if (buttons[col][row].getIcon().equals(kafelekBomba)) BOMBS--;
            buttons[col][row].setIcon(flaga);
            isWinner();
        } else {
            buttons[col][row].setIcon(kafelek);
        }
    }

    private void isWinner(){
        System.out.println(BOMBS);
        if (BOMBS == 0) {
            System.out.println("win");
            buttons[(COLUMNS/2)][(ROWS/2)].setIcon(smile);
            buttons[(COLUMNS/2)-1][(ROWS/2)-1].setIcon(you);
            buttons[(COLUMNS/2)][(ROWS/2)-1].setIcon(won);
            buttons[(COLUMNS/2)-1][(ROWS/2)].setIcon(have);
            GameStat = false;
        }
    }

    private void setBomb(int col, int row){
        if (!buttons[col][row].getIcon().equals(bomba)) {
            buttons[col][row].setIcon(bomba);
            GameStat = false;
        }
    }

    private boolean isBomb(int col, int row) {
        if (inMargin(col, row) && buttons[col][row].getIcon().equals(kafelekBomba) || inMargin(col, row) && buttons[col][row].getIcon().equals(flaga)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean inMargin(int col, int row){
        if (col >= 0 && col < COLUMNS && row >= 0 && row < ROWS) {
            return true;
        } else {
            return false;
        }
    }

    private int nearbyMines(int col, int row) {
        int minesNearby = 0;
        for (int i=col-1; i<=col+1; i++) {
            for (int j=row-1; j<=row+1; j++) {
                if (isBomb(i, j)) {
                    minesNearby++;
                }
            }
        }
        return minesNearby;
    }

    private boolean isBlank(int col, int row) {
        if (inMargin(col, row)) {
            if(nearbyMines(col, row) == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void reveal(int col, int row) {
        if (isBlank(col, row)) {
            revealBlanks(col, row);
        } else {
            if (inMargin(col, row) && !isBlank(col, row) && !buttons[col][row].getIcon().equals(flaga)) {
                setIcon(col, row);
            }
        }
    }

    public void revealBlanks(int col, int row) {
        if (buttons[col][row].getBackground().equals(zero)) {
            return;
        }
        else {
            buttons[col][row].setIcon(zero);
            for (int i = col - 1; i <= col + 1; i++) {
                for (int j = row - 1; j <= row + 1; j++) {
                    if (isBlank(i, j)) {
                        revealBlanks(i, j);
                    } else {
                        reveal(i, j);
                    }
                }
            }
        }
    }

    public class myMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (GameStat == true){
                for (int row = 0; row < ROWS; row++) {
                    for (int col = 0; col < COLUMNS; col++) {
                        Icon button = buttons[col][row].getIcon();
                        if (e.getButton() == 3 && e.getSource() == buttons[col][row]) {
                            System.out.println(COLUMNS + " " + ROWS);
                            System.out.println(row + " " + col);
                            setFlag(col,row);
                        } else if (e.getButton() == 1 && e.getSource() == buttons[col][row]){
                            if(button.equals(kafelekBomba)){
                                setBomb(col,row);
                            } else {
                                reveal(col, row);
                            }
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

    private class myActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameStat = true;
            game();
        }
    }
}